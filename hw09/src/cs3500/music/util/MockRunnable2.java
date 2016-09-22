package cs3500.music.util;

/**
 * Created by Oliver on 11/23/2015.
 */

/**
 * A mock key runnable used for testing purposes.
 */
public class MockRunnable2 implements Runnable {
  StringBuilder builder;

  /**
   * Constructs a MockRunnable.
   *
   * @param builder The mock's StringBuilder for testing.
   */
  public MockRunnable2(StringBuilder builder) {
    this.builder = builder;
  }

  @Override
  public void run() {
    builder.append("success2");
  }
}
