package com.pubnub.api.endpoints.objects_vsp.space;

import com.pubnub.api.PubNub;
import com.pubnub.api.endpoints.objects_api.CompositeParameterEnricher;
import com.pubnub.api.endpoints.objects_api.utils.Include.CustomIncludeAware;
import com.pubnub.api.endpoints.objects_vsp.SpaceEndpoint;
import com.pubnub.api.managers.RetrofitManager;
import com.pubnub.api.managers.TelemetryManager;
import com.pubnub.api.managers.token_manager.TokenManager;
import com.pubnub.api.models.consumer.objects_vsp.space.Space;
import com.pubnub.api.models.consumer.objects_vsp.space.UpdateSpaceResult;
import com.pubnub.api.models.server.objects_api.EntityEnvelope;

import java.util.Map;

public abstract class UpdateSpace extends SpaceEndpoint<UpdateSpace, EntityEnvelope<Space>, UpdateSpaceResult> implements CustomIncludeAware<UpdateSpace> {
    public UpdateSpace(
            final PubNub pubNub,
            final TelemetryManager telemetry,
            final RetrofitManager retrofitInstance,
            final TokenManager tokenManager,
            final CompositeParameterEnricher compositeParameterEnricher) {
        super(pubNub, telemetry, retrofitInstance, compositeParameterEnricher, tokenManager);
    }

    public static UpdateSpace create(
            final PubNub pubNub,
            final TelemetryManager telemetryManager,
            final RetrofitManager retrofitManager,
            final TokenManager tokenManager) {
        final CompositeParameterEnricher compositeParameterEnricher = CompositeParameterEnricher.createDefault();
        return new UpdateSpaceCommand(pubNub, telemetryManager, retrofitManager, tokenManager, compositeParameterEnricher);
    }

    public abstract UpdateSpace name(String name);

    public abstract UpdateSpace description(String description);

    public abstract UpdateSpace custom(Map<String, Object> custom);

    public abstract UpdateSpace status(String status);

    public abstract UpdateSpace type(String type);

}
