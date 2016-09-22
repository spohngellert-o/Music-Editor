package cs3500.music.view;

/**
 * Created by Oliver on 11/21/2015.
 */

/**
 * A collection of views that represent the audio aspect of the view.
 */
public interface MidiView extends MusicView {

  /**
   * Initiates the start and end of playing notes.
   */
  void playBeat();

  /**
   * Pauses the playing of the song.
   */
  void pause();

  /**
   * Restarts the notes that were paused.
   */
  void restart();

  /**
   * Refreshes the notes that are being set to play at the current beat.
   */
  void adjustNotesToPlay();

  /**
   * Renders for MIDI.
   */
  public void renderForMidi();
}
