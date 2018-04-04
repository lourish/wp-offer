package com.lourish.wpoffer.web.api;

import static com.lourish.wpoffer.assertj.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.springframework.http.MediaType;

import com.lourish.wpoffer.domain.Offer;
import com.lourish.wpoffer.service.OfferCommandService;
import com.lourish.wpoffer.test.JsonTemplates;

public class UpdateOfferRestControllerTest extends AbstractDocumentingApiControllerTest {

    @Mock
    private OfferCommandService offerService;

    @Captor
    private ArgumentCaptor<Offer> offerCaptor;

    private UpdateOfferRestController controller;

    @Before
    public void setUp() {
        controller = new UpdateOfferRestController(offerService);
        setUpDocumentingMockMvc(controller);
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
        given(offerService.createOffer(any(Offer.class))).willReturn(returnedOffer);

        // when
        mockMvc.perform(post("/offers").content(JsonTemplates.offer(description, price, currency, expires))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))

                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("desc", is(description)))
                .andExpect(jsonPath("price", is(price.doubleValue())))
                .andExpect(jsonPath("currency", is(currency)))
                .andDo(print())
                .andDo(document("create-offer", requestFields(DocumentationSnippets.OFFER_REQUEST_FIELD_DESCRIPTORS),
                        responseFields(DocumentationSnippets.OFFER_RESPONSE_FIELD_DESCRIPTORS)));

        verify(offerService).createOffer(offerCaptor.capture());
        assertThat(offerCaptor.getValue()).hasDesc(description)
                .hasPrice(price)
                .hasCurrency(currency)
                .hasExpires(expires);
    }

    @Test
    public void deleteCancelsOffer() throws Exception {
        mockMvc.perform(delete("/offers/id/{id}", "an-offer-id"))
                // then
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("cancel-offer", pathParameters(parameterWithName("id")
                        .description("id of offer to cancel"))));

    }

}
