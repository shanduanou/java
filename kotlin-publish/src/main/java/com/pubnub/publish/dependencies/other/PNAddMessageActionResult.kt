package com.pubnub.publish.dependencies.other

import com.pubnub.publish.dependencies.PubNub
import com.pubnub.publish.dependencies.models.PNMessageAction

/**
 * Result for the [PubNub.addMessageAction] API operation.
 *
 * Essentially a wrapper around [PNMessageAction].
 */
class PNAddMessageActionResult internal constructor(action: PNMessageAction) :
    PNMessageAction(action)
