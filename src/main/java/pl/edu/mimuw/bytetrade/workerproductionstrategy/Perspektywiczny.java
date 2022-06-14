package pl.edu.mimuw.bytetrade.workerproductionstrategy;

import pl.edu.mimuw.bytetrade.itemtype.VirtualItem;
import pl.edu.mimuw.bytetrade.worker.Worker;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Produces the thing with the highest increase in average price from {@code perspectiveHistory}
 * days ago to yesterday.
 */
final class Perspektywiczny extends WorkerProductionStrategy {

  private final int perspectiveHistory;

  Perspektywiczny(int perspectiveHistory) {
    if (perspectiveHistory < 1) throw new IllegalArgumentException("Lookbehind must be positive.");
    this.perspectiveHistory = perspectiveHistory;
  }

  @Override
  public String toString() {
    return "Perspektywiczny{" + "perspectiveHistory=" + perspectiveHistory + '}';
  }

  @Override
  public VirtualItem whatToProduceToday(Worker worker) {
    var exchange = worker.getSimulation().getExchange();
    var day = worker.getSimulation().getDayNumber();

    return Arrays.stream(VirtualItem.values())
        .sorted(Comparator.comparingInt(VirtualItem::getIndex).reversed())
        .max(
            Comparator.comparingDouble(
                virtualItem ->
                    exchange
                            .getStatistics()
                            .getAveragePriceXDaysAgo(
                                worker.whatWouldProduce(virtualItem),
                                Math.min(perspectiveHistory, day))
                        - exchange
                            .getStatistics()
                            .getAveragePriceXDaysAgo(worker.whatWouldProduce(virtualItem), 1)))
        .orElse(VirtualItem.Diamond);
  }
}
