package pl.edu.mimuw.bytetrade.exchangestrategy;

import pl.edu.mimuw.bytetrade.exchange.Exchange;
import pl.edu.mimuw.bytetrade.worker.Worker;

import java.util.List;
import java.util.Set;

final class Zrównoważona extends ExchangeStrategy {
  private final ExchangeStrategy strategy_one;
  private final ExchangeStrategy strategy_two;

  public Zrównoważona() {
    this.strategy_one = new Kapitalistyczna();
    this.strategy_two = new Socjalistyczna();
  }

  @Override
  public List<Worker> prioritizeWorkers(Exchange exchange, Set<Worker> workers) {
    var day = exchange.getSimulation().getDayNumber();
    if (day % 2 == 0) return strategy_one.prioritizeWorkers(exchange, workers);
    else return strategy_two.prioritizeWorkers(exchange, workers);
  }

  @Override
  public String toString() {
    return "Zrównoważona{}";
  }
}
