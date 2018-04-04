package com.lourish.wpoffer.web.api;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.lourish.wpoffer.domain.Offer;
import com.lourish.wpoffer.repository.redis.OfferRepository;

@RestController
public class ReadOfferRestController {

    private final OfferRepository offerRepository;

    public ReadOfferRestController(final OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @GetMapping(value = "/offers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Iterable<Offer> listOffers() {
        final Iterable<Offer> offers = offerRepository.findAll();
        return offers;
    }

    @GetMapping(value = "/offers/id/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getOffer(@PathVariable("id") final String offerId) {
        final Optional<Offer> offer = offerRepository.findById(offerId);
        if (offer.isPresent()) {
            return new ResponseEntity<Offer>(offer.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    }

}
