package com.namabile

import java.util.Properties

import com.twitter.bijection.avro.SpecificAvroCodecs._
import com.typesafe.config.ConfigFactory
import kafka.javaapi.producer.Producer
import kafka.producer.{KeyedMessage, ProducerConfig}
import com.namabile.TwitterStream.OnTweetPosted
import com.namabile.avro.Tweet
import twitter4j.{Status, FilterQuery}
import org.apache.avro.generic.GenericData.Record


//object KafkaProducerApp {

  //private val conf = ConfigFactory.load()

  //val KafkaTopic = "tweets"

  //val kafkaProducer = {
    //val props = new Properties()
    //props.put("metadata.broker.list", conf.getString("kafka.brokers"))
    //props.put("request.required.acks", "1")
    //val config = new ProducerConfig(props)
    //new Producer[String, Array[Byte]](config)
  //}

  //def main (args: Array[String]) {
    //val twitterStream = TwitterStream.getStream
    //twitterStream.addListener(new OnTweetPosted(s => sendToKafka(toTweet(s))))
    //twitterStream.sample("english")
  //}

  //private def toTweet(s: Status): Tweet = {
    //new Tweet(s.getUser.getName, s.getText)
  //}

  //private def sendToKafka(t: Tweet) {
    //println(t)
    //println(toJson(t.getSchema).apply(t))
    //val tweetEnc = toBinary.apply(t)
    //println(tweetEnc)
    //val msg = new KeyedMessage[String, Array[Byte]](KafkaTopic, tweetEnc)
    //kafkaProducer.send(msg)
  //}

//}
