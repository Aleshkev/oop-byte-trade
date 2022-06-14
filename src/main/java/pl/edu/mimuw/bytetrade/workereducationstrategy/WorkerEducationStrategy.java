package pl.edu.mimuw.bytetrade.workereducationstrategy;

import pl.edu.mimuw.bytetrade.worker.Worker;

public abstract class WorkerEducationStrategy {
  public abstract boolean whetherToBeLearningToday(Worker worker);

  public boolean equals(Object o) {
    throw new UnsupportedOperationException("Don't compare WorkerEducationStrategy objects.");
  }

  @Override
  public int hashCode() {
    throw new UnsupportedOperationException("Don't hash WorkerEducationStrategy objects.");
  }
}
