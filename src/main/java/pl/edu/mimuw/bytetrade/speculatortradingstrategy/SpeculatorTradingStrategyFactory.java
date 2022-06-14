package pl.edu.mimuw.bytetrade.speculatortradingstrategy;

public class SpeculatorTradingStrategyFactory {
  private SpeculatorTradingStrategyFactory() {}

  SpeculatorTradingStrategy średni(int history) {
    return new Średni(history);
  }

  SpeculatorTradingStrategy wypukły() {
    return new Wypukły();
  }

  SpeculatorTradingStrategy regulującyRynek() {
    return new RegulującyRynek();
  }
}
