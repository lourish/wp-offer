package com.lourish.wpoffer.web.api;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

import org.springframework.restdocs.payload.FieldDescriptor;

public final class DocumentationSnippets {
    public static final FieldDescriptor EXPIRES_FIELD = fieldWithPath("expires")
            .description("This expiry time for this offer as an ISO basic date time string,"
                    + " in the local timzone to the nearest second");

    public static final FieldDescriptor CURRENCY_FIELD = fieldWithPath("currency").description(
            "This currency for this offer's price");

    public static final FieldDescriptor PRICE_FIELD = fieldWithPath("price")
            .type(Number.class)
            .description("This offer's price");

    public static final FieldDescriptor DESCRIPTION_FIELD = fieldWithPath("desc").description(
            "A description of this offer");
    public static final FieldDescriptor ID_FIELD = fieldWithPath("id").description(
            "A unique identifier for this offer");
    public static final FieldDescriptor TTL_FIELD = fieldWithPath("ttl").description(
            "The time to live for this offer - the number of seconds until it expires");

    public static final FieldDescriptor[] OFFER_REQUEST_FIELD_DESCRIPTORS = new FieldDescriptor[] {
            DESCRIPTION_FIELD,
            PRICE_FIELD,
            CURRENCY_FIELD,
            EXPIRES_FIELD };
    public static final FieldDescriptor[] OFFER_RESPONSE_FIELD_DESCRIPTORS = new FieldDescriptor[] {
            ID_FIELD,
            DESCRIPTION_FIELD,
            PRICE_FIELD,
            CURRENCY_FIELD,
            EXPIRES_FIELD,
            TTL_FIELD };

    private DocumentationSnippets() {

    }
}
