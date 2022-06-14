package pl.edu.mimuw.bytetrade.physicalitem;

import pl.edu.mimuw.bytetrade.itemtype.VirtualItem;

import java.util.function.Function;

public final class Program extends PhysicalItem {
  public final int level;

  Program(int level) {
    this.level = level;
  }

  @Override
  public VirtualItem getVirtual() {
    return VirtualItem.Program;
  }

  @Override
  public <T> T match(
      Function<Food, T> whenFood,
      Function<Clothes, T> whenClothes,
      Function<Tool, T> whenTool,
      Function<Diamond, T> whenDiamond,
      Function<Program, T> whenProgram) {
    return whenProgram.apply(this);
  }

  @Override
  public String toString() {
    return "Program{" + "level=" + level + '}';
  }
}
