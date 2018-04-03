package com.lourish.wpoffer.domain;

import static com.lourish.wpoffer.TimeUtils.asSeconds;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
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
public final class Offer {

    @JsonProperty
    @Id
    private String id;

    @JsonProperty
    @NotBlank
    private String desc;
    @JsonProperty
    @NotNull
    @Min(0)
    private BigDecimal price;
    @JsonProperty
    @NotBlank
    private String currency;
    @JsonProperty
    @NotNull
    private LocalDateTime expires;

    /**
     * @return time until expiry in seconds (negative if passed)
     */
    @TimeToLive
    public long getTimeToLive() {
        return asSeconds(Duration.between(LocalDateTime.now(), expires).toMillis());
    }

    protected Offer() {
        // For framework support
    }

    private Offer(final Offer o) {
        this.id = o.id;
        this.desc = o.desc;
        this.price = o.price;
        this.currency = o.currency;
    }

    /**
     * Create an offer without an id
     *
     * @param desc
     * @param price
     * @param currency
     */
    public Offer(final String desc, final BigDecimal price, final String currency, final LocalDateTime expires) {
        id = null;
        this.desc = desc;
        this.price = price;
        this.currency = currency;
        this.expires = expires;
    }

    @JsonCreator
    public Offer(@JsonProperty("id") final String id, @JsonProperty("desc") final String desc,
            @JsonProperty("price") final BigDecimal price, @JsonProperty("currency") final String currency,
            @JsonProperty("expires") final LocalDateTime expires) {
        this.id = id;
        this.desc = desc;
        this.price = price;
        this.currency = currency;
        this.expires = expires;
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
                + "]";
    }

}
