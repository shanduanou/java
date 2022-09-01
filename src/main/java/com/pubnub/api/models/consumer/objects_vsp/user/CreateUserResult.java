package com.pubnub.api.models.consumer.objects_vsp.user;

import com.pubnub.api.models.server.objects_api.EntityEnvelope;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CreateUserResult extends UserResult {
    public CreateUserResult(EntityEnvelope<User> entityEnvelope) {
        super(entityEnvelope);
    }
}

