package cs3500.music.model;

/**
 * Created by Oliver on 12/12/2015.
 */
abstract public class BasicRepeatable extends Repeatable {

  /**
   * INVARIANTS: 1) repeatCount > 0 2) See Repeatable invariants
   */
  private int repeatCount;

  /**
   *
   * @param repeatFrom
   * @param repeatTo
   * @param repeatCount
   */
  public BasicRepeatable(int repeatFrom, int repeatTo, int repeatCount) {
    super(repeatFrom, repeatTo);
    if (repeatCount < 0) {
      throw new IllegalArgumentException("Repeat count must be >= 0");
    }
    this.repeatCount = repeatCount;
  }

  @Override
  public boolean doneRepeating(int repeatCount) {
    return repeatCount >= this.repeatCount - 1;
  }

  @Override
  public int getSkipTo(int skipNumber) {
    if (skipNumber >= repeatCount - 1) {
      throw new IllegalArgumentException("Skip number too large");
    }
    return this.getRepeatTo();
  }

  @Override
  public int beatToSet(int repeatNumber, int currentBeat) {
    if (repeatNumber >= this.repeatCount) {
      throw new IllegalArgumentException("repeat out of range");
    } else if (currentBeat == this.getRepeatFrom()) {
      return this.getRepeatTo();
    }
    return currentBeat + 1;
  }

  @Override
  public boolean isBasic() {
    return true;
  }

  @Override
  public int getRepeatCount() {
    return this.repeatCount;
  }
}
