package pl.edu.mimuw.bytetrade.workerproductionstrategy;

public class WorkerProductionStrategyFactory {
  private WorkerProductionStrategyFactory() {}

  public static WorkerProductionStrategy krótkowzroczny() {
    return new Krótkowzroczny();
  }

  public static WorkerProductionStrategy chciwy() {
    return new Chciwy();
  }

  public static WorkerProductionStrategy średniak(int averageProductionHistory) {
    return new Średniak(averageProductionHistory);
  }

  public static WorkerProductionStrategy perspektywiczny(int perspectiveHistory) {
    return new Perspektywiczny(perspectiveHistory);
  }

  public static WorkerProductionStrategy losowy() {
    return new Losowy();
  }
}
