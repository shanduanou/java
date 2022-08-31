package com.pubnub.api.services.vsp;

import com.google.gson.JsonElement;
import com.pubnub.api.models.consumer.objects_vsp.user.User;
import com.pubnub.api.models.server.objects_api.EntityEnvelope;
import com.pubnub.api.models.server.objects_vsp.CreateUserPayload;
import com.pubnub.api.models.server.objects_vsp.UpdateUserPayload;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

public interface UserService {

    @POST("/v3/objects/{subKey}/users/{userId}")
    @Headers("Content-Type: application/json; charset=UTF-8")
    Call<EntityEnvelope<User>> createUser(@Path("subKey") String subKey, @Path("userId") String userId, @Body CreateUserPayload createUserPayload, @QueryMap(encoded = true) Map<String, String> options);

    @GET("/v3/objects/{subKey}/users/{userId}")
    Call<EntityEnvelope<User>> fetchUser(@Path("subKey") String subKey, @Path("userId") String userId, @QueryMap(encoded = true) Map<String, String> options);

    @DELETE("/v3/objects/{subKey}/users/{userId}")
    Call<EntityEnvelope<JsonElement>> removeUser(@Path("subKey") String subKey, @Path("userId") String userId, @QueryMap(encoded = true) Map<String, String> options);

    @PATCH("/v3/objects/{subKey}/users/{userId}")
    Call<EntityEnvelope<User>> updateUser(@Path("subKey") String subKey, @Path("userId") String userId, @Body UpdateUserPayload updateUserPayload, @QueryMap(encoded = true) Map<String, String> options);
}
