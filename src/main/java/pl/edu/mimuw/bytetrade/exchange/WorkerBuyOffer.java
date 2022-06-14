package pl.edu.mimuw.bytetrade.exchange;

import pl.edu.mimuw.bytetrade.counter.Stack;
import pl.edu.mimuw.bytetrade.itemtype.VirtualItem;
import pl.edu.mimuw.bytetrade.worker.Worker;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class WorkerBuyOffer {

  public final Worker worker;

  public final Stack<VirtualItem> items;

  public WorkerBuyOffer(Worker worker, Stack<VirtualItem> items) {
    this.worker = worker;
    this.items = items;
  }

  public static List<WorkerBuyOffer> prioritizeOffers(Set<WorkerBuyOffer> offers) {
    return offers.stream()
        .sorted(Comparator.comparingInt(offer -> offer.items.item.getIndex()))
        .collect(Collectors.toList());
  }

  @Override
  public int hashCode() {
    return Objects.hash(worker, items);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    WorkerBuyOffer that = (WorkerBuyOffer) o;
    return worker.equals(that.worker) && items.equals(that.items);
  }

  @Override
  public String toString() {
    return "WorkerBuyOffer{" + "stack=" + items + ", worker=" + worker.getId() + '}';
  }

  public WorkerBuyOffer withCount(double count) {
    return new WorkerBuyOffer(worker, items.withCount(count));
  }
}
