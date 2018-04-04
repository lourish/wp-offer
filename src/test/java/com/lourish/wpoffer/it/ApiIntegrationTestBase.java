package com.lourish.wpoffer.it;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.lourish.it.EmbeddedRedis;
import com.lourish.wpoffer.WpOfferApplication;
import com.lourish.wpoffer.domain.Offer;
import com.lourish.wpoffer.domain.Offers;
import com.lourish.wpoffer.repository.redis.OfferRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "server.servlet.context-path=/", classes = WpOfferApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = { EmbeddedRedis.class })
public abstract class ApiIntegrationTestBase {
    @LocalServerPort
    private int port;

    @Autowired
    protected OfferRepository offerRepo;

    protected OfferApiClient getClient() {
        return new OfferApiClient("localhost", port);
    }

    protected Offer createNewOffer() {
        final Offer offer = Offers.offerWithoutId();
        final Offer newOffer = offerRepo.save(offer);
        return newOffer;
    }

}
