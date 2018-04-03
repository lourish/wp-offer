package com.lourish.wpoffer.test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class JsonTemplates {
    private JsonTemplates() {
    }

    public static String offer(final String description, final BigDecimal price, final String currency,
            final LocalDateTime expires) {
        return String.format("{\"desc\": \"%s\","
                + "\"price\": \"%s\","
                + "\"currency\":\"%s\","
                + "\"expires\": \"%s\"}", description, price.toPlainString(), currency,
                DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(expires));
    }
}
