package com.lourish.wpoffer.it;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import com.lourish.wpoffer.domain.Offer;

public class ListOffersIntegTest extends ApiIntegrationTestBase {
    @Test
    public void listOffersReturnsExistingOffers() {
        // given
        final Offer newOffer = createNewOffer();

        // when
        final List<Offer> offers = getClient().list();

        // then
        assertThat(offers).extracting(Offer::getId).contains(newOffer.getId());
    }

    @Test
    public void listOffersReturnsEmptyListIfNone() {
        // given
        offerRepo.deleteAll();

        // when
        final List<Offer> offers = getClient().list();

        // then
        assertThat(offers).isEmpty();
    }

}
