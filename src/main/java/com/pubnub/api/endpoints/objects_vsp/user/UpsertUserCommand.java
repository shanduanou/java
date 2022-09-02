package com.pubnub.api.endpoints.objects_vsp.user;

import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.endpoints.objects_api.CompositeParameterEnricher;
import com.pubnub.api.endpoints.objects_api.utils.Include.HavingCustomInclude;
import com.pubnub.api.enums.PNOperationType;
import com.pubnub.api.managers.RetrofitManager;
import com.pubnub.api.managers.TelemetryManager;
import com.pubnub.api.managers.token_manager.TokenManager;
import com.pubnub.api.models.consumer.objects_vsp.user.UpsertUserResult;
import com.pubnub.api.models.consumer.objects_vsp.user.User;
import com.pubnub.api.models.server.objects_api.EntityEnvelope;
import com.pubnub.api.models.server.objects_vsp.user.UpsertUserPayload;
import retrofit2.Call;
import retrofit2.Response;

import java.util.HashMap;
import java.util.Map;

public class UpsertUserCommand extends UpsertUser implements HavingCustomInclude<UpsertUser> {
    private String name;
    private String email;
    private String profileUrl;
    private String externalId;
    private Map<String, Object> custom;
    private String status;
    private String type;

    public UpsertUserCommand(
            final PubNub pubNub,
            final TelemetryManager telemetryManager,
            final RetrofitManager retrofitManager,
            final TokenManager tokenManager,
            final CompositeParameterEnricher compositeParameterEnricher) {
        super(pubNub, telemetryManager, retrofitManager, tokenManager, compositeParameterEnricher);
    }

    @Override
    protected Call<EntityEnvelope<User>> executeCommand(Map<String, String> effectiveParams) throws PubNubException {
        final HashMap<String, Object> customHashMap = new HashMap<>();
        if (custom != null) {
            customHashMap.putAll(custom);
        }

        final UpsertUserPayload upsertUserPayload = new UpsertUserPayload(name, email, externalId, profileUrl, customHashMap, status, type);

        String subscribeKey = getPubnub().getConfiguration().getSubscribeKey();
        return getRetrofit().getUserService().upsertUser(subscribeKey, effectiveUserId().getValue(), upsertUserPayload, effectiveParams);
    }

    @Override
    public UpsertUser name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public UpsertUser email(String email) {
        this.email = email;
        return this;
    }

    @Override
    public UpsertUser profileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
        return this;
    }

    @Override
    public UpsertUser externalId(String externalId) {
        this.externalId = externalId;
        return this;
    }

    @Override
    public UpsertUser custom(Map<String, Object> custom) {
        this.custom = custom;
        return this;
    }

    @Override
    public UpsertUser status(String status) {
        this.status = status;
        return this;
    }

    @Override
    public UpsertUser type(String type) {
        this.type = type;
        return this;
    }

    @Override
    protected UpsertUserResult createResponse(Response<EntityEnvelope<User>> input) throws PubNubException {
        if (input.body() != null) {
            return new UpsertUserResult(input.body());
        } else {
            return new UpsertUserResult();
        }
    }

    @Override
    protected PNOperationType getOperationType() {
        return PNOperationType.PNUpsertUserOperation;
    }

    @Override
    public CompositeParameterEnricher getCompositeParameterEnricher() {
        return super.getCompositeParameterEnricher();
    }
}
