package pl.edu.mimuw.bytetrade.physicalitem;

import pl.edu.mimuw.bytetrade.itemtype.VirtualItem;

import java.util.function.Consumer;
import java.util.function.Function;

import static pl.edu.mimuw.bytetrade.util.FunctionUtil.consumerToFunction;

public abstract class PhysicalItem implements Comparable<PhysicalItem> {

  public abstract VirtualItem getVirtual();

  public void matchConsumer(
      Consumer<Food> whenFood,
      Consumer<Clothes> whenClothes,
      Consumer<Tool> whenTool,
      Consumer<Diamond> whenDiamond,
      Consumer<Program> whenProgram) {
    match(
        consumerToFunction(whenFood),
        consumerToFunction(whenClothes),
        consumerToFunction(whenTool),
        consumerToFunction(whenDiamond),
        consumerToFunction(whenProgram));
  }

  public abstract <T> T match(
      Function<Food, T> whenFood,
      Function<Clothes, T> whenClothes,
      Function<Tool, T> whenTool,
      Function<Diamond, T> whenDiamond,
      Function<Program, T> whenProgram);

  @Override
  public int compareTo(PhysicalItem physicalItem) {
    // TODO
    // This should compare first to check if the items are of the same virtual type, and then by
    // quality / level.
    return 0;
  }
}
