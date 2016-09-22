package cs3500.music.model;

/**
 * Created by Oliver on 11/10/2015.
 */

/**
 * Represents a classification of playable entities in music
 */
public abstract class Playable {
  public enum Pitches {
    C(0), Cs(1), D(2), Ds(3), E(4), F(5), Fs(6), G(7), Gs(8), A(9), As(10), B(11);

    private int value;

    /**
     * Constructs a Pitches enum
     *
     * @param value the pitch value
     */
    Pitches(int value) {
      this.value = value;
    }

    /**
     * Return the value of the pitch
     *
     * @return the pitch value
     */
    public int getValue() {
      return value;
    }
  }

  private Pitches pitch;
  private int instrument;
  private int octave;
  private int startWhen;
  private int endWhen;
  private int volume;

  // INVARIANT 1: notes must end after the same beat that they begin on
  // INVARIANT 2: note pitch values cannot go under 0 or over 127
  // INVARIANT 3: instrument has to be between 1 and 128
  // INVARIANT 4: volume must be between 0 and 127

  /**
   * Constructs a {@Link Playable} that represents one playable entity in a song
   *
   * @param pitch      the representation of the note's pitch portrayed by an enumeration
   *                   that holds the 12 different pitches
   * @param octave     the octave at which this note is on represented by an integer
   * @param startWhen  the beat in which the note starts
   * @param endWhen    the beat in which the note ends
   * @param instrument the instrument that plays this note
   * @param volume     the volume of the note
   * @throws IllegalArgumentException when a given octave is negative or above 10
   * @throws IllegalArgumentException when a given note's pitch value is higher than 127 or less
   *                                  than 0
   * @throws IllegalArgumentException when a note is set to start before the 0th beat
   * @throws IllegalArgumentException when a note is set to end before it starts
   * @throws IllegalArgumentException when a note is given an invalid instrument < 1 or > 128
   * @throws IllegalArgumentException when a note is given an invalid volume < 0 or > 127
   */
  protected Playable(Pitches pitch, int octave,
                     int startWhen, int endWhen, int instrument, int volume) {
    this.pitch = pitch;
    if (octave < 0 || octave > 10) {
      throw new IllegalArgumentException("Outside of perceivable range.");
    }
    if (pitch.getValue() + (12 * octave) > 127 ||
            pitch.getValue() + (12 * octave) < 0) {
      throw new IllegalArgumentException("Outside of perceivable range.");
    }
    this.octave = octave;
    if (startWhen < 0) {
      throw new IllegalArgumentException("Cannot start at negative time.");
    }
    if (endWhen < startWhen) {
      throw new IllegalArgumentException("Note cannot end before it begins.");
    }
    this.startWhen = startWhen;
    this.endWhen = endWhen;
    if (volume < 0 || volume > 127) {
      throw new IllegalArgumentException("Not a valid volume.");
    }
    this.volume = volume;
    if (instrument < 1 || instrument > 128) {
      throw new IllegalArgumentException("Not a valid instrument.");
    }
    this.instrument = instrument;
  }

  /**
   * Returns the playable's starting time
   *
   * @return respective start beat represented as an integer
   */
  public int getStartingTime() {
    return this.startWhen;
  }

  /**
   * Returns the playable's pitch
   *
   * @return respective pitch value represented as an integer
   */
  public int getPitchVal() {
    return (this.octave * 12) + this.pitch.getValue();
  }

  /**
   * gets the volume
   *
   * @return the volume
   */
  public int getVolume() {
    return this.volume;
  }

  /**
   * Returns the note's octave
   *
   * @return respective octave represented as an integer
   */
  public int getOctave() {
    return this.octave;
  }

  /**
   * Returns the playable's ending time
   *
   * @return respective end beat represented as an integer
   */
  public int getEndingTime() {
    return this.endWhen;
  }

  /**
   * Returns the playable's note name base on pitch value
   *
   * @param val pitch value
   * @return the name in format: "C#5 "
   */
  public static String noteValToName(int val) {
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
   * Returns the playable's respective instrument
   *
   * @return respective instrument represented as an integer
   */
  public int getInstrument() {
    return this.instrument;
  }

}
