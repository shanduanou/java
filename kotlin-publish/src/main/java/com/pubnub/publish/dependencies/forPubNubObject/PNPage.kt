package com.pubnub.publish.dependencies.forPubNubObject

sealed class PNPage {
    abstract val pageHash: String
    data class PNNext(override val pageHash: String) : PNPage()
    data class PNPrev(override val pageHash: String) : PNPage()
}
