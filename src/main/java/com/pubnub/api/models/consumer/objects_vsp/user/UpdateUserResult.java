package com.pubnub.api.models.consumer.objects_vsp.user;

import com.pubnub.api.models.server.objects_api.EntityEnvelope;
import lombok.*;

@Getter
@Setter(AccessLevel.PACKAGE)
@NoArgsConstructor
@ToString(callSuper = true)
public class UpdateUserResult extends EntityEnvelope<User> {
    public UpdateUserResult(final EntityEnvelope<User> envelope) {
        this.status = envelope.getStatus();
        this.data = envelope.getData();
    }
}
