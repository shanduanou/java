package com.pubnub.api.models.server.objects_vsp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


public class CreateUserPayload extends UserPayload {
    public CreateUserPayload(String name, String email, String externalId, String profileUrl, Object custom, String status, String type) {
        super(name, email, externalId, profileUrl, custom, status, type);
    }
}
