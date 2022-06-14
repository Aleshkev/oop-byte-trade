package pl.edu.mimuw.bytetrade.worker;

public final class WorkerHunger {
  private static final int howManyDaysToDieOfHunger = 3;
  private int howManyDaysHungry;

  public WorkerHunger() {
    this.howManyDaysHungry = 0;
  }

  public void eatAtTheCanteen() {
    eatFoodToday(100);
  }

  public void eatFoodToday(double amount) {
    if (amount < 100) ++howManyDaysHungry;
    else howManyDaysHungry = 0;
  }

  public boolean isDead() {
    return howManyDaysHungry >= howManyDaysToDieOfHunger;
  }

  public int getBonusToProduction() {
    if (howManyDaysHungry == 1) return -100;
    if (howManyDaysHungry == 2) return -300;
    assert howManyDaysHungry < howManyDaysToDieOfHunger;
    return 0;
  }

  @Override
  public String toString() {
    return "WorkerHunger{" + "howManyDaysHungry=" + howManyDaysHungry + '}';
  }
}
