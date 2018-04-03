package com.lourish.wpoffer.assertj;

/**
 * Entry point for assertions of different data types. Each method in this class is a static factory for the
 * type-specific assertion objects.
 */
@javax.annotation.Generated(value="assertj-assertions-generator")
public class Assertions {

  /**
   * Creates a new instance of <code>{@link com.lourish.wpoffer.domain.OfferAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  @org.assertj.core.util.CheckReturnValue
  public static com.lourish.wpoffer.domain.OfferAssert assertThat(com.lourish.wpoffer.domain.Offer actual) {
    return new com.lourish.wpoffer.domain.OfferAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link com.lourish.wpoffer.domain.OffersAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  @org.assertj.core.util.CheckReturnValue
  public static com.lourish.wpoffer.domain.OffersAssert assertThat(com.lourish.wpoffer.domain.Offers actual) {
    return new com.lourish.wpoffer.domain.OffersAssert(actual);
  }

  /**
   * Creates a new <code>{@link Assertions}</code>.
   */
  protected Assertions() {
    // empty
  }
}
