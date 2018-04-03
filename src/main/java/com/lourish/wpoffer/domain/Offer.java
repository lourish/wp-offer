package com.lourish.wpoffer.domain;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Immutable offer
 *
 * @author dave
 *
 */
public final class Offer {

    @JsonProperty
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

    private Offer(Offer o) {
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
    public Offer(String desc, BigDecimal price, String currency) {
        id = null;
        this.desc = desc;
        this.price = price;
        this.currency = currency;
    }

    @JsonCreator
    public Offer(@JsonProperty("id") String id, @JsonProperty("desc") String desc,
            @JsonProperty("price") BigDecimal price, @JsonProperty("currency") String currency) {
        this.id = id;
        this.desc = desc;
        this.price = price;
        this.currency = currency;
    }

    public String getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public Offer withDescription(String val) {
        Offer newOffer = new Offer(this);
        newOffer.desc = val;
        return newOffer;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public Offer withPrice(BigDecimal val) {
        Offer newOffer = new Offer(this);
        newOffer.price = val;
        return newOffer;
    }

    public Offer withCurrency(String val) {
        Offer newOffer = new Offer(this);
        newOffer.currency = val;
        return newOffer;
    }

    public Offer withId(String val) {
        Offer newOffer = new Offer(this);
        newOffer.id = val;
        return newOffer;
    }

    @Override
    public String toString() {
        return "Offer [id=" + id + ", desc=" + desc + ", price=" + price + ", currency=" + currency + "]";
    }

}
