package com.namabile

import com.typesafe.config.ConfigFactory
import com.namabile.avro.Tweet
import kafka.serializer.DefaultDecoder
import org.apache.avro.io.DecoderFactory
import org.apache.avro.specific.SpecificDatumReader
import org.apache.spark._
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.StreamingContext._
import org.apache.spark.streaming.kafka._
import org.apache.spark.streaming.{Minutes, Seconds, StreamingContext}
import org.apache.spark.rdd.RDD

object ConsumeTwitterStream extends App {
  private val conf = ConfigFactory.load()
  private val sparkMaster = conf.getString("addresses.spark_master")

  private val sparkConf = new SparkConf().setAppName("WindowTweetCount").setMaster(sparkMaster)
  private val ssc = new StreamingContext(sparkConf, Seconds(2))
  private val sc = new SparkContext(sparkConf)
  private val sqlContext = new org.apache.spark.sql.SQLContext(sc)

  ssc.checkpoint("hdfs://ip-10-0-0-127.ec2.internal:8020/user/root/checkPointDir")

  val kafkaConf = Map(
    "metadata.broker.list" -> conf.getString("addresses.kafka"), // Default kafka broker list location
    "zookeeper.connect" -> conf.getString("addresses.zookeeper"), // Default zookeeper location
    "group.id" -> "tweets-example",
    "zookeeper.connection.timeout.ms" -> "1000"
  )

  val topics = "tweets"
  val topicMap = topics.split(",").map((_, 2)).toMap
  // Create a new stream which can decode byte arrays.  For this exercise, the incoming stream only contain user and product Ids
  val lines = KafkaUtils.createStream[String, Array[Byte], DefaultDecoder, DefaultDecoder](ssc, kafkaConf, topicMap, StorageLevel.MEMORY_ONLY_SER).map(_._2)

  // Get a count of the tweets per user in the last 10 minutes, refreshing every 2 seconds
  val tweetRDD = lines.map{ bytes: Array[Byte] => tweetDecode(bytes) }
  val tweetCounts = tweetRDD.map{ tweet: Tweet => (tweet.name.toString, 1L) }.reduceByKeyAndWindow(_ + _, _ - _, Minutes(10), Seconds(2), 2)

  // Print out the counts
  tweetCounts.print

  // write a parquet file to hdfs every 5 minutes
  tweetRDD.window(Minutes(5)).foreachRDD {
    rdd: RDD[Tweet] =>
      val path = "hdfs://ip-10-0-0-127.ec2.internal:8020/user/root/tweets"
      val timestamp: Long = System.currentTimeMillis / 1000
      val prefix = "tweets-" + timestamp
      val suffix = ".parquet"
      rdd.write.parquet(prefix + suffix)
      println("file written!")
  }

  ssc.start()
  ssc.awaitTermination()

  // Deserialize the byte array into an avro object
  // https://cwiki.apache.org/confluence/display/AVRO/FAQtil {
  def tweetDecode(bytes: Array[Byte]): Tweet = {
    val reader = new SpecificDatumReader[Tweet](Tweet.getClassSchema)
    val decoder = DecoderFactory.get.binaryDecoder(bytes, null)
    reader.read(null, decoder)
  }
}

