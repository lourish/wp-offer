package com.lourish.wpoffer.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public final class Offers {
    private Offers() {

    }

    public static Offer offerWithoutId() {
        return new Offer("desc", new BigDecimal("1.23"), "currency", LocalDateTime.now().plusHours(1));
    }
}
