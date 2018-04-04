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
    private final IdGenerator idGenerator;

    public OfferCommandService(final IdGenerator idGenerator, final OfferRepository repository) {
        this.idGenerator = idGenerator;
        this.repository = repository;
    }

    /**
     * Create an offer after providing an ID
     *
     * @return Created offer
     */
    public Offer createOffer(final Offer offer) {
        return repository.save(offer.withId(idGenerator.generateId(offer)));
    }

    public void deleteById(final String offerId) {
        repository.deleteById(offerId);
    }

}
