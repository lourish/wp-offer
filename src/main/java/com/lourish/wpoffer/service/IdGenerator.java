package com.lourish.wpoffer.service;

import com.lourish.wpoffer.domain.Offer;

public interface IdGenerator {

    String generateId(Offer offer);

}
