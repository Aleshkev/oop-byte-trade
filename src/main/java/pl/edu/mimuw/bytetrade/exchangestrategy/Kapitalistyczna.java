package pl.edu.mimuw.bytetrade.exchangestrategy;

import pl.edu.mimuw.bytetrade.exchange.Exchange;
import pl.edu.mimuw.bytetrade.worker.Worker;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

final class Kapitalistyczna extends ExchangeStrategy {
  @Override
  public List<Worker> prioritizeWorkers(Exchange exchange, Set<Worker> workers) {
    return workers.stream()
        .sorted(
            Comparator.comparingDouble(Worker::howManyDiamondsDoTheyHave)
                .thenComparingInt(Worker::getId)
                .reversed())
        .collect(Collectors.toList());
  }

  @Override
  public String toString() {
    return "Kapitalistyczna{}";
  }
}
