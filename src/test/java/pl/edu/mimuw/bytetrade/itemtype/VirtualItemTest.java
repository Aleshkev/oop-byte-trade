package pl.edu.mimuw.bytetrade.itemtype;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VirtualItemTest {
  @Test
  void testThatOrderIsPreserved() {
    assertEquals(
        Arrays.stream(VirtualItem.values())
            .sorted(Comparator.comparingInt(VirtualItem::getIndex).reversed())
            .max(
                Comparator.comparingInt(
                    x -> (x == VirtualItem.Food || x == VirtualItem.Diamond ? 1 : 0)))
            .orElse(VirtualItem.Clothes),
        VirtualItem.Diamond);
  }
}
