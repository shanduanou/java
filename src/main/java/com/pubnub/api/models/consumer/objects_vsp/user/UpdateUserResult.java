package com.pubnub.api.models.consumer.objects_vsp.user;

import com.pubnub.api.models.server.objects_api.EntityEnvelope;
import lombok.*;

@NoArgsConstructor
public class UpdateUserResult extends UserResult {
    public UpdateUserResult(EntityEnvelope<User> entityEnvelope) {
        super(entityEnvelope);
    }
}
