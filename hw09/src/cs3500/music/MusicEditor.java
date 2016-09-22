package cs3500.music;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import cs3500.music.controller.ConcreteController;
import cs3500.music.model.BasicRepeat;
import cs3500.music.model.MultipleEndingRepeat;
import cs3500.music.model.Song;
import cs3500.music.util.MusicReader;
import cs3500.music.util.ViewFactory;
import cs3500.music.view.CompoundMidiGuiView;
import cs3500.music.view.ConcreteCompoundView;
import cs3500.music.view.GuiView;
import cs3500.music.view.MidiView;
import cs3500.music.view.MusicView;

/**
 * Created by Oliver on 11/10/2015.
 */

/**
 * Main class
 */
public final class MusicEditor {
  public static void main(String[] args) {
    String view = args[0];
    String file = args[1];
    try {
      if (args[0].equals("compound")) {
        FileReader fr = new FileReader(new File(file));
        Song song = (Song) MusicReader.parseFile(fr, new Song.SongBuilder());
        //song.addRepeatable(new MultipleEndingRepeat(15, 4, 11, 31, 11, 15, 15, 19));
        song.addRepeatable(new BasicRepeat(31, 11, 2));
        song.addRepeatable(new BasicRepeat(6, 0, 2));
        ViewFactory factory = new ViewFactory();
        CompoundMidiGuiView gvf = (CompoundMidiGuiView) factory.factory(view, song);
        ConcreteController controller = new ConcreteController(gvf, song);
        controller.start();
      } else if (args[0].equals("midi")) {
        FileReader fr = new FileReader(new File(file));
        Song song = (Song) MusicReader.parseFile(fr, new Song.SongBuilder());
        ViewFactory factory = new ViewFactory();
        MidiView gvf = (MidiView) factory.factory(view, song);
        gvf.renderForMidi();
      } else {
        FileReader fr = new FileReader(new File(file));
        Song song = (Song) MusicReader.parseFile(fr, new Song.SongBuilder());
        ViewFactory factory = new ViewFactory();
        MusicView gvf = factory.factory(view, song);
        gvf.render();
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
