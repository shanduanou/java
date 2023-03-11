package com.pubnub.publish.dependencies.forPubNubObject

import com.pubnub.publish.dependencies.other.PNChannelGroupPatternGrant
import com.pubnub.publish.dependencies.other.PNChannelGroupResourceGrant
import com.pubnub.publish.dependencies.other.PNGrant

interface ChannelGroupGrant : PNGrant {
    companion object {
        fun id(
            id: String,
            read: Boolean = false,
            manage: Boolean = false
        ): ChannelGroupGrant = PNChannelGroupResourceGrant(
            id = id,
            read = read,
            manage = manage
        )

        fun pattern(
            pattern: String,
            read: Boolean = false,
            manage: Boolean = false
        ): ChannelGroupGrant = PNChannelGroupPatternGrant(
            id = pattern,
            read = read,
            manage = manage
        )
    }
}
