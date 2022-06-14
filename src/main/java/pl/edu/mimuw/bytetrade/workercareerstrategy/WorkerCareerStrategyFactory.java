package pl.edu.mimuw.bytetrade.workercareerstrategy;

public class WorkerCareerStrategyFactory {
  private WorkerCareerStrategyFactory() {}

  public static WorkerCareerStrategy conservative() {
    return new Conservative();
  }

  public static WorkerCareerStrategy revolutionary() {
    return new Revolutionary();
  }
}
