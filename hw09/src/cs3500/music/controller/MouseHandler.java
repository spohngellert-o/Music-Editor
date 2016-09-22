package cs3500.music.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import cs3500.music.util.MouseRunnable;

/**
 * Created by Oliver on 11/20/2015.
 */

/**
 * Deals with mouse inputs and triggers functionality based on them
 */
public class MouseHandler implements MouseListener {
  private final MouseRunnable[] onClick, onPress, onRelease;

  /**
   * Constructs an instance of MouseHandler.
   */
  public MouseHandler() {
    MouseRunnable initRunnable = new MouseRunnable() {
      public void apply(int x, int y) {

      }
    };
    this.onClick = new MouseRunnable[3];
    this.onPress = new MouseRunnable[3];
    this.onRelease = new MouseRunnable[3];
    for (int i = 0; i < 3; i++) {
      onClick[i] = initRunnable;
      onPress[i] = initRunnable;
      onRelease[i] = initRunnable;
    }
  }

  /**
   * Adds the given runnable to the handler based on input code and type
   *
   * @param code     -- which button was pressed
   * @param runnable -- the code to be run on mouse event
   * @param type     -- what type of event (click, press, release)
   * @throws IllegalArgumentException if code is out of bounds, or type is invalid
   */
  public void addMouseEvent(int code, MouseRunnable runnable, String type) {
    if (0 <= code && code <= 2) {
      if (type.equalsIgnoreCase("clicked")) {
        this.onClick[code] = runnable;
      } else if (type.equalsIgnoreCase("pressed")) {
        this.onPress[code] = runnable;
      } else if (type.equalsIgnoreCase("released")) {
        this.onRelease[code] = runnable;
      } else {
        throw new IllegalArgumentException("INVALID TYPE");
      }
    } else {
      throw new IllegalArgumentException("INVALID CODE");
    }

  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (e.getButton() <= 3 && e.getButton() >= 1) {
      this.onClick[e.getButton() - 1].apply(e.getX(), e.getY());
    }

  }

  @Override
  public void mousePressed(MouseEvent e) {
    if (e.getButton() <= 3 && e.getButton() >= 1) {
      this.onPress[e.getButton() - 1].apply(e.getX(), e.getY());
    }
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    if (e.getButton() <= 3 && e.getButton() >= 1) {
      this.onRelease[e.getButton() - 1].apply(e.getX(), e.getY());
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }
}
