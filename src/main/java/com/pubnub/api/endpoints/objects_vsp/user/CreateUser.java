package com.pubnub.api.endpoints.objects_vsp.user;

import com.pubnub.api.PubNub;
import com.pubnub.api.endpoints.objects_api.CompositeParameterEnricher;
import com.pubnub.api.endpoints.objects_api.utils.Include.CustomIncludeAware;
import com.pubnub.api.endpoints.objects_vsp.UserEndpoint;
import com.pubnub.api.managers.RetrofitManager;
import com.pubnub.api.managers.TelemetryManager;
import com.pubnub.api.managers.token_manager.TokenManager;
import com.pubnub.api.models.consumer.objects_vsp.user.User;
import com.pubnub.api.models.server.objects_api.EntityEnvelope;

import java.util.Map;

public abstract class CreateUser extends UserEndpoint<CreateUser, EntityEnvelope<User>, User> implements CustomIncludeAware<CreateUser> {

    CreateUser(
            final PubNub pubNub,
            final TelemetryManager telemetry,
            final RetrofitManager retrofitInstance,
            final TokenManager tokenManager,
            final CompositeParameterEnricher compositeParameterEnricher) {
        super(pubNub, telemetry, retrofitInstance, compositeParameterEnricher, tokenManager);
    }

    public static CreateUser create(
            final PubNub pubNub,
            final TelemetryManager telemetryManager,
            final RetrofitManager retrofitManager,
            final TokenManager tokenManager) {
        final CompositeParameterEnricher compositeParameterEnricher = CompositeParameterEnricher.createDefault();
        return new CreateUserCommand(pubNub, telemetryManager, retrofitManager, tokenManager, compositeParameterEnricher);
    }

    public abstract CreateUser name(String name);

    public abstract CreateUser email(String email);

    public abstract CreateUser profileUrl(String profileUrl);

    public abstract CreateUser externalId(String externalId);

    public abstract CreateUser custom(Map<String, Object> custom);

    public abstract CreateUser status(String status);

    public abstract CreateUser type(String type);
}
