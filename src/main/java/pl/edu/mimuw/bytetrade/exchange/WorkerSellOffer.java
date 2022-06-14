package pl.edu.mimuw.bytetrade.exchange;

import pl.edu.mimuw.bytetrade.counter.Stack;
import pl.edu.mimuw.bytetrade.physicalitem.PhysicalItem;
import pl.edu.mimuw.bytetrade.worker.Worker;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class WorkerSellOffer {
  public final Worker worker;
  public final Stack<PhysicalItem> items;

  public WorkerSellOffer(Worker worker, Stack<PhysicalItem> items) {
    this.worker = worker;
    this.items = items;
  }

  public static List<WorkerSellOffer> prioritizeOffers(Set<WorkerSellOffer> offers) {
    return offers.stream()
        .sorted(Comparator.comparingInt(offer -> offer.items.item.getVirtual().getIndex()))
        .collect(Collectors.toList());
  }

  @Override
  public String toString() {
    return "WorkerSellOffer{" + "worker=" + worker.getId() + ", items=" + items + '}';
  }
}
