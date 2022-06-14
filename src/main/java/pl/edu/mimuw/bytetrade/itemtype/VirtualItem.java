package pl.edu.mimuw.bytetrade.itemtype;

import pl.edu.mimuw.bytetrade.physicalitem.PhysicalItem;

/**
 * This is a class that represents a single abstract type of item, without distinction between
 * different quality / advancement levels. It can be used with a {@link
 * pl.edu.mimuw.bytetrade.counter.Stack} to store information about the number of the items.
 */
public enum VirtualItem {
  Food,
  Clothes,
  Tool,
  Diamond,
  Program;

  public int getIndex() {
    return ordinal();
  }

  public boolean isUnsellable() {
    return this == Diamond;
  }

  public boolean isLike(PhysicalItem item) {
    return item.getVirtual().equals(this);
  }
}
