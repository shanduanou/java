package com.pubnub.publish.dependencies.other

import java.io.InputStream

data class PNDownloadFileResult(
    val fileName: String,
    val byteStream: InputStream?
)
