package pl.edu.mimuw.bytetrade.exchange;

import pl.edu.mimuw.bytetrade.itemtype.VirtualItem;
import pl.edu.mimuw.bytetrade.physicalitem.PhysicalItem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class ExchangeStatistics {

  private final Map<VirtualItem, Double> zerothDayPrices;
  private final Map<Integer, Set<WorkerBuyOffer>> workerBuyOffers;
  private final Map<Integer, Set<WorkerSellOffer>> workerSellOffers;
  private final Map<Integer, Set<SpeculatorBuyOffer>> speculatorBuyOffers;
  private final Map<Integer, Set<SpeculatorSellOffer>> speculatorSellOffers;
  private final Map<Integer, Set<SpeculatorBuyOffer>> workerToSpeculatorDeals;
  private final Map<Integer, Set<SpeculatorSellOffer>> speculatorToWorkerDeals;
  private final Exchange exchange;

  public ExchangeStatistics(Exchange exchange, Map<VirtualItem, Double> zerothDayPrices) {
    this.exchange = exchange;
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

  public Set<SpeculatorBuyOffer> getSpeculatorBuyOffers(int day) {
    return Set.copyOf(speculatorBuyOffers.getOrDefault(day, new HashSet<>()));
  }

  public void addWorkerBuyOffer(WorkerBuyOffer offer) {
    addToSet(offer, workerBuyOffers);
  }

  private <T> void addToSet(T offer, Map<Integer, Set<T>> offers) {
    var day = exchange.getSimulation().getDayNumber();
    if (!offers.containsKey(day)) offers.put(day, new HashSet<>());
    offers.get(day).add(offer);
  }

  public void addWorkerSellOffer(WorkerSellOffer offer) {
    addToSet(offer, workerSellOffers);
  }

  public void addSpeculatorBuyOffer(SpeculatorBuyOffer offer) {
    addToSet(offer, speculatorBuyOffers);
  }

  public void addSpeculatorSellOffer(SpeculatorSellOffer offer) {
    addToSet(offer, speculatorSellOffers);
  }

  public void addWorkerToSpeculatorDeal(SpeculatorBuyOffer offer) {
    addToSet(offer, workerToSpeculatorDeals);
  }

  public void addSpeculatorToWorkerDeal(SpeculatorSellOffer offer) {
    addToSet(offer, speculatorToWorkerDeals);
  }

  /** Average price {@code x} days ago of the items of type {@code virtualItem}. */
  public double getAveragePriceXDaysAgo(VirtualItem virtualItem, int x) {
    return getAveragePriceXDaysAgo(virtualItem, item -> true, x);
  }

  /**
   * Average price {@code x} days ago of the items of type {@code virtualItem} matching the
   * predicate.
   */
  public double getAveragePriceXDaysAgo(
      VirtualItem virtualItem, Predicate<PhysicalItem> filterItems, int x) {
    var day = exchange.getSimulation().getDayNumber();
    var dayToLookAt = day - x;
    if (dayToLookAt < 0) throw new IllegalArgumentException("Not enough days to look back.");
    if (dayToLookAt == 0) return zerothDayPrices.get(virtualItem);
    return getWorkerToSpeculatorDeals(day).stream()
            .filter(offer -> filterItems.test(offer.items.item))
            .mapToDouble(offer -> offer.totalPrice().count)
            .sum()
        + getSpeculatorToWorkerDeals(day).stream()
            .filter(offer -> filterItems.test(offer.items.item))
            .mapToDouble(offer -> offer.totalPrice().count)
            .sum();
  }

  public Set<SpeculatorBuyOffer> getWorkerToSpeculatorDeals(int day) {
    return Set.copyOf(workerToSpeculatorDeals.getOrDefault(day, new HashSet<>()));
  }

  public Set<SpeculatorSellOffer> getSpeculatorToWorkerDeals(int day) {
    return Set.copyOf(speculatorToWorkerDeals.getOrDefault(day, new HashSet<>()));
  }

  public double getAveragePriceXDaysAgo(PhysicalItem physicalItem, int x) {
    return getAveragePriceXDaysAgo(physicalItem.getVirtual(), physicalItem::equals, x);
  }

  public double getAveragePriceOverLastNDays(VirtualItem virtualItem, int n) {
    return getAveragePriceOverLastNDays(virtualItem, item -> true, n);
  }

  /**
   * Average price of products of type {@code virtualItem} matching the predicate over the last
   * {@code n} days.
   */
  public double getAveragePriceOverLastNDays(
      VirtualItem virtualItem, Predicate<PhysicalItem> filterItems, int n) {
    // TODO
    return 0;
  }

  public double getAveragePriceOverLastNDays(PhysicalItem physicalItem, int n) {
    return getAveragePriceOverLastNDays(physicalItem.getVirtual(), physicalItem::equals, n);
  }

  /** How many of the items matching the filter have been offered to be sold. */
  public double howManyWereOfferedToBeSoldInTheLastNDays(VirtualItem virtualItem, int n) {
    var day = exchange.getSimulation().getDayNumber();
    return IntStream.range(Math.max(day - n, 0), day - 1)
        .mapToDouble(
            i ->
                getWorkerSellOffers(i).stream().mapToDouble(offer -> offer.items.count).sum()
                    + getSpeculatorSellOffers(i).stream()
                        .mapToDouble(offer -> offer.items.count)
                        .sum())
        .sum();
  }

  public Set<WorkerSellOffer> getWorkerSellOffers(int day) {
    return Set.copyOf(workerSellOffers.getOrDefault(day, new HashSet<>()));
  }

  public Set<SpeculatorSellOffer> getSpeculatorSellOffers(int day) {
    return Set.copyOf(speculatorSellOffers.getOrDefault(day, new HashSet<>()));
  }

  public double howManyWereOfferedToBeSoldByWorkersXDaysAgo(PhysicalItem physicalItem, int x) {
    var day = exchange.getSimulation().getDayNumber();
    if (day - x == 0) return 0;
    return getWorkerSellOffers(day - x).stream().mapToDouble(offer -> offer.items.count).sum();
  }
}
