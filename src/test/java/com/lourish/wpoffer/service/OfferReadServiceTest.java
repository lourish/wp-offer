package com.lourish.wpoffer.service;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import com.lourish.wpoffer.domain.Offer;
import com.lourish.wpoffer.domain.Offers;
import com.lourish.wpoffer.repository.redis.OfferRepository;

public class OfferReadServiceTest {
    @Rule
    public MockitoRule mockRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);

    @Mock
    private OfferRepository repository;

    private OfferReadService service;

    @Before
    public void setUp() {
        service = new OfferReadService(repository);
    }

    @Test
    public void findCurrentOffersRemovesNullElementsFromRepository() throws Exception {
        // given
        final Offer existingOffer = Offers.offerWithId();
        given(service.findCurrentOffers()).willReturn(asList(null, existingOffer, null));

        // when
        final List<Offer> currentOffers = service.findCurrentOffers();

        // then
        assertThat(currentOffers).containsOnly(existingOffer);
    }
}
