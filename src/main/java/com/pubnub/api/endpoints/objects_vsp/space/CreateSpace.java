package com.pubnub.api.endpoints.objects_vsp.space;

import com.pubnub.api.PubNub;
import com.pubnub.api.SpaceId;
import com.pubnub.api.endpoints.objects_api.CompositeParameterEnricher;
import com.pubnub.api.endpoints.objects_api.ObjectApiEndpoint;
import com.pubnub.api.endpoints.objects_api.utils.Include.CustomIncludeAware;
import com.pubnub.api.managers.RetrofitManager;
import com.pubnub.api.managers.TelemetryManager;
import com.pubnub.api.managers.token_manager.TokenManager;
import com.pubnub.api.models.consumer.objects_vsp.space.Space;
import com.pubnub.api.models.server.objects_api.EntityEnvelope;

import java.util.Map;

public abstract class CreateSpace extends ObjectApiEndpoint<EntityEnvelope<Space>, Space> implements CustomIncludeAware<CreateSpace> {
    public CreateSpace(
            final PubNub pubNub,
            final TelemetryManager telemetry,
            final RetrofitManager retrofitInstance,
            final TokenManager tokenManager,
            final CompositeParameterEnricher compositeParameterEnricher) {
        super(pubNub, telemetry, retrofitInstance, compositeParameterEnricher, tokenManager);
    }

    public static CreateSpace create(
            final SpaceId spaceId,
            final PubNub pubNub,
            final TelemetryManager telemetryManager,
            final RetrofitManager retrofitManager,
            final TokenManager tokenManager) {
        final CompositeParameterEnricher compositeParameterEnricher = CompositeParameterEnricher.createDefault();
        return new CreateSpaceCommand(spaceId, pubNub, telemetryManager, retrofitManager, tokenManager, compositeParameterEnricher);
    }

    public abstract CreateSpace name(String name);

    public abstract CreateSpace description(String description);

    public abstract CreateSpace custom(Map<String, Object> custom);

    public abstract CreateSpace status(String status);

    public abstract CreateSpace type(String type);

}
