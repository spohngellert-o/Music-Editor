package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by Oliver on 11/20/2015.
 */

/**
 * Interface that represents all controllers dealing with the song editor.
 */
public interface MusicController {

  /**
   * Initiates the controller's renders.
   */
  public void start();

  /**
   * Pauses the view including gui and midi.
   */
  public void pause();

  /**
   * Plays the view including gui and midi.
   */
  public void play();

}
