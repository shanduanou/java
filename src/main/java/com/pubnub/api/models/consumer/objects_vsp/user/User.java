package com.pubnub.api.models.consumer.objects_vsp.user;

import com.pubnub.api.models.consumer.objects_api.PNObject;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class User extends PNObject {
    private String name;
    private String email;
    private String externalId;
    private String profileUrl;
    private String status;
    private String type;

    public User(String id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public User setCustom(Object custom) {
        super.setCustom(custom);
        return this;
    }
}
