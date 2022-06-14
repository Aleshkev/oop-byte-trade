package pl.edu.mimuw.bytetrade.workerbuyingstrategy;

import pl.edu.mimuw.bytetrade.counter.Stack;
import pl.edu.mimuw.bytetrade.exchange.Exchange;
import pl.edu.mimuw.bytetrade.itemtype.VirtualItem;
import pl.edu.mimuw.bytetrade.worker.Worker;
import pl.edu.mimuw.bytetrade.exchange.WorkerBuyOffer;

/** Like {@link Technofob} but also buys enough clothes to have enough for the next day. */
final class Czyścioszek extends WorkerBuyingStrategy {
  @Override
  public void makeOffersForToday(Worker worker, Exchange exchange) {
    exchange.addWorkerBuyOffer(new WorkerBuyOffer(worker, new Stack<>(VirtualItem.Food, 100)));
    makeOfferToBuyClothesIfNeeded(worker, exchange);
  }

  static void makeOfferToBuyClothesIfNeeded(Worker worker, Exchange exchange) {
    var clothesThatWillSurelyRemain =
        worker.getThingsToUse().clothes.stream()
            .filter(stack -> stack.item.durability > 1)
            .mapToDouble(stack -> stack.count)
            .sum();
    var clothesThatMayBeUsedUp =
        worker.getThingsToUse().clothes.stream()
            .filter(stack -> stack.item.durability == 1)
            .mapToDouble(stack -> stack.count)
            .sum();

    // We assume a pessimistic strategy here, in which the clothes with the least remaining
    // durability will be used up.
    var howManyClothesWillRemain =
        clothesThatWillSurelyRemain + Math.max(0, clothesThatMayBeUsedUp - 100);

    if (howManyClothesWillRemain < worker.howManyClothesUsesInADay()) {
      exchange.addWorkerBuyOffer(
          new WorkerBuyOffer(
              worker,
              new Stack<>(
                  VirtualItem.Clothes,
                  worker.howManyClothesUsesInADay() - howManyClothesWillRemain)));
    }
  }

  @Override
  public String toString() {
    return "Czyścioszek{}";
  }
}
