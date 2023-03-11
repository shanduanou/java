package com.pubnub.publish.dependencies.models

data class PNDownloadableFile(
    override val id: String,
    override val name: String,
    val url: String
) : PNFile