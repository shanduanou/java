package com.pubnub.api.endpoints.objects_vsp.space;

import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.endpoints.objects_api.CompositeParameterEnricher;
import com.pubnub.api.endpoints.objects_api.utils.Include.HavingCustomInclude;
import com.pubnub.api.enums.PNOperationType;
import com.pubnub.api.managers.RetrofitManager;
import com.pubnub.api.managers.TelemetryManager;
import com.pubnub.api.managers.token_manager.TokenManager;
import com.pubnub.api.models.consumer.objects_vsp.space.Space;
import com.pubnub.api.models.consumer.objects_vsp.space.CreateSpaceResult;
import com.pubnub.api.models.server.objects_api.EntityEnvelope;
import com.pubnub.api.models.server.objects_vsp.space.CreateSpacePayload;
import retrofit2.Call;
import retrofit2.Response;

import java.util.Map;

public final class CreateSpaceCommand extends CreateSpace implements HavingCustomInclude<CreateSpace> {
    private String name;
    private String description;
    private Map<String, Object> custom;
    private String status;
    private String type;


    public CreateSpaceCommand(
            final PubNub pubNub,
            final TelemetryManager telemetryManager,
            final RetrofitManager retrofitManager,
            final TokenManager tokenManager,
            final CompositeParameterEnricher compositeParameterEnricher) {
        super(pubNub, telemetryManager, retrofitManager, tokenManager, compositeParameterEnricher);
    }

    @Override
    public CreateSpace name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public CreateSpace description(String description) {
        this.description = description;
        return this;
    }

    @Override
    public CreateSpace custom(Map<String, Object> custom) {
        this.custom = custom;
        return this;
    }

    @Override
    public CreateSpace status(String status) {
        this.status = status;
        return this;
    }

    @Override
    public CreateSpace type(String type) {
        this.type = type;
        return this;
    }

    @Override
    protected Call<EntityEnvelope<Space>> executeCommand(Map<String, String> effectiveParams) throws PubNubException {
        final CreateSpacePayload createSpacePayload = new CreateSpacePayload(name, description, custom, status, type);
        String subscribeKey = getPubnub().getConfiguration().getSubscribeKey();

        return getRetrofit().getSpaceService().createSpace(subscribeKey, getSpaceId().getValue(), createSpacePayload, effectiveParams);
    }

    @Override
    public CompositeParameterEnricher getCompositeParameterEnricher() {
        return super.getCompositeParameterEnricher();
    }

    @Override
    protected CreateSpaceResult createResponse(Response<EntityEnvelope<Space>> input) throws PubNubException {
        if (input.body() != null) {
            return new CreateSpaceResult(input.body());
        } else {
            return new CreateSpaceResult();
        }
    }

    @Override
    protected PNOperationType getOperationType() {
        return PNOperationType.PNCreateSpaceOperation;
    }
}
