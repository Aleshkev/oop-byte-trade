package pl.edu.mimuw.bytetrade.workereducationstrategy;

import pl.edu.mimuw.bytetrade.worker.Worker;

final class Rozkładowy extends WorkerEducationStrategy {
  public Rozkładowy() {}

  @Override
  public boolean whetherToBeLearningToday(Worker worker) {
    return 1. - 1. / (worker.getSimulation().getDayNumber() + 3.)
        < worker.getSimulation().getRandom().nextDouble();
  }

  @Override
  public String toString() {
    return "Rozkładowy{}";
  }
}
