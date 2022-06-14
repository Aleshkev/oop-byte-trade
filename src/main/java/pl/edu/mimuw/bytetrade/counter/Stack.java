package pl.edu.mimuw.bytetrade.counter;

import java.util.Map;
import java.util.Objects;

public class Stack<T> {
  public final T item;
  public final double count;

  public Stack(Map.Entry<T, Integer> entry) {
    this(entry.getKey(), entry.getValue());
  }

  public Stack(T item, double count) {
    this.item = item;
    if (count <= 0) throw new IllegalArgumentException("Stack size must be positive.");
    this.count = count;
  }

  @Override
  public int hashCode() {
    return Objects.hash(item, count);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Stack<?> stack = (Stack<?>) o;
    return count == stack.count && item.equals(stack.item);
  }

  @Override
  public String toString() {
    return "Stack{" + "item=" + item + ", count=" + count + '}';
  }

  public Stack<T> withCount(double count) {
    return new Stack<>(item, count);
  }
}
