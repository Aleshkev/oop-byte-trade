package pl.edu.mimuw.bytetrade.simulation;

public abstract class Agent {

  private final Simulation simulation;
  private final int id;

  protected Agent(Simulation simulation, int id) {
    this.simulation = simulation;
    this.id = id;
  }

  public Simulation getSimulation() {
    return simulation;
  }

  public int getId() {
    return id;
  }
}
