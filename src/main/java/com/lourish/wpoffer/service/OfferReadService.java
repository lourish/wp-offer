package com.lourish.wpoffer.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.lourish.wpoffer.domain.Offer;
import com.lourish.wpoffer.repository.redis.OfferRepository;

/**
 * Service handling read
 *
 * @author dave
 *
 */
@Service
public class OfferReadService {
    private final OfferRepository repository;

    public OfferReadService(final OfferRepository repository) {
        this.repository = repository;

    }

    public Optional<Offer> findById(final String offerId) {
        return repository.findById(offerId);
    }

    /**
     * Find all non-expired offers.
     *
     * @return
     */
    public List<Offer> findCurrentOffers() {
        final Iterable<Offer> allOffers = repository.findAll();
        final List<Offer> nonNullOffers = removeExpiredOffersYetToBeRemoved(allOffers);
        return nonNullOffers;
    }

    /*
     * Offers which have expired but still exist in index awaiting cleanup must
     * be removed
     */
    private List<Offer> removeExpiredOffersYetToBeRemoved(final Iterable<Offer> offers) {
        final List<Offer> nonNullOffers = StreamSupport.stream(offers.spliterator(), false)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return nonNullOffers;
    }

}
