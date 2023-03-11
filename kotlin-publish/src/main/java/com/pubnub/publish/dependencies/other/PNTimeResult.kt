package com.pubnub.publish.dependencies.other


/**
 * Result of the [PubNub.time] operation.
 *
 * @property timetoken Current time token.
 */
class PNTimeResult internal constructor(
    val timetoken: Long
)