package pl.edu.mimuw.bytetrade.worker;

import pl.edu.mimuw.bytetrade.counter.Stack;
import pl.edu.mimuw.bytetrade.exchange.Exchange;
import pl.edu.mimuw.bytetrade.exchange.SpeculatorBuyOffer;
import pl.edu.mimuw.bytetrade.exchange.SpeculatorSellOffer;
import pl.edu.mimuw.bytetrade.exchange.WorkerSellOffer;
import pl.edu.mimuw.bytetrade.itemtype.VirtualItem;
import pl.edu.mimuw.bytetrade.physicalitem.PhysicalItem;
import pl.edu.mimuw.bytetrade.physicalitem.PhysicalItemCollection;
import pl.edu.mimuw.bytetrade.physicalitem.PhysicalItemFactory;
import pl.edu.mimuw.bytetrade.simulation.Agent;
import pl.edu.mimuw.bytetrade.simulation.Simulation;
import pl.edu.mimuw.bytetrade.workerbuyingstrategy.WorkerBuyingStrategy;
import pl.edu.mimuw.bytetrade.workercareerstrategy.WorkerCareerStrategy;
import pl.edu.mimuw.bytetrade.workereducationstrategy.WorkerEducationStrategy;
import pl.edu.mimuw.bytetrade.workerproductionstrategy.WorkerProductionStrategy;

import java.util.Map;

public final class Worker extends Agent {
  private final Map<VirtualItem, Integer> baseProductivity;
  private final WorkerEducationStrategy educationStrategy;
  private final WorkerCareerStrategy careerStrategy;
  private final WorkerBuyingStrategy buyingStrategy;
  private final WorkerProductionStrategy productionStrategy;

  private final WorkerCareer career;
  private final WorkerHunger hunger;

  /** Everything the worker has bought. */
  private final PhysicalItemCollection thingsToUse;
  /** Everything the worker has produced today. Will be sold before the end of the day. */
  private final PhysicalItemCollection thingsToSell;

  private final PhysicalItemCollection thingsProducedToday;

  public Worker(
      Simulation simulation,
      int id,
      Map<VirtualItem, Integer> baseProductivity,
      WorkerEducationStrategy educationStrategy,
      WorkerCareerStrategy careerStrategy,
      WorkerBuyingStrategy buyingStrategy,
      WorkerProductionStrategy productionStrategy,
      VirtualItem currentSpecialization,
      int levelAtCurrentSpecialization) {
    super(simulation, id);

    this.baseProductivity = Map.copyOf(baseProductivity);
    for (var value : this.baseProductivity.values())
      if (value % 100 != 0)
        throw new IllegalArgumentException("Base productivity must be a multiple of 100");

    this.educationStrategy = educationStrategy;
    this.careerStrategy = careerStrategy;
    this.buyingStrategy = buyingStrategy;
    this.productionStrategy = productionStrategy;

    this.career = new WorkerCareer(currentSpecialization, levelAtCurrentSpecialization);
    this.hunger = new WorkerHunger();

    this.thingsToUse = new PhysicalItemCollection();
    this.thingsToSell = new PhysicalItemCollection();
    this.thingsProducedToday = new PhysicalItemCollection();
  }

  public WorkerCareer getCareer() {
    return career;
  }

  public void learnOrWork() {
    assert thingsToSell.isEmpty();
    if (educationStrategy.whetherToBeLearningToday(this)) learnToday();
    else workToday();
  }

  private void learnToday() {
    var specialty = careerStrategy.whatToSpecializeInToday(this);
    career.setSpecializationThenLevelUp(specialty);
    hunger.eatAtTheCanteen();
  }

  private void workToday() {
    var whatToProduce = productionStrategy.whatToProduceToday(this);
    produce(whatToProduce);

    // Eat something, using up random 100 units of food.
    var foodToEatNow = thingsToUse.food.getNRandom(100);
    hunger.eatFoodToday(foodToEatNow.count());
    thingsToUse.filterOut(foodToEatNow);

    // Use up all the tools.
    thingsToUse.tools.clear();

    // Use 100 random clothes (decrease durability and remove used up clothes).
    var clothesToUseNow = thingsToUse.clothes.getNRandom(howManyClothesUsesInADay());
    thingsToUse.filterOut(clothesToUseNow);
    for (var stack : clothesToUseNow) {
      var clothes = stack.item.withDecreasedDurability();
      if (clothes.isUsedUp()) continue;
      thingsToUse.add(clothes, stack.count);
    }
  }

  private void produce(VirtualItem virtualItem) {
    var quantity = howMuchWouldProduce(virtualItem);
    // TODO
  }

  /** How many units of clothing will the worker attempt to use in a single day. */
  public double howManyClothesUsesInADay() {
    return 100;
  }

  public double howMuchWouldProduce(VirtualItem virtualItem) {
    var base = baseProductivity.getOrDefault(virtualItem, 0);

    var bonus = 0;
    bonus += career.getBonusToProductionOf(virtualItem);
    bonus += hunger.getBonusToProduction();
    for (var stack : thingsToUse.tools)
      bonus += stack.count * stack.item.getBonusToProductionWhenUsed();
    // TODO: Penalty for lack of clothes

    return Math.max(0, base * (100 + bonus) / 100);
  }

  public PhysicalItem whatWouldProduce(VirtualItem virtualItem) {
    switch (virtualItem) {
      case Food:
        return PhysicalItemFactory.food();
      case Clothes:
        return PhysicalItemFactory.clothes(career.getLevelAtProductionOf(VirtualItem.Clothes));
      case Tool:
        return PhysicalItemFactory.tool(career.getLevelAtProductionOf(VirtualItem.Tool));
      case Diamond:
        return PhysicalItemFactory.diamond();
      case Program:
        return PhysicalItemFactory.program(career.getLevelAtProductionOf(VirtualItem.Program));
    }
    throw new AssertionError("Unreachable.");
  }

  public double howManyThingsDidProduceToday() {
    return thingsProducedToday.count();
  }

  public void makeOffers(Exchange exchange) {
    // Sell everything.
    thingsToSell.stream()
        .forEach(
            stack ->
                exchange.addWorkerSellOffer(
                    new WorkerSellOffer(this, new Stack<>(stack.item, stack.count))));

    buyingStrategy.makeOffersForToday(this, exchange);
  }

  public void finalizeSellOffer(SpeculatorBuyOffer offer) {
    thingsToSell.remove(offer.items);
    thingsToUse.add(offer.totalPrice());
  }

  public void finalizeBuyOffer(SpeculatorSellOffer offer) {
    thingsToUse.add(offer.items);
    thingsToUse.remove(offer.totalPrice());
  }

  public boolean isAlive() {
    return true;
  }

  public boolean canAfford(double amount) {
    return howManyDiamondsDoTheyHave() >= amount;
  }

  public double howManyDiamondsDoTheyHave() {
    return thingsToUse.diamonds.count();
  }

  public PhysicalItemCollection getThingsToUse() {
    return thingsToUse;
  }
}
