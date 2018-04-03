package com.lourish.wpoffer.web.api;

import static com.lourish.wpoffer.assertj.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.lourish.wpoffer.domain.Offer;
import com.lourish.wpoffer.service.OfferCommandService;
import com.lourish.wpoffer.test.JsonTemplates;

public class CreateOfferRestControllerTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);

    private MockMvc mockMvc;

    @Mock
    private OfferCommandService offerCommandService;

    @Captor
    private ArgumentCaptor<Offer> offerCaptor;

    private CreateOfferRestController createOfferController;

    @Before
    public void setUp() {
        createOfferController = new CreateOfferRestController(offerCommandService);
        mockMvc = MockMvcBuilders.standaloneSetup(createOfferController).build();
    }

    @Test
    public void postGivesBadRequestStatusOnInvalidRequestParams() throws Exception {
        mockMvc.perform(post("/offers").param("desc", "")
                .param("price", "-1.23"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void postCreatesOffer() throws Exception {
        // given
        final String id = "an-id";
        final String description = "a description";
        final BigDecimal price = new BigDecimal("1.23");
        final String currency = "GBP";
        final LocalDateTime expires = LocalDateTime.now().plusHours(1);
        final Offer returnedOffer = new Offer(id, description, price, currency, expires);
        given(offerCommandService.createOffer(any(Offer.class))).willReturn(returnedOffer);

        // when
        mockMvc.perform(post("/offers").content(JsonTemplates.offer(description, price, currency, expires))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))

                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("desc", is(description)))
                .andExpect(jsonPath("price", is(1.23)))
                .andExpect(jsonPath("currency", is(currency)));

        verify(offerCommandService).createOffer(offerCaptor.capture());
        assertThat(offerCaptor.getValue()).hasDesc(description)
                .hasPrice(price)
                .hasCurrency(currency)
                .hasExpires(expires);
    }

}
