package cs3500.music.model;

/**
 * Created by Mike on 11/2/2015.
 */

/**
 * Represents a Note that is a music note
 */
public final class Note extends Playable {

  /**
   * Constructs a {@Link Note} that represents one note in a song
   *
   * @param pitch     the representation of the note's pitch portrayed by an enumeration that holds
   *                  the 12 different pitches
   * @param octave    the octave at which this note is on represented by an integer
   * @param startWhen the beat in which the note starts
   * @param endWhen   the beat in which the note ends
   * @throws IllegalArgumentException when a given octave is negative or above 10
   * @throws IllegalArgumentException when a given note's pitch value is higher than 127 or less
   *                                  than 0
   * @throws IllegalArgumentException when a note is set to start before the 0th beat
   * @throws IllegalArgumentException when a note is set to end before it starts
   */
  public Note(Pitches pitch, int octave, int startWhen, int endWhen, int instrument, int volume) {
    super(pitch, octave, startWhen, endWhen, instrument, volume);
  }

  @Override
  public boolean equals(Object that) {
    if (!(that instanceof Note)) {
      return false;
    }
    if (that == this) {
      return true;
    }
    return this.getStartingTime() == ((Note) that).getStartingTime() &&
            this.getEndingTime() == ((Note) that).getEndingTime() &&
            this.getPitchVal() == ((Note) that).getPitchVal() &&
            this.getInstrument() == ((Note) that).getInstrument();
  }

  @Override
  public int hashCode() {
    return Integer.hashCode(this.getPitchVal() * 31 + (this.getStartingTime() -
            this.getEndingTime()) * 37);
  }
}
