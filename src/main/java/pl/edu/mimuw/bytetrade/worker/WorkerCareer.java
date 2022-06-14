package pl.edu.mimuw.bytetrade.worker;

import pl.edu.mimuw.bytetrade.itemtype.VirtualItem;

import java.util.HashMap;
import java.util.Map;

/** Manages the worker's career and specializations. */
public final class WorkerCareer {
  private final Map<VirtualItem, Integer> levelAtProductionOf;
  private VirtualItem currentSpecialization;

  public WorkerCareer(VirtualItem currentSpecialization, int levelAtCurrentSpecialization) {
    this.levelAtProductionOf = new HashMap<>();
    levelAtProductionOf.put(currentSpecialization, levelAtCurrentSpecialization);
    this.currentSpecialization = currentSpecialization;
  }

  public VirtualItem getCurrentSpecialization() {
    return currentSpecialization;
  }

  public void setSpecializationThenLevelUp(VirtualItem specialization) {
    currentSpecialization = specialization;
    levelAtProductionOf.put(specialization, getLevelAtProductionOf(specialization) + 1);
  }

  public int getLevelAtProductionOf(VirtualItem item) {
    return levelAtProductionOf.getOrDefault(item, 0);
  }

  public int getBonusToProductionOf(VirtualItem item) {
    if (item != currentSpecialization) return 0;
    var level = getLevelAtProductionOf(item);
    assert level > 0;
    if (level == 1) return 50;
    if (level == 2) return 150;
    if (level == 3) return 300;
    return 300 + 25 * (level - 3);
  }

  @Override
  public String toString() {
    return "WorkerCareer{"
        + ("levelAtProductionOf=" + levelAtProductionOf)
        + (", currentSpecialization=" + currentSpecialization)
        + '}';
  }
}
