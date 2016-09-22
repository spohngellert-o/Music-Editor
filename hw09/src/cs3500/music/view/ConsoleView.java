package cs3500.music.view;

import java.io.IOException;

import cs3500.music.model.Playable;
import cs3500.music.model.SongEditor;

/**
 * Created by Oliver on 11/11/2015.
 */

/**
 * A view that shows the song in a printed format on the console
 */
public class ConsoleView implements MusicView {
  private SongEditor s;
  private Appendable output;

  /**
   * Constructs a ConsoleView given a song
   *
   * @param s the song to be viewed
   */
  public ConsoleView(SongEditor s) {
    this.s = s;
    output = System.out;
  }

  /**
   * Contructs a ConsoleView given a song and an output
   *
   * @param s      the song to be viewed
   * @param output the output to be used
   */
  public ConsoleView(SongEditor s, Appendable output) {
    this.s = s;
    this.output = output;
  }

  /**
   * Converts a note pitch value into its String representation
   *
   * @param val the note's pitch value
   * @return the String version in the form "C#5"
   */
  private String noteValToName(int val) {
    int noteName = val % 12;
    int octave = Math.floorDiv(val, 12);
    String result = "";
    if (noteName == 0) {
      result = "C";
    } else if (noteName == 1) {
      result = "C#";
    } else if (noteName == 2) {
      result = "D";
    } else if (noteName == 3) {
      result = "D#";
    } else if (noteName == 4) {
      result = "E";
    } else if (noteName == 5) {
      result = "F";
    } else if (noteName == 6) {
      result = "F#";
    } else if (noteName == 7) {
      result = "G";
    } else if (noteName == 8) {
      result = "G#";
    } else if (noteName == 9) {
      result = "A";
    } else if (noteName == 10) {
      result = "A#";
    } else if (noteName == 11) {
      result = "B";
    }
    result = result + Integer.toString(octave);
    return result;
  }

  /**
   * Returns a String that represents a beat line for the ConsoleView.
   *
   * @param range The length of the line to be constructed.
   * @param beat  The beat where the notes will be checked for.
   * @return A String representation of notes starting or being held out at a beat.
   */
  private String noteLine(int range, int beat) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < range; i++) {
      sb.append("    ");
    }
    for (Playable n : this.s.getBeatsNotes(beat)) {
      if (n.getStartingTime() == beat) {
        sb.setCharAt((n.getPitchVal() - this.s.getLowestPitch()) * 4, 'X');
      } else if (n.getStartingTime() < beat && n.getEndingTime() > beat) {
        sb.setCharAt((n.getPitchVal() - this.s.getLowestPitch()) * 4, '|');
      }
    }
    return sb.toString();
  }

  @Override
  public void render() {
    int beatPadding = Integer.toString(this.s.getSongLength() - 1).length();
    int range = this.s.getHighestPitch() - this.s.getLowestPitch() + 1;


    String firstRow = " ";
    while (firstRow.length() < beatPadding + 1) {
      firstRow = firstRow + " ";
    }

    int notesPast = 0;
    for (int i = this.s.getLowestPitch(); i <= this.s.getHighestPitch(); i++) {
      String temp = "";
      temp = Playable.noteValToName(this.s.getLowestPitch() + notesPast);
      while (temp.length() < 4) {
        temp = temp + " ";
      }
      firstRow = firstRow + temp;
      notesPast++;
    }
    try {
      output.append(firstRow + "\n");
    } catch (IOException e) {
      e.printStackTrace();
    }


    for (int i = 0; i < this.s.getSongLength(); i++) {
      String line = Integer.toString(i);
      while (line.length() < beatPadding) {
        line = line + " ";
      }
      line = line + " " + this.noteLine(range, i);
      try {
        output.append(line + "\n");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
