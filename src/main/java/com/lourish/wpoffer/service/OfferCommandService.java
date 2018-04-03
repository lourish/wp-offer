package com.lourish.wpoffer.service;

import org.springframework.stereotype.Service;

import com.lourish.wpoffer.domain.Offer;
import com.lourish.wpoffer.repository.redis.OfferRepository;

/**
 * Service to deal with write operations on {@link Offer}
 *
 * @author dave
 *
 */
@Service
public class OfferCommandService {

    private final OfferRepository repository;

    public OfferCommandService(final OfferRepository repository) {
        this.repository = repository;

    }

    /**
     * Create an offer after providing an ID
     *
     * @return Created offer
     */
    public Offer createOffer(final Offer offer) {
        return repository.save(offer);
    }

}
