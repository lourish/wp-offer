package com.lourish.wpoffer.it;

import static com.lourish.wpoffer.assertj.Assertions.assertThat;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.lourish.it.EmbeddedRedis;
import com.lourish.wpoffer.WpOfferApplication;
import com.lourish.wpoffer.domain.Offer;
import com.lourish.wpoffer.repository.redis.OfferRepository;
import com.lourish.wpoffer.test.JsonTemplates;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "server.servlet.context-path=/", classes = WpOfferApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = { EmbeddedRedis.class })
public class CreateOfferIntegTest {

    @LocalServerPort
    private int port;
    private RestTemplate restTemplate;

    @Autowired
    private OfferRepository offerRepo;

    @Before
    public void setUp() {
        restTemplate = new RestTemplate();
    }

    @Test
    public void createOffer() throws Exception {
        // given
        final String description = "a description";
        final BigDecimal price = new BigDecimal("1.23");
        final String currency = "GBP";
        final LocalDateTime expires = LocalDateTime.now().plusHours(1);
        // when
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(asList(MediaType.APPLICATION_JSON));
        final HttpEntity<String> entity = new HttpEntity<String>(JsonTemplates.offer(description, price, currency,
                expires),
                headers);
        final ResponseEntity<Offer> response = restTemplate.postForEntity(getUrl("/offers"), entity, Offer.class);

        // then
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        final Offer createdOffer = response.getBody();
        assertThat(createdOffer.getId()).isNotNull();
        final Optional<Offer> offerInRepo = offerRepo.findById(createdOffer.getId());
        assertThat(offerInRepo).isPresent();
        assertThat(offerInRepo.get()).hasDesc(description)
                .hasCurrency(currency)
                .hasPrice(price)
                .hasExpires(expires);
    }

    private URI getUrl(final String path) {
        return URI.create("http://localhost:" + port + path);
    }

}
