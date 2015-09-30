package com.namabile

import com.typesafe.config.ConfigFactory
import com.namabile.avro.Tweet
import kafka.serializer.DefaultDecoder
import org.apache.avro.io.DecoderFactory
import org.apache.avro.specific.SpecificDatumReader
import org.apache.spark._
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Minutes, Seconds, StreamingContext}

object ConsumeTwitterStream {
  // implement me
  private val conf = ConfigFactory.load()
  private val sparkMaster = conf.getString("addresses.sparkMaster")

  private val sparkConf = new SparkConf().setAppName("WindowTweetCount").setMaster(sparkMaster)

  private val ssc = new StreamingContext(sparkConf, Seconds(2))

  ssc.checkpoint("./checkpointDir")

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

  // Get a count of all the users and the products they visited in the last 10 minutes, refreshing every 2 seconds
  // Get a count of the tweets per user in the last 10 minutes, refreshing every 2 seconds
  val tweetRDD = lines.map{ bytes: Array[Byte] => AvroUtil.tweetDecode(bytes) }.map{ tweet: Tweet => (tweet.name, 1L) }
  val tweetCounts = tweetRDD.reduceByKeyAndWindow(_ + _, _ - _, Minutes(10), Seconds(2), 2)

  tweetCounts.print // Print out the results.  Or we can produce new kafka events containing the mapped ids.

  ssc.start()
  ssc.awaitTermination()
}

object AvroUtil {
  // Deserialize the byte array into an avro object
  // https://cwiki.apache.org/confluence/display/AVRO/FAQtil {
  val reader = new SpecificDatumReader[Tweet](Tweet.getClassSchema)
  def tweetDecode(bytes: Array[Byte]): Tweet = {
    val decoder = DecoderFactory.get.binaryDecoder(bytes, null)
    reader.read(null, decoder)
  }
}