package com.pubnub.publish.dependencies.forPubNubObject

interface NewSubscribeModule {
    companion object {
        internal fun create(
            pubnub: PubNub,
            listenerManager: ListenerManager,
            incomingPayloadProcessor: IncomingPayloadProcessor
        ): NewSubscribeModule {
            return InternalSubscribeModule.create(
                pubnub = pubnub,
                listenerManager = listenerManager,
                incomingPayloadProcessor = incomingPayloadProcessor
            )
        }
    }

    fun subscribe(
        channels: List<String>,
        channelGroups: List<String>,
        withPresence: Boolean,
        withTimetoken: Long
    )

    fun unsubscribe(
        channels: List<String> = emptyList(),
        channelGroups: List<String> = emptyList()
    )

    fun unsubscribeAll()
    fun getSubscribedChannels(): List<String>
    fun getSubscribedChannelGroups(): List<String>
    fun cancel()
}