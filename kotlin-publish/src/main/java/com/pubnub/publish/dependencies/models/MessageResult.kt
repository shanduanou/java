package com.pubnub.publish.dependencies.models

import com.google.gson.JsonElement

/**
 * @property message The actual message content
 */
interface MessageResult : PubSubResult {
    val message: JsonElement
}
