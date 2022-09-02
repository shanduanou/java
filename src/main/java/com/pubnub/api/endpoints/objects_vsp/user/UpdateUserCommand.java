package com.pubnub.api.endpoints.objects_vsp.user;

import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.endpoints.objects_api.CompositeParameterEnricher;
import com.pubnub.api.endpoints.objects_api.utils.Include.HavingCustomInclude;
import com.pubnub.api.enums.PNOperationType;
import com.pubnub.api.managers.RetrofitManager;
import com.pubnub.api.managers.TelemetryManager;
import com.pubnub.api.managers.token_manager.TokenManager;
import com.pubnub.api.models.consumer.objects_vsp.user.UpdateUserResult;
import com.pubnub.api.models.consumer.objects_vsp.user.User;
import com.pubnub.api.models.server.objects_api.EntityEnvelope;
import com.pubnub.api.models.server.objects_vsp.user.UpdateUserPayload;
import retrofit2.Call;
import retrofit2.Response;

import java.util.HashMap;
import java.util.Map;

public class UpdateUserCommand extends UpdateUser implements HavingCustomInclude<UpdateUser> {
    private String name;
    private String email;
    private String profileUrl;
    private String externalId;
    private Map<String, Object> custom;
    private String status;
    private String type;

    public UpdateUserCommand(
            final PubNub pubNub,
            final TelemetryManager telemetryManager,
            final RetrofitManager retrofitManager,
            final TokenManager tokenManager,
            final CompositeParameterEnricher compositeParameterEnricher) {
        super(pubNub, telemetryManager, retrofitManager, compositeParameterEnricher, tokenManager);

    }

    @Override
    protected Call<EntityEnvelope<User>> executeCommand(Map<String, String> effectiveParams) throws PubNubException {
        //This is workaround to accept custom maps that are instances of anonymous classes not handled by gson
        final HashMap<String, Object> customHashMap = new HashMap<>();
        if (custom != null) {
            customHashMap.putAll(custom);
        }

        final UpdateUserPayload updateUserPayload = new UpdateUserPayload(name, email, externalId, profileUrl, customHashMap, status, type);

        String subscribeKey = getPubnub().getConfiguration().getSubscribeKey();
        return getRetrofit()
                .getUserService()
                .updateUser(subscribeKey, effectiveUserId().getValue(), updateUserPayload, effectiveParams);
    }

    @Override
    public UpdateUser name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public UpdateUser email(String email) {
        this.email = email;
        return this;
    }

    @Override
    public UpdateUser profileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
        return this;
    }

    @Override
    public UpdateUser externalId(String externalId) {
        this.externalId = externalId;
        return this;
    }

    @Override
    public UpdateUser custom(Map<String, Object> custom) {
        this.custom = custom;
        return this;
    }

    @Override
    public UpdateUser status(String status) {
        this.status = status;
        return this;
    }

    @Override
    public UpdateUser type(String type) {
        this.type = type;
        return this;
    }

    @Override
    protected UpdateUserResult createResponse(Response<EntityEnvelope<User>> input) throws PubNubException {
        if (input.body() != null) {
            return new UpdateUserResult(input.body());
        } else {
            return new UpdateUserResult();
        }
    }

    @Override
    protected PNOperationType getOperationType() {
        return PNOperationType.PNUpdateUserOperation;
    }

    @Override
    public CompositeParameterEnricher getCompositeParameterEnricher() {
        return super.getCompositeParameterEnricher();
    }
}
