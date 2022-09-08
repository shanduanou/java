package com.pubnub.api.integration.objects_vsp.user;

import com.google.gson.JsonObject;
import com.pubnub.api.PubNubException;
import com.pubnub.api.UserId;
import com.pubnub.api.endpoints.objects_vsp.user.FetchUser;
import com.pubnub.api.integration.objects.ObjectsApiBaseIT;
import com.pubnub.api.integration.objects.uuid.UUIDMetadataIT;
import com.pubnub.api.models.consumer.objects_vsp.user.RemoveUserResult;
import com.pubnub.api.models.consumer.objects_vsp.user.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Random;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class UserIT extends ObjectsApiBaseIT {
    private static final Logger LOG = LoggerFactory.getLogger(UUIDMetadataIT.class);
    private static final int NUMBER_OF_RANDOM_TEST_UUIDS = 10;


    private final String randomUserId = getRandomUserIdValue();
    private final String randomName = randomName();
    private final String randomEmail = randomEmail();
    private final String randomProfileUrl = randomProfileUrl();
    private final String randomExternalId = randomExternalId();

    @Test
    public void createUserHappyPath() throws PubNubException {
        //given

        //when
        final User user = pubNubUnderTest.createUser(new UserId(randomUserId))
                .name(randomName)
                .email(randomEmail)
                .profileUrl(randomProfileUrl)
                .externalId(randomExternalId)
                .custom(customUserObject())
                .includeCustom(true)
                .status(STATUS_ACTIVE)
                .type(TYPE_HUMAN)
                .sync();

        //then
        assertNotNull(user);
        assertEquals(randomUserId, user.getId());
        assertEquals(randomName, user.getName());
        assertEquals(randomEmail, user.getEmail());
        assertEquals(randomProfileUrl, user.getProfileUrl());
        assertEquals(randomExternalId, user.getExternalId());
        assertNotNull(user.getCustom());
        assertEquals(STATUS_ACTIVE, user.getStatus());
        assertEquals(TYPE_HUMAN, user.getType());
    }

    @Test
    public void fetchUserHappyPath() throws PubNubException {
        //given
        pubNubUnderTest.createUser(new UserId(randomUserId))
                .name(randomName)
                .email(randomEmail)
                .profileUrl(randomProfileUrl)
                .externalId(randomExternalId)
                .custom(customUserObject())
                .includeCustom(true)
                .status(STATUS_ACTIVE)
                .type(TYPE_HUMAN)
                .sync();

        //when
        User user = pubNubUnderTest.fetchUser(new UserId(randomUserId))
                .includeCustom(true)
                .sync();

        assertNotNull(user);
        assertEquals(randomUserId, user.getId());
        assertEquals(randomName, user.getName());
        assertEquals(randomEmail, user.getEmail());
        assertEquals(randomProfileUrl, user.getProfileUrl());
        assertEquals(randomExternalId, user.getExternalId());
        assertNotNull(user.getCustom());
        assertEquals(STATUS_ACTIVE, user.getStatus());
        assertEquals(TYPE_HUMAN, user.getType());
    }


    @Test
    public void removeUserHappyPath() throws PubNubException {
        // given
        pubNubUnderTest.createUser(new UserId(randomUserId))
                .name(randomName)
                .email(randomEmail)
                .profileUrl(randomProfileUrl)
                .externalId(randomExternalId)
                .custom(customUserObject())
                .includeCustom(true)
                .status(STATUS_ACTIVE)
                .type(TYPE_HUMAN)
                .sync();

        //when
        RemoveUserResult removeUserResult = pubNubUnderTest.removeUser(new UserId(randomUserId))
                .sync();

        //then
        assertNotNull(removeUserResult);
        assertEquals(HttpStatus.SC_OK, removeUserResult.getStatus());

        //verify if user not exist
        FetchUser fetchUser = pubNubUnderTest.fetchUser(new UserId(randomUserId))
                .includeCustom(true);

        PubNubException exception = assertThrows(PubNubException.class, () -> fetchUser.sync());

        assertTrue(exception.getMessage().contains("Requested resource not found"));
    }

    @Test
    public void updateUser_passing_full_object_happyPath() throws PubNubException {
        // given
        String updatedName = "updatedName" + randomName();
        String updatedEmail = "updatedEmail" + randomEmail();
        String updatedProfileUrl = "updatedProfileUrl" + randomProfileUrl();
        String updatedExternalId = "updatedExternalId" + randomExternalId();
        Map<String, Object> updateCustom = updatedCustomUserObject();
        String updatedStatus = "updatedStatus" + STATUS_ACTIVE;
        String updatedType = "updatedType" + TYPE_HUMAN;

        pubNubUnderTest.createUser(new UserId(randomUserId))
                .name(randomName)
                .email(randomEmail)
                .profileUrl(randomProfileUrl)
                .externalId(randomExternalId)
                .custom(customUserObject())
                .includeCustom(true)
                .status(STATUS_ACTIVE)
                .type(TYPE_HUMAN)
                .sync();

        // when
        User userAfterUpdate = pubNubUnderTest.updateUser(new UserId(randomUserId))
                .name(updatedName)
                .email(updatedEmail)
                .profileUrl(updatedProfileUrl)
                .externalId(updatedExternalId)
                .custom(updateCustom)
                .includeCustom(true)
                .status(updatedStatus)
                .type(updatedType)
                .sync();

        // then
        assertNotNull(userAfterUpdate);
        assertEquals(randomUserId, userAfterUpdate.getId());
        assertEquals(updatedName, userAfterUpdate.getName());
        assertEquals(updatedEmail, userAfterUpdate.getEmail());
        assertEquals(updatedProfileUrl, userAfterUpdate.getProfileUrl());
        assertEquals(updatedExternalId, userAfterUpdate.getExternalId());
        assertEquals("\"val1_updated\"", ((JsonObject) userAfterUpdate.getCustom()).getAsJsonObject().get("param1").toString());
        assertEquals("\"val2_updated\"", ((JsonObject) userAfterUpdate.getCustom()).getAsJsonObject().get("param2").toString());
        assertEquals("\"added\"", ((JsonObject) userAfterUpdate.getCustom()).getAsJsonObject().get("param3").toString());
        assertEquals(updatedStatus, userAfterUpdate.getStatus());
        assertEquals(updatedType, userAfterUpdate.getType());

        User user = pubNubUnderTest.fetchUser(new UserId(randomUserId))
                .includeCustom(true)
                .sync();

        assertNotNull(user);
        assertEquals(randomUserId, user.getId());
        assertEquals(updatedName, user.getName());
        assertEquals(updatedEmail, user.getEmail());
        assertEquals(updatedProfileUrl, user.getProfileUrl());
        assertEquals(updatedExternalId, user.getExternalId());
        assertNotNull(user.getCustom());
        assertEquals(updatedStatus, user.getStatus());
        assertEquals(updatedType, user.getType());
    }

    @Test(expected = PubNubException.class)
    public void updateUser_should_throw_exception_when_updating_user_that_does_not_exist() throws PubNubException {
        // given
        String updatedName = "updatedName" + randomName();

        // when
        User user = pubNubUnderTest.updateUser(new UserId(randomUserId))
                .name(updatedName)
                .sync();

        //then
        assertEquals(randomUserId, user.getId());
        assertEquals(updatedName, user.getName());
    }

    @Test
    public void upsertUserHappyPath_newUserCreated() throws PubNubException {
        //given

        //when
        User user = pubNubUnderTest.upsertUser(new UserId(randomUserId))
                .name(randomName)
                .email(randomEmail)
                .profileUrl(randomProfileUrl)
                .externalId(randomExternalId)
                .custom(customUserObject())
                .includeCustom(true)
                .status(STATUS_ACTIVE)
                .type(TYPE_HUMAN)
                .sync();

        //then
        assertNotNull(user);
        assertEquals(randomUserId, user.getId());
        assertEquals(randomName, user.getName());
        assertEquals(randomEmail, user.getEmail());
        assertEquals(randomProfileUrl, user.getProfileUrl());
        assertEquals(randomExternalId, user.getExternalId());
        assertNotNull(user.getCustom());
        assertEquals(STATUS_ACTIVE, user.getStatus());
        assertEquals(TYPE_HUMAN, user.getType());

    }

    @Test
    public void upsertUserHappyPath_existingUserUpdated() throws PubNubException {
        //given
        String updatedName = "updatedName" + randomName();
        String updatedEmail = "updatedEmail" + randomEmail();
        String updatedProfileUrl = "updatedProfileUrl" + randomProfileUrl();
        String updatedExternalId = "updatedExternalId" + randomExternalId();
        Map<String, Object> updateCustom = updatedCustomUserObject();
        String updatedStatus = "updatedStatus" + STATUS_ACTIVE;
        String updatedType = "updatedType" + TYPE_HUMAN;

        pubNubUnderTest.createUser(new UserId(randomUserId))
                .name(randomName)
                .email(randomEmail)
                .profileUrl(randomProfileUrl)
                .externalId(randomExternalId)
                .custom(customUserObject())
                .includeCustom(true)
                .status(STATUS_ACTIVE)
                .type(TYPE_HUMAN)
                .sync();

        //when
        User user = pubNubUnderTest.upsertUser(new UserId(randomUserId))
                .name(updatedName)
                .email(updatedEmail)
                .profileUrl(updatedProfileUrl)
                .externalId(updatedExternalId)
                .custom(updateCustom)
                .includeCustom(true)
                .status(updatedStatus)
                .type(updatedType)
                .sync();

        //then
        assertNotNull(user);
        assertEquals(randomUserId, user.getId());
        assertEquals(updatedName, user.getName());
        assertEquals(updatedEmail, user.getEmail());
        assertEquals(updatedProfileUrl, user.getProfileUrl());
        assertEquals(updatedExternalId, user.getExternalId());
        assertEquals("\"val1_updated\"", ((JsonObject) user.getCustom()).getAsJsonObject().get("param1").toString());
        assertEquals("\"val2_updated\"", ((JsonObject) user.getCustom()).getAsJsonObject().get("param2").toString());
        assertEquals("\"added\"", ((JsonObject) user.getCustom()).getAsJsonObject().get("param3").toString());
        assertEquals(updatedStatus, user.getStatus());
        assertEquals(updatedType, user.getType());
    }

    @After
    public void tearDown() throws Exception {
        pubNubUnderTest.removeUser(new UserId(randomUserId)).sync();
    }

    private String getRandomUserIdValue() {
        return "userId" + new Random().nextInt(100000);
    }

    private String randomExternalId() {
        return UUID.randomUUID().toString();
    }

    private String randomEmail() {
        return RandomStringUtils.randomAlphabetic(6) + "@example.com";
    }

    private String randomProfileUrl() {
        return "http://" + RandomStringUtils.randomAlphabetic(5, 15) + ".com";
    }
}
