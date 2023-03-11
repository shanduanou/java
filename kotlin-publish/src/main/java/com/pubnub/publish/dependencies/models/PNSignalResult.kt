package com.pubnub.publish.dependencies.models

import com.google.gson.JsonElement
import com.pubnub.publish.dependencies.SubscribeCallback

/**
 * Wrapper around a signal received in [SubscribeCallback.signal].
 */
data class PNSignalResult internal constructor(
    private val basePubSubResult: PubSubResult,
    override val message: JsonElement
) : MessageResult, PubSubResult by basePubSubResult
