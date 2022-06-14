package pl.edu.mimuw.bytetrade.speculatortradingstrategy;

import pl.edu.mimuw.bytetrade.counter.Stack;
import pl.edu.mimuw.bytetrade.exchange.Exchange;
import pl.edu.mimuw.bytetrade.exchange.SpeculatorBuyOffer;
import pl.edu.mimuw.bytetrade.exchange.SpeculatorSellOffer;
import pl.edu.mimuw.bytetrade.physicalitem.PhysicalItemFactory;
import pl.edu.mimuw.bytetrade.speculator.Speculator;

/**
 * Buys things if the function created from prices over the last 3 days is strictly convex. Sells if
 * it is strictly concave. At 10% below or 10% above the last day's average price.
 *
 * <p>This means the speculator will both buy and sell the same item at the same time in the first
 * two days!!
 */
final class Wypukły extends SpeculatorTradingStrategy {
  @Override
  public void makeOffers(Speculator speculator, Exchange exchange) {
    var itemsToTrade = speculator.getItemsToTrade();
    for (var items : PhysicalItemFactory.getAllItems()) {
      var item = items.item;
      if (item.getVirtual().isUnsellable()) continue;

      var basePrice = exchange.getStatistics().getAveragePriceXDaysAgo(item, 1);

      var day = exchange.getSimulation().getDayNumber();

      var isStrictlyConvex =
          day < 3
              || isStrictlyConvex(
                  exchange.getStatistics().getAveragePriceXDaysAgo(item, 3),
                  exchange.getStatistics().getAveragePriceXDaysAgo(item, 2),
                  exchange.getStatistics().getAveragePriceXDaysAgo(item, 1));
      if (isStrictlyConvex) {
        exchange.addSpeculatorBuyOffer(
            new SpeculatorBuyOffer(speculator, new Stack<>(item, 100), basePrice * (1. - .1)));
      }

      var isStrictlyConcave =
          day < 3
              || isStrictlyConcave(
                  exchange.getStatistics().getAveragePriceXDaysAgo(item, 3),
                  exchange.getStatistics().getAveragePriceXDaysAgo(item, 2),
                  exchange.getStatistics().getAveragePriceXDaysAgo(item, 1));
      if (isStrictlyConcave) {
        exchange.addSpeculatorSellOffer(
            new SpeculatorSellOffer(
                speculator, new Stack<>(item, itemsToTrade.count(item)), basePrice * (1. + .1)));
      }
    }
  }

  private boolean isStrictlyConvex(double a, double b, double c) {
    return a < b && b > c;
  }

  private boolean isStrictlyConcave(double a, double b, double c) {
    return a > b && b < c;
  }

  @Override
  public String toString() {
    return "Wypukły{}";
  }
}
