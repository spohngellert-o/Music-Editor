package cs3500.music.model;

import java.util.ArrayList;
import java.util.List;

import cs3500.music.util.NumberComparator;

/**
 * Created by Oliver on 12/12/2015.
 */
public abstract class MultipleEndingRepeatable extends Repeatable {
  /**
   * INVARIANTS:
   * 1) skipTo is sorted in increasing order
   * 2) skipFrom < all ints in skipTo
   * 3) skipFrom > 0
   * 4) skipFrom >= repeatFrom
   * 5) endOfRepeats
   * 6) endOfRepeats
   */
  //
  private List<Integer> skipTo;
  //
  private int skipFrom;
  //
  private int endOfRepeats;

  MultipleEndingRepeatable(int repeatFrom, int repeatTo, int skipFrom,
                           int endOfRepeats, int ... skipTo) {
    super(repeatFrom, repeatTo);
    this.endOfRepeats = endOfRepeats;
    this.skipFrom = skipFrom;
    if(repeatFrom < skipFrom) {
      throw new IllegalArgumentException("Must skip from before where it repeats");
    }
    this.skipTo = new ArrayList<Integer>();
    for(int currSkip: skipTo) {
      if(currSkip < this.skipFrom) {
        throw new IllegalArgumentException("Skip To must be >= Skip From");
      }
      else {
        this.skipTo.add(currSkip);
      }
    }
    this.skipTo.sort(new NumberComparator());
    System.out.println(this.skipTo.toString());
  }

  /**
   * Gets the number of skips performed in this multiple ending repeat
   * @return -- how many endings there are
   */
  public int numberOfSkips() {
    return this.skipTo.size();
  }


  @Override
  public int getSkipTo(int skipNumber) {
    if(skipNumber >= this.skipTo.size()) {
      throw new IllegalArgumentException("This skip is not contained in the repeat");
    }
    return this.skipTo.get(skipNumber);
  }

  @Override
  public int getEnd() {
    return this.endOfRepeats;
  }

  /**
   *
   * @param repeatNumber
   * @return
   */
  private int getNextRepeatFrom(int repeatNumber) {
    for(int i = repeatNumber; i < this.skipTo.size() - 1; i++) {
      if(!(this.skipTo.get(i) == this.skipTo.get(i + 1))) {
        return this.skipTo.get(i + 1);
      }
    }
    return this.endOfRepeats;

  }

  @Override
  public int beatToSet(int repeatNumber, int currentBeat) {
    if(repeatNumber - 1 >= this.skipTo.size()) {
      throw new IllegalArgumentException("repeat number out of range");
    }
    else if(currentBeat == skipFrom) {
      return this.skipTo.get(repeatNumber - 1);
    }
    else if(this.getNextRepeatFrom(repeatNumber - 1) == currentBeat &&
            repeatNumber < this.skipTo.size()) {

      return this.getRepeatTo();
    }
    return currentBeat + 1;
  }
  @Override
  public boolean doneRepeating(int skipNumber) {
    return skipNumber - 1 >= this.numberOfSkips();
  }

  @Override
  public boolean isBasic() {
    return false;
  }

  /**
   *
   * @return
   */

  @Override
  public List<Integer> getSkipToList() {
    return this.skipTo;
  }

  @Override
  public int getSkipFrom() {
    return this.skipFrom;
  }
}
