package com.lourish.wpoffer.it;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.lourish.wpoffer.domain.Offer;

public class CancelOfferIntegTest extends ApiIntegrationTestBase {
    @Test
    public void cancelExistingOffer() {
        // given
        final Offer newOffer = createNewOffer();

        // when
        getClient().cancel(newOffer.getId());

        // then
        assertThat(offerRepo.existsById(newOffer.getId()))
                .describedAs("Offer exists").isFalse();
    }

    @Test
    public void cancelNonExistantIdSucceeds() {
        // when
        getClient().cancel("not-an-id");

        // then expect no exception
    }

}
