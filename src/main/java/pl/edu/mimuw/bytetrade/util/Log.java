package pl.edu.mimuw.bytetrade.util;

public class Log {
  private static boolean enableLogging = true;

  private Log() {}

  public static void info(String s) {
    System.err.println("(INFO) " + s);
  }
}
