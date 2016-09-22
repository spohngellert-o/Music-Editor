package cs3500.music.controller;

import com.sun.glass.events.MouseEvent;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

import cs3500.music.model.BasicRepeatable;
import cs3500.music.model.MultipleEndingRepeatable;
import cs3500.music.model.Playable;
import cs3500.music.model.Repeatable;
import cs3500.music.model.SongEditor;
import cs3500.music.util.MouseRunnable;
import cs3500.music.view.CompoundMidiGuiView;
import cs3500.music.view.CompoundView;

/**
 * Created by Oliver on 11/21/2015.
 */

/**
 * A concrete instance of a MusicController that handles our program's compound view.
 */
public class ConcreteController implements MusicController {
  // The controller's handler for keyboard inputs.
  private KeyboardHandler keyboardHandler;
  // The controller's handler for mouse inputs.
  private MouseHandler mouseHandler;
  // The view that the controller interacts with.
  private CompoundMidiGuiView view;
  // The model that the controller interacts with.
  private SongEditor song;
  // A boolean representing if the view is in pause or play mode.
  private boolean isPlaying;
  // The note being selected during edit mode.
  private Playable noteEditing;
  // An integer representing which instrument the user wishes to deal with in the view.
  private int instrument;
  // A collection of visited repeatables
  private Map<Integer, Repeatable> visitedRepeats;
  // Current repeatable being played
  private Repeatable currRepeatable;
  // How many times through the current repeat
  private int repeatNumber;

