package com.lourish.wpoffer.service;

import com.lourish.wpoffer.domain.Offer;
import com.lourish.wpoffer.web.service.IdGenerator;

public class StubIdGenerator implements IdGenerator {

    private String fixedId;

    public StubIdGenerator(String fixedId) {
        this.fixedId = fixedId;
    }

    @Override
    public String generateId(Offer offer) {
        return fixedId;
    }

    public String getFixedId() {
        return fixedId;
    }

}
