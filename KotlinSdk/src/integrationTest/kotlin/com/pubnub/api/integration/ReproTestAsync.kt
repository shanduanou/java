package com.pubnub.api.integration

import com.pubnub.api.PNConfiguration
import com.pubnub.api.PubNub
import com.pubnub.api.UserId
import com.pubnub.api.callbacks.SubscribeCallback
import com.pubnub.api.enums.PNLogVerbosity
import com.pubnub.api.models.consumer.PNStatus
import com.pubnub.api.models.consumer.pubsub.PNMessageResult
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class ReproTestAsync {

    fun publisher(): PubNub {
        val publisherConfig = PNConfiguration(UserId("publisher")).apply {
            publishKey = ""   //put proper values here
            subscribeKey = "" //put proper values here
            secretKey = ""
            logVerbosity = PNLogVerbosity.BODY
        }
        return PubNub(publisherConfig)
    }

    fun subscriber(): PubNub {
        return PubNub(PNConfiguration(UserId("subscriber")).apply {
            publishKey = ""    //put proper values here
            subscribeKey = ""  //put proper values here
            secretKey = ""
            logVerbosity = PNLogVerbosity.BODY
        })
    }

    fun PubNub.publishTwoMessages(channel: String) {
        publish(
            channel = channel,
            message = "msg1async"
        ).async { result, status ->
            if (!status.error) {
                println("----Publish timetoken ${result!!.timetoken}")
            }else{
                println("----Publish error ${status.error}")
            }
            println("----Status code ${status.statusCode}, timeToken ${result!!.timetoken}")
        }

        publish(
            channel = channel,
            message = "msg2async"
        ).async { result, status ->
            if (!status.error) {
                println("----Publish timetoken ${result!!.timetoken}")
            }else{
                println("----Publish error ${status.error}")
            }
            println("----Status code ${status.statusCode}, timeToken ${result!!.timetoken}")
        }
    }

    @Test
    fun testRawPubNub() {
        val subscriber = subscriber()
        val channel = "truffleChannel"
        val messagesLatch = CountDownLatch(2)

        subscriber.addListener(object : SubscribeCallback() {
            override fun status(pubnub: PubNub, pnStatus: PNStatus) {
                println("----Status in listener: $pnStatus")
            }

            override fun message(pubnub: PubNub, pnMessageResult: PNMessageResult) {
                println("----Subscriber: $pnMessageResult")
                messagesLatch.countDown()
            }
        })

        subscriber.subscribe(channels = listOf(channel))
        Thread.sleep(2000)
        publisher().publishTwoMessages(channel)

        if (!messagesLatch.await(10_000, TimeUnit.MILLISECONDS)) {
            throw RuntimeException("-----Subscribe failed")
        }
    }
}