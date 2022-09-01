package com.pubnub.api.models.consumer.objects_vsp.user;

import com.pubnub.api.models.server.objects_api.EntityEnvelope;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter(AccessLevel.PACKAGE)
@NoArgsConstructor
@ToString(callSuper = true)
public abstract class UserResult extends EntityEnvelope<User> {
    public UserResult(final EntityEnvelope<User> entityEnvelope) {
        this.status = entityEnvelope.getStatus();
        this.data = entityEnvelope.getData();
    }
}
