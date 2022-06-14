package pl.edu.mimuw.bytetrade.workereducationstrategy;

import pl.edu.mimuw.bytetrade.worker.Worker;

final class Oszczędny extends WorkerEducationStrategy {
  private final int diamondsLimit;

  public Oszczędny(int diamondsLimit) {
    this.diamondsLimit = diamondsLimit;
  }

  @Override
  public boolean whetherToBeLearningToday(Worker worker) {
    return worker.howManyDiamondsDoTheyHave() > diamondsLimit;
  }

  @Override
  public String toString() {
    return "Oszczędny{" + "diamondsLimit=" + diamondsLimit + '}';
  }
}
