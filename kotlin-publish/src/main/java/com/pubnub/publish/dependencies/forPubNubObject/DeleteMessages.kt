package com.pubnub.publish.dependencies.forPubNubObject


import com.pubnub.publish.dependencies.*
import com.pubnub.publish.dependencies.other.PNDeleteMessagesResult
import retrofit2.Call
import retrofit2.Response
import java.util.*

/**
 * @see [PubNub.deleteMessages]
 */
class DeleteMessages internal constructor(
    pubnub: PubNub,
    val channels: List<String>,
    val start: Long? = null,
    val end: Long? = null
) : Endpoint<Void, PNDeleteMessagesResult>(pubnub) {

    override fun validateParams() {
        super.validateParams()
        if (channels.isEmpty()) throw PubNubException(PubNubError.CHANNEL_MISSING)
    }

    override fun doWork(queryParams: HashMap<String, String>): Call<Void> {
        addQueryParams(queryParams)

        return pubnub.retrofitManager.historyService.deleteMessages(
            pubnub.configuration.subscribeKey,
            channels.toCsv(),
            queryParams
        )
    }

    override fun createResponse(input: Response<Void>): PNDeleteMessagesResult =
        PNDeleteMessagesResult()

    override fun operationType() = PNOperationType.PNDeleteMessagesOperation

    private fun addQueryParams(queryParams: MutableMap<String, String>) {
        start?.run { queryParams["start"] = this.toString().toLowerCase(Locale.US) }
        end?.run { queryParams["end"] = this.toString().toLowerCase(Locale.US) }
    }
}