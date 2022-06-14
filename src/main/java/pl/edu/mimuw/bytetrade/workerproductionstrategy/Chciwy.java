package pl.edu.mimuw.bytetrade.workerproductionstrategy;

import pl.edu.mimuw.bytetrade.itemtype.VirtualItem;
import pl.edu.mimuw.bytetrade.worker.Worker;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Produces the thing that will bring the most profit assuming it is bought at the average price
 * from one day ago (including the quality / level of the thing).
 */
final class Chciwy extends WorkerProductionStrategy {
  @Override
  public VirtualItem whatToProduceToday(Worker worker) {
    var exchange = worker.getSimulation().getExchange();

    return Arrays.stream(VirtualItem.values())
        .sorted(Comparator.comparingInt(VirtualItem::getIndex).reversed())
        .min(
            Comparator.comparingDouble(
                virtualItem ->
                    worker.howMuchWouldProduce(virtualItem)
                        * exchange.getAveragePriceXDaysAgo(
                            worker.whatWouldProduce(virtualItem)::equals, 1)))
        .orElse(VirtualItem.Diamond);
  }

  @Override
  public String toString() {
    return "Chciwy{}";
  }
}
