package com.lourish.wpoffer.web.api;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lourish.wpoffer.domain.Offer;
import com.lourish.wpoffer.service.OfferCommandService;

@RestController
public class UpdateOfferRestController {

    private final OfferCommandService offerService;

    public UpdateOfferRestController(final OfferCommandService offerService) {
        this.offerService = offerService;
    }

    @InitBinder
    protected void initBinder(final WebDataBinder binder) {
        binder.setDisallowedFields("id");
    }

    @PostMapping(value = "/offers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Offer createOffer(@Validated @RequestBody final Offer offerToCreate) {
        final Offer createdOffer = offerService.createOffer(offerToCreate);
        return createdOffer;
    }

    @DeleteMapping(value = "/offers/id/{id}")
    public void cancelOffer(@PathVariable("id") final String offerId) {
        offerService.deleteById(offerId);
    }

}
