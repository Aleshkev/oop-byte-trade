package pl.edu.mimuw.bytetrade.physicalitem;

import pl.edu.mimuw.bytetrade.counter.Bag;
import pl.edu.mimuw.bytetrade.counter.Stack;

import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A collection of {@link PhysicalItem} elements. They are stored by their type, allowing for easy,
 * type-system-friendly querying.
 */
public class PhysicalItemCollection implements Iterable<Stack<? extends PhysicalItem>> {
  public final Bag<Food> food;
  public final Bag<Clothes> clothes;
  public final Bag<Tool> tools;
  public final Bag<Diamond> diamonds;
  public final Bag<Program> programs;

  public PhysicalItemCollection() {
    this.food = new Bag<>();
    this.clothes = new Bag<>();
    this.tools = new Bag<>();
    this.diamonds = new Bag<>();
    this.programs = new Bag<>();
  }

  public void takeAllFrom(PhysicalItemCollection items) {
    items.allCounters().forEach(this::filterOut);
  }

  private Stream<Bag<? extends PhysicalItem>> allCounters() {
    return Stream.of(food, clothes, tools, diamonds, programs);
  }

  public void filterOut(Bag<? extends PhysicalItem> items) {
    items.stream().forEach(this::remove);
  }

  public void remove(Stack<? extends PhysicalItem> item) {
    remove(item.item, item.count);
  }

  public void remove(PhysicalItem value, double count) {
    value.matchConsumer(
        food1 -> food.remove(food1, count),
        clothes1 -> clothes.remove(clothes1, count),
        tool -> tools.remove(tool, count),
        diamond -> diamonds.remove(diamond, count),
        program -> programs.remove(program, count));
  }

  public void addAllFrom(Bag<? extends PhysicalItem> items) {
    items.stream().forEach(this::add);
  }

  public void add(Stack<? extends PhysicalItem> item) {
    add(item.item, item.count);
  }

  public void add(PhysicalItem value, double count) {
    value.matchConsumer(
        food1 -> food.add(food1, count),
        clothes1 -> clothes.add(clothes1, count),
        tool -> tools.add(tool, count),
        diamond -> diamonds.add(diamond, count),
        program -> programs.add(program, count));
  }

  public double count() {
    return allCounters().mapToDouble(Bag::count).sum();
  }

  public double count(PhysicalItem item) {
    return item.match(food::count, clothes::count, tools::count, diamonds::count, programs::count);
  }

  public boolean isEmpty() {
    return allCounters().allMatch(Bag::isEmpty);
  }

  public Set<Stack<? extends PhysicalItem>> entrySet() {
    return stream().collect(Collectors.toSet());
  }

  public Stream<Stack<? extends PhysicalItem>> stream() {
    // TODO
    return null;
  }

  @Override
  public String toString() {
    return "ItemCounters{"
        + "food="
        + food
        + ", clothes="
        + clothes
        + ", tools="
        + tools
        + ", diamonds="
        + diamonds
        + ", programs="
        + programs
        + '}';
  }

  @Override
  public Iterator<Stack<? extends PhysicalItem>> iterator() {
    return stream().iterator();
  }
}
