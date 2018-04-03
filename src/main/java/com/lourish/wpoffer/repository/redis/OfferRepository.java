package com.lourish.wpoffer.repository.redis;

import org.springframework.data.repository.CrudRepository;

import com.lourish.wpoffer.domain.Offer;

public interface OfferRepository extends CrudRepository<Offer, String> {

}
