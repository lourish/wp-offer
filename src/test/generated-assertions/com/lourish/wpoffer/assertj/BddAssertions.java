package com.lourish.wpoffer.assertj;

/**
 * Entry point for BDD assertions of different data types.
 */
@javax.annotation.Generated(value="assertj-assertions-generator")
public class BddAssertions {

  /**
   * Creates a new instance of <code>{@link com.lourish.wpoffer.domain.OfferAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  @org.assertj.core.util.CheckReturnValue
  public static com.lourish.wpoffer.domain.OfferAssert then(com.lourish.wpoffer.domain.Offer actual) {
    return new com.lourish.wpoffer.domain.OfferAssert(actual);
  }

  /**
   * Creates a new <code>{@link BddAssertions}</code>.
   */
  protected BddAssertions() {
    // empty
  }
}
