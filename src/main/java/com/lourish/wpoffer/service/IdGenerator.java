package com.lourish.wpoffer.service;

import com.lourish.wpoffer.domain.Offer;

/**
 * Instances can provide unique ids for REDIS entities
 * 
 * @author dave
 *
 */
public interface IdGenerator {

    /**
     * Generate an ID for the given offer
     * 
     * @param offer
     *            Offer (with fields set)
     * @return an ID to store the offer with
     */
    String generateId(Offer offer);

}
