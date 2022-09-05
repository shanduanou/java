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
import com.pubnub.api.models.consumer.objects_vsp.space.UpsertSpaceResult;
import com.pubnub.api.models.server.objects_api.EntityEnvelope;
import com.pubnub.api.models.server.objects_vsp.space.UpsertSpacePayload;
import retrofit2.Call;
import retrofit2.Response;

import java.util.HashMap;
import java.util.Map;

public class UpsertSpaceCommand extends UpsertSpace implements HavingCustomInclude<UpsertSpace> {
    private String name;
    private String description;
    private Map<String, Object> custom;
    private String status;
    private String type;

    public UpsertSpaceCommand(
            final PubNub pubNub,
            final TelemetryManager telemetryManager,
            final RetrofitManager retrofitManager,
            final TokenManager tokenManager,
            final CompositeParameterEnricher compositeParameterEnricher) {
        super(pubNub, telemetryManager, retrofitManager, tokenManager, compositeParameterEnricher);
    }

    @Override
    protected Call<EntityEnvelope<Space>> executeCommand(Map<String, String> effectiveParams) throws PubNubException {
        final HashMap<String, Object> customHashMap = new HashMap<>();
        if (custom != null) {
            customHashMap.putAll(custom);
        }

        final UpsertSpacePayload upsertUserPayload = new UpsertSpacePayload(name, description, customHashMap, status, type);

        String subscribeKey = getPubnub().getConfiguration().getSubscribeKey();
        return getRetrofit().getSpaceService().upsertSpace(subscribeKey, getSpaceId().getValue(), upsertUserPayload, effectiveParams);
    }

    @Override
    protected UpsertSpaceResult createResponse(Response<EntityEnvelope<Space>> input) throws PubNubException {
        if (input != null) {
            return new UpsertSpaceResult(input.body());
        } else {
            return new UpsertSpaceResult();
        }
    }

    @Override
    protected PNOperationType getOperationType() {
        return PNOperationType.PNUpsertSpaceOperation;
    }

    @Override
    public CompositeParameterEnricher getCompositeParameterEnricher() {
        return super.getCompositeParameterEnricher();
    }

    @Override
    public UpsertSpace name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public UpsertSpace description(String description) {
        this.description = description;
        return this;
    }

    @Override
    public UpsertSpace custom(Map<String, Object> custom) {
        this.custom = custom;
        return this;
    }

    @Override
    public UpsertSpace status(String status) {
        this.status = status;
        return this;
    }

    @Override
    public UpsertSpace type(String type) {
        this.type = type;
        return this;
    }
}
