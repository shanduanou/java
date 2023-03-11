package com.pubnub.publish.dependencies.models

import com.pubnub.publish.dependencies.SubscribeCallback

/**
 * Wrapper around message actions received in [SubscribeCallback.messageAction].
 *
 * @property event The message action event. Could be `added` or `removed`.
 * @property data The actual message action.
 */
data class PNMessageActionResult(
    private val result: BasePubSubResult,
    override val event: String,
    override val data: PNMessageAction
) : ObjectResult<PNMessageAction>, PubSubResult by result {
    val messageAction: PNMessageAction = data
}
