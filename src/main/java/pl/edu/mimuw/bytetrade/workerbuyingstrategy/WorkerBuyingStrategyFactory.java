package pl.edu.mimuw.bytetrade.workerbuyingstrategy;

public class WorkerBuyingStrategyFactory {
  private WorkerBuyingStrategyFactory() {}

  public static WorkerBuyingStrategy technofob() {
    return new Technofob();
  }

  public static WorkerBuyingStrategy czyścioszek() {
    return new Czyścioszek();
  }

  public static WorkerBuyingStrategy zmechanizowany(double numberOfTools) {
    return new Zmechanizowany(numberOfTools);
  }

  public static WorkerBuyingStrategy gadżeciarz(double numberOfTools) {
    return new Gadżeciarz(numberOfTools);
  }
}
