package com.lourish.wpoffer.service;

import static com.lourish.wpoffer.assertj.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.lourish.wpoffer.domain.Offer;
import com.lourish.wpoffer.web.service.IdGenerator;
import com.lourish.wpoffer.web.service.OfferCommandService;

public class OfferCommandServiceTest {

    private static final String STUB_ID = "an-id";
    private OfferCommandService service;
    private IdGenerator idGenerator = new StubIdGenerator(STUB_ID);
    private StubOfferRepository repository = new StubOfferRepository();

    @Before
    public void setUp() {
        service = new OfferCommandService(idGenerator, repository);
    }

    @Test
    public void createOfferGeneratesIdAndSaves() throws Exception {
        // given
        final Offer offer = new Offer("desc", new BigDecimal("1.23"), "cur");

        // when
        Offer createdOffer = service.createOffer(offer);

        // then
        Offer savedOffer = repository.popLastSavedOffer();
        assertThat(savedOffer).hasId(STUB_ID);
        assertThat(createdOffer).hasId(STUB_ID);
    }
}
