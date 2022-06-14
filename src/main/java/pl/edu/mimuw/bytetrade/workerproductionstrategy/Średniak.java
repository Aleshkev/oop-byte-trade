package pl.edu.mimuw.bytetrade.workerproductionstrategy;

import pl.edu.mimuw.bytetrade.itemtype.VirtualItem;
import pl.edu.mimuw.bytetrade.worker.Worker;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

/**
 * Produces the thing the maximal average price of which was the highest during the last {@code
 * averageProductionHistory} days.
 */
final class Średniak extends WorkerProductionStrategy {

  private final int averageProductionHistory;

  Średniak(int averageProductionHistory) {
    if (averageProductionHistory < 1)
      throw new IllegalArgumentException("Lookbehind must be positive.");
    this.averageProductionHistory = averageProductionHistory;
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
                    IntStream.range(1, Math.min(averageProductionHistory, day))
                        .mapToDouble(
                            daysAgo ->
                                exchange.getAveragePriceXDaysAgo(
                                    worker.whatWouldProduce(virtualItem)::equals, daysAgo))
                        .max()
                        .orElse(0)))
        .orElse(VirtualItem.Diamond);
  }

  @Override
  public String toString() {
    return "Średniak{" + "averageProductionHistory=" + averageProductionHistory + '}';
  }
}
