package pl.edu.mimuw.bytetrade.itemtype;

import pl.edu.mimuw.bytetrade.physicalitem.PhysicalItem;

public enum VirtualItem {
  Food,
  Clothes,
  Tool,
  Diamond,
  Program;

  public int getIndex() {
    return ordinal();
  }

  public boolean isSellable() {
    return this != Diamond;
  }

  public boolean isLike(PhysicalItem item) {
    return item.getVirtual().equals(this);
  }
}
