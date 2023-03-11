package com.pubnub.api.integration

import com.pubnub.api.PNConfiguration
import com.pubnub.api.PubNub
import com.pubnub.api.UserId
import com.pubnub.api.callbacks.SubscribeCallback
import com.pubnub.api.enums.PNLogVerbosity
import com.pubnub.api.models.consumer.PNStatus
import com.pubnub.api.models.consumer.pubsub.PNMessageResult
import org.junit.Test
import java.lang.Long.min
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class ReproTest {

    fun publisher(): PubNub {
        val publisherConfig = PNConfiguration(UserId("publisher")).apply {
            publishKey = "pub-c-44aa8d5a-607b-499c-8704-443acf8073a9"
            subscribeKey = "sub-c-cb8b98b4-cd27-11ec-b360-1a35c262c233"
            secretKey = ""
            logVerbosity = PNLogVerbosity.BODY
        }
        return PubNub(publisherConfig)
    }

    fun subscriber(): PubNub {
        return PubNub(PNConfiguration(UserId("subscriber")).apply {
            publishKey = "pub-c-44aa8d5a-607b-499c-8704-443acf8073a9"
            subscribeKey = "sub-c-cb8b98b4-cd27-11ec-b360-1a35c262c233"
            secretKey = ""
            logVerbosity = PNLogVerbosity.BODY
        })
    }

    fun PubNub.publishTwoMessagesAndGetTimetoken(channel: String): Long {
        val tt1 = publish(
            channel = channel,
            message = "msg1"
        ).sync()!!.timetoken
        println("----tt1:  $tt1")

        val tt2 = publish(
            channel = channel,
            message = "msg2"
        ).sync()!!.timetoken
        println("----tt2: $tt2")


        return min(tt1, tt2) - 10
    }

    fun PubNub.publishTwoMessages(channel: String) {
        publish(
            channel = channel,
            message = "msg1async"
        ).async { result, status ->
            if (!status.error) {
//                println("----Publish timetoken ${result!!.timetoken}")
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
//                println("----Publish timetoken ${result!!.timetoken}")
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
                println("----status in listener: $pnStatus")
            }

            override fun message(pubnub: PubNub, pnMessageResult: PNMessageResult) {
                println("----Subscriber: $pnMessageResult")
                messagesLatch.countDown()
            }
        })

        subscriber.subscribe(channels = listOf(channel))
//        val timetoken = publisher().publishTwoMessagesAndGetTimetoken(channel)  //sync
        Thread.sleep(2000)
        publisher().publishTwoMessages(channel)  //async
//        subscriber.subscribe(channels = listOf(channel), withTimetoken = timetoken)

        if (!messagesLatch.await(10_000, TimeUnit.MILLISECONDS)) {
            throw RuntimeException("-----Subscribe failed")
        }
    }

    @Test
    fun subscribeWithTimeToken() {
        val subscriber = subscriber()
        val messagesLatch = CountDownLatch(2)
        val channel = "truffleChannel"
        val timeToken01: Long = 16685957135861679
        val timeToken02: Long = 16685957135882701
        val timetoken = min(timeToken01, timeToken02) - 10

        subscriber.addListener(object : SubscribeCallback() {
            override fun status(pubnub: PubNub, pnStatus: PNStatus) {
                println("----status in listener: $pnStatus")
            }

            override fun message(pubnub: PubNub, pnMessageResult: PNMessageResult) {
                println("----Subscriber: $pnMessageResult")
                messagesLatch.countDown()
            }
        })

        subscriber.subscribe(channels = listOf(channel), withTimetoken = timetoken)

        if (!messagesLatch.await(5_000, TimeUnit.MILLISECONDS)) {
            throw RuntimeException("-----Subscribe failed")
        }
    }
}

