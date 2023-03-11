package com.pubnub.publish.dependencies

internal val Boolean.numericString: String
    get() = if (this) "1" else "0"

internal val Boolean.valueString: String
    get() = "$this"
