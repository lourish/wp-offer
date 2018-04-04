package com.lourish.wpoffer.domain;

import static com.lourish.wpoffer.assertj.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

import org.junit.Test;

public class OfferTest {
    private static final int MILLIS_IN_SECONDS = 1000;

    @Test
    public void constructionSetsTimeToLive() throws Exception {
        // given
        final LocalDateTime testStartTime = LocalDateTime.now();
        final LocalDateTime expires = testStartTime.plusDays(1);

        // when
        final Offer offer = new Offer("desc", BigDecimal.ONE, "currency", expires);

        // then
        verifyTtl(testStartTime, offer);

    }

    @Test
    public void withDescriptionCreatesNewOfferWithDescription() {
        // given
        final Offer originalOffer = Offers.offerWithId();

        // when
        final Offer newOffer = originalOffer.withDescription("newDesc");

        // then
        assertThat(newOffer).hasDesc("newDesc")
                .hasCurrency(originalOffer.getCurrency())
                .hasPrice(originalOffer.getPrice())
                .hasExpires(originalOffer.getExpires())
                .hasId(originalOffer.getId())
                .hasTtl(originalOffer.getTtl());
    }

    @Test
    public void withIdCreatesNewOfferWithId() {
        // given
        final Offer originalOffer = Offers.offerWithId();

        // when
        final Offer newOffer = originalOffer.withId("newId");

        // then
        assertThat(newOffer).hasDesc(originalOffer.getDesc())
                .hasCurrency(originalOffer.getCurrency())
                .hasPrice(originalOffer.getPrice())
                .hasExpires(originalOffer.getExpires())
                .hasId("newId")
                .hasTtl(originalOffer.getTtl());
    }

    @Test
    public void withPriceCreatesNewOfferWithPrice() {
        // given
        final Offer originalOffer = Offers.offerWithId();

        // when
        final BigDecimal newPrice = originalOffer.getPrice().add(BigDecimal.ONE);
        final Offer newOffer = originalOffer.withPrice(newPrice);

        // then
        assertThat(newOffer).hasDesc(originalOffer.getDesc())
                .hasCurrency(originalOffer.getCurrency())
                .hasPrice(newPrice)
                .hasExpires(originalOffer.getExpires())
                .hasId(originalOffer.getId())
                .hasTtl(originalOffer.getTtl());
    }

    @Test
    public void withCurrencyCreatesNewOfferWithCurrency() {
        // given
        final Offer originalOffer = Offers.offerWithId();

        // when
        final Offer newOffer = originalOffer.withCurrency("newCur");

        // then
        assertThat(newOffer).hasDesc(originalOffer.getDesc())
                .hasCurrency("newCur")
                .hasPrice(originalOffer.getPrice())
                .hasExpires(originalOffer.getExpires())
                .hasId(originalOffer.getId())
                .hasTtl(originalOffer.getTtl());
    }

    private void verifyTtl(final LocalDateTime testStartTime, final Offer actualOffer) {
        final LocalDateTime expires = actualOffer.getExpires();
        final long ceilTtlSecs = Duration.between(testStartTime, expires).toMillis() / MILLIS_IN_SECONDS;
        final long floorTtlSecs = Duration.between(LocalDateTime.now(), expires).toMillis() / MILLIS_IN_SECONDS - 1;
        final long ttlSecs = actualOffer.getTtl() / MILLIS_IN_SECONDS;

        assertThat(ttlSecs).isBetween(floorTtlSecs, ceilTtlSecs);
    }

}
