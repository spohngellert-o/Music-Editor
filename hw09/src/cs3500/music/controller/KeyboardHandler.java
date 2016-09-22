package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Oliver on 11/20/2015.
 */

/**
 * Deals with key inputs and triggers functionality based on them
 */
public class KeyboardHandler implements KeyListener {
  private final Map<Integer, Runnable> typed, pressed, released;

  /**
   * Constructs an instance of KeyboardHandler.
   */
  public KeyboardHandler() {
    this.typed = new HashMap<Integer, Runnable>();
    this.pressed = new HashMap<Integer, Runnable>();
    this.released = new HashMap<Integer, Runnable>();
  }

  /**
   * Allows client to add a key event to the correct collection based on input type.
   *
   * @param code     - a key event that is set to initailize the event
   * @param runnable - the effect of the key event
   * @param type     - the type of key event
   */
  public void addKeyEvent(int code, Runnable runnable, String type) {
    if (type.equalsIgnoreCase("typed")) {
      if (!typed.containsKey(code)) {
        typed.put(code, runnable);
      } else {
        typed.remove(code);
        typed.put(code, runnable);
      }
    } else if (type.equalsIgnoreCase("pressed")) {
      if (!pressed.containsKey(code)) {
        pressed.put(code, runnable);
      } else {
        pressed.remove(code);
        pressed.put(code, runnable);
      }
    } else if (type.equalsIgnoreCase("released")) {
      if (!released.containsKey(code)) {
        released.put(code, runnable);
      } else {
        released.remove(code);
        released.put(code, runnable);
      }
    } else {
      throw new IllegalArgumentException("INVALID TYPE");
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {
    if (this.typed.containsKey(e.getKeyCode())) {
      this.typed.get(e.getKeyCode()).run();
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (this.pressed.containsKey(e.getKeyCode())) {

      this.pressed.get(e.getKeyCode()).run();
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    if (this.released.containsKey(e.getKeyCode())) {
      this.released.get(e.getKeyCode()).run();
    }
  }
}