  /**
   * Constructs the controller to communicate between model and view.
   *
   * @param view - The respective view for this controller.
   * @param song - The respective model for this controller.
   */
  public ConcreteController(CompoundMidiGuiView view, SongEditor song) {
    this.keyboardHandler = new KeyboardHandler();
    this.isPlaying = false;
    this.mouseHandler = new MouseHandler();
    noteEditing = null;
    // Adds the pause/play functionality.
    this.visitedRepeats = new HashMap<Integer, Repeatable>();
    this.currRepeatable = null;
    this.keyboardHandler.addKeyEvent(KeyEvent.VK_SPACE, new Runnable() {
      @Override
      public void run() {
        if (isPlaying) {
          isPlaying = !isPlaying;
          pause();
        } else {
          isPlaying = !isPlaying;
          ConcreteController.this.view.restart();
          play();
        }

      }
    }, "released");
    // Adds the rewind by one beat functionality.
    this.keyboardHandler.addKeyEvent(KeyEvent.VK_1, new Runnable() {
      @Override
      public void run() {
        if (!isPlaying) {
          ConcreteController.this.song.setBeat(Math.max(ConcreteController.this.song.getBeat() - 1,
                  0));
          ConcreteController.this.view.renderGui();
        }

      }
    }, "pressed");
    // Adds the fast forward by one beat functionality.
    this.keyboardHandler.addKeyEvent(KeyEvent.VK_2, new Runnable() {
      @Override
      public void run() {
        if (!isPlaying) {
          ConcreteController.this.song.setBeat(Math.min(ConcreteController.this.song.getBeat() + 1,
                  ConcreteController.this.song.getSongLength()));
          ConcreteController.this.view.renderGui();
        }

      }
    }, "pressed");
    // Adds the rewind to start functionality.
    this.keyboardHandler.addKeyEvent(KeyEvent.VK_3, new Runnable() {
      @Override
      public void run() {
        if (!isPlaying) {
          ConcreteController.this.song.setBeat(0);
          ConcreteController.this.view.renderGui();
        }

      }
    }, "pressed");
    // Makes sure to refresh the notes going to be played after using 1, 2, or 3.
    this.keyboardHandler.addKeyEvent(KeyEvent.VK_1, new Runnable() {
      @Override
      public void run() {
        ConcreteController.this.view.adjustNotesToPlay();
      }
    }, "released");
    this.keyboardHandler.addKeyEvent(KeyEvent.VK_2, new Runnable() {
      @Override
      public void run() {
        ConcreteController.this.view.adjustNotesToPlay();
      }
    }, "released");
    this.keyboardHandler.addKeyEvent(KeyEvent.VK_3, new Runnable() {
      @Override
      public void run() {
        ConcreteController.this.view.adjustNotesToPlay();
      }
    }, "released");
    // The next four add the scrolling functionality by arrow keys.
    this.keyboardHandler.addKeyEvent(KeyEvent.VK_LEFT, new Runnable() {
      @Override
      public void run() {
        if (!isPlaying) {
          ConcreteController.this.view.movePosition(-45, true);
        }
      }
    }, "pressed");
    this.keyboardHandler.addKeyEvent(KeyEvent.VK_RIGHT, new Runnable() {
      @Override
      public void run() {
        if (!isPlaying) {
          ConcreteController.this.view.movePosition(45, true);
        }

      }
    }, "pressed");
    this.keyboardHandler.addKeyEvent(KeyEvent.VK_UP, new Runnable() {
      @Override
      public void run() {
        ConcreteController.this.view.movePosition(-45, false);
      }
    }, "pressed");
    this.keyboardHandler.addKeyEvent(KeyEvent.VK_DOWN, new Runnable() {
      @Override
      public void run() {
        ConcreteController.this.view.movePosition(45, false);
      }
    }, "pressed");
    // Adds the scroll to home functionality.
    this.keyboardHandler.addKeyEvent(KeyEvent.VK_HOME, new Runnable() {
      @Override
      public void run() {
        if (!isPlaying) {
          ConcreteController.this.view.goHome();
        }
      }
    }, "released");
    // Adds the scroll to end functionality.
    this.keyboardHandler.addKeyEvent(KeyEvent.VK_END, new Runnable() {
      @Override
      public void run() {
        if (!isPlaying) {
          ConcreteController.this.view.goEnd();
        }
      }
    }, "released");
    // Allows the user to set the program to "Add Mode" which allows users to
    // add notes by left click.
    this.keyboardHandler.addKeyEvent(KeyEvent.VK_A, new Runnable() {
      @Override
      public void run() {
        try {
          String instrument = Objects.requireNonNull(JOptionPane.showInputDialog(null,
                  "What instrument do you want to " + "add? (1-128)"));
          // the String to int conversion happens here
          int i = Integer.parseInt(instrument.trim());
          if (i >= 1 && i <= 128) {
            ConcreteController.this.instrument = i;
            ConcreteController.this.setMouseHandler("A");
          } else JOptionPane.showMessageDialog(null, "INVALID INSTRUMENT");
        } catch (NumberFormatException nfe) {
          JOptionPane.showMessageDialog(null, "INVALID INSTRUMENT");
        } catch (NullPointerException e) {

        }
      }
    }, "released");
    // Allows the user to set the program to "Remove Mode" which allows users to
    // remove notes by left click.
    this.keyboardHandler.addKeyEvent(KeyEvent.VK_R, new Runnable() {
      @Override
      public void run() {

        try {
          String instrument = Objects.requireNonNull(JOptionPane.showInputDialog(null,
                  "What instrument do you want to " + "remove? (1-128)"));
          // the String to int conversion happens here
          int i = Integer.parseInt(instrument.trim());
          if (i >= 1 && i <= 128) {
            ConcreteController.this.instrument = i;
            ConcreteController.this.setMouseHandler("R");
          } else JOptionPane.showMessageDialog(null, "INVALID INSTRUMENT");
        } catch (NumberFormatException nfe) {
          JOptionPane.showMessageDialog(null, "INVALID INSTRUMENT");
        } catch (NullPointerException e) {

        }
      }
    }, "released");
    this.keyboardHandler.addKeyEvent(KeyEvent.VK_7, new Runnable() {
      @Override
      public void run() {
        ConcreteController.this.song.setLowestPitch(ConcreteController.this.song.getLowestPitch()
                - 1);
        ConcreteController.this.view.renderGui();
      }
    }, "released");

    this.keyboardHandler.addKeyEvent(KeyEvent.VK_8, new Runnable() {
      @Override
      public void run() {
        ConcreteController.this.song.setHighestPitch(ConcreteController.this.song.getHighestPitch()
                + 1);
        ConcreteController.this.view.renderGui();
      }
    }, "released");
    this.keyboardHandler.addKeyEvent(KeyEvent.VK_9, new Runnable() {
      @Override
      public void run() {
        ConcreteController.this.song.setSongLength(ConcreteController.this.song.getSongLength()
                + 4);
        ConcreteController.this.view.renderGui();
      }
    }, "released");
    // Allows the user to set the program to "Edit Mode" which allows users to
    // edit notes' pitches by left click.
    this.keyboardHandler.addKeyEvent(KeyEvent.VK_E, new Runnable() {
      @Override
      public void run() {

        try {
          String instrument = Objects.requireNonNull(JOptionPane.showInputDialog(null,
                  "What instrument do you want to " + "edit? (1-128)"));
          // the String to int conversion happens here
          int i = Integer.parseInt(instrument.trim());
          if (i >= 1 && i <= 128) {
            ConcreteController.this.instrument = i;
            ConcreteController.this.setMouseHandler("E");
          } else JOptionPane.showMessageDialog(null, "INVALID INSTRUMENT");
        } catch (NumberFormatException nfe) {
          JOptionPane.showMessageDialog(null, "INVALID INSTRUMENT");
        } catch (NullPointerException e) {

        }
      }
    }, "released");
    // Sets the program to "Default Mode" which makes nothing happen on left click.
    this.keyboardHandler.addKeyEvent(KeyEvent.VK_D, new Runnable() {
      @Override
      public void run() {
        ConcreteController.this.setMouseHandler("D");
      }
    }, "released");
    this.view = Objects.requireNonNull(view);
    this.song = Objects.requireNonNull(song);
    this.view.addKeyListener(this.keyboardHandler);
    this.view.addMouseListener(this.mouseHandler);
  }

