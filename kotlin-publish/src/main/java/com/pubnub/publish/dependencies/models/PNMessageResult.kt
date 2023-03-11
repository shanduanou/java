package com.pubnub.publish.dependencies.models

import com.google.gson.JsonElement

/**
 * Wrapper around an actual message received in [SubscribeCallback.message].
 */
data class PNMessageResult internal constructor(
    private val basePubSubResult: PubSubResult,
    override val message: JsonElement
) : MessageResult, PubSubResult by basePubSubResult
