package pl.edu.mimuw.bytetrade.physicalitem;

import pl.edu.mimuw.bytetrade.itemtype.VirtualItem;

import java.util.Objects;
import java.util.function.Function;

public class Clothes extends PhysicalItem {
  public final int quality;
  public final int durability;

  Clothes(int quality) {
    this(quality, quality * quality);
  }

  Clothes(int quality, int durability) {
    super();
    this.quality = quality;
    this.durability = durability;
  }

  @Override
  public int hashCode() {
    return Objects.hash(quality, durability);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    pl.edu.mimuw.bytetrade.physicalitem.Clothes clothes =
        (pl.edu.mimuw.bytetrade.physicalitem.Clothes) o;
    return quality == clothes.quality && durability == clothes.durability;
  }

  @Override
  public String toString() {
    return "Clothes{" + "quality=" + quality + ", durability=" + durability + '}';
  }

  public Clothes withDecreasedDurability() {
    return new Clothes(quality, durability - 1);
  }

  public boolean isUsedUp() {
    return durability <= 0;
  }

  @Override
  public VirtualItem getVirtual() {
    return VirtualItem.Clothes;
  }

  @Override
  public <T> T match(
      Function<Food, T> whenFood,
      Function<pl.edu.mimuw.bytetrade.physicalitem.Clothes, T> whenClothes,
      Function<Tool, T> whenTool,
      Function<Diamond, T> whenDiamond,
      Function<Program, T> whenProgram) {
    return whenClothes.apply(this);
  }
}
