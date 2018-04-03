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
import com.lourish.wpoffer.web.service.OfferCommandService;

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
        String id = "an-id";
        String description = "a description";
        BigDecimal price = new BigDecimal("1.23");
        String currency = "GBP";
        Offer returnedOffer = new Offer(id, description, price, currency);
        given(offerCommandService.createOffer(any(Offer.class))).willReturn(returnedOffer);

        // when
        mockMvc.perform(post("/offers").content(String.format("{\"desc\": \"%s\","
                + "\"price\": \"%s\","
                + "\"currency\":\"%s\"}", description, price.toPlainString(), currency))
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
                .hasCurrency(currency);
    }

}
