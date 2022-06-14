package pl.edu.mimuw.bytetrade.workerproductionstrategy;

import pl.edu.mimuw.bytetrade.itemtype.VirtualItem;
import pl.edu.mimuw.bytetrade.worker.Worker;

/** Produces a random item. */
final class Losowy extends WorkerProductionStrategy {
  @Override
  public VirtualItem whatToProduceToday(Worker worker) {
    return VirtualItem.values()[
        worker.getSimulation().getRandom().nextInt(VirtualItem.values().length)];
  }

  @Override
  public String toString() {
    return "Losowy{}";
  }
}
