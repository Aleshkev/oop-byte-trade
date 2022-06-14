package pl.edu.mimuw.bytetrade.workercareerstrategy;

import pl.edu.mimuw.bytetrade.itemtype.VirtualItem;
import pl.edu.mimuw.bytetrade.worker.Worker;

import java.util.Arrays;
import java.util.Comparator;

final class Revolutionary extends WorkerCareerStrategy {
  @Override
  public VirtualItem whatToSpecializeInToday(Worker worker) {
    if (worker.getSimulation().getDayNumber() % 7 == 0)
      return worker.getCareer().getCurrentSpecialization();

    var exchange = worker.getSimulation().getExchange();

    var n = Math.max(1, worker.getId() % 17);

    return Arrays.stream(VirtualItem.values())
        .sorted(Comparator.comparingInt(VirtualItem::getIndex).reversed())
        .max(
            Comparator.comparingDouble(
                virtualItem ->
                    exchange
                        .getStatistics()
                        .howManyWereOfferedToBeSoldInTheLastNDays(virtualItem, n)))
        .orElse(VirtualItem.Diamond);
  }

  @Override
  public String toString() {
    return "Revolutionary{}";
  }
}
