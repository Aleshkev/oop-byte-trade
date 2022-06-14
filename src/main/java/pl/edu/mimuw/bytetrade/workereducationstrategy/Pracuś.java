package pl.edu.mimuw.bytetrade.workereducationstrategy;

import pl.edu.mimuw.bytetrade.worker.Worker;

final class Pracuś extends WorkerEducationStrategy {
  public Pracuś() {}

  @Override
  public boolean whetherToBeLearningToday(Worker worker) {
    return false;
  }
}
