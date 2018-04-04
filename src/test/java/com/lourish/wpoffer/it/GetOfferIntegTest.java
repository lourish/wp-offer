package com.lourish.wpoffer.it;

import static com.lourish.wpoffer.assertj.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import com.lourish.wpoffer.domain.Offer;

public class GetOfferIntegTest extends ApiIntegrationTestBase {
    @Test
    public void getExistingOffer() {
        // given
        final Offer offer = createNewOffer();

        // when
        final Offer returnedOffer = getClient().getById(offer.getId());

        // then
        assertThat(returnedOffer).hasDesc(offer.getDesc())
                .hasCurrency(offer.getCurrency())
                .hasPrice(offer.getPrice())
                .hasExpires(offer.getExpires());
    }

    @Test
    public void getNonExistentOfferReturnsNotFound() {
        // when
        try {
            getClient().getById("not-an-id");
        } catch (final Exception e) {
            assertThat(e).isInstanceOf(HttpClientErrorException.class);
            final HttpClientErrorException clientEx = (HttpClientErrorException) e;
            assertThat(clientEx.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }
    }

}
