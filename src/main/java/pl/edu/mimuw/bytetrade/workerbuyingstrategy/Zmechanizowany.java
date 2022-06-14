package pl.edu.mimuw.bytetrade.workerbuyingstrategy;

import pl.edu.mimuw.bytetrade.counter.Stack;
import pl.edu.mimuw.bytetrade.exchange.Exchange;
import pl.edu.mimuw.bytetrade.itemtype.VirtualItem;
import pl.edu.mimuw.bytetrade.worker.Worker;
import pl.edu.mimuw.bytetrade.exchange.WorkerBuyOffer;

/** Like {@link Czyścioszek} but also buys {@code numberOfTools} tools each day. */
final class Zmechanizowany extends WorkerBuyingStrategy {
  private final double numberOfTools;

  public Zmechanizowany(double numberOfTools) {
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
  }
}
