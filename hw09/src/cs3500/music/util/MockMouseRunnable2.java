package cs3500.music.util;

/**
 * Created by Oliver on 11/24/2015.
 */

/**
 * Another mock mouse runnable used for testing purposes.
 */
public class MockMouseRunnable2 implements MouseRunnable {
  StringBuilder builder;

  /**
   * Constructs a MockMouseRunnable2.
   *
   * @param builder The mock's StringBuilder for testing.
   */
  public MockMouseRunnable2(StringBuilder builder) {
    this.builder = builder;
  }

  @Override
  public void apply(int x, int y) {
    builder.append(y + " " + x);
  }
}
