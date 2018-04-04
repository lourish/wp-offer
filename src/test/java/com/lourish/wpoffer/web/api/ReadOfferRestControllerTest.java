package com.lourish.wpoffer.web.api;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.springframework.http.MediaType;

import com.lourish.wpoffer.domain.Offer;
import com.lourish.wpoffer.domain.Offers;
import com.lourish.wpoffer.service.OfferReadService;

public class ReadOfferRestControllerTest extends AbstractDocumentingApiControllerTest {

    @Mock
    private OfferReadService offerReadService;

    @Captor
    private ArgumentCaptor<Offer> offerCaptor;

    private ReadOfferRestController controller;

    @Before
    public void setUp() {
        controller = new ReadOfferRestController(offerReadService);
        setUpDocumentingMockMvc(controller);
    }

    @Test
    public void listOffers() throws Exception {
        // given
        final Offer returnedOffer = Offers.offerWithId();
        given(offerReadService.findCurrentOffers()).willReturn(asList(returnedOffer));

        // when
        mockMvc.perform(get("/offers")
                .accept(MediaType.APPLICATION_JSON_UTF8))

                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].desc", is(returnedOffer.getDesc())))
                .andExpect(jsonPath("[0].price", is(returnedOffer.getPrice().doubleValue())))
                .andExpect(jsonPath("[0].currency", is(returnedOffer.getCurrency())))
                .andDo(print())
                .andDo(document("list-offers", responseFields(
                        fieldWithPath("[]").description("Array of current offers"))
                                .andWithPrefix("[].",
                                        DocumentationSnippets.OFFER_RESPONSE_FIELD_DESCRIPTORS)));

    }

    @Test
    public void getOffer() throws Exception {
        // given
        final Offer returnedOffer = Offers.offerWithId();
        given(offerReadService.findById(anyString())).willReturn(Optional.of(returnedOffer));

        // when
        mockMvc.perform(get("/offers/id/{id}", "an-offer-id"))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("desc", is(returnedOffer.getDesc())))
                .andExpect(jsonPath("price", is(returnedOffer.getPrice().doubleValue())))
                .andExpect(jsonPath("currency", is(returnedOffer.getCurrency())))
                .andDo(print())
                .andDo(document("get-offer", pathParameters(parameterWithName("id")
                        .description("id of offer to cancel")),
                        responseFields(DocumentationSnippets.OFFER_RESPONSE_FIELD_DESCRIPTORS)));

    }

}
