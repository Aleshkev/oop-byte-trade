package pl.edu.mimuw.bytetrade.util;

import java.util.function.Consumer;

public class FunctionUtil {
  private FunctionUtil() {}

  public static <T> java.util.function.Function<T, Void> consumerToFunction(Consumer<T> f) {
    return x -> {
      f.accept(x);
      return null;
    };
  }
}
