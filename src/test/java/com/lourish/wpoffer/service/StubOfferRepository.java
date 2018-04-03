package com.lourish.wpoffer.service;

import java.util.Deque;
import java.util.LinkedList;

import com.lourish.wpoffer.domain.Offer;
import com.lourish.wpoffer.web.repository.OfferUpdateRepository;

/**
 * Stubs an OfferUpdateRepository without forcing unit tests to rely on mocking
 * libs etc and also saves exact state of objects when methods are called
 * treating objects as immutable
 *
 * @author dave
 *
 */
public class StubOfferRepository implements OfferUpdateRepository {

    private Deque<Offer> savedOffers = new LinkedList<>();

    @Override
    public Offer save(Offer offer) {
        savedOffers.push(copyOffer(offer));
        return copyOffer(offer);
    }

    private Offer copyOffer(Offer offer) {
        return new Offer(offer.getId(), offer.getDesc(), offer.getPrice(), offer.getCurrency());
    }

    public Offer popLastSavedOffer() {
        return savedOffers.pop();
    }

}
