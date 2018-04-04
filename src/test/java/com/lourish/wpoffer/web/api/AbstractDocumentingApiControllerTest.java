package com.lourish.wpoffer.web.api;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

import org.junit.Rule;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.templates.TemplateFormats;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.lourish.wpoffer.WpOfferApplication;

public abstract class AbstractDocumentingApiControllerTest {
    @Rule
    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");
    @Rule
    public final MockitoRule mockRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);

    protected MockMvc mockMvc;

    protected void setUpDocumentingMockMvc(final Object controller) {
        final MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter(
                new WpOfferApplication().objectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setMessageConverters(jsonMessageConverter)
                .apply(documentationConfiguration(restDocumentation).snippets()
                        .withTemplateFormat(TemplateFormats.markdown()))
                .build();
    }

}
