package com.lourish.wpoffer.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

import com.lourish.wpoffer.domain.Offer;
import com.lourish.wpoffer.domain.Offers;
import com.lourish.wpoffer.web.service.Md5IdGenerator;

public class Md5IdGeneratorTest {

    private Md5IdGenerator idGenerator = new Md5IdGenerator();

    @Test
    public void generateIdGeneratesIdFromOffer() {

        Offer offer = Offers.offerWithoutId();
        String id = idGenerator.generateId(offer);
        assertThat(id).isNotBlank();
    }

    @Test
    public void generateIdGeneratesDifferentIdsWhenDescriptionPriceAndCurrenyChange() {
        // given
        Offer offer = Offers.offerWithoutId();
        String originalId = idGenerator.generateId(offer);

        // when
        String changedDescriptionId = idGenerator.generateId(offer.withDescription("new desc"));
        String changedPriceId = idGenerator.generateId(offer.withPrice(BigDecimal.TEN));
        String changedCurrencyId = idGenerator.generateId(offer.withCurrency("new cur"));

        // then
        assertThat(originalId).isNotEqualTo(changedDescriptionId)
                .isNotEqualTo(changedPriceId)
                .isNotEqualTo(changedCurrencyId);
    }

}
