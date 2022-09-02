package com.pubnub.api.models.consumer.objects_vsp.space;

import com.google.gson.JsonElement;
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
public class RemoveSpaceResult extends EntityEnvelope<JsonElement> {
    public RemoveSpaceResult(final EntityEnvelope<JsonElement> entityEnvelope) {
        this.status = entityEnvelope.getStatus();
        this.data = entityEnvelope.getData();
    }
}
