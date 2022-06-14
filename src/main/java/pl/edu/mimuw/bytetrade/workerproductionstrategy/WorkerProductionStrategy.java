package pl.edu.mimuw.bytetrade.workerproductionstrategy;

import pl.edu.mimuw.bytetrade.itemtype.VirtualItem;
import pl.edu.mimuw.bytetrade.worker.Worker;

public abstract class WorkerProductionStrategy {
  public abstract VirtualItem whatToProduceToday(Worker worker);
}
