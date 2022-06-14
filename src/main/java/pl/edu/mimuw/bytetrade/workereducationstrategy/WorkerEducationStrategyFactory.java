package pl.edu.mimuw.bytetrade.workereducationstrategy;

public class WorkerEducationStrategyFactory {
  private WorkerEducationStrategyFactory() {}

  public static WorkerEducationStrategy rozkładowy() {
    return new Rozkładowy();
  }

  public static WorkerEducationStrategy okresowy(int period) {
    return new Okresowy(period);
  }

  public static WorkerEducationStrategy oszczędny(int diamondsLimit) {
    return new Oszczędny(diamondsLimit);
  }

  public static WorkerEducationStrategy student(int buffer, int timeframe) {
    return new Student(buffer, timeframe);
  }

  public static WorkerEducationStrategy pracuś() {
    return new Pracuś();
  }
}
