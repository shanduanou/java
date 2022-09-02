package com.pubnub.api.models.server.objects_vsp.space;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class CreateSpacePayload {
    private final String name;
    private final String description;
    private final Object custom;
    private final String status;
    private final String type;
}
