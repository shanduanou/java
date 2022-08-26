package com.pubnub.api.endpoints.objects_vsp.user;

import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.endpoints.objects_api.CompositeParameterEnricher;
import com.pubnub.api.endpoints.objects_api.utils.Include.HavingCustomInclude;
import com.pubnub.api.enums.PNOperationType;
import com.pubnub.api.managers.RetrofitManager;
import com.pubnub.api.managers.TelemetryManager;
import com.pubnub.api.managers.token_manager.TokenManager;
import com.pubnub.api.models.consumer.objects_vsp.user.CreateUserResult;
import com.pubnub.api.models.consumer.objects_vsp.user.User;
import com.pubnub.api.models.server.objects_api.EntityEnvelope;
import com.pubnub.api.models.server.objects_vsp.CreateUserPayload;
import retrofit2.Call;
import retrofit2.Response;

import java.util.HashMap;
import java.util.Map;

final public class CreateUserCommand extends CreateUser implements HavingCustomInclude<CreateUser> {
    private String name;
    private String email;
    private String profileUrl;
    private String externalId;
    private Map<String, Object> custom;
    private String status;
    private String type;

    public CreateUserCommand(
            final PubNub pubNub,
            final TelemetryManager telemetryManager,
            final RetrofitManager retrofitManager,
            final TokenManager tokenManager,
            final CompositeParameterEnricher compositeParameterEnricher) {
        super(pubNub, telemetryManager, retrofitManager, tokenManager, compositeParameterEnricher);
    }

    @Override
    protected Call<EntityEnvelope<User>> executeCommand(Map<String, String> effectiveParams) throws PubNubException {
        //This is workaround to accept custom maps that are instances of anonymous classes not handled by gson
        final HashMap<String, Object> customHashMap = new HashMap<>();
        if (custom != null) {
            customHashMap.putAll(custom);
        }

        final CreateUserPayload createUserPayload = new CreateUserPayload(name, email, externalId, profileUrl, customHashMap, status, type);

        return getRetrofit().getUserService().createUser(getPubnub().getConfiguration().getSubscribeKey(), effectiveUserId().getValue(), createUserPayload, effectiveParams);
    }

    @Override
    public CompositeParameterEnricher getCompositeParameterEnricher() {
        return super.getCompositeParameterEnricher();
    }


    @Override
    public CreateUser name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public CreateUser email(String email) {
        this.email = email;
        return this;
    }

    @Override
    public CreateUser profileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
        return this;
    }

    @Override
    public CreateUser externalId(String externalId) {
        this.externalId = externalId;
        return this;
    }

    @Override
    public CreateUser custom(Map<String, Object> custom) {
        this.custom = custom;
        return this;
    }

    @Override
    public CreateUser status(String status) {
        this.status = status;
        return this;
    }

    @Override
    public CreateUser type(String type) {
        this.type = type;
        return this;
    }

    @Override
    protected CreateUserResult createResponse(Response<EntityEnvelope<User>> input) throws PubNubException {
        if (input.body() != null) {
            return new CreateUserResult(input.body());
        } else {
            return new CreateUserResult();
        }
    }

    @Override
    protected PNOperationType getOperationType() {
        return PNOperationType.PNCreateUserOperation;
    }
}
