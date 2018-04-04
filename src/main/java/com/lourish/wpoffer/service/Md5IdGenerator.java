package com.lourish.wpoffer.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.ZoneOffset;

import org.springframework.stereotype.Component;

import com.lourish.wpoffer.domain.Offer;

/**
 * ID generator which uses the MD5 hash of the currency, price, description and
 * expiry fields to create an id for an offer. The same id will be created for
 * any offer with the same values for the fields.
 *
 * Requires MD5 MessageDigest
 *
 * @author dave
 *
 */
@Component
public class Md5IdGenerator implements IdGenerator {

    @Override
    public String generateId(final Offer offer) {
        try {
            final String offerFields = new StringBuilder()
                    .append(offer.getCurrency())
                    .append(":")
                    .append(offer.getPrice().toPlainString())
                    .append(":")
                    .append(offer.getDesc())
                    .append(":")
                    .append(offer.getExpires().toEpochSecond(ZoneOffset.UTC))
                    .toString();

            final MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(offerFields.getBytes("UTF-8"));
            final byte[] digest = md.digest();
            final String idString = javax.xml.bind.DatatypeConverter.printHexBinary(digest);
            return idString;
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new ServiceException("Failed to generate ID for offer " + offer, e);
        }
    }
}
