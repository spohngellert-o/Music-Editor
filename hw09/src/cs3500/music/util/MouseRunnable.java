package cs3500.music.util;

/**
 * Created by Oliver on 11/22/2015.
 */

/**
 * A function object for MouseEvents.
 */
public interface MouseRunnable {
  /**
   * The function object's function.
   *
   * @param x The x-coordinate of the mouse click.
   * @param y The y-coordinate of the mouse click.
   */
  public void apply(int x, int y);
}
