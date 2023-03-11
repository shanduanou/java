package com.pubnub.publish.dependencies.other

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName

class AccessManagerGrantPayload {

    internal var level: String? = null

    internal var ttl = 0

    @SerializedName("subscribe_key")
    internal var subscribeKey: String? = null

    internal val channels: Map<String, PNAccessManagerKeysData>? = null

    @SerializedName("channel-groups")
    internal val channelGroups: JsonElement? = null

    @SerializedName("auths")
    internal val authKeys: Map<String, PNAccessManagerKeyData>? = null

    internal val channel: String? = null
}