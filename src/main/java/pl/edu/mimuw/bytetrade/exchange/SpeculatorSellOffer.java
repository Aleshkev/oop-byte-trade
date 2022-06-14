package pl.edu.mimuw.bytetrade.exchange;

import pl.edu.mimuw.bytetrade.counter.Stack;
import pl.edu.mimuw.bytetrade.physicalitem.Diamond;
import pl.edu.mimuw.bytetrade.physicalitem.PhysicalItem;
import pl.edu.mimuw.bytetrade.physicalitem.PhysicalItemFactory;
import pl.edu.mimuw.bytetrade.speculator.Speculator;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class SpeculatorSellOffer {
  public final Stack<PhysicalItem> items;
  public final double price;
  public final Speculator speculator;

  public SpeculatorSellOffer(Speculator speculator, Stack<PhysicalItem> items, double price) {
    this.speculator = speculator;
    this.items = items;
    this.price = price;
  }

  /** Orders the offers according to attractiveness from the perspective of the {@code buyOffer}. */
  public static List<SpeculatorSellOffer> prioritizeOffers(
      Set<SpeculatorSellOffer> sellOffers, WorkerBuyOffer buyOffer) {
    return sellOffers.stream()
        .filter(offer -> offer.items.item.getVirtual() == buyOffer.items.item)
        .sorted(Comparator.comparing(offer -> offer.items.item))
        .collect(Collectors.toList());
  }

  @Override
  public int hashCode() {
    return Objects.hash(items, price, speculator);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SpeculatorSellOffer that = (SpeculatorSellOffer) o;
    return price == that.price && items.equals(that.items) && speculator.equals(that.speculator);
  }

  @Override
  public String toString() {
    return "SpeculatorSellOffer{"
        + "item="
        + items
        + ", price="
        + price
        + ", speculator="
        + speculator
        + '}';
  }

  public SpeculatorSellOffer withCount(double count) {
    return new SpeculatorSellOffer(speculator, items.withCount(count), price);
  }

  public Stack<Diamond> totalPrice() {
    return new Stack<>(PhysicalItemFactory.diamond(), items.count * price);
  }
}