  public ConcreteController(CompoundMidiGuiView view, SongEditor song,
                            KeyboardHandler keyboardHandler, MouseHandler mouseHandler,
                            int instrument) {
    this.instrument = instrument;
    this.keyboardHandler = keyboardHandler;
    this.isPlaying = false;
    this.mouseHandler = mouseHandler;
    noteEditing = null;
    // Adds the pause/play functionality.
    this.keyboardHandler.addKeyEvent(KeyEvent.VK_SPACE, new Runnable() {
      @Override
      public void run() {
        if (isPlaying) {
          isPlaying = !isPlaying;
          pause();
        } else {
          isPlaying = !isPlaying;
          ConcreteController.this.view.restart();
          play();
        }

      }
    }, "released");
    this.keyboardHandler.addKeyEvent(KeyEvent.VK_7, new Runnable() {
      @Override
      public void run() {
        ConcreteController.this.song.setLowestPitch(ConcreteController.this.song.getLowestPitch()
                - 1);
        ConcreteController.this.view.renderGui();
      }
    }, "released");

    this.keyboardHandler.addKeyEvent(KeyEvent.VK_8, new Runnable() {
      @Override
      public void run() {
        ConcreteController.this.song.setHighestPitch(ConcreteController.this.song.getHighestPitch()
                + 1);
        ConcreteController.this.view.renderGui();
      }
    }, "released");
    this.keyboardHandler.addKeyEvent(KeyEvent.VK_9, new Runnable() {
      @Override
      public void run() {
        ConcreteController.this.song.setSongLength(ConcreteController.this.song.getSongLength()
                + 4);
        ConcreteController.this.view.renderGui();
      }
    }, "released");
    // Adds the rewind by one beat functionality.
    this.keyboardHandler.addKeyEvent(KeyEvent.VK_1, new Runnable() {
      @Override
      public void run() {
        if (!isPlaying) {
          ConcreteController.this.song.setBeat(Math.max(ConcreteController.this.song.getBeat() - 1,
                  0));
          ConcreteController.this.view.renderGui();
        }

      }
    }, "pressed");
    // Adds the fast forward by one beat functionality.
    this.keyboardHandler.addKeyEvent(KeyEvent.VK_2, new Runnable() {
      @Override
      public void run() {
        if (!isPlaying) {
          ConcreteController.this.song.setBeat(Math.min(ConcreteController.this.song.getBeat() + 1,
                  ConcreteController.this.song.getSongLength()));
          ConcreteController.this.view.renderGui();
        }

      }
    }, "pressed");
    // Adds the rewind to start functionality.
    this.keyboardHandler.addKeyEvent(KeyEvent.VK_3, new Runnable() {
      @Override
      public void run() {
        if (!isPlaying) {
          ConcreteController.this.song.setBeat(0);
          ConcreteController.this.view.renderGui();
        }

      }
    }, "pressed");
    // The next four add the scrolling functionality by arrow keys.
    this.keyboardHandler.addKeyEvent(KeyEvent.VK_LEFT, new Runnable() {
      @Override
      public void run() {
        if (!isPlaying) {
          ConcreteController.this.view.movePosition(-45, true);
        }
      }
    }, "pressed");
    this.keyboardHandler.addKeyEvent(KeyEvent.VK_RIGHT, new Runnable() {
      @Override
      public void run() {
        if (!isPlaying) {
          ConcreteController.this.view.movePosition(45, true);
        }

      }
    }, "pressed");
    this.keyboardHandler.addKeyEvent(KeyEvent.VK_UP, new Runnable() {
      @Override
      public void run() {
        ConcreteController.this.view.movePosition(-45, false);
      }
    }, "pressed");
    this.keyboardHandler.addKeyEvent(KeyEvent.VK_DOWN, new Runnable() {
      @Override
      public void run() {
        ConcreteController.this.view.movePosition(45, false);
      }
    }, "pressed");
    // Adds the scroll to home functionality.
    this.keyboardHandler.addKeyEvent(KeyEvent.VK_HOME, new Runnable() {
      @Override
      public void run() {
        if (!isPlaying) {
          ConcreteController.this.view.goHome();
        }
      }
    }, "released");
    // Adds the scroll to end functionality.
    this.keyboardHandler.addKeyEvent(KeyEvent.VK_END, new Runnable() {
      @Override
      public void run() {
        if (!isPlaying) {
          ConcreteController.this.view.goEnd();
        }
      }
    }, "released");
    // Allows the user to set the program to "Add Mode" which allows users to
    // add notes by left click.
    this.keyboardHandler.addKeyEvent(KeyEvent.VK_A, new Runnable() {
      @Override
      public void run() {
        try {
          String instrument = Objects.requireNonNull(JOptionPane.showInputDialog(null,
                  "What instrument do you want to " + "add? (1-128)"));
          // the String to int conversion happens here
          int i = Integer.parseInt(instrument.trim());
          if (i >= 1 && i <= 128) {
            ConcreteController.this.instrument = i;
            ConcreteController.this.setMouseHandler("A");
          } else JOptionPane.showMessageDialog(null, "INVALID INSTRUMENT");
        } catch (NumberFormatException nfe) {
          JOptionPane.showMessageDialog(null, "INVALID INSTRUMENT");
        } catch (NullPointerException e) {

        }
      }
    }, "released");
    // Allows the user to set the program to "Remove Mode" which allows users to
    // remove notes by left click.
    this.keyboardHandler.addKeyEvent(KeyEvent.VK_R, new Runnable() {
      @Override
      public void run() {

        try {
          String instrument = Objects.requireNonNull(JOptionPane.showInputDialog(null,
                  "What instrument do you want to " + "remove? (1-128)"));
          // the String to int conversion happens here
          int i = Integer.parseInt(instrument.trim());
          if (i >= 1 && i <= 128) {
            ConcreteController.this.instrument = i;
            ConcreteController.this.setMouseHandler("R");
          } else JOptionPane.showMessageDialog(null, "INVALID INSTRUMENT");
        } catch (NumberFormatException nfe) {
          JOptionPane.showMessageDialog(null, "INVALID INSTRUMENT");
        } catch (NullPointerException e) {

        }
      }
    }, "released");
    // Allows the user to set the program to "Edit Mode" which allows users to
    // edit notes' pitches by left click.
    this.keyboardHandler.addKeyEvent(KeyEvent.VK_E, new Runnable() {
      @Override
      public void run() {

        try {
          String instrument = Objects.requireNonNull(JOptionPane.showInputDialog(null,
                  "What instrument do you want to " + "edit? (1-128)"));
          // the String to int conversion happens here
          int i = Integer.parseInt(instrument.trim());
          if (i >= 1 && i <= 128) {
            ConcreteController.this.instrument = i;
            ConcreteController.this.setMouseHandler("E");
          } else JOptionPane.showMessageDialog(null, "INVALID INSTRUMENT");
        } catch (NumberFormatException nfe) {
          JOptionPane.showMessageDialog(null, "INVALID INSTRUMENT");
        } catch (NullPointerException e) {

        }
      }
    }, "released");
    // Sets the program to "Default Mode" which makes nothing happen on left click.
    this.keyboardHandler.addKeyEvent(KeyEvent.VK_D, new Runnable() {
      @Override
      public void run() {
        ConcreteController.this.setMouseHandler("D");
      }
    }, "released");
    this.view = Objects.requireNonNull(view);
    this.song = Objects.requireNonNull(song);
    this.view.addKeyListener(this.keyboardHandler);
    this.view.addMouseListener(this.mouseHandler);
  }

