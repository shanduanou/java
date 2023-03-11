package com.pubnub.publish.dependencies.other



/**
 * Result of the [PubNub.whereNow] operation.
 *
 * @property channels List of channels where a UUID is present.
 */
class PNWhereNowResult internal constructor(
    val channels: List<String>
)
