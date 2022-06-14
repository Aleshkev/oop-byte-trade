package pl.edu.mimuw.bytetrade.counter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Bag<T> implements Iterable<Stack<T>> {

  private final Map<T, Double> counts;

  public Bag(Iterable<Stack<T>> iterable) {
    this();
    iterable.forEach(this::add);
  }

  public Bag() {
    this.counts = new HashMap<>();
  }

  public void add(Stack<T> stack) {
    add(stack.item, stack.count);
  }

  public void add(T item, double count) {
    counts.put(item, count(item) + count);
  }

  public double count(T item) {
    return counts.getOrDefault(item, 0.0);
  }

  public void removeByValue(T value) {
    remove(new Stack<>(value, count(value)));
  }

  public void remove(Stack<T> stack) {
    remove(stack.item, stack.count);
  }

  public void remove(T item, double count) {
    var newCount = count(item) - count;
    if (newCount < 0) throw new RuntimeException("Too few items in the collection.");
    if (newCount == 0) counts.remove(item);
    else counts.put(item, newCount);
  }

  public void remove(Bag<T> source) {
    source.stream().forEach(this::remove);
  }

  public Stream<Stack<T>> stream() {
    return counts.entrySet().stream().map(Stack::new);
  }

  public void clear() {
    counts.clear();
  }

  public Bag<T> getNRandom(double n) {
    return getNRandom(n, x -> true);
  }

  public Bag<T> getNRandom(double n, Function<T, Boolean> filter) {}

  @Override
  public String toString() {
    return "Counter{" + "counts=" + counts + '}';
  }

  public double count() {
    return counts.values().stream().mapToDouble(x -> x).sum();
  }

  public boolean isEmpty() {
    return counts.isEmpty();
  }

  public Set<Stack<T>> entrySet() {
    return Set.copyOf(stream().collect(Collectors.toSet()));
  }

  @Override
  public Iterator<Stack<T>> iterator() {
    return stream().iterator();
  }
}
