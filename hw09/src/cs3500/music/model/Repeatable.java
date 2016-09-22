package cs3500.music.model;

import java.util.List;

/**
 * Created by Oliver on 12/12/2015.
 */
public abstract class Repeatable {
  /**
   * INVARIANTS:
   * 1) repeatFrom > repeatTo
   * 2) repeatFrom, repeatTo >= 0
   */
  private int repeatFrom, repeatTo;

  Repeatable(int repeatFrom, int repeatTo) {
    if(repeatFrom <= repeatTo) {
      throw new IllegalArgumentException("Repeat from must be greater than" +
              "repeat to");
    }
    if(repeatFrom < 0 ||  repeatTo < 0) {
      throw new IllegalArgumentException("Arguments must be positive");
    }
    this.repeatFrom = repeatFrom;
    this.repeatTo = repeatTo;
  }

  /**
   * Determines if the repeatable is done being executed
   * @param skipNumber -- The number of skips that have been performed
   * @return -- Whether or not all skips have been performed
   */
  public boolean doneRepeating(int skipNumber) {
    return skipNumber > 0;
  }

  /**
   * Gets where to repeat from
   * @return -- where you repeat from
   */
  public int getRepeatFrom() {
    return this.repeatFrom;
  }

  /**
   * Gets where to repeat to
   * @return -- Where to repeat to
   */
  public int getRepeatTo() {
    return this.repeatTo;
  }

  /**
   * Gets the end of the repeatable
   * @return -- The end of the repeatable
   */
  public int getEnd() {
    return this.repeatFrom;
  }

  /**
   * Gets the beat to skip to given the skip number
   * @param skipNumber -- The skip number to use to skip
   * @return -- Where to skip to
   * @throws IllegalArgumentException -- When the skip is not available
   */
  abstract public int getSkipTo(int skipNumber);

  /**
   * Gives back the appropriate beat to set based upon the current repeatable
   * @param repeatNumber -- The number of times a repeat has been executed
   * @param currentBeat -- the current beat of the song
   * @return the beat the song should adjust to
   * @throws IllegalArgumentException if the repeatNumber is out of range, or the currentBeat
   * is out of range.
   */
  abstract public int beatToSet(int repeatNumber, int currentBeat);

  abstract public boolean isBasic();


  public List<Integer> getSkipToList() {
    return null;
  }

  public int getSkipFrom() {
    return Integer.parseInt(null);
  }

  public int getRepeatCount() {
    return Integer.parseInt(null);
  }
}
