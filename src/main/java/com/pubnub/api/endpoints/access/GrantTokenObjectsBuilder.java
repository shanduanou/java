package com.pubnub.api.endpoints.access;

import com.pubnub.api.models.consumer.access_manager.v3.ChannelGrant;
import com.pubnub.api.models.consumer.access_manager.v3.ChannelGroupGrant;
import com.pubnub.api.models.consumer.access_manager.v3.UUIDGrant;

import java.util.List;

public class GrantTokenObjectsBuilder {
    private final GrantToken grantToken;

    public GrantTokenObjectsBuilder(GrantToken grantToken) {
        this.grantToken = grantToken;
    }

    public GrantTokenObjectsBuilder ttl(Integer arg) {
        return this;
    }

    public GrantTokenObjectsBuilder meta(Object arg) {
        return this;
    }

    public GrantTokenObjectsBuilder authorizedUUID(String arg) {
        return this;
    }

    public GrantTokenObjectsBuilder channels(List<ChannelGrant> arg) {
        return this;
    }

    public GrantTokenObjectsBuilder channelGroups(List<ChannelGroupGrant> arg) {
        return this;
    }

    public GrantTokenObjectsBuilder uuids(List<UUIDGrant> arg) {
        return this;
    }
}
