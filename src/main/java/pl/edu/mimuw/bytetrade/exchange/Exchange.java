package pl.edu.mimuw.bytetrade.exchange;

import pl.edu.mimuw.bytetrade.exchangestrategy.ExchangeStrategy;
import pl.edu.mimuw.bytetrade.itemtype.VirtualItem;
import pl.edu.mimuw.bytetrade.physicalitem.PhysicalItemCollection;
import pl.edu.mimuw.bytetrade.simulation.Simulation;

import java.util.Map;

public class Exchange {
  private final ExchangeStatistics statistics;
  private final Simulation simulation;
  private final ExchangeStrategy strategy;

  public Exchange(
      Simulation simulation, ExchangeStrategy strategy, Map<VirtualItem, Double> zerothDayPrices) {
    this.simulation = simulation;

    this.strategy = strategy;
    this.statistics = new ExchangeStatistics(this, zerothDayPrices);
  }

  /** Notify the exchange a new day has just begun. */
  public void newDay() {
    var day = simulation.getDayNumber();
    assert getStatistics().getWorkerBuyOffers(day).isEmpty();
    assert getStatistics().getWorkerSellOffers(day).isEmpty();
    assert getStatistics().getSpeculatorBuyOffers(day).isEmpty();
    assert getStatistics().getSpeculatorSellOffers(day).isEmpty();
  }

  public ExchangeStatistics getStatistics() {
    return statistics; // TODO: Make immutable / expose only some of the methods here
  }

  public void doExchange() {
    var workers = strategy.prioritizeWorkers(this, simulation.getActiveWorkers());

    // TODO

    //    for (var worker : workers) {
    //      var sellOffers = WorkerSellOffer.prioritizeOffers(workerSellOffers.get(worker));
    //      for (var sellOffer : sellOffers) {}
    //    }
    //
    //    for (var worker : workers) {
    //      var buyOffers = WorkerBuyOffer.prioritizeOffers(workerBuyOffers.get(worker));
    //      for (var buyOffer : buyOffers) {
    //        var remainingBuyOffer = buyOffer;
    //        while (remainingBuyOffer != null) {
    //          var sellOffers = SpeculatorSellOffer.prioritizeOffers(speculatorSellOffers,
    // buyOffer);
    //          if (sellOffers.isEmpty()) break;
    //          var sellOffer = sellOffers.get(0);
    //
    //          var tradedAmount =
    //              Math.min(
    //                  Math.min(remainingBuyOffer.items.count, sellOffer.items.count),
    //                  worker.howManyDiamondsDoTheyHave() / sellOffer.price);
    //          assert worker.canAfford(tradedAmount * sellOffer.price);
    //          remainingBuyOffer =
    //              tradedAmount == remainingBuyOffer.items.count
    //                  ? null
    //                  : remainingBuyOffer.withCount(remainingBuyOffer.items.count - tradedAmount);
    //          var remainingSellOffer =
    //              tradedAmount == sellOffer.items.count
    //                  ? null
    //                  : sellOffer.withCount(sellOffer.items.count - tradedAmount);
    //        }
    //      }
    //    }
  }

  public void addWorkerSellOffer(WorkerSellOffer offer) {
    getStatistics().addWorkerSellOffer(offer);
  }

  public void addWorkerBuyOffer(WorkerBuyOffer offer) {
    getStatistics().addWorkerBuyOffer(offer);
  }

  public void addSpeculatorSellOffer(SpeculatorSellOffer offer) {
    getStatistics().addSpeculatorSellOffer(offer);
  }

  public void addSpeculatorBuyOffer(SpeculatorBuyOffer offer) {
    getStatistics().addSpeculatorBuyOffer(offer);
  }

  public Simulation getSimulation() {
    return simulation;
  }

  /** Returns all the items that could possibly be traded this day. */
  public PhysicalItemCollection getAllItems() {
    // TODO
    return null;
  }
}
