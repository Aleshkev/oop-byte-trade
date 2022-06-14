package pl.edu.mimuw.bytetrade.workerbuyingstrategy;

import pl.edu.mimuw.bytetrade.exchange.Exchange;
import pl.edu.mimuw.bytetrade.worker.Worker;

public abstract class WorkerBuyingStrategy {
  public abstract void makeOffersForToday(Worker worker, Exchange exchange);
}
