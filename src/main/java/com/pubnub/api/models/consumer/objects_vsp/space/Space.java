package com.pubnub.api.models.consumer.objects_vsp.space;

import com.pubnub.api.models.consumer.objects_api.PNObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Space extends PNObject {
    private String name;
    private String description;
    private String status;
    private String type;

    public Space(String id, String name, String description){
        super(id);
        this.name = name;
        this.description = description;
    }

    public Space(String id, String name){
        this(id, name, null);
    }

    @Override
    public PNObject setCustom(Object custom) {
        super.setCustom(custom);
        return this;
    }
}
