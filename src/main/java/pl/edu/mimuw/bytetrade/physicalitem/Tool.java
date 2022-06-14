package pl.edu.mimuw.bytetrade.physicalitem;

import pl.edu.mimuw.bytetrade.itemtype.VirtualItem;

import java.util.function.Function;

public final class Tool extends PhysicalItem {
  public final int quality;

  Tool(int quality) {
    this.quality = quality;
  }

  public int getBonusToProductionWhenUsed() {
    return quality;
  }

  @Override
  public VirtualItem getVirtual() {
    return VirtualItem.Tool;
  }

  @Override
  public <T> T match(
      Function<Food, T> whenFood,
      Function<Clothes, T> whenClothes,
      Function<Tool, T> whenTool,
      Function<Diamond, T> whenDiamond,
      Function<Program, T> whenProgram) {
    return whenTool.apply(this);
  }

  @Override
  public String toString() {
    return "Tool{" + "quality=" + quality + '}';
  }
}