  @Override
  public void start() {
    this.view.render();
  }

  @Override
  public void play() {
    int currBeat = this.song.getBeat();
    if (currBeat != this.song.getSongLength()) {
      if (this.song.isRepeatableAtBeat(currBeat)
              && !this.visitedRepeats.containsKey(currBeat)) {
        this.currRepeatable = this.song.getRepeatableAtBeat(currBeat);
        this.song.setBeat(this.currRepeatable.getRepeatTo());
        this.visitedRepeats.put(currBeat, this.currRepeatable);
        this.repeatNumber++;
        this.view.play();
      } else if (this.currRepeatable != null) {
        this.handleRepeatable(this.currRepeatable);
        this.view.play();
      } else {
        this.view.play();
        this.song.setBeat(currBeat + 1);

      }
      Timer t = new Timer();
      t.schedule(new TimerTask() {
        @Override
        public void run() {
          if (ConcreteController.this.isPlaying) {
            ConcreteController.this.play();
          }
        }
      }, this.song.getTempo() / 1000);
    } else {
      this.isPlaying = false;
    }

  }


  private void handleRepeatable(Repeatable repeat) {
    if (repeat.doneRepeating(this.repeatNumber)) {
      this.currRepeatable = null;
      this.repeatNumber = 0;
      this.song.setBeat(this.song.getBeat() + 1);
    } else {
      int currBeat = this.song.getBeat();
      this.song.setBeat(this.currRepeatable.beatToSet(repeatNumber, this.song.getBeat()));
      if (currBeat >= this.song.getBeat()) {
        this.repeatNumber++;
      }
    }
  }

