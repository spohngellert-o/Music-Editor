package cs3500.music.util;

import cs3500.music.model.SongEditor;
import cs3500.music.view.ConcreteCompoundView;
import cs3500.music.view.ConsoleView;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.MidiViewImpl;
import cs3500.music.view.MusicView;

/**
 * Created by Oliver on 11/12/2015.
 */

/**
 * Factory class for Views
 */
public class ViewFactory {

  /**
   * Constructs a ViewFactory
   */
  public ViewFactory() {

  }

  /**
   * Creates a MusicView based on the given information
   *
   * @param view Type of view
   * @param song Song you want to view
   * @return The desired View
   * @throws IllegalArgumentException View is not valid
   */
  public MusicView factory(String view, SongEditor song) throws IllegalArgumentException {
    if (view.equals("console")) {
      return new ConsoleView(song);
    }
    if (view.equals("midi")) {
      return new MidiViewImpl(song);
    }
    if (view.equals("gui")) {
      return new GuiViewFrame(song);
    }
    if (view.equals("compound")) {
      return new ConcreteCompoundView(new MidiViewImpl(song), new GuiViewFrame(song));
    }
    throw new IllegalArgumentException("You didn't give a valid view");
  }
}