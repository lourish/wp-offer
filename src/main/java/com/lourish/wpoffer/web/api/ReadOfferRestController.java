package com.lourish.wpoffer.web.api;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.lourish.wpoffer.domain.Offer;
import com.lourish.wpoffer.service.OfferReadService;

/**
 * Read-only operations
 *
 * @author dave
 *
 */
@RestController
public class ReadOfferRestController {

    private final OfferReadService offerReadService;

    public ReadOfferRestController(final OfferReadService offerReadService) {
        this.offerReadService = offerReadService;
    }

    @GetMapping(value = "/offers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Offer> listOffers() {
        final List<Offer> offers = offerReadService.findCurrentOffers();
        return offers;
    }

    @GetMapping(value = "/offers/id/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getOffer(@PathVariable("id") final String offerId) {
        final Optional<Offer> offer = offerReadService.findById(offerId);
        if (offer.isPresent()) {
            return new ResponseEntity<Offer>(offer.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    }

}
