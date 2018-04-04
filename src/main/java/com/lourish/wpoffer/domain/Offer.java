package com.lourish.wpoffer.domain;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Immutable offer
 *
 * @author dave
 *
 */
@RedisHash("offers")
public class Offer {

    private String id;

    @NotBlank
    private String desc;
    @NotNull
    @Min(0)
    private BigDecimal price;
    @NotBlank
    private String currency;
    @NotNull
    private LocalDateTime expires;

    /**
     * @return time until expiry in seconds (negative if passed)
     */
    @TimeToLive(unit = TimeUnit.MILLISECONDS)
    @Min(0)
    private Long ttl;

    protected Offer() {
        // For framework support
    }

    private Offer(final Offer o) {
        this.id = o.id;
        this.desc = o.desc;
        this.price = o.price;
        this.currency = o.currency;
        this.expires = o.expires;
        this.ttl = o.ttl;
    }

    /**
     * Create an offer without an id
     *
     */
    public Offer(final String desc, final BigDecimal price, final String currency, final LocalDateTime expires) {
        id = null;
        this.desc = desc;
        this.price = price;
        this.currency = currency;
        this.expires = expires;
        this.ttl = calculateTtl(expires);
    }

    @JsonCreator
    public Offer(@JsonProperty("id") final String id, @JsonProperty("desc") final String desc,
            @JsonProperty("price") final BigDecimal price, @JsonProperty("currency") final String currency,
            @JsonProperty("expires") final LocalDateTime expires) {
        this(desc, price, currency, expires);
        this.id = id;
    }

    public Long getTtl() {
        return ttl;
    }

    public String getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public Offer withDescription(final String val) {
        final Offer newOffer = new Offer(this);
        newOffer.desc = val;
        return newOffer;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public LocalDateTime getExpires() {
        return expires;
    }

    public Offer withPrice(final BigDecimal val) {
        final Offer newOffer = new Offer(this);
        newOffer.price = val;
        return newOffer;
    }

    public Offer withCurrency(final String val) {
        final Offer newOffer = new Offer(this);
        newOffer.currency = val;
        return newOffer;
    }

    public Offer withId(final String val) {
        final Offer newOffer = new Offer(this);
        newOffer.id = val;
        return newOffer;
    }

    public Offer withExpires(final LocalDateTime expiry) {
        final Offer newOffer = new Offer(this);
        newOffer.expires = expiry;
        newOffer.ttl = calculateTtl(expires);
        return newOffer;
    }

    private long calculateTtl(final LocalDateTime expires) {
        return Duration.between(LocalDateTime.now(), expires).toMillis();
    }

    @Override
    public String toString() {
        return "Offer [id=" + id
                + ", desc="
                + desc
                + ", price="
                + price
                + ", currency="
                + currency
                + ", expires="
                + expires
                + ", ttl="
                + ttl
                + "]";
    }

}
