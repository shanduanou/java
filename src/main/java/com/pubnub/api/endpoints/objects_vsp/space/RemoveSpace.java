package com.pubnub.api.endpoints.objects_vsp.space;

import com.google.gson.JsonElement;
import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.endpoints.objects_api.CompositeParameterEnricher;
import com.pubnub.api.endpoints.objects_vsp.SpaceEndpoint;
import com.pubnub.api.enums.PNOperationType;
import com.pubnub.api.managers.RetrofitManager;
import com.pubnub.api.managers.TelemetryManager;
import com.pubnub.api.managers.token_manager.TokenManager;
import com.pubnub.api.models.consumer.objects_vsp.space.RemoveSpaceResult;
import com.pubnub.api.models.server.objects_api.EntityEnvelope;
import retrofit2.Call;
import retrofit2.Response;

import java.util.Collections;
import java.util.Map;

public class RemoveSpace extends SpaceEndpoint<RemoveSpace, EntityEnvelope<JsonElement>, RemoveSpaceResult> {

    public RemoveSpace(
            final PubNub pubNub,
            final TelemetryManager telemetry,
            final RetrofitManager retrofitInstance,
            final TokenManager tokenManager) {
        super(pubNub, telemetry, retrofitInstance, CompositeParameterEnricher.createDefault(), tokenManager);
    }

    @Override
    protected Call<EntityEnvelope<JsonElement>> executeCommand(Map<String, String> effectiveParams) throws PubNubException {
        return getRetrofit()
                .getSpaceService()
                .removeSpace(getPubnub().getConfiguration().getSubscribeKey(), getSpaceId().getValue(), Collections.emptyMap());
    }

    @Override
    protected RemoveSpaceResult createResponse(Response<EntityEnvelope<JsonElement>> input) throws PubNubException {
        return new RemoveSpaceResult(input.body());
    }

    @Override
    protected PNOperationType getOperationType() {
        return PNOperationType.PNRemoveUserOperation;
    }

}
