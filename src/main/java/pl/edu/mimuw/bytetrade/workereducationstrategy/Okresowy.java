package pl.edu.mimuw.bytetrade.workereducationstrategy;

import pl.edu.mimuw.bytetrade.worker.Worker;

final class Okresowy extends WorkerEducationStrategy {
  private final int period;

  public Okresowy(int period) {
    this.period = period;
  }

  @Override
  public boolean whetherToBeLearningToday(Worker worker) {
    return worker.getSimulation().getDayNumber() % period == 0;
  }

  @Override
  public String toString() {
    return "Okresowy{" + "period=" + period + '}';
  }
}
