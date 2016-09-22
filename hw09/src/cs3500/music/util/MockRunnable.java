package cs3500.music.util;

/**
 * Created by Oliver on 11/23/2015.
 */

/**
 * A mock key runnable used for testing purposes.
 */
public class MockRunnable implements Runnable {
  StringBuilder tester;

  /**
   * Constructs a MockRunnable.
   *
   * @param builder The mock's StringBuilder for testing.
   */
  public MockRunnable(StringBuilder builder) {
    tester = builder;
  }

  @Override
  public void run() {
    tester.append("success");
  }
}
