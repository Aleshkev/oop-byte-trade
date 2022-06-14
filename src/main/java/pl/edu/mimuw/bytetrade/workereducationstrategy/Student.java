package pl.edu.mimuw.bytetrade.workereducationstrategy;

import pl.edu.mimuw.bytetrade.physicalitem.PhysicalItemFactory;
import pl.edu.mimuw.bytetrade.worker.Worker;

final class Student extends WorkerEducationStrategy {
  int buffer;
  int timeframe;

  public Student(int buffer, int timeframe) {
    this.buffer = buffer;
    this.timeframe = timeframe;
  }

  @Override
  public boolean whetherToBeLearningToday(Worker worker) {
    var exchange = worker.getSimulation().getExchange();

    var averageFoodPrice =
        exchange
            .getStatistics()
            .getAveragePriceOverLastNDays(PhysicalItemFactory.food(), timeframe);
    return worker.howManyDiamondsDoTheyHave() >= 100.0 * buffer * averageFoodPrice;
  }

  @Override
  public String toString() {
    return "Student{" + "buffer=" + buffer + ", timeframe=" + timeframe + '}';
  }
}
