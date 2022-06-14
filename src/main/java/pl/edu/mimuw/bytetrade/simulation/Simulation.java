package pl.edu.mimuw.bytetrade.simulation;

import pl.edu.mimuw.bytetrade.exchange.Exchange;
import pl.edu.mimuw.bytetrade.speculator.Speculator;
import pl.edu.mimuw.bytetrade.worker.Worker;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class Simulation {
  private final int length;
  private final int clothesShortagePenalty;
  private final Random random;
  private final Exchange exchange;
  private final Set<Worker> allWorkers;
  private final Set<Speculator> allSpeculators;
  private int dayNumber;

  public Simulation(Exchange exchange, int length, int clothesShortagePenalty) {
    this.exchange = exchange;
    this.dayNumber = 0;
    this.length = length;
    this.clothesShortagePenalty = clothesShortagePenalty;
    this.random = new Random();
    this.allWorkers = new HashSet<>();
    this.allSpeculators = new HashSet<>();
  }

  public void doDay() {
    ++dayNumber;

    for (var worker : allWorkers) worker.learnOrWork();

    exchange.newDay();
    for (var worker : allWorkers) worker.makeOffers(exchange);
    for (var speculator : allSpeculators) speculator.makeOffers(exchange);
    exchange.doExchange();
  }

  public Random getRandom() {
    return random;
  }

  public int getDayNumber() {
    return dayNumber;
  }

  public int getClothesShortagePenalty() {
    return clothesShortagePenalty;
  }

  public Set<Worker> getActiveWorkers() {
    return allWorkers.stream().filter(Worker::isAlive).collect(Collectors.toSet());
  }

  public Exchange getExchange() {
    return exchange;
  }

  public Set<Speculator> getAllSpeculators() {
    return allSpeculators;
  }
}
