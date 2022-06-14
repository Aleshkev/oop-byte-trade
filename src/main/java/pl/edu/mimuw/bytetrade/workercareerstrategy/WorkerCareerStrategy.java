package pl.edu.mimuw.bytetrade.workercareerstrategy;

import pl.edu.mimuw.bytetrade.itemtype.VirtualItem;
import pl.edu.mimuw.bytetrade.worker.Worker;

public abstract class WorkerCareerStrategy {

  public abstract VirtualItem whatToSpecializeInToday(Worker worker);
}
