package pl.edu.mimuw.bytetrade.physicalitem;

public class PhysicalItemFactory {

  private static final Food foodSingleton = new Food();
  private static final Diamond diamondSingleton = new Diamond();

  /**
   * We store all the items that were ever created. This is necessary to create purchase offers for
   * things a speculator does not have.
   */
  private static final PhysicalItemCollection allItems = new PhysicalItemCollection();

  private PhysicalItemFactory() {}

  public static Food food() {
    return save(foodSingleton);
  }

  /** Register that an item has been created. */
  private static <T extends PhysicalItem> T save(T item) {
    allItems.add(item, 1);
    return item;
  }

  public static Clothes clothes(int quality) {
    return save(new Clothes(quality));
  }

  public static Tool tool(int quality) {
    return save(new Tool(quality));
  }

  public static Diamond diamond() {
    return save(diamondSingleton);
  }

  public static Program program(int level) {
    return save(new Program(level));
  }

  public static PhysicalItemCollection getAllItems() {
    return allItems;
  }
}
