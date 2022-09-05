package com.pubnub.api.integration.objects_vsp.user;

import com.google.gson.JsonObject;
import com.pubnub.api.PubNubException;
import com.pubnub.api.UserId;
import com.pubnub.api.endpoints.objects_vsp.user.FetchUser;
import com.pubnub.api.integration.objects.ObjectsApiBaseIT;
import com.pubnub.api.integration.objects.uuid.UUIDMetadataIT;
import com.pubnub.api.models.consumer.objects_vsp.user.CreateUserResult;
import com.pubnub.api.models.consumer.objects_vsp.user.FetchUserResult;
import com.pubnub.api.models.consumer.objects_vsp.user.RemoveUserResult;
import com.pubnub.api.models.consumer.objects_vsp.user.UpdateUserResult;
import com.pubnub.api.models.consumer.objects_vsp.user.UpsertUserResult;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
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
        final CreateUserResult createUserResult = pubNubUnderTest.createUser()
                .userId(new UserId(randomUserId))
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
        assertNotNull(createUserResult);
        assertEquals(HttpStatus.SC_OK, createUserResult.getStatus());
        assertEquals(randomUserId, createUserResult.getData().getId());
        assertEquals(randomName, createUserResult.getData().getName());
        assertEquals(randomEmail, createUserResult.getData().getEmail());
        assertEquals(randomProfileUrl, createUserResult.getData().getProfileUrl());
        assertEquals(randomExternalId, createUserResult.getData().getExternalId());
        assertNotNull(createUserResult.getData().getCustom());
        assertEquals(STATUS_ACTIVE, createUserResult.getData().getStatus());
        assertEquals(TYPE_HUMAN, createUserResult.getData().getType());
    }

    @Test
    public void fetchUserHappyPath() throws PubNubException {
        //given
        final CreateUserResult createUserResult = pubNubUnderTest.createUser()
                .userId(new UserId(randomUserId))
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
        FetchUserResult fetchUserResult = pubNubUnderTest.fetchUser()
                .userId(new UserId(randomUserId))
                .includeCustom(true)
                .sync();

        assertNotNull(fetchUserResult);
        assertEquals(HttpStatus.SC_OK, fetchUserResult.getStatus());
        assertEquals(randomUserId, fetchUserResult.getData().getId());
        assertEquals(randomName, fetchUserResult.getData().getName());
        assertEquals(randomEmail, fetchUserResult.getData().getEmail());
        assertEquals(randomProfileUrl, fetchUserResult.getData().getProfileUrl());
        assertEquals(randomExternalId, fetchUserResult.getData().getExternalId());
        assertNotNull(fetchUserResult.getData().getCustom());
        assertEquals(STATUS_ACTIVE, fetchUserResult.getData().getStatus());
        assertEquals(TYPE_HUMAN, fetchUserResult.getData().getType());
    }


    @Test
    public void removeUserHappyPath() throws PubNubException {
        // given
        pubNubUnderTest.createUser()
                .userId(new UserId(randomUserId))
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
        RemoveUserResult removeUserResult = pubNubUnderTest.removeUser()
                .userId(new UserId(randomUserId))
                .sync();

        //then
        assertNotNull(removeUserResult);
        assertEquals(HttpStatus.SC_OK, removeUserResult.getStatus());

        //verify if user not exist
        FetchUser fetchUser = pubNubUnderTest.fetchUser()
                .userId(new UserId(randomUserId))
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

        pubNubUnderTest.createUser()
                .userId(new UserId(randomUserId))
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
        UpdateUserResult updateUserResult = pubNubUnderTest.updateUser()
                .userId(new UserId(randomUserId))
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
        assertNotNull(updateUserResult);
        assertEquals(HttpStatus.SC_OK, updateUserResult.getStatus());
        assertEquals(randomUserId, updateUserResult.getData().getId());
        assertEquals(updatedName, updateUserResult.getData().getName());
        assertEquals(updatedEmail, updateUserResult.getData().getEmail());
        assertEquals(updatedProfileUrl, updateUserResult.getData().getProfileUrl());
        assertEquals(updatedExternalId, updateUserResult.getData().getExternalId());
        assertEquals("\"val1_updated\"", ((JsonObject) updateUserResult.getData().getCustom()).getAsJsonObject().get("param1").toString());
        assertEquals("\"val2_updated\"", ((JsonObject) updateUserResult.getData().getCustom()).getAsJsonObject().get("param2").toString());
        assertEquals("\"added\"", ((JsonObject) updateUserResult.getData().getCustom()).getAsJsonObject().get("param3").toString());
        assertEquals(updatedStatus, updateUserResult.getData().getStatus());
        assertEquals(updatedType, updateUserResult.getData().getType());

        FetchUserResult fetchUserResult = pubNubUnderTest.fetchUser()
                .userId(new UserId(randomUserId))
                .includeCustom(true)
                .sync();

        assertNotNull(fetchUserResult);
        assertEquals(HttpStatus.SC_OK, fetchUserResult.getStatus());
        assertEquals(randomUserId, fetchUserResult.getData().getId());
        assertEquals(updatedName, fetchUserResult.getData().getName());
        assertEquals(updatedEmail, fetchUserResult.getData().getEmail());
        assertEquals(updatedProfileUrl, fetchUserResult.getData().getProfileUrl());
        assertEquals(updatedExternalId, fetchUserResult.getData().getExternalId());
        assertNotNull(fetchUserResult.getData().getCustom());
        assertEquals(updatedStatus, fetchUserResult.getData().getStatus());
        assertEquals(updatedType, fetchUserResult.getData().getType());
    }

    @Test
    public void updateUser_creates_user_if_user_doesnot_exist() throws PubNubException {
        // given
        String updatedName = "updatedName" + randomName();

        // when
        UpdateUserResult updateUserResult = pubNubUnderTest.updateUser()
                .userId(new UserId(randomUserId))
                .name(updatedName)
                .sync();

        //then
        assertEquals(HttpStatus.SC_OK, updateUserResult.getStatus());
        assertEquals(randomUserId, updateUserResult.getData().getId());
        assertEquals(updatedName, updateUserResult.getData().getName());
    }

    @Test
    public void upsertUserHappyPath_newUserCreated() throws PubNubException {
        //given

        //when
        UpsertUserResult upsertUserResult = pubNubUnderTest.upsertUser()
                .userId(new UserId(randomUserId))
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
        assertNotNull(upsertUserResult);
        assertEquals(HttpStatus.SC_OK, upsertUserResult.getStatus());
        assertEquals(randomUserId, upsertUserResult.getData().getId());
        assertEquals(randomName, upsertUserResult.getData().getName());
        assertEquals(randomEmail, upsertUserResult.getData().getEmail());
        assertEquals(randomProfileUrl, upsertUserResult.getData().getProfileUrl());
        assertEquals(randomExternalId, upsertUserResult.getData().getExternalId());
        assertNotNull(upsertUserResult.getData().getCustom());
        assertEquals(STATUS_ACTIVE, upsertUserResult.getData().getStatus());
        assertEquals(TYPE_HUMAN, upsertUserResult.getData().getType());

    }

    @Test
    public void upsertUserHappyPath_currentUserUpdated() throws PubNubException {
        //given
        String updatedName = "updatedName" + randomName();
        String updatedEmail = "updatedEmail" + randomEmail();
        String updatedProfileUrl = "updatedProfileUrl" + randomProfileUrl();
        String updatedExternalId = "updatedExternalId" + randomExternalId();
        Map<String, Object> updateCustom = updatedCustomUserObject();
        String updatedStatus = "updatedStatus" + STATUS_ACTIVE;
        String updatedType = "updatedType" + TYPE_HUMAN;

        pubNubUnderTest.createUser()
                .userId(new UserId(randomUserId))
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
        UpsertUserResult upsertUserResult = pubNubUnderTest.upsertUser()
                .userId(new UserId(randomUserId))
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
        assertNotNull(upsertUserResult);
        assertEquals(HttpStatus.SC_OK, upsertUserResult.getStatus());
        assertEquals(randomUserId, upsertUserResult.getData().getId());
        assertEquals(updatedName, upsertUserResult.getData().getName());
        assertEquals(updatedEmail, upsertUserResult.getData().getEmail());
        assertEquals(updatedProfileUrl, upsertUserResult.getData().getProfileUrl());
        assertEquals(updatedExternalId, upsertUserResult.getData().getExternalId());
        assertEquals("\"val1_updated\"", ((JsonObject) upsertUserResult.getData().getCustom()).getAsJsonObject().get("param1").toString());
        assertEquals("\"val2_updated\"", ((JsonObject) upsertUserResult.getData().getCustom()).getAsJsonObject().get("param2").toString());
        assertEquals("\"added\"", ((JsonObject) upsertUserResult.getData().getCustom()).getAsJsonObject().get("param3").toString());
        assertEquals(updatedStatus, upsertUserResult.getData().getStatus());
        assertEquals(updatedType, upsertUserResult.getData().getType());
    }

    @After
    public void tearDown() throws Exception {
        pubNubUnderTest.removeUser().userId(new UserId(randomUserId)).sync();
    }

    private String getRandomUserIdValue() {
        return "userId" + new Random().nextInt(100000);
    }

    private static List<String> randomTestUUIDs() {
        final List<String> uuids = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_RANDOM_TEST_UUIDS; i++) {
            uuids.add(UUID.randomUUID().toString());
        }
        return uuids;
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
