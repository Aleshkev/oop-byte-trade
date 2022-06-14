package pl.edu.mimuw.bytetrade.workerbuyingstrategy;

import pl.edu.mimuw.bytetrade.counter.Stack;
import pl.edu.mimuw.bytetrade.exchange.Exchange;
import pl.edu.mimuw.bytetrade.exchange.WorkerBuyOffer;
import pl.edu.mimuw.bytetrade.itemtype.VirtualItem;
import pl.edu.mimuw.bytetrade.worker.Worker;

/**
 * Like {@link Zmechanizowany}, but also buys programs to match the amount of things the worker
 * produced this day.
 */
final class Gadżeciarz extends WorkerBuyingStrategy {
  private final double numberOfTools;

  public Gadżeciarz(double numberOfTools) {
    if (numberOfTools <= 0)
      throw new IllegalArgumentException("Can't buy negative amounts of tools.");
    this.numberOfTools = numberOfTools;
  }

  @Override
  public void makeOffersForToday(Worker worker, Exchange exchange) {
    exchange.addWorkerBuyOffer(new WorkerBuyOffer(worker, new Stack<>(VirtualItem.Food, 100)));

    exchange.addWorkerBuyOffer(
        new WorkerBuyOffer(worker, new Stack<>(VirtualItem.Tool, numberOfTools)));

    Czyścioszek.makeOfferToBuyClothesIfNeeded(worker, exchange);

    exchange.addWorkerBuyOffer(
        new WorkerBuyOffer(
            worker, new Stack<>(VirtualItem.Program, worker.howManyThingsDidProduceToday())));
  }
}
