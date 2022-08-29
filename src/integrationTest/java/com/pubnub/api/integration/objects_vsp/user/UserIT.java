package com.pubnub.api.integration.objects_vsp.user;

import com.pubnub.api.PubNubException;
import com.pubnub.api.UserId;
import com.pubnub.api.integration.objects.ObjectsApiBaseIT;
import com.pubnub.api.integration.objects.uuid.UUIDMetadataIT;
import com.pubnub.api.models.consumer.objects_vsp.user.CreateUserResult;
import com.pubnub.api.models.consumer.objects_vsp.user.RemoveUserResult;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserIT extends ObjectsApiBaseIT {
    private static final Logger LOG = LoggerFactory.getLogger(UUIDMetadataIT.class);
    private static final int NUMBER_OF_RANDOM_TEST_UUIDS = 10;
    public static final String STATUS_ACTIVE = "Active";
    public static final String TYPE_HUMAN = "Human";

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
                .custom(customUUIDObject())
                .includeCustom(true)
                .status(STATUS_ACTIVE)
                .type(TYPE_HUMAN)
                .sync();

        //then
        assertNotNull(createUserResult);
        assertEquals(HttpStatus.SC_OK, createUserResult.getStatus());
//        assertEquals(randomUserId, createUserResult.getData().getId()); //no UserId in response for now. Asked Dara.
        assertEquals(randomName, createUserResult.getData().getName());
        assertEquals(randomEmail, createUserResult.getData().getEmail());
        assertEquals(randomProfileUrl, createUserResult.getData().getProfileUrl());
        assertEquals(randomExternalId, createUserResult.getData().getExternalId());
        assertNotNull(createUserResult.getData().getCustom());
        assertEquals(STATUS_ACTIVE, createUserResult.getData().getStatus());
        assertEquals(TYPE_HUMAN, createUserResult.getData().getType());
    }

    @Test
    public void removeUserHappyPath() throws PubNubException {
        final CreateUserResult createUserResult = pubNubUnderTest.createUser()
                .userId(new UserId(randomUserId))
                .name(randomName)
                .email(randomEmail)
                .profileUrl(randomProfileUrl)
                .externalId(randomExternalId)
                .custom(customUUIDObject())
                .includeCustom(true)
                .status(STATUS_ACTIVE)
                .type(TYPE_HUMAN)
                .sync();


        RemoveUserResult removeUserResult = pubNubUnderTest.removeUser()
                .userId(new UserId(randomUserId))
                .sync();

        //then
        assertNotNull(removeUserResult);
        assertEquals(HttpStatus.SC_OK, removeUserResult.getStatus());
        //ToDo get user and check if not found. Waiting for get user

    }

    private String getRandomUserIdValue() {
        return "userId" + new Random().nextInt(1000);
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

    private String randomName() {
        return RandomStringUtils.randomAlphabetic(5, 10) + " " + RandomStringUtils.randomAlphabetic(5, 10);
    }

    private String randomProfileUrl() {
        return "http://" + RandomStringUtils.randomAlphabetic(5, 15) + ".com";
    }

    private Map<String, Object> customUUIDObject() {
        return new HashMap<String, Object>() {
            {
                putIfAbsent("uuid_param1", "val1");
                putIfAbsent("uuid_param2", "val2");
            }
        };
    }
}
