package com.lourish.wpoffer.service;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Optional;

import com.lourish.wpoffer.domain.Offer;
import com.lourish.wpoffer.repository.redis.OfferRepository;

/**
 * Stubs an OfferRepository without forcing unit tests to rely on mocking libs
 * etc
 *
 * @author dave
 *
 */
public class StubOfferRepository implements OfferRepository {

    private final Deque<Offer> savedOffers = new LinkedList<>();

    @SuppressWarnings("unchecked")
    @Override
    public Offer save(final Offer offer) {
        savedOffers.push(copyOffer(offer));
        return copyOffer(offer);
    }

    private Offer copyOffer(final Offer offer) {
        return new Offer(offer.getId(), offer.getDesc(), offer.getPrice(), offer.getCurrency(), offer.getExpires());
    }

    public Offer popLastSavedOffer() {
        return savedOffers.pop();
    }

    @Override
    public <S extends Offer> Iterable<S> saveAll(final Iterable<S> entities) {
        // noop
        return null;
    }

    @Override
    public Optional<Offer> findById(final String id) {
        // noop
        return null;
    }

    @Override
    public boolean existsById(final String id) {
        return false;
    }

    @Override
    public Iterable<Offer> findAll() {
        // noop
        return null;
    }

    @Override
    public Iterable<Offer> findAllById(final Iterable<String> ids) {
        // noop
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(final String id) {
        // noop
    }

    @Override
    public void delete(final Offer entity) {
        // noop
    }

    @Override
    public void deleteAll(final Iterable<? extends Offer> entities) {
        // noop
    }

    @Override
    public void deleteAll() {
        // noop
    }

}
