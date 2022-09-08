package com.pubnub.api.endpoints.objects_vsp.user;

import com.pubnub.api.PubNub;
import com.pubnub.api.UserId;
import com.pubnub.api.endpoints.objects_api.CompositeParameterEnricher;
import com.pubnub.api.endpoints.objects_api.ObjectApiEndpoint;
import com.pubnub.api.endpoints.objects_api.utils.Include.CustomIncludeAware;
import com.pubnub.api.managers.RetrofitManager;
import com.pubnub.api.managers.TelemetryManager;
import com.pubnub.api.managers.token_manager.TokenManager;
import com.pubnub.api.models.consumer.objects_vsp.user.User;
import com.pubnub.api.models.server.objects_api.EntityEnvelope;

import java.util.Map;

public abstract class UpsertUser extends ObjectApiEndpoint<EntityEnvelope<User>, User> implements CustomIncludeAware<UpsertUser> {
    UpsertUser(
            final PubNub pubNub,
            final TelemetryManager telemetry,
            final RetrofitManager retrofitInstance,
            final TokenManager tokenManager,
            final CompositeParameterEnricher compositeParameterEnricher) {
        super(pubNub, telemetry, retrofitInstance, compositeParameterEnricher, tokenManager);
    }

    public static UpsertUser create(
            final UserId userId,
            final PubNub pubNub,
            final TelemetryManager telemetryManager,
            final RetrofitManager retrofitManager,
            final TokenManager tokenManager) {
        final CompositeParameterEnricher compositeParameterEnricher = CompositeParameterEnricher.createDefault();
        return new UpsertUserCommand(userId, pubNub, telemetryManager, retrofitManager, tokenManager, compositeParameterEnricher);
    }

    public abstract UpsertUser name(String name);

    public abstract UpsertUser email(String email);

    public abstract UpsertUser profileUrl(String profileUrl);

    public abstract UpsertUser externalId(String externalId);

    public abstract UpsertUser custom(Map<String, Object> custom);

    public abstract UpsertUser status(String status);

    public abstract UpsertUser type(String type);
}
