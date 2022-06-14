package pl.edu.mimuw.bytetrade.exchangestrategy;

import pl.edu.mimuw.bytetrade.exchange.Exchange;
import pl.edu.mimuw.bytetrade.worker.Worker;

import java.util.List;
import java.util.Set;

public abstract class ExchangeStrategy {
  public abstract List<Worker> prioritizeWorkers(Exchange exchange, Set<Worker> workers);

  @Override
  public int hashCode() {
    throw new UnsupportedOperationException("Don't hash ExchangeStrategy objects.");
  }

  @Override
  public boolean equals(Object o) {
    throw new UnsupportedOperationException("Don't compare ExchangeStrategy objects.");
  }
}
