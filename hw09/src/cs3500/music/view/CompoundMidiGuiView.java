package cs3500.music.view;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

/**
 * Created by Oliver on 11/24/2015.
 */

/**
 * A sub-interface to hold methods specific to compound views with midi and gui.
 */
public interface CompoundMidiGuiView extends CompoundView {
  /**
   * Renders the GUI aspect of the view.
   */
  void renderGui();

  /**
   * Moves the scroll by a given factor in a direction given in the parameters. False represents a
   * horizontal translation, true represents a vertical.
   *
   * @param factor An integer representing how far the scroll will move.
   */
  void movePosition(int factor, boolean orientation);

  /**
   * Adds the MouseListener to the correct view.
   *
   * @param listener The listener desired to be added.
   */
  void addMouseListener(MouseListener listener);

  /**
   * Adds the KeyListener to the correct view.
   *
   * @param listener The listener desired to be added.
   */
  void addKeyListener(KeyListener listener);

  /**
   * Pauses the both views.
   */
  void pause();

  /**
   * Resume both views.
   */
  void play();

  /**
   * Restarts both views.
   */
  void restart();

  /**
   * Refreshes the notes that are being set to play at the current beat.
   */
  void adjustNotesToPlay();

  /**
   * Moves the scroll to the beginning.
   */
  void goHome();

  /**
   * Moves the scroll to the end.
   */
  void goEnd();
}
