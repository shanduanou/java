package com.pubnub.api.endpoints.access;

import com.pubnub.api.models.consumer.access_manager.v3.ChannelGroupGrant;
import com.pubnub.api.models.consumer.access_manager.v3.UUIDGrant;

import java.util.List;

public class GrantTokenVSPBuilder {
    private final GrantToken grantToken;

    public GrantTokenVSPBuilder(GrantToken grantToken) {
        this.grantToken = grantToken;
    }

    public GrantTokenVSPBuilder ttl(Integer arg) {
        return this;
    }

    public GrantTokenVSPBuilder meta(Object arg) {
        return this;
    }

    public GrantTokenVSPBuilder authorizedUUID(String arg) {
        return this;
    }

    public GrantTokenVSPBuilder spaces(List<ChannelGroupGrant> arg) {
        return this;
    }

    public GrantTokenVSPBuilder users(List<UUIDGrant> arg) {
        return this;
    }
}
