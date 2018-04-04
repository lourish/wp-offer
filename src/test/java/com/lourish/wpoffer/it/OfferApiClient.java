package com.lourish.wpoffer.it;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.lourish.wpoffer.domain.Offer;
import com.lourish.wpoffer.test.JsonTemplates;

public class OfferApiClient {
    private static final HttpHeaders JSON_HEADERS = new HttpHeaders();
    static {
        JSON_HEADERS.setContentType(MediaType.APPLICATION_JSON);
        JSON_HEADERS.setAccept(asList(MediaType.APPLICATION_JSON));
    }

    private final String uriBase;
    private final RestTemplate restTemplate = new RestTemplate();

    public OfferApiClient(final String host, final int port) {
        this.uriBase = "http://" + host + ":" + port;
    }

    public Offer getById(final String id) {
        final Offer offer = getRequest(getUrl("/offers/id/" + id), Offer.class);
        return offer;
    }

    public List<Offer> list() {
        final Offer[] offers = getRequest(getUrl("/offers/"), Offer[].class);
        return asList(offers);
    }

    public void cancel(final String offerId) {
        deleteRequest(getUrl("/offers/id/" + offerId));
    }

    public Offer create(final Offer o) {
        return postRequest(getUrl("/offers"), JsonTemplates.offer(o.getDesc(), o.getPrice(), o.getCurrency(), o
                .getExpires()),
                Offer.class);
    }

    private void deleteRequest(final URI uri) {
        restTemplate.delete(uri);
    }

    private <T> T getRequest(final URI uri, final Class<T> entityType) {
        final HttpEntity<Object> entity = new HttpEntity<Object>(JSON_HEADERS);
        final ResponseEntity<T> response = restTemplate.exchange(uri, HttpMethod.GET, entity, entityType);
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        return response.getBody();
    }

    private <T> T postRequest(final URI uri, final String body, final Class<T> entityType) {
        final HttpEntity<String> entity = new HttpEntity<String>(body, JSON_HEADERS);
        final ResponseEntity<T> response = restTemplate.postForEntity(uri, entity, entityType);
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        return response.getBody();
    }

    protected URI getUrl(final String path) {
        return URI.create(uriBase + path);
    }

}
