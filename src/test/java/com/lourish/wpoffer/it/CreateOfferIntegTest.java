package com.lourish.wpoffer.it;

import static com.lourish.wpoffer.assertj.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Test;

import com.lourish.wpoffer.domain.Offer;

public class CreateOfferIntegTest extends ApiIntegrationTestBase {
    private static final int MILLIS_IN_SECONDS = 1000;
    private static final String DESCRIPTION = "a description";
    private static final BigDecimal PRICE = new BigDecimal("1.23");
    private static final String CURRENCY = "GBP";

    @Test
    public void createOffer() {
        // given
        final LocalDateTime expires = LocalDateTime.now().plusHours(1);

        // when
        final Offer createdOffer = makeRequestWithExpiry(expires);

        // then
        final Offer actualOffer = verifyOfferExists(createdOffer);
        assertThat(actualOffer).hasDesc(DESCRIPTION)
                .hasCurrency(CURRENCY)
                .hasPrice(PRICE)
                .hasExpires(expires);
    }

    @Test
    public void multipleCallsToCreateOfferOnlyCreateOfferOnce() {
        // given
        final LocalDateTime expires = LocalDateTime.now().plusHours(1);
        offerRepo.deleteAll();

        // when
        final Offer createdOffer = makeRequestWithExpiry(expires);
        makeRequestWithExpiry(expires);

        // then
        verifyOfferExists(createdOffer);
        assertThat(offerRepo.count()).isEqualTo(1);
    }

    @Test
    public void createOfferSetsTtl() {
        // given
        final LocalDateTime testStartTime = LocalDateTime.now();
        final LocalDateTime expires = testStartTime.plusHours(1);

        // when
        final Offer createdOffer = makeRequestWithExpiry(expires);

        // then
        final Offer actualOffer = verifyOfferExists(createdOffer);
        assertThat(actualOffer)
                .hasExpires(expires);
        verifyTtl(testStartTime, actualOffer);
    }

    private Offer makeRequestWithExpiry(final LocalDateTime expires) {
        final Offer offer = new Offer(DESCRIPTION, PRICE, CURRENCY, expires);
        final Offer createdOffer = getClient().create(offer);
        return createdOffer;
    }

    private void verifyTtl(final LocalDateTime testStartTime, final Offer actualOffer) {
        final LocalDateTime expires = actualOffer.getExpires();
        final long ceilTtlSecs = Duration.between(testStartTime, expires).toMillis() / MILLIS_IN_SECONDS;
        final long floorTtlSecs = Duration.between(LocalDateTime.now(), expires).toMillis() / MILLIS_IN_SECONDS - 1;
        final long ttlSecs = actualOffer.getTtl() / MILLIS_IN_SECONDS;

        assertThat(ttlSecs).isBetween(floorTtlSecs, ceilTtlSecs);
    }

    private Offer verifyOfferExists(final Offer createdOffer) {
        assertThat(createdOffer.getId()).isNotNull();
        final Optional<Offer> offerInRepo = offerRepo.findById(createdOffer.getId());
        assertThat(offerInRepo).isPresent();
        return offerInRepo.get();
    }

}
