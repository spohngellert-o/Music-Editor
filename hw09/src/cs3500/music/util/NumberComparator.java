package cs3500.music.util;

import java.util.Comparator;

/**
 * Created by Oliver on 12/12/2015.
 */

/**
 * Comparator to ensure the list is sorted in increasing order
 */
public class NumberComparator implements Comparator<Integer> {
  @Override
  public int compare(Integer o1, Integer o2) {
    return o1 - o2;
  }
}
