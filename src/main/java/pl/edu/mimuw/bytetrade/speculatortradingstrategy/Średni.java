package pl.edu.mimuw.bytetrade.speculatortradingstrategy;

import pl.edu.mimuw.bytetrade.counter.Stack;
import pl.edu.mimuw.bytetrade.exchange.Exchange;
import pl.edu.mimuw.bytetrade.physicalitem.PhysicalItemFactory;
import pl.edu.mimuw.bytetrade.speculator.Speculator;
import pl.edu.mimuw.bytetrade.exchange.SpeculatorBuyOffer;
import pl.edu.mimuw.bytetrade.exchange.SpeculatorSellOffer;

/**
 * Offers to buy things:
 *
 * <ol>
 *   <li>If the speculator has a thing, at 5% below its average price over last {@code history}
 *       days.
 *   <li>If they don't have the thing, 10% below.
 * </ol>
 *
 * Offers to sell everything 10% above the average price over last {@code history} days.
 */
final class Średni extends SpeculatorTradingStrategy {
  private final int history;

  Średni(int history) {
    this.history = history;
  }

  @Override
  public void makeOffers(Speculator speculator, Exchange exchange) {
    var itemsToTrade = speculator.getItemsToTrade();
    for (var stack : PhysicalItemFactory.getAllItems()) {
      var item = stack.item;
      if (item.getVirtual().isUnsellable()) continue;
      var basePrice = exchange.getStatistics().getAveragePriceOverLastNDays(item, history);

      if (itemsToTrade.count(item) > 0) {
        exchange.addSpeculatorBuyOffer(
            new SpeculatorBuyOffer(speculator, new Stack<>(item, 100), basePrice * (1. - .1)));
        exchange.addSpeculatorSellOffer(
            new SpeculatorSellOffer(
                speculator, new Stack<>(item, itemsToTrade.count(item)), basePrice * (1. + .1)));
      } else {
        exchange.addSpeculatorBuyOffer(
            new SpeculatorBuyOffer(speculator, new Stack<>(item, 100), basePrice * (1. - .05)));
      }
    }
  }

  @Override
  public String toString() {
    return "Średni{" + "history=" + history + '}';
  }
}
