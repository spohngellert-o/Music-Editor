package cs3500.music.view;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.*;

import cs3500.music.model.MultipleEndingRepeatable;
import cs3500.music.model.Playable;
import cs3500.music.model.Repeatable;
import cs3500.music.model.SongEditor;

/**
 * A view that displays a song in a graphical user interface form
 */
public class ConcreteGuiViewPanel extends JPanel {
  private final SongEditor song;

  /**
   * Constructs a ConcreteGuiViewPanel
   *
   * @param song the song to display
   */
  ConcreteGuiViewPanel(SongEditor song) {
    this.song = Objects.requireNonNull(song);
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

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    int highest = song.getHighestPitch();
    int lowest = song.getLowestPitch();
    for (int i = 0; i <= (int) Math.ceil(this.song.getSongLength() / 4); i++) {
      g.drawString("" + i * 4, 40 + (80 * i), 20);
    }
    for (int i = 0; i < this.song.getSongLength(); i++) {
      List<Playable> currNotes = this.song.getBeatsNotes(i);
      for (Playable p : currNotes) {
        boolean toDisplay = true;
        if (p.getStartingTime() == i) {
          g.setColor(Color.black);
        }
        if (p.getEndingTime() == i) {
          toDisplay = false;
        }
        if (toDisplay) {
          g.fillRect(40 + 20 * i, 26 + 20 * (highest - p.getPitchVal()), 20, 19);
          g.setColor(Color.green);
        }
      }
    }
    g.setColor(Color.black);

    g.drawRect(40, 25, (((this.song.getSongLength() / 4) + 1) * 4) * 20,
            (highest - lowest + 1) * 20);
    for (int i = 1; i < ((this.song.getSongLength() / 4) + 1); i++) {
      g.drawLine(40 + (80 * i), 25, 40 + (80 * i), 25 + (20 * (highest - lowest + 1)));
    }
    for (int i = 1; i <= highest - lowest; i++) {
      g.drawLine(40, 25 + (20 * i), 40 + (((this.song.getSongLength() / 4) + 1) * 4) * 20,
              25 + (20 * i));
    }
    for (int i = 0; lowest <= highest; i++) {
      g.drawString(noteValToName(highest), 10, 40 + (20 * i));
      highest--;
    }


    List<Repeatable> repeats = this.song.getRepeatables();
    for (int i = 0; i < repeats.size(); i++) {
      Repeatable currRepeat = repeats.get(i);
      Graphics2D strokeSet = (Graphics2D) g;
      if (currRepeat.isBasic()) {
        g.setColor(Color.blue);
        int beatRepeatFrom = currRepeat.getRepeatFrom() + 1;
        strokeSet.setStroke(new BasicStroke(3));
        g.drawLine(40 + (20 * beatRepeatFrom), 27, 40 + (20 * beatRepeatFrom),
                23 + (20 * (song.getHighestPitch() + 1 - song.getLowestPitch())));
        strokeSet.setStroke(new BasicStroke(1));
        g.drawLine(35 + (20 * beatRepeatFrom), 25, 35 + (20 * beatRepeatFrom),
                25 + (20 * (song.getHighestPitch() + 1 - song.getLowestPitch())));
        int beatRepeatTo = currRepeat.getRepeatTo();
        if (beatRepeatTo > 0) {
          strokeSet.setStroke(new BasicStroke(3));
          g.drawLine(40 + (20 * beatRepeatTo), 27, 40 + (20 * beatRepeatTo),
                  23 + (20 * (song.getHighestPitch() + 1 - song.getLowestPitch())));
          strokeSet.setStroke(new BasicStroke(1));
          g.drawLine(45 + (20 * beatRepeatTo), 25, 45 + (20 * beatRepeatTo),
                  25 + (20 * (song.getHighestPitch() + 1 - song.getLowestPitch())));
        }
        g.drawString("Repeat " + Integer.toString(currRepeat.getRepeatCount()) + "x",
                45 + (20 * beatRepeatTo),
                45 + (20 * (song.getHighestPitch() + 1 - song.getLowestPitch())));
      } else {
        g.setColor(Color.orange);
        int beatRepeatTo = currRepeat.getRepeatTo();
        int beatSkipFrom = currRepeat.getSkipFrom();
        g.drawLine(40 + (20 * beatSkipFrom), 25, 40 + (20 * beatSkipFrom),
                25 + (20 * (song.getHighestPitch() + 1 - song.getLowestPitch())));
        g.drawLine(40 + (20 * beatSkipFrom),
                30 + (20 * (song.getHighestPitch() + 1 - song.getLowestPitch())),
                40 + (20 * beatSkipFrom),
                48 + (20 * (song.getHighestPitch() + 1 - song.getLowestPitch())));
        g.drawLine(40 + (20 * beatSkipFrom),
                48 + (20 * (song.getHighestPitch() + 1 - song.getLowestPitch())),
                100 + (20 * beatSkipFrom),
                48 + (20 * (song.getHighestPitch() + 1 - song.getLowestPitch())));
        if (beatRepeatTo > 0) {
          strokeSet.setStroke(new BasicStroke(3));
          g.drawLine(40 + (20 * beatRepeatTo), 27, 40 + (20 * beatRepeatTo),
                  23 + (20 * (song.getHighestPitch() + 1 - song.getLowestPitch())));
          strokeSet.setStroke(new BasicStroke(1));
          g.drawLine(45 + (20 * beatRepeatTo), 25, 45 + (20 * beatRepeatTo),
                  25 + (20 * (song.getHighestPitch() + 1 - song.getLowestPitch())));
        }
        List<Integer> skipToList = currRepeat.getSkipToList();
        for (Integer skipTo : skipToList) {
          strokeSet.setStroke(new BasicStroke(3));
          g.drawLine(40 + (20 * skipTo), 27, 40 + (20 * skipTo),
                  23 + (20 * (song.getHighestPitch() + 1 - song.getLowestPitch())));
          strokeSet.setStroke(new BasicStroke(1));
          g.drawLine(35 + (20 * skipTo), 25, 35 + (20 * skipTo),
                  25 + (20 * (song.getHighestPitch() + 1 - song.getLowestPitch())));
          g.drawLine(40 + (20 * skipTo),
                  30 + (20 * (song.getHighestPitch() + 1 - song.getLowestPitch())),
                  40 + (20 * skipTo),
                  48 + (20 * (song.getHighestPitch() + 1 - song.getLowestPitch())));
          g.drawLine(40 + (20 * skipTo),
                  48 + (20 * (song.getHighestPitch() + 1 - song.getLowestPitch())),
                  100 + (20 * skipTo),
                  48 + (20 * (song.getHighestPitch() + 1 - song.getLowestPitch())));
        }
        int endings = 1;
        g.drawString(Integer.toString(endings),
                45 + (20 * beatSkipFrom),
                42 + (20 * (song.getHighestPitch() + 1 - song.getLowestPitch())));
        if (this.countEndings(skipToList, currRepeat.getSkipFrom()) > 0) {
          endings += this.countEndings(skipToList, currRepeat.getSkipFrom());
          g.drawString("- " + Integer.toString(endings),
                  55 + (20 * beatSkipFrom),
                  42 + (20 * (song.getHighestPitch() + 1 - song.getLowestPitch())));
        }
        List<Integer> visitedSkipToList = new ArrayList<Integer>();
        for (Integer skipTo : skipToList) {
          if (skipTo != beatSkipFrom && !visitedSkipToList.contains(skipTo)) {
            visitedSkipToList.add(skipTo);
            endings += 1;
            g.drawString(Integer.toString(endings),
                    45 + (20 * skipTo),
                    42 + (20 * (song.getHighestPitch() + 1 - song.getLowestPitch())));
            if (this.countEndings(skipToList, skipTo) > 1) {
              endings += this.countEndings(skipToList, skipTo) - 1;
              g.drawString("- " + Integer.toString(endings),
                      55 + (20 * skipTo),
                      42 + (20 * (song.getHighestPitch() + 1 - song.getLowestPitch())));
            }
          }
        }


      }


      g.setColor(Color.RED);
      g.drawLine(40 + (20 * song.getBeat()), 25, 40 + (20 * song.getBeat()),
              25 + (20 * (song.getHighestPitch() + 1 - song.getLowestPitch())));

    }
  }

  /**
   *
   * @param endings
   * @param endBeat
   * @return
   */
  private int countEndings(List<Integer> endings, int endBeat) {
    int result = 0;
    for(Integer e : endings) {
      if(e == endBeat) {
        result += 1;
      }
    }
    return result;
  }

  /**
   * Returns the x-coordinate of the red line, or current beat inspected.
   *
   * @return The respective x-coordinate represented as an integer.
   */
  public int getBeatPositionX() {
    return 40 + (20 * song.getBeat());
  }

  /**
   * Repaints the view.
   */
  public void renderAtBeat() {
    this.repaint();
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension((((this.song.getSongLength() / 4) + 1) * 4) * 20 + 60,
            (this.song.getHighestPitch() - this.song.getLowestPitch() + 1) * 20 + 50);
  }
}