package com.lourish.wpoffer.web.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.lourish.wpoffer.domain.Offer;

public class Md5IdGenerator implements IdGenerator {

    @Override
    public String generateId(Offer offer) {
        try {
            String offerFields = new StringBuilder()
                    .append(offer.getCurrency())
                    .append(":")
                    .append(offer.getPrice().toPlainString())
                    .append(":")
                    .append(offer.getDesc())
                    .toString();

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(offerFields.getBytes("UTF-8"));
            byte[] digest = md.digest();
            String idString = javax.xml.bind.DatatypeConverter.printHexBinary(digest);
            return idString;
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new ServiceException("Failed to generate ID for offer " + offer, e);
        }
    }
}
