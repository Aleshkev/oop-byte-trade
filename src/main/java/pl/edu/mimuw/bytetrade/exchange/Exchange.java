package pl.edu.mimuw.bytetrade.exchange;

import pl.edu.mimuw.bytetrade.exchangestrategy.ExchangeStrategy;
import pl.edu.mimuw.bytetrade.itemtype.VirtualItem;
import pl.edu.mimuw.bytetrade.physicalitem.PhysicalItem;
import pl.edu.mimuw.bytetrade.simulation.Simulation;

import java.util.Map;
import java.util.function.Predicate;

public class Exchange {
  private final Simulation simulation;
  private final ExchangeStrategy strategy;
  private final ExchangeStatistics statistics;

  public Exchange(
      Simulation simulation, ExchangeStrategy strategy, Map<VirtualItem, Double> zerothDayPrices) {
    this.simulation = simulation;

    this.strategy = strategy;
    this.statistics = new ExchangeStatistics(zerothDayPrices);
  }

  /** Notify the exchange a new day has just begun. */
  public void newDay() {
    assert workerSellOffers.isEmpty();
    assert workerBuyOffers.isEmpty();
    assert speculatorSellOffers.isEmpty();
    assert speculatorBuyOffers.isEmpty();
  }

  public void doExchange() {
    var workers = strategy.prioritizeWorkers(this, simulation.getActiveWorkers());

    for (var worker : workers) {
      var sellOffers = WorkerSellOffer.prioritizeOffers(workerSellOffers.get(worker));
      for (var sellOffer : sellOffers) {}
    }

    for (var worker : workers) {
      var buyOffers = WorkerBuyOffer.prioritizeOffers(workerBuyOffers.get(worker));
      for (var buyOffer : buyOffers) {
        var remainingBuyOffer = buyOffer;
        while (remainingBuyOffer != null) {
          var sellOffers = SpeculatorSellOffer.prioritizeOffers(speculatorSellOffers, buyOffer);
          if (sellOffers.isEmpty()) break;
          var sellOffer = sellOffers.get(0);

          var tradedAmount =
              Math.min(
                  Math.min(remainingBuyOffer.items.count, sellOffer.items.count),
                  worker.howManyDiamondsDoTheyHave() / sellOffer.price);
          assert worker.canAfford(tradedAmount * sellOffer.price);
          remainingBuyOffer =
              tradedAmount == remainingBuyOffer.items.count
                  ? null
                  : remainingBuyOffer.withCount(remainingBuyOffer.items.count - tradedAmount);
          var remainingSellOffer =
              tradedAmount == sellOffer.items.count
                  ? null
                  : sellOffer.withCount(sellOffer.items.count - tradedAmount);
        }
      }
    }
  }

  public void addWorkerSellOffer(WorkerSellOffer offer) {
    workerSellOffers.get(offer.worker).add(offer);
  }

  public void addWorkerBuyOffer(WorkerBuyOffer offer) {
    workerBuyOffers.get(offer.worker).add(offer);
  }

  public void addSpeculatorSellOffer(SpeculatorSellOffer offer) {
    speculatorSellOffers.add(offer);
  }

  public void addSpeculatorBuyOffer(SpeculatorBuyOffer offer) {
    speculatorBuyOffers.add(offer);
  }

  public double getAveragePriceXDaysAgo(Predicate<PhysicalItem> filterItems, int x) {
    if (simulation.getDayNumber() - x < 0)
      throw new IllegalArgumentException("Not enough days to look back.");
  }

  public double getAveragePriceOverLastNDays(Predicate<PhysicalItem> filterItems, int n) {

    for (var i = 0; simulation.getDayNumber() - i >= 0 && i < n; ++i) {}
  }

  /** How many of the items matching the filter have been sold */
  public double getCountOverLastNDays(Predicate<PhysicalItem> filterItems, int n) {}

  public double getCountXDaysAgo(Predicate<PhysicalItem> filterItems, int n) {}

  public Simulation getSimulation() {
    return simulation;
  }
}
