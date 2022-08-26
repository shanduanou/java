package com.pubnub.api.endpoints.objects_vsp;

import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.UserId;
import com.pubnub.api.endpoints.objects_api.CompositeParameterEnricher;
import com.pubnub.api.endpoints.objects_api.ObjectApiEndpoint;
import com.pubnub.api.managers.RetrofitManager;
import com.pubnub.api.managers.TelemetryManager;
import com.pubnub.api.managers.token_manager.TokenManager;

public abstract class UserEndpoint<SELF extends UserEndpoint, INPUT, OUTPUT> extends ObjectApiEndpoint<INPUT, OUTPUT> {
    private UserId userId;

    protected UserEndpoint(
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

    public SELF userId(final UserId userId) {
        this.userId = userId;
        return (SELF) this;
    }

    protected UserId effectiveUserId() {   //chyba nie może być tak bo jak w URL nie ma userId to nie powinno zadziałać chyba, że tak chcemy?
        try {
            return (userId != null) ? userId : getPubnub().getConfiguration().getUserId();
        } catch (PubNubException e) {
            throw new RuntimeException(e);
        }
    }
}
