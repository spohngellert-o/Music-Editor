package cs3500.music.view;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

/**
 * Created by Oliver on 11/20/2015.
 */

/**
 * A collection of views that give the user a gui display.
 */
public interface GuiView extends MusicView {
  /**
   * Adds a MouseListener to the gui view.
   *
   * @param listener The respective MouseListener.
   */
  void addMouseListener(MouseListener listener);

  /**
   * Adds a KeyListener to the gui view.
   *
   * @param listener The respective KeyListener.
   */
  void addKeyListener(KeyListener listener);

  /**
   * Repaints the view.
   */
  void renderAtBeat();

  /**
   * Moves the scroll by a given factor in a given orientation.
   *
   * @param factor      An integer representing how much the scroll moves by.
   * @param orientation A boolean representing which direction the scroll will move in. Horizontal
   *                    translation is true while vertical is false.
   */
  void movePosition(int factor, boolean orientation);

  /**
   * Moves the scroll to the beginning position.
   */
  void goHome();

  /**
   * Moves the scroll the ending position.
   */
  void goEnd();
}
