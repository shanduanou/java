package com.pubnub.api.endpoints.objects_vsp.space;

import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.endpoints.objects_api.CompositeParameterEnricher;
import com.pubnub.api.endpoints.objects_api.utils.Include.HavingCustomInclude;
import com.pubnub.api.enums.PNOperationType;
import com.pubnub.api.managers.RetrofitManager;
import com.pubnub.api.managers.TelemetryManager;
import com.pubnub.api.managers.token_manager.TokenManager;
import com.pubnub.api.models.consumer.objects_vsp.space.FetchSpaceResult;
import com.pubnub.api.models.consumer.objects_vsp.space.Space;
import com.pubnub.api.models.server.objects_api.EntityEnvelope;
import retrofit2.Call;
import retrofit2.Response;

import java.util.Map;

public class FetchSpaceCommand extends FetchSpace implements HavingCustomInclude<FetchSpace> {
    public FetchSpaceCommand(
            final PubNub pubNub,
            final TelemetryManager telemetryManager,
            final RetrofitManager retrofitManager,
            final TokenManager tokenManager,
            final CompositeParameterEnricher compositeParameterEnricher) {
        super(pubNub, telemetryManager, retrofitManager, tokenManager, compositeParameterEnricher);
    }

    @Override
    protected Call<EntityEnvelope<Space>> executeCommand(Map<String, String> effectiveParams) throws PubNubException {
        return getRetrofit()
                .getSpaceService()
                .fetchSpace(getPubnub().getConfiguration().getSubscribeKey(), getSpaceId().getValue(), effectiveParams);
    }

    @Override
    protected FetchSpaceResult createResponse(Response<EntityEnvelope<Space>> input) throws PubNubException {
        if (input.body() != null) {
            return new FetchSpaceResult(input.body());
        } else {
            return new FetchSpaceResult();
        }
    }

    @Override
    protected PNOperationType getOperationType() {
        return PNOperationType.PNFetchSpaceOperation;
    }

    @Override
    public CompositeParameterEnricher getCompositeParameterEnricher() {
        return super.getCompositeParameterEnricher();
    }
}
