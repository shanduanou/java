package com.pubnub.publish.dependencies.other

import com.pubnub.publish.dependencies.PubNub

/**
 * Result of [PubNub.addPushNotificationsOnChannels] operation.
 */
class PNPushAddChannelResult

class PNPushListProvisionsResult internal constructor(
    val channels: List<String>
)

class PNPushRemoveAllChannelsResult

class PNPushRemoveChannelResult
