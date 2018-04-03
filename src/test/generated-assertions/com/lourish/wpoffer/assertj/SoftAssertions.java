package com.lourish.wpoffer.assertj;

/**
 * Entry point for soft assertions of different data types.
 */
@javax.annotation.Generated(value="assertj-assertions-generator")
public class SoftAssertions extends org.assertj.core.api.SoftAssertions {

  /**
   * Creates a new "soft" instance of <code>{@link com.lourish.wpoffer.domain.OfferAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  @org.assertj.core.util.CheckReturnValue
  public com.lourish.wpoffer.domain.OfferAssert assertThat(com.lourish.wpoffer.domain.Offer actual) {
    return proxy(com.lourish.wpoffer.domain.OfferAssert.class, com.lourish.wpoffer.domain.Offer.class, actual);
  }

}
