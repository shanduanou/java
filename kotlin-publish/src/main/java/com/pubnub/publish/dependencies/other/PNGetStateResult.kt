package com.pubnub.publish.dependencies.other

import com.google.gson.JsonElement

/**
 * Result of the [PubNub.getPresenceState] operation.
 *
 * @property stateByUUID Map of UUIDs and the user states.
 */
class PNGetStateResult internal constructor(
    val stateByUUID: Map<String, JsonElement>
)
