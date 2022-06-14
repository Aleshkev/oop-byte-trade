package pl.edu.mimuw.bytetrade.physicalitem;

public class PhysicalItemFactory {

  private static final Food foodSingleton = new Food();
  private static final Diamond diamondSingleton = new Diamond();

  private PhysicalItemFactory() {}

  public static Food food() {
    return foodSingleton;
  }

  public static Clothes clothes(int quality) {
    return new Clothes(quality);
  }

  public static Tool tool(int quality) {
    return new Tool(quality);
  }

  public static Diamond diamond() {
    return diamondSingleton;
  }

  public static Program program(int level) {
    return new Program(level);
  }
}
