package pl.edu.mimuw.bytetrade.workerbuyingstrategy;

import pl.edu.mimuw.bytetrade.counter.Stack;
import pl.edu.mimuw.bytetrade.exchange.Exchange;
import pl.edu.mimuw.bytetrade.itemtype.VirtualItem;
import pl.edu.mimuw.bytetrade.worker.Worker;
import pl.edu.mimuw.bytetrade.exchange.WorkerBuyOffer;

/** Buys 100 food a day. */
final class Technofob extends WorkerBuyingStrategy {
  @Override
  public void makeOffersForToday(Worker worker, Exchange exchange) {
    exchange.addWorkerBuyOffer(new WorkerBuyOffer(worker, new Stack<>(VirtualItem.Food, 100)));
  }

  @Override
  public String toString() {
    return "Technofob{}";
  }
}
