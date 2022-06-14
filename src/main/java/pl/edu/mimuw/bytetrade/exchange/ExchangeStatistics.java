package pl.edu.mimuw.bytetrade.exchange;

import pl.edu.mimuw.bytetrade.itemtype.VirtualItem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ExchangeStatistics {
  private final Map<VirtualItem, Double> zerothDayPrices;

  private final Map<Integer, Set<WorkerBuyOffer>> workerBuyOffers;
  private final Map<Integer, Set<WorkerSellOffer>> workerSellOffers;
  private final Map<Integer, Set<SpeculatorBuyOffer>> speculatorBuyOffers;
  private final Map<Integer, Set<SpeculatorSellOffer>> speculatorSellOffers;
  private final Map<Integer, Set<SpeculatorBuyOffer>> workerToSpeculatorDeals;
  private final Map<Integer, Set<SpeculatorSellOffer>> speculatorToWorkerDeals;

  public ExchangeStatistics(Map<VirtualItem, Double> zerothDayPrices) {
    this.zerothDayPrices = zerothDayPrices;

    workerBuyOffers = new HashMap<>();
    workerSellOffers = new HashMap<>();
    speculatorBuyOffers = new HashMap<>();
    speculatorSellOffers = new HashMap<>();
    workerToSpeculatorDeals = new HashMap<>();
    speculatorToWorkerDeals = new HashMap<>();
  }

  public Set<WorkerBuyOffer> getWorkerBuyOffers(int day) {
    return Set.copyOf(workerBuyOffers.getOrDefault(day, new HashSet<>()));
  }

  public Set<WorkerSellOffer> getWorkerSellOffers(int day) {
    return Set.copyOf(workerSellOffers.getOrDefault(day, new HashSet<>()));
  }

  public Set<SpeculatorBuyOffer> getSpeculatorBuyOffers(int day) {
    return Set.copyOf(speculatorBuyOffers.getOrDefault(day, new HashSet<>()));
  }

  public Set<SpeculatorSellOffer> getSpeculatorSellOffers(int day) {
    return Set.copyOf(speculatorSellOffers.getOrDefault(day, new HashSet<>()));
  }

  public void addWorkerBuyOffer(Exchange exchange, WorkerBuyOffer offer) {
    addToSet(exchange, offer, workerBuyOffers);
  }

  private <T> void addToSet(Exchange exchange, T offer, Map<Integer, Set<T>> offers) {
    var day = exchange.getSimulation().getDayNumber();
    if (!offers.containsKey(day)) offers.put(day, new HashSet<>());
    offers.get(day).add(offer);
  }

  public void addWorkerSellOffer(Exchange exchange, WorkerSellOffer offer) {
    addToSet(exchange, offer, workerSellOffers);
  }

  public void addSpeculatorBuyOffer(Exchange exchange, SpeculatorBuyOffer offer) {
    addToSet(exchange, offer, speculatorBuyOffers);
  }

  public void addSpeculatorSellOffer(Exchange exchange, SpeculatorSellOffer offer) {
    addToSet(exchange, offer, speculatorSellOffers);
  }

  public void addWorkerToSpeculatorDeal(Exchange exchange, SpeculatorBuyOffer offer) {
    addToSet(exchange, offer, workerToSpeculatorDeals);
  }

  public void addSpeculatorToWorkerDeal(Exchange exchange, SpeculatorSellOffer offer) {
    addToSet(exchange, offer, speculatorToWorkerDeals);
  }
}
