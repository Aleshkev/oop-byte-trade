package pl.edu.mimuw.bytetrade.exchange;

import pl.edu.mimuw.bytetrade.counter.Stack;
import pl.edu.mimuw.bytetrade.physicalitem.Diamond;
import pl.edu.mimuw.bytetrade.physicalitem.PhysicalItem;
import pl.edu.mimuw.bytetrade.physicalitem.PhysicalItemFactory;
import pl.edu.mimuw.bytetrade.speculator.Speculator;

import java.util.Objects;

public class SpeculatorBuyOffer {
  public final Stack<PhysicalItem> items;
  public final double price;

  public final Speculator speculator;

  public SpeculatorBuyOffer(Speculator speculator, Stack<PhysicalItem> items, double price) {
    this.speculator = speculator;
    this.items = items;
    this.price = price;
  }

  public Stack<Diamond> totalPrice() {
    return new Stack<>(PhysicalItemFactory.diamond(), items.count * price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(items, price, speculator);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SpeculatorBuyOffer that = (SpeculatorBuyOffer) o;
    return price == that.price && items == that.items && speculator.equals(that.speculator);
  }

  @Override
  public String toString() {
    return "SpeculatorBuyOffer{"
        + "item="
        + items
        + ", price="
        + price
        + ", speculator="
        + speculator
        + '}';
  }
}
