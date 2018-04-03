package com.lourish.wpoffer.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lourish.wpoffer.domain.Offer;
import com.lourish.wpoffer.service.OfferCommandService;

@RestController
public class CreateOfferRestController {

    private final OfferCommandService offerCommandService;

    public CreateOfferRestController(final OfferCommandService offerCommandService) {
        this.offerCommandService = offerCommandService;
    }

    @InitBinder
    protected void initBinder(final WebDataBinder binder) {
        binder.setDisallowedFields("id");
    }

    @PostMapping(value = "/offers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Offer> createOffer(@Validated @RequestBody final Offer offerToCreate) {
        final Offer createdOffer = offerCommandService.createOffer(offerToCreate);
        return new ResponseEntity<Offer>(createdOffer, HttpStatus.OK);
    }
}
