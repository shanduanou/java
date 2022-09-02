package com.pubnub.api.services.vsp;

import com.pubnub.api.models.consumer.objects_vsp.space.Space;
import com.pubnub.api.models.server.objects_api.EntityEnvelope;
import com.pubnub.api.models.server.objects_vsp.space.CreateSpacePayload;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

import java.util.Map;

public interface SpaceService {

    @POST("/v3/objects/{subKey}/spaces/{spaceId}")
    @Headers("Content-Type: application/json; charset=UTF-8")
    Call<EntityEnvelope<Space>> createSpace(@Path("subKey") String subKey, @Path("spaceId") String spaceId, @Body CreateSpacePayload createSpacePayload, @QueryMap(encoded = true) Map<String, String> options);
}
