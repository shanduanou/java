package com.pubnub.api.endpoints.access;

import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.endpoints.TestHarness;
import com.pubnub.api.enums.PNLogVerbosity;
import com.pubnub.api.models.consumer.access_manager.v3.ChannelGrant;
import com.pubnub.api.models.consumer.access_manager.v3.ChannelGroupGrant;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.Objects;

import static com.pubnub.api.builder.PubNubErrorBuilder.PNERR_RESOURCES_MISSING;
import static com.pubnub.api.builder.PubNubErrorBuilder.PNERR_SECRET_KEY_MISSING;
import static com.pubnub.api.builder.PubNubErrorBuilder.PNERR_SUBSCRIBE_KEY_MISSING;
import static com.pubnub.api.builder.PubNubErrorBuilder.PNERR_TTL_MISSING;
import static org.junit.Assert.assertEquals;

public class GrantTokenEndpointTest extends TestHarness {

    private final PubNub pubnub = this.createPubNubInstance();

    @Before
    public void beforeEach() throws IOException {
        pubnub.getConfiguration().setSecretKey("secretKey").setIncludeInstanceIdentifier(true);
    }

    @Test
    public void test() throws PubNubException {
        PNConfiguration conf = new PNConfiguration();
        conf.setPublishKey("pub-c-395744cc-604e-410e-9074-ade176d5aac0")
                .setSubscribeKey("sub-c-fdc9b0fc-3e1a-11ea-9e28-4eda21f1b082")
                .setSecretKey("sec-c-M2YzM2MxMDgtM2EyNS00ZTkxLWEzM2ItM2E5MTJlYTc0NGFi")
                .setOrigin("ingress-tcp-pub.az1.pdx1.aws.int.ps.pn")
                .setLogVerbosity(PNLogVerbosity.BODY)
                .setSecure(false);

        PubNub pn = new PubNub(conf);
        String token = Objects.requireNonNull(pn.grantToken()
                .channels(Collections.singletonList(ChannelGrant.name("test1").write()))
                .ttl(1000)
                .sync()).getToken();

        pn.revokeToken()
                .token(token)
                .sync();
    }

    @Test
    public void validate_NoResourceSet() {
        try {
            pubnub.grantToken()
                    .ttl(1)
                    .sync();
        } catch (PubNubException e) {
            assertEquals(PNERR_RESOURCES_MISSING, e.getPubnubError().getErrorCode());
        }
    }

    @Test
    public void validate_NoTTLSet() {
        try {
            pubnub.grantToken()
                    .channels(Collections.singletonList(ChannelGrant.name("test").read()))
                    .sync();
        } catch (PubNubException e) {
            assertEquals(PNERR_TTL_MISSING, e.getPubnubError().getErrorCode());
        }
    }

    @Test
    public void validate_SecretKeyMissing() {
        try {
            createPubNubInstance().grantToken()
                    .ttl(1)
                    .channelGroups(Collections.singletonList(ChannelGroupGrant.id("test").read()))
                    .sync();
        } catch (PubNubException e) {
            assertEquals(PNERR_SECRET_KEY_MISSING, e.getPubnubError().getErrorCode());
        }
    }

    @Test
    public void validate_SubscribeKeyMissing() {
        try {
            new PubNub(new PNConfiguration().setSecretKey("secret")).grantToken()
                    .ttl(1)
                    .channelGroups(Collections.singletonList(ChannelGroupGrant.id("test").read()))
                    .sync();
        } catch (PubNubException e) {
            assertEquals(PNERR_SUBSCRIBE_KEY_MISSING, e.getPubnubError().getErrorCode());
        }
    }
}
