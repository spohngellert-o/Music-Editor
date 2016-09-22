package cs3500.music.model;

import java.util.ArrayList;
import java.util.List;

import cs3500.music.util.NumberComparator;

/**
 * Created by Oliver on 12/12/2015.
 */
public class MultipleEndingRepeat extends MultipleEndingRepeatable {


  public MultipleEndingRepeat(int repeatFrom, int repeatTo, int
                              skipFrom, int repeatEnd, int ... skipTo) {
    super(repeatFrom, repeatTo, skipFrom, repeatEnd, skipTo);


  }

}
