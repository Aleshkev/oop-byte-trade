package pl.edu.mimuw.bytetrade.exchangestrategy;

public class ExchangeStrategyFactory {
  private ExchangeStrategyFactory() {}

  public static ExchangeStrategy kapitalistyczna() {
    return new Kapitalistyczna();
  }

  public static ExchangeStrategy socjalistyczna() {
    return new Socjalistyczna();
  }

  public static ExchangeStrategy zrównoważona() {
    return new Zrównoważona();
  }
}
