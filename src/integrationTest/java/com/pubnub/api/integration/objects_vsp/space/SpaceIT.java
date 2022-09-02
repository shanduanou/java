package com.pubnub.api.integration.objects_vsp.space;

import com.pubnub.api.PubNubException;
import com.pubnub.api.SpaceId;
import com.pubnub.api.integration.objects.ObjectsApiBaseIT;
import com.pubnub.api.models.consumer.objects_vsp.user.CreateSpaceResult;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SpaceIT extends ObjectsApiBaseIT {

    private final String randomSpaceId = getRandomSpaceIdValue();
    private final String randomName = randomName();
    private final String randomDescription = randomDescription();

    @Test
    public void createUserHappyPath() throws PubNubException {
        //given

        //when
        CreateSpaceResult createSpaceResult = pubNubUnderTest.createSpace()
                .spaceId(new SpaceId(randomSpaceId))
                .name(randomName)
                .description(randomDescription)
                .custom(customSpaceObject())
                .includeCustom(true)
                .status(STATUS_ACTIVE)
                .type(TYPE_HUMAN)
                .sync();

        //then
        assertNotNull(createSpaceResult);
        assertEquals(HttpStatus.SC_OK, createSpaceResult.getStatus());
        assertEquals(randomSpaceId, createSpaceResult.getData().getId());
        assertEquals(randomName, createSpaceResult.getData().getName());
        assertEquals(randomDescription, createSpaceResult.getData().getDescription());
        assertNotNull(createSpaceResult.getData().getCustom());
        assertEquals(STATUS_ACTIVE, createSpaceResult.getData().getStatus());
        assertEquals(TYPE_HUMAN, createSpaceResult.getData().getType());
    }


    private String getRandomSpaceIdValue() {
        return "spaceId" + new Random().nextInt(100000);
    }

    private static String randomDescription() {
        return RandomStringUtils.randomAlphabetic(50, 160);
    }
}