  @Override
  public void pause() {
    this.view.pause();
  }

  /**
   * Given the mode, sets the program to the respective mouse runnable.
   *
   * @param mode - A String representation of the program's mode.
   * @throws IllegalArgumentException when an invalid mode is sent.
   */
  public void setMouseHandler(String mode) {
    // DEFAULT MODE
    if (mode.equals("D")) {
      this.mouseHandler.addMouseEvent(0, new MouseRunnable() {
        @Override
        public void apply(int x, int y) {

        }
      }, "pressed");
      this.mouseHandler.addMouseEvent(0, new MouseRunnable() {
        @Override
        public void apply(int x, int y) {

        }
      }, "pressed");
      // ADD MODE
    } else if (mode.equalsIgnoreCase("A")) {
      this.mouseHandler.addMouseEvent(0, new MouseRunnable() {
        @Override
        public void apply(int x, int y) {
          String length = JOptionPane.showInputDialog(null, "How long is the " +
                  "note?");
          String volume = JOptionPane.showInputDialog(null, "What volume is your " +
                  "note? (0-127)");
          int pitchVal = ((((y - 26) / 20) - ConcreteController.this.song.getHighestPitch()) * -1);
          int beat = (x - 40) / 20;
          try {
            int lengthInt = Integer.parseInt(length.trim());
            int volumeInt = Integer.parseInt(volume.trim());
            ConcreteController.this.song.addNote(ConcreteController.pitchValToEnum(pitchVal),
                    pitchVal / 12, beat, beat + lengthInt, ConcreteController.this.instrument,
                    volumeInt);
          } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Invalid number for number or length");
          } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Note not allowed");
          } catch (NullPointerException ex) {

          }
          ConcreteController.this.view.renderGui();
        }
      }, "pressed");
    } else if (mode.equals("R")) {
      this.mouseHandler.addMouseEvent(0, new MouseRunnable() {
        @Override
        public void apply(int x, int y) {
          int pitchVal = ((((y - 26) / 20) - ConcreteController.this.song.getHighestPitch()) * -1);
          ConcreteController.this.song.removeNote(pitchVal, (x - 40) / 20,
                  ConcreteController.this.instrument);
          ConcreteController.this.view.renderGui();
        }
      }, "pressed");
      this.mouseHandler.addMouseEvent(1, new MouseRunnable() {
        @Override
        public void apply(int x, int y) {

        }
      }, "pressed");
    } else if (mode.equals("E")) {
      this.mouseHandler.addMouseEvent(0, new MouseRunnable() {
        @Override
        public void apply(int x, int y) {
          if (ConcreteController.this.noteEditing == null) {
            int pitchVal = ((((y - 26) / 20) -
                    ConcreteController.this.song.getHighestPitch()) * -1);
            noteEditing = ConcreteController.this.song.removeNote(pitchVal, (x - 40) / 20,
                    ConcreteController.this.instrument);
            ConcreteController.this.view.renderGui();
          } else {
            int pitchVal = ((((y - 26) / 20) -
                    ConcreteController.this.song.getHighestPitch()) * -1);
            int beat = (x - 40) / 20;
            try {
              int lengthInt = ConcreteController.this.noteEditing.getEndingTime() -
                      ConcreteController.this.noteEditing.getStartingTime();
              int volumeInt = ConcreteController.this.noteEditing.getVolume();
              ConcreteController.this.song.addNote(ConcreteController.pitchValToEnum(pitchVal),
                      pitchVal / 12, beat, beat + lengthInt, ConcreteController.this.instrument,
                      volumeInt);
            } catch (NumberFormatException nfe) {
              JOptionPane.showMessageDialog(null, "Invalid number for number or length");
            } catch (IllegalArgumentException ex) {
              JOptionPane.showMessageDialog(null, "Note not allowed");
              ConcreteController.this.song.addNote(ConcreteController.pitchValToEnum(
                              ConcreteController.this.noteEditing.getPitchVal()
                      ), ConcreteController.this.noteEditing.getOctave(),
                      ConcreteController.this.noteEditing.getStartingTime(),
                      ConcreteController.this.noteEditing.getEndingTime(),
                      ConcreteController.this.noteEditing.getInstrument(),
                      ConcreteController.this.noteEditing.getVolume());
              ConcreteController.this.noteEditing = null;
            } catch (NullPointerException ex) {

            }
            ConcreteController.this.noteEditing = null;
            ConcreteController.this.view.renderGui();
          }
        }
      }, "pressed");
      this.mouseHandler.addMouseEvent(1, new MouseRunnable() {
        @Override
        public void apply(int x, int y) {

        }
      }, "pressed");
    } else {
      throw new IllegalArgumentException("Not a valid mode.");
    }
  }

  /**
   * Checks if a note exists at the given mouse coordinates.
   *
   * @param x - The x coordinate of the mouse.
   * @param y - The y coordinate of the mouse.
   * @return - Is there a note at the mouse?
   */
  private boolean hasNote(int x, int y) {
    //g.fillRect(40 + 20 * i, 25 + 20 * (highest - p.getPitchVal()) + 1, 20, 19);
    boolean result = false;
    int beat = (x - 40) / 20;

    List<Playable> notesAtBeat = this.song.getBeatsNotes(beat);
    for (Playable p : notesAtBeat) {
      if (p.getPitchVal() == ((((y - 26) / 20) - this.song.getHighestPitch()) * -1)) {
        result = true;
      }
    }
    return result;
  }

  /**
   * Converts an integer pitch value into its respective pitch enumeration representation.
   *
   * @param val - The integer value of the pitch value.
   * @return The enumeration that represents the pitch value.
   */
  public static Playable.Pitches pitchValToEnum(int val) {
    val = val % 12;
    if (val == 0) {
      return Playable.Pitches.C;
    } else if (val == 1) {
      return Playable.Pitches.Cs;
    } else if (val == 2) {
      return Playable.Pitches.D;
    } else if (val == 3) {
      return Playable.Pitches.Ds;
    } else if (val == 4) {
      return Playable.Pitches.E;
    } else if (val == 5) {
      return Playable.Pitches.F;
    } else if (val == 6) {
      return Playable.Pitches.Fs;
    } else if (val == 7) {
      return Playable.Pitches.G;
    } else if (val == 8) {
      return Playable.Pitches.Gs;
    } else if (val == 9) {
      return Playable.Pitches.A;
    } else if (val == 10) {
      return Playable.Pitches.As;
    } else if (val == 11) {
      return Playable.Pitches.B;
    } else {
      return null;
    }
  }
}
