package com.pubnub.api.services.vsp;

import com.pubnub.api.models.consumer.objects_vsp.user.User;
import com.pubnub.api.models.server.objects_api.EntityEnvelope;
import com.pubnub.api.models.server.objects_vsp.CreateUserPayload;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

public interface UserService {

    @POST("/v3/objects/{subKey}/users/{userId}")
    @Headers("Content-Type: application/json; charset=UTF-8")
    Call<EntityEnvelope<User>> createUser(@Path("subKey") String subKey, @Path("userId") String userId, @Body CreateUserPayload createUserPayload, @QueryMap(encoded = true) Map<String, String> options);

}
