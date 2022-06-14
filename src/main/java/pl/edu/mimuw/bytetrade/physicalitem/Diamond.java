package pl.edu.mimuw.bytetrade.physicalitem;

import pl.edu.mimuw.bytetrade.itemtype.VirtualItem;

import java.util.function.Function;

public class Diamond extends PhysicalItem {

  Diamond() {}

  @Override
  public VirtualItem getVirtual() {
    return VirtualItem.Diamond;
  }

  @Override
  public <T> T match(
      Function<Food, T> whenFood,
      Function<Clothes, T> whenClothes,
      Function<Tool, T> whenTool,
      Function<pl.edu.mimuw.bytetrade.physicalitem.Diamond, T> whenDiamond,
      Function<Program, T> whenProgram) {
    return whenDiamond.apply(this);
  }

  public boolean equals(Object o) {
    if (this == o) return true;
    return o != null && getClass() == o.getClass();
  }

  @Override
  public String toString() {
    return "Diamond{}";
  }
}
