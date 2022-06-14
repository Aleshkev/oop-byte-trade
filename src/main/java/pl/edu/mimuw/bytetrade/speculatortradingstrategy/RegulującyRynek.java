package pl.edu.mimuw.bytetrade.speculatortradingstrategy;

import pl.edu.mimuw.bytetrade.exchange.Exchange;
import pl.edu.mimuw.bytetrade.physicalitem.PhysicalItemFactory;
import pl.edu.mimuw.bytetrade.speculator.Speculator;

final class RegulującyRynek extends SpeculatorTradingStrategy {
  @Override
  public void makeOffers(Speculator speculator, Exchange exchange) {
    var day = speculator.getSimulation().getDayNumber();
    if (day < 2) return;

    for (var items : PhysicalItemFactory.getAllItems()) {
      var item = items.item;

      var pI = exchange.getCountXDaysAgo(item::equals, 0);
      var pJ = exchange.getCountXDaysAgo(item::equals, 1);
      var basePrice = exchange.getAveragePriceXDaysAgo(item::equals, 1) * (day / Math.max());
    }
  }

  @Override
  public String toString() {
    return "RegulującyRynek{}";
  }
}
