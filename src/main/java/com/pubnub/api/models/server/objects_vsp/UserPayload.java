package com.pubnub.api.models.server.objects_vsp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public abstract class UserPayload {
    private final String name;
    private final String email;
    private final String externalId;
    private final String profileUrl;
    private final Object custom;
    private final String status;
    private final String type;
}
