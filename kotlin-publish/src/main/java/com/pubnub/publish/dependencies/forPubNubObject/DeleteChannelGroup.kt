package com.pubnub.publish.dependencies.forPubNubObject

import com.pubnub.publish.dependencies.*
import com.pubnub.publish.dependencies.other.PNChannelGroupsDeleteGroupResult
import retrofit2.Call
import retrofit2.Response

/**
 * @see [PubNub.deleteChannelGroup]
 */
class DeleteChannelGroup internal constructor(
    pubnub: PubNub,
    val channelGroup: String
) : Endpoint<Void, PNChannelGroupsDeleteGroupResult>(pubnub) {

    override fun validateParams() {
        super.validateParams()
        if (channelGroup.isBlank()) throw PubNubException(PubNubError.GROUP_MISSING)
    }

    override fun getAffectedChannelGroups() = listOf(channelGroup)

    override fun doWork(queryParams: HashMap<String, String>): Call<Void> {
        return pubnub.retrofitManager.channelGroupService
            .deleteChannelGroup(
                pubnub.configuration.subscribeKey,
                channelGroup,
                queryParams
            )
    }

    override fun createResponse(input: Response<Void>): PNChannelGroupsDeleteGroupResult =
        PNChannelGroupsDeleteGroupResult()

    override fun operationType() = PNOperationType.PNRemoveGroupOperation
}
