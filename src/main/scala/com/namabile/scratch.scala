package com.namabile

import com.typesafe.config.ConfigFactory
import twitter4j._
import twitter4j.conf._
import com.namabile.avro.Tweet
import com.gensler.scalavro.types.AvroType
import com.gensler.scalavro.io.AvroTypeIO
import org.apache.avro.specific._
import org.apache.avro.generic._

object scratch {

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

  def main(args: Array[String]) {
    val twitterStream = new TwitterStreamFactory(getTwitterConf).getInstance
    twitterStream.addListener(simpleStatusListener)
    twitterStream.filter(new FilterQuery().follow(1344951,5988062,807095,3108351))
    println("streaming")
    Thread.sleep(10000)
    twitterStream.cleanUp
    twitterStream.shutdown
  }

  def simpleStatusListener = new StatusListener() {
    def onStatus(s: Status) {
      //println(s.getText)
      sendToKafka(s: Status)
    }
    def onDeletionNotice(statusDeletionNotice: StatusDeletionNotice) {}
    def onTrackLimitationNotice(numberOfLimitedStatuses: Int) {}
    def onException(ex: Exception) { ex.printStackTrace }
    def onScrubGeo(arg0: Long, arg1: Long) {}
    def onStallWarning(warning: StallWarning) {}
  }

  def sendToKafka(s: Status) {
    val tweet = buildTweet(s)
    val avroType = AvroType[Tweet]
    val io: AvroTypeIO[Tweet] = avroType.io
    val bytes: java.io.OutputStream = io.write(tweet, bytes)
    println(bytes)
  }

  def buildTweet(s: Status): Tweet = {
    new Tweet(s.getUser.getName, s.getText)
  }


}
