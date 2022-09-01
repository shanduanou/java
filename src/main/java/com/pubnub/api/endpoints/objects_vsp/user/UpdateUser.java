package com.pubnub.api.endpoints.objects_vsp.user;

import com.pubnub.api.PubNub;
import com.pubnub.api.endpoints.objects_api.CompositeParameterEnricher;
import com.pubnub.api.endpoints.objects_api.utils.Include.CustomIncludeAware;
import com.pubnub.api.endpoints.objects_vsp.UserEndpoint;
import com.pubnub.api.managers.RetrofitManager;
import com.pubnub.api.managers.TelemetryManager;
import com.pubnub.api.managers.token_manager.TokenManager;
import com.pubnub.api.models.consumer.objects_vsp.user.UpdateUserResult;
import com.pubnub.api.models.consumer.objects_vsp.user.User;
import com.pubnub.api.models.server.objects_api.EntityEnvelope;

import java.util.Map;

public abstract class UpdateUser extends UserEndpoint<UpdateUser, EntityEnvelope<User>, UpdateUserResult> implements CustomIncludeAware<UpdateUser> {
    UpdateUser(
            final PubNub pubNub,
            final TelemetryManager telemetryManager,
            final RetrofitManager retrofitManager,
            final CompositeParameterEnricher compositeParameterEnricher,
            final TokenManager tokenManager) {
        super(pubNub, telemetryManager, retrofitManager, compositeParameterEnricher, tokenManager);
    }

    public static UpdateUser create(
            final PubNub pubNub,
            final TelemetryManager telemetryManager,
            final RetrofitManager retrofitManager,
            final TokenManager tokenManager) {
        final CompositeParameterEnricher compositeParameterEnricher = CompositeParameterEnricher.createDefault();
        return new UpdateUserCommand(pubNub, telemetryManager, retrofitManager, tokenManager, compositeParameterEnricher);
    }

    public abstract UpdateUser name(String name);

    public abstract UpdateUser email(String email);

    public abstract UpdateUser profileUrl(String profileUrl);

    public abstract UpdateUser externalId(String externalId);

    public abstract UpdateUser custom(Map<String, Object> custom);

    public abstract UpdateUser status(String status);

    public abstract UpdateUser type(String type);
}
