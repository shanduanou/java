package com.pubnub.api;

import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.enums.PNReconnectionPolicy;
import com.pubnub.api.enums.PNStatusCategory;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.objects_api.channel.PNChannelMetadataResult;
import com.pubnub.api.models.consumer.objects_api.membership.PNMembershipResult;
import com.pubnub.api.models.consumer.objects_api.uuid.PNUUIDMetadataResult;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;
import com.pubnub.api.models.consumer.pubsub.PNSignalResult;
import com.pubnub.api.models.consumer.pubsub.files.PNFileEventResult;
import com.pubnub.api.models.consumer.pubsub.message_actions.PNMessageActionResult;
import org.jetbrains.annotations.NotNull;

public class Example {
    private final PubNub pubnub;

    public Example(PubNub pubnub) {
        this.pubnub = pubnub;
    }

    void stopSubscribe() {
        //it'll preserve current timetoken that we have
        pubnub.disconnect();
    }

    void startSubscribeRestart() {
        //uses the previously set timetoken. Do not alter it
        pubnub.reconnect();
    }

    void startSubscribeResetingTheTimetoken() {
        //resets the timetoken to whatever value passed orn
        pubnub.subscribe()
                .withTimetoken(42L)
                .execute();
        //resets it to default starting value - a 0
        pubnub.subscribe()
                .execute();
    }

    void detection403() {
        pubnub.addListener(new SubscribeCallback() {
            @Override
            public void status(@NotNull PubNub pubnub, @NotNull PNStatus pnStatus) {
                if (pnStatus.getCategory() == PNStatusCategory.PNAccessDeniedCategory) {
                    //user can react here
                }
            }

            @Override
            public void message(@NotNull PubNub pubnub, @NotNull PNMessageResult pnMessageResult) {

            }

            @Override
            public void presence(@NotNull PubNub pubnub, @NotNull PNPresenceEventResult pnPresenceEventResult) {

            }

            @Override
            public void signal(@NotNull PubNub pubnub, @NotNull PNSignalResult pnSignalResult) {

            }

            @Override
            public void uuid(@NotNull PubNub pubnub, @NotNull PNUUIDMetadataResult pnUUIDMetadataResult) {

            }

            @Override
            public void channel(@NotNull PubNub pubnub, @NotNull PNChannelMetadataResult pnChannelMetadataResult) {

            }

            @Override
            public void membership(@NotNull PubNub pubnub, @NotNull PNMembershipResult pnMembershipResult) {

            }

            @Override
            public void messageAction(@NotNull PubNub pubnub, @NotNull PNMessageActionResult pnMessageActionResult) {

            }

            @Override
            public void file(@NotNull PubNub pubnub, @NotNull PNFileEventResult pnFileEventResult) {

            }
        });
    }


    void reconnectionConfiguration() {
        PNConfiguration configuration = new PNConfiguration();

        //reconnections disabled
        configuration.setReconnectionPolicy(PNReconnectionPolicy.NONE);

        //linear policy
        configuration.setReconnectionPolicy(PNReconnectionPolicy.LINEAR);
        configuration.setMaximumReconnectionRetries(5);

        //exponential policy
        configuration.setReconnectionPolicy(PNReconnectionPolicy.EXPONENTIAL);
        configuration.setMaximumReconnectionRetries(7);
        PubNub pubnub = new PubNub(configuration);
    }
}
