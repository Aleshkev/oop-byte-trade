package pl.edu.mimuw.bytetrade.workercareerstrategy;

import pl.edu.mimuw.bytetrade.itemtype.VirtualItem;
import pl.edu.mimuw.bytetrade.worker.Worker;

final class Conservative extends WorkerCareerStrategy {
  @Override
  public VirtualItem whatToSpecializeInToday(Worker worker) {
    return worker.getCareer().getCurrentSpecialization();
  }

  @Override
  public String toString() {
    return "Conservative{}";
  }
}
