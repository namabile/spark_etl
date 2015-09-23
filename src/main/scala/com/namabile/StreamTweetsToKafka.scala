package com.namabile

import java.util
import java.io.ByteArrayOutputStream

import com.typesafe.config.ConfigFactory
import twitter4j._
import twitter4j.conf._
import com.namabile.avro.Tweet
import org.apache.avro.io.EncoderFactory
import org.apache.avro.specific.SpecificDatumWriter
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}

// see here http://capykoa.com/articles/14
object StreamTweetsToKafka {

  private val conf = ConfigFactory.load()

  private val getTwitterConf: Configuration = {
    val twitterConf = new ConfigurationBuilder()
      .setOAuthConsumerKey(conf.getString("twitter.consumerKey"))
      .setOAuthConsumerSecret(conf.getString("twitter.consumerSecret"))
      .setOAuthAccessToken(conf.getString("twitter.accessToken"))
      .setOAuthAccessTokenSecret(conf.getString("twitter.accessTokenSecret"))
      .build()
    twitterConf
  }

  private val topic = "tweets"

  // Zookeeper connection properties
  private val props = new util.HashMap[String, Object]()
  private val brokers = conf.getString("kafka.brokers")

  props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers)
  // Kafka avro message stream comes in as a byte array
  props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArraySerializer")
  props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")

  private val producer = new KafkaProducer[String, Array[Byte]](props)

  def main(args: Array[String]) {
    val twitterStream = new TwitterStreamFactory(getTwitterConf).getInstance
    twitterStream.addListener(simpleStatusListener)
    twitterStream.filter(new FilterQuery().follow(1344951,5988062,807095,3108351))
    Thread.sleep(10000)
    twitterStream.cleanUp
    twitterStream.shutdown
  }

  def simpleStatusListener = new StatusListener() {
    def onStatus(s: Status) {
      val tweet = buildTweet(s)
      val tweetBytes = serializeTweet(tweet)
      val message = new ProducerRecord[String, Array[Byte]](topic, null, tweetBytes) // Create a new producer record to send the message in
      println("tweet!")
      producer.send(message)
    }
    def onDeletionNotice(statusDeletionNotice: StatusDeletionNotice) {}
    def onTrackLimitationNotice(numberOfLimitedStatuses: Int) {}
    def onException(ex: Exception) { ex.printStackTrace }
    def onScrubGeo(arg0: Long, arg1: Long) {}
    def onStallWarning(warning: StallWarning) {}
  }

  def buildTweet(s: Status): Tweet = {
    new Tweet(s.getUser.getName, s.getText)
  }

  def serializeTweet(tweet: Tweet): Array[Byte] = {
    val out = new ByteArrayOutputStream()
    // https://cwiki.apache.org/confluence/display/AVRO/FAQ
    val encoder = EncoderFactory.get.binaryEncoder(out, null)
    val writer = new SpecificDatumWriter[Tweet](Tweet.getClassSchema)

    writer.write(tweet, encoder)
    encoder.flush
    out.close
    out.toByteArray
  }

}
