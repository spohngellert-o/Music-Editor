package cs3500.music.util;

/**
 * Created by Oliver on 11/24/2015.
 */

/**
 * A mock mouse runnable used for testing purposes.
 */
public class MockMouseRunnable implements MouseRunnable {
  StringBuilder builder;

  /**
   * Constructs a MockMouseRunnable.
   *
   * @param builder The mock's StringBuilder for testing.
   */
  public MockMouseRunnable(StringBuilder builder) {
    this.builder = builder;
  }

  @Override
  public void apply(int x, int y) {
    builder.append(x + " " + y);
  }
}
