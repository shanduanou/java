package com.pubnub.api.integration.objects_vsp.space;

import com.google.gson.JsonObject;
import com.pubnub.api.PubNubException;
import com.pubnub.api.SpaceId;
import com.pubnub.api.integration.objects.ObjectsApiBaseIT;
import com.pubnub.api.models.consumer.objects_vsp.space.RemoveSpaceResult;
import com.pubnub.api.models.consumer.objects_vsp.space.Space;
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
        Space space = pubNubUnderTest.createSpace(new SpaceId(randomSpaceId))
                .name(randomName)
                .description(randomDescription)
                .custom(customSpaceObject())
                .includeCustom(true)
                .status(STATUS_ACTIVE)
                .type(TYPE_HUMAN)
                .sync();

        //then
        assertNotNull(space);
        assertEquals(randomSpaceId, space.getId());
        assertEquals(randomName, space.getName());
        assertEquals(randomDescription, space.getDescription());
        assertNotNull(space.getCustom());
        assertEquals(STATUS_ACTIVE, space.getStatus());
        assertEquals(TYPE_HUMAN, space.getType());
    }

    @Test(expected = PubNubException.class)
    public void should_throw_exception_when_space_with_the_spaceId_exists() throws PubNubException {
        //given
        pubNubUnderTest.createSpace(new SpaceId(randomSpaceId))
                .name(randomName)
                .description(randomDescription)
                .custom(customSpaceObject())
                .includeCustom(true)
                .status(STATUS_ACTIVE)
                .type(TYPE_HUMAN)
                .sync();

        //when
        pubNubUnderTest.createSpace(new SpaceId(randomSpaceId))
                .name(randomName)
                .description(randomDescription)
                .custom(customSpaceObject())
                .includeCustom(true)
                .status(STATUS_ACTIVE)
                .type(TYPE_HUMAN)
                .sync();

        //then
    }

    @Test
    public void removeSpaceHappyPath() throws PubNubException {
        //given
        pubNubUnderTest.createSpace(new SpaceId(randomSpaceId))
                .name(randomName)
                .description(randomDescription)
                .custom(customSpaceObject())
                .includeCustom(true)
                .status(STATUS_ACTIVE)
                .type(TYPE_HUMAN)
                .sync();

        //when
        RemoveSpaceResult removeSpaceResult = pubNubUnderTest.removeSpace(new SpaceId(randomSpaceId)).sync();

        //then
        assertEquals(HttpStatus.SC_OK, removeSpaceResult.getStatus());


    }

    @Test
    public void fetchSpaceHappyPath() throws PubNubException {
        //given
        pubNubUnderTest.createSpace(new SpaceId(randomSpaceId))
                .name(randomName)
                .description(randomDescription)
                .custom(customSpaceObject())
                .includeCustom(true)
                .status(STATUS_ACTIVE)
                .type(TYPE_HUMAN)
                .sync();

        //when
        Space space = pubNubUnderTest.fetchSpace(new SpaceId(randomSpaceId))
                .sync();

        //then
        assertNotNull(space);
        assertEquals(randomSpaceId, space.getId());
        assertEquals(randomName, space.getName());
        assertEquals(randomDescription, space.getDescription());
        assertNotNull(space.getCustom());
        assertEquals(STATUS_ACTIVE, space.getStatus());
        assertEquals(TYPE_HUMAN, space.getType());

    }

    @Test
    public void updateSpace_passing_full_object_happyPath() throws PubNubException {
        //given
        String updatedName = "updatedName" + randomName();
        String updatedDescription = "updatedDescription" + randomName();
        Map<String, Object> updateCustom = updatedCustomSpaceObject();
        String updatedStatus = "updatedStatus" + STATUS_ACTIVE;
        String updatedType = "updatedType" + TYPE_HUMAN;

        pubNubUnderTest.createSpace(new SpaceId(randomSpaceId))
                .name(randomName)
                .description(randomDescription)
                .custom(customSpaceObject())
                .includeCustom(true)
                .status(STATUS_ACTIVE)
                .type(TYPE_HUMAN)
                .sync();

        //when
        Space updatedSpace = pubNubUnderTest.updateSpace(new SpaceId(randomSpaceId))
                .name(updatedName)
                .description(updatedDescription)
                .custom(updateCustom)
                .status(updatedStatus)
                .type(updatedType)
                .sync();

        //then
        assertNotNull(updatedSpace);
        assertEquals(randomSpaceId, updatedSpace.getId());
        assertEquals(updatedName, updatedSpace.getName());
        assertEquals(updatedDescription, updatedSpace.getDescription());
        assertEquals(updatedDescription, updatedSpace.getDescription());
        assertEquals("\"val1_updated\"", ((JsonObject) updatedSpace.getCustom()).getAsJsonObject().get("param1").toString());
        assertEquals("\"val2_updated\"", ((JsonObject) updatedSpace.getCustom()).getAsJsonObject().get("param2").toString());
        assertEquals("\"added\"", ((JsonObject) updatedSpace.getCustom()).getAsJsonObject().get("param3").toString());
        assertEquals(updatedStatus, updatedSpace.getStatus());
        assertEquals(updatedType, updatedSpace.getType());

        Space space = pubNubUnderTest.fetchSpace(new SpaceId(randomSpaceId))
                .sync();

        assertNotNull(space);
        assertEquals(randomSpaceId, space.getId());
        assertEquals(updatedName, space.getName());
        assertEquals(updatedDescription, space.getDescription());
        assertNotNull(space.getCustom());
        assertEquals(updatedStatus, space.getStatus());
        assertEquals(updatedType, space.getType());
    }

    @Test(expected = PubNubException.class)
    public void updateSpace_should_throw_exception_when_updating_space_that_does_not_exist() throws PubNubException {
        //given
        String updatedName = "updatedName" + randomName();

        //when
        pubNubUnderTest.updateSpace(new SpaceId(randomSpaceId))
                .name(updatedName)
                .sync();

        //then

    }

    @Test
    public void upsertSpaceHappyPath_newSpaceCreated() throws PubNubException {
        //given

        //when
        Space space = pubNubUnderTest.upsertSpace(new SpaceId(randomSpaceId))
                .name(randomName)
                .description(randomDescription)
                .custom(customSpaceObject())
                .includeCustom(true)
                .status(STATUS_ACTIVE)
                .type(TYPE_HUMAN)
                .sync();

        //then
        assertNotNull(space);
        assertEquals(randomSpaceId, space.getId());
        assertEquals(randomName, space.getName());
        assertEquals(randomDescription, space.getDescription());
        assertNotNull(space.getCustom());
        assertEquals(STATUS_ACTIVE, space.getStatus());
        assertEquals(TYPE_HUMAN, space.getType());
    }

    @Test
    public void upsertSpaceHappyPath_existingSpaceUpdated() throws PubNubException {
        //given
        String updatedName = "updatedName" + randomName();
        String updatedDescription = "updatedDescription" + randomName();
        Map<String, Object> updateCustom = updatedCustomSpaceObject();
        String updatedStatus = "updatedStatus" + STATUS_ACTIVE;
        String updatedType = "updatedType" + TYPE_HUMAN;

        pubNubUnderTest.createSpace(new SpaceId(randomSpaceId))
                .name(randomName)
                .description(randomDescription)
                .custom(customSpaceObject())
                .includeCustom(true)
                .status(STATUS_ACTIVE)
                .type(TYPE_HUMAN)
                .sync();

        //when
        Space spaceAfterUpsert = pubNubUnderTest.upsertSpace(new SpaceId(randomSpaceId))
                .name(updatedName)
                .description(updatedDescription)
                .custom(updateCustom)
                .status(updatedStatus)
                .type(updatedType)
                .sync();

        //then
        assertNotNull(spaceAfterUpsert);
        assertEquals(randomSpaceId, spaceAfterUpsert.getId());
        assertEquals(updatedName, spaceAfterUpsert.getName());
        assertEquals(updatedDescription, spaceAfterUpsert.getDescription());
        assertEquals(updatedDescription, spaceAfterUpsert.getDescription());
        assertEquals("\"val1_updated\"", ((JsonObject) spaceAfterUpsert.getCustom()).getAsJsonObject().get("param1").toString());
        assertEquals("\"val2_updated\"", ((JsonObject) spaceAfterUpsert.getCustom()).getAsJsonObject().get("param2").toString());
        assertEquals("\"added\"", ((JsonObject) spaceAfterUpsert.getCustom()).getAsJsonObject().get("param3").toString());
        assertEquals(updatedStatus, spaceAfterUpsert.getStatus());
        assertEquals(updatedType, spaceAfterUpsert.getType());

        Space space = pubNubUnderTest.fetchSpace(new SpaceId(randomSpaceId))
                .sync();

        assertNotNull(space);
        assertEquals(randomSpaceId, space.getId());
        assertEquals(updatedName, space.getName());
        assertEquals(updatedDescription, space.getDescription());
        assertNotNull(space.getCustom());
        assertEquals(updatedStatus, space.getStatus());
        assertEquals(updatedType, space.getType());
    }

    @After
    public void tearDown() throws Exception {
        pubNubUnderTest.removeSpace(new SpaceId(randomSpaceId)).sync();
    }

    private String getRandomSpaceIdValue() {
        return "spaceId" + new Random().nextInt(100000);
    }

    private static String randomDescription() {
        return RandomStringUtils.randomAlphabetic(50, 160);
    }
}
