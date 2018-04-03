package com.lourish.wpoffer.service;

import static com.lourish.wpoffer.assertj.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.lourish.wpoffer.domain.Offer;
import com.lourish.wpoffer.domain.Offers;
import com.lourish.wpoffer.service.OfferCommandService;

public class OfferCommandServiceTest {

    private static final String STUB_ID = "an-id";
    private OfferCommandService service;
    private final StubOfferRepository repository = new StubOfferRepository();

    @Before
    public void setUp() {
        service = new OfferCommandService(repository);
    }

    @Test
    public void createOfferGeneratesIdAndSaves() throws Exception {
        // given
        final Offer offer = Offers.offerWithoutId();

        // when
        final Offer createdOffer = service.createOffer(offer);

        // then
        final Offer savedOffer = repository.popLastSavedOffer();
        assertThat(savedOffer).hasId(STUB_ID);
        assertThat(createdOffer).hasId(STUB_ID);
    }
}
