package pl.edu.mimuw.bytetrade.physicalitem;

import pl.edu.mimuw.bytetrade.itemtype.VirtualItem;

import java.util.function.Function;

public class Food extends PhysicalItem {
  Food() {}

  @Override
  public VirtualItem getVirtual() {
    return VirtualItem.Food;
  }

  @Override
  public <T> T match(
      Function<pl.edu.mimuw.bytetrade.physicalitem.Food, T> whenFood,
      Function<Clothes, T> whenClothes,
      Function<Tool, T> whenTool,
      Function<Diamond, T> whenDiamond,
      Function<Program, T> whenProgram) {
    return whenFood.apply(this);
  }

  @Override
  public String toString() {
    return "Food{}";
  }
}
