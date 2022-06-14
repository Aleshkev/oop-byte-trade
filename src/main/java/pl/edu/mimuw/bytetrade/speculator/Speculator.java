package pl.edu.mimuw.bytetrade.speculator;

import pl.edu.mimuw.bytetrade.exchange.Exchange;
import pl.edu.mimuw.bytetrade.exchange.SpeculatorBuyOffer;
import pl.edu.mimuw.bytetrade.exchange.SpeculatorSellOffer;
import pl.edu.mimuw.bytetrade.physicalitem.PhysicalItemCollection;
import pl.edu.mimuw.bytetrade.simulation.Agent;
import pl.edu.mimuw.bytetrade.simulation.Simulation;
import pl.edu.mimuw.bytetrade.speculatortradingstrategy.SpeculatorTradingStrategy;

public class Speculator extends Agent {
  private final SpeculatorTradingStrategy tradingStrategy;

  private final PhysicalItemCollection itemsToTrade;

  public Speculator(Simulation simulation, int id, SpeculatorTradingStrategy tradingStrategy) {
    super(simulation, id);
    this.tradingStrategy = tradingStrategy;
    this.itemsToTrade = new PhysicalItemCollection();
  }

  public PhysicalItemCollection getItemsToTrade() {
    return itemsToTrade;
  }

  public void makeOffers(Exchange exchange) {
    tradingStrategy.makeOffers(this, exchange);
  }

  public void finalizeBuyOffer(SpeculatorBuyOffer offer) {
    itemsToTrade.remove(offer.totalPrice());
    itemsToTrade.add(offer.items);
  }

  public void finalizeSellOffer(SpeculatorSellOffer offer) {
    itemsToTrade.add(offer.totalPrice());
    itemsToTrade.remove(offer.items);
  }
}
