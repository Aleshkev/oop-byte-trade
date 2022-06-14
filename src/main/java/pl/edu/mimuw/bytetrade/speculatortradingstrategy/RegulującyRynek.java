package pl.edu.mimuw.bytetrade.speculatortradingstrategy;

import pl.edu.mimuw.bytetrade.counter.Stack;
import pl.edu.mimuw.bytetrade.exchange.Exchange;
import pl.edu.mimuw.bytetrade.exchange.SpeculatorBuyOffer;
import pl.edu.mimuw.bytetrade.exchange.SpeculatorSellOffer;
import pl.edu.mimuw.bytetrade.physicalitem.PhysicalItemFactory;
import pl.edu.mimuw.bytetrade.speculator.Speculator;

final class RegulującyRynek extends SpeculatorTradingStrategy {
  @Override
  public void makeOffers(Speculator speculator, Exchange exchange) {
    var day = speculator.getSimulation().getDayNumber();
    if (day < 2) return;

    var itemsToTrade = speculator.getItemsToTrade();

    for (var items : exchange.getAllItems()) {
      var item = items.item;

      var pI = exchange.getStatistics().howManyWereOfferedToBeSoldByWorkersXDaysAgo(item, 0);
      var pJ = exchange.getStatistics().howManyWereOfferedToBeSoldByWorkersXDaysAgo(item, 1);
      var basePrice =
          exchange.getStatistics().getAveragePriceXDaysAgo(item, 1) * pI / Math.max(pJ, 1);

      exchange.addSpeculatorBuyOffer(
          new SpeculatorBuyOffer(speculator, new Stack<>(item, 100), basePrice * (1. - .1)));
      exchange.addSpeculatorSellOffer(
          new SpeculatorSellOffer(
              speculator, new Stack<>(item, itemsToTrade.count(item)), basePrice * (1. + .1)));
    }
  }

  @Override
  public String toString() {
    return "RegulującyRynek{}";
  }
}
