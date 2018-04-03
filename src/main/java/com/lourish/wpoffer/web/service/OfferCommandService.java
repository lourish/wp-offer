package com.lourish.wpoffer.web.service;

import org.springframework.stereotype.Service;

import com.lourish.wpoffer.domain.Offer;
import com.lourish.wpoffer.web.repository.OfferUpdateRepository;

/**
 * Service to deal with write operations on {@link Offer}
 *
 * @author dave
 *
 */
@Service
public class OfferCommandService {

    private IdGenerator idGenerator;
    private OfferUpdateRepository repository;

    public OfferCommandService(IdGenerator idGenerator, OfferUpdateRepository repository) {
        this.idGenerator = idGenerator;
        this.repository = repository;

    }

    /**
     * Create an offer after providing an ID
     *
     * @return Created offer
     */
    public Offer createOffer(Offer offer) {
        String id = idGenerator.generateId(offer);
        Offer offerToCreate = offer.withId(id);
        return repository.save(offerToCreate);
    }

}
