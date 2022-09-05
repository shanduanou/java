package com.pubnub.api.integration.objects_vsp.space;

import com.google.gson.JsonObject;
import com.pubnub.api.PubNubException;
import com.pubnub.api.SpaceId;
import com.pubnub.api.integration.objects.ObjectsApiBaseIT;
import com.pubnub.api.models.consumer.objects_vsp.space.CreateSpaceResult;
import com.pubnub.api.models.consumer.objects_vsp.space.FetchSpaceResult;
import com.pubnub.api.models.consumer.objects_vsp.space.RemoveSpaceResult;
import com.pubnub.api.models.consumer.objects_vsp.space.UpdateSpaceResult;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Test;

import java.util.Map;
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

    @Test
    public void removeSpaceHappyPath() throws PubNubException {
        //given
        pubNubUnderTest.createSpace()
                .spaceId(new SpaceId(randomSpaceId))
                .name(randomName)
                .description(randomDescription)
                .custom(customSpaceObject())
                .includeCustom(true)
                .status(STATUS_ACTIVE)
                .type(TYPE_HUMAN)
                .sync();

        //when
        RemoveSpaceResult removeSpaceResult = pubNubUnderTest.removeSpace().spaceId(new SpaceId(randomSpaceId)).sync();

        //then
        assertEquals(HttpStatus.SC_OK, removeSpaceResult.getStatus());


    }

    @Test
    public void fetchSpaceHappyPath() throws PubNubException {
        //given
        pubNubUnderTest.createSpace()
                .spaceId(new SpaceId(randomSpaceId))
                .name(randomName)
                .description(randomDescription)
                .custom(customSpaceObject())
                .includeCustom(true)
                .status(STATUS_ACTIVE)
                .type(TYPE_HUMAN)
                .sync();

        //when
        FetchSpaceResult fetchSpaceResult = pubNubUnderTest.fetchSpace()
                .spaceId(new SpaceId(randomSpaceId))
                .sync();

        //then
        assertNotNull(fetchSpaceResult);
        assertEquals(HttpStatus.SC_OK, fetchSpaceResult.getStatus());
        assertEquals(randomSpaceId, fetchSpaceResult.getData().getId());
        assertEquals(randomName, fetchSpaceResult.getData().getName());
        assertEquals(randomDescription, fetchSpaceResult.getData().getDescription());
        assertNotNull(fetchSpaceResult.getData().getCustom());
        assertEquals(STATUS_ACTIVE, fetchSpaceResult.getData().getStatus());
        assertEquals(TYPE_HUMAN, fetchSpaceResult.getData().getType());

    }

    @Test
    public void updateUser_passing_full_object_happyPath() throws PubNubException {
        //given
        String updatedName = "updatedName" + randomName();
        String updatedDescription = "updatedDescription" + randomName();
        Map<String, Object> updateCustom = updatedCustomSpaceObject();
        String updatedStatus = "updatedStatus" + STATUS_ACTIVE;
        String updatedType = "updatedType" + TYPE_HUMAN;

        pubNubUnderTest.createSpace()
                .spaceId(new SpaceId(randomSpaceId))
                .name(randomName)
                .description(randomDescription)
                .custom(customSpaceObject())
                .includeCustom(true)
                .status(STATUS_ACTIVE)
                .type(TYPE_HUMAN)
                .sync();

        //when
        UpdateSpaceResult updateSpaceResult = pubNubUnderTest.updateSpace()
                .spaceId(new SpaceId(randomSpaceId))
                .name(updatedName)
                .description(updatedDescription)
                .custom(updateCustom)
                .status(updatedStatus)
                .type(updatedType)
                .sync();

        //then
        assertNotNull(updateSpaceResult);
        assertEquals(HttpStatus.SC_OK, updateSpaceResult.getStatus());
        assertEquals(randomSpaceId, updateSpaceResult.getData().getId());
        assertEquals(updatedName, updateSpaceResult.getData().getName());
        assertEquals(updatedDescription, updateSpaceResult.getData().getDescription());
        assertEquals(updatedDescription, updateSpaceResult.getData().getDescription());
        assertEquals("\"val1_updated\"", ((JsonObject) updateSpaceResult.getData().getCustom()).getAsJsonObject().get("param1").toString());
        assertEquals("\"val2_updated\"", ((JsonObject) updateSpaceResult.getData().getCustom()).getAsJsonObject().get("param2").toString());
        assertEquals("\"added\"", ((JsonObject) updateSpaceResult.getData().getCustom()).getAsJsonObject().get("param3").toString());
        assertEquals(updatedStatus, updateSpaceResult.getData().getStatus());
        assertEquals(updatedType, updateSpaceResult.getData().getType());

        FetchSpaceResult fetchSpaceResult = pubNubUnderTest.fetchSpace()
                .spaceId(new SpaceId(randomSpaceId))
                .sync();

        assertNotNull(fetchSpaceResult);
        assertEquals(HttpStatus.SC_OK, fetchSpaceResult.getStatus());
        assertEquals(randomSpaceId, fetchSpaceResult.getData().getId());
        assertEquals(updatedName, fetchSpaceResult.getData().getName());
        assertEquals(updatedDescription, fetchSpaceResult.getData().getDescription());
        assertNotNull(fetchSpaceResult.getData().getCustom());
        assertEquals(updatedStatus, fetchSpaceResult.getData().getStatus());
        assertEquals(updatedType, fetchSpaceResult.getData().getType());
    }

    @Test
    public void updateSpace_creates_space_if_space_doesnot_exist() throws PubNubException {
        //given
        String updatedName = "updatedName" + randomName();

        //when
        UpdateSpaceResult updateSpaceResult = pubNubUnderTest.updateSpace()
                .spaceId(new SpaceId(randomSpaceId))
                .name(updatedName)
                .sync();

        //then
        assertEquals(HttpStatus.SC_OK, updateSpaceResult.getStatus());
        assertEquals(randomSpaceId, updateSpaceResult.getData().getId());
        assertEquals(null, updateSpaceResult.getData().getDescription());
        assertEquals(null, updateSpaceResult.getData().getStatus());
        assertEquals(null, updateSpaceResult.getData().getType());
        assertEquals(updatedName, updateSpaceResult.getData().getName());
    }

    @After
    public void tearDown() throws Exception {
        pubNubUnderTest.removeSpace().spaceId(new SpaceId(randomSpaceId)).sync();
    }

    private String getRandomSpaceIdValue() {
        return "spaceId" + new Random().nextInt(100000);
    }

    private static String randomDescription() {
        return RandomStringUtils.randomAlphabetic(50, 160);
    }
}
