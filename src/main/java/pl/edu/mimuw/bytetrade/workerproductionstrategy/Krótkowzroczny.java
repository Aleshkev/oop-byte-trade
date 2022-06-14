package pl.edu.mimuw.bytetrade.workerproductionstrategy;

import pl.edu.mimuw.bytetrade.itemtype.VirtualItem;
import pl.edu.mimuw.bytetrade.worker.Worker;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Produces the item that has the highest average value, disregarding the quality/level or the
 * amount that will be produced.
 */
final class Krótkowzroczny extends WorkerProductionStrategy {
  @Override
  public VirtualItem whatToProduceToday(Worker worker) {
    var exchange = worker.getSimulation().getExchange();

    return Arrays.stream(VirtualItem.values())
        .sorted(Comparator.comparingInt(VirtualItem::getIndex).reversed())
        .min(
            Comparator.comparingDouble(
                virtualItem -> exchange.getStatistics().getAveragePriceXDaysAgo(virtualItem, 1)))
        .orElse(VirtualItem.Diamond);
  }

  @Override
  public String toString() {
    return "Krótkowzroczny{}";
  }
}
