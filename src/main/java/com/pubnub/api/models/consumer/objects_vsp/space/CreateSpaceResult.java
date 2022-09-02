package com.pubnub.api.models.consumer.objects_vsp.space;

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
public class CreateSpaceResult extends EntityEnvelope<Space> {
    public CreateSpaceResult(final EntityEnvelope<Space> entityEnvelope) {
        this.status = entityEnvelope.getStatus();
        this.data = entityEnvelope.getData();
    }
}
