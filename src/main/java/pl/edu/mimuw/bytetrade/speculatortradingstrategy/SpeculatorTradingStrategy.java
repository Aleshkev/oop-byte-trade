package pl.edu.mimuw.bytetrade.speculatortradingstrategy;

import pl.edu.mimuw.bytetrade.exchange.Exchange;
import pl.edu.mimuw.bytetrade.speculator.Speculator;

public abstract class SpeculatorTradingStrategy {
  public abstract void makeOffers(Speculator speculator, Exchange exchange);
}
