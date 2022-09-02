package com.pubnub.api.endpoints.objects_vsp;

import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.SpaceId;
import com.pubnub.api.builder.PubNubErrorBuilder;
import com.pubnub.api.endpoints.objects_api.CompositeParameterEnricher;
import com.pubnub.api.endpoints.objects_api.ObjectApiEndpoint;
import com.pubnub.api.managers.RetrofitManager;
import com.pubnub.api.managers.TelemetryManager;
import com.pubnub.api.managers.token_manager.TokenManager;

public abstract class SpaceEndpoint<SELF extends  SpaceEndpoint, INPUT, OUTPUT> extends ObjectApiEndpoint<INPUT, OUTPUT> {
    protected SpaceId spaceId;

    protected SpaceEndpoint(
            final PubNub pubnubInstance,
            final TelemetryManager telemetry,
            final RetrofitManager retrofitInstance,
            final CompositeParameterEnricher compositeParameterEnricher,
            final TokenManager tokenManager) {
        super(pubnubInstance, telemetry, retrofitInstance, compositeParameterEnricher, tokenManager);
    }

    @Override
    protected void validateParams() throws PubNubException {
        super.validateParams();
    }

    public SELF spaceId(final SpaceId spaceId){
        this.spaceId = spaceId;
        return (SELF) this;
    }

    protected SpaceId getSpaceId(){
        return spaceId;
    }
}
