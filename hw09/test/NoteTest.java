import org.junit.Test;

import cs3500.music.model.Note;
import cs3500.music.model.Playable;

import static org.junit.Assert.*;

/**
 * Created by Mike on 11/2/2015.
 */

/**
 * Tests for the Note class
 */
public class NoteTest {

  // TEST ERRORS IN CONSTRUCTOR
  @Test(expected = IllegalArgumentException.class)
  public void testBadConstructors() {
    Note n = new Note(Playable.Pitches.C, -1, 0, 100, 1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadConstructors1() {
    Note n = new Note(Note.Pitches.C, 11, 0, 100, 1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadConstructors2() {
    Note n = new Note(Note.Pitches.C, 5, -1, 100, 1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadConstructors3() {
    Note n = new Note(Note.Pitches.C, 5, 5, 1, 1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadConstructors4() {
    Note n = new Note(Note.Pitches.Gs, 10, 0, 100, 1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadConstructors5() {
    Note n = new Note(Note.Pitches.Gs, 10, 0, 100, 1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadConstructors6() {
    Note n = new Note(Note.Pitches.Gs, 10, 0, 100, 129, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadConstructors7() {
    Note n = new Note(Note.Pitches.Gs, 10, 0, 100, 1, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadConstructors8() {
    Note n = new Note(Note.Pitches.Gs, 10, 0, 100, 1, 128);
  }

  @Test
  public void testGetStartingTime() {
    Note n1 = new Note(Note.Pitches.C, 0, 0, 1, 1, 0);
    Note n2 = new Note(Note.Pitches.Cs, 0, 2, 3, 1, 0);
    Note n3 = new Note(Note.Pitches.D, 0, 4, 5, 1, 0);
    Note n4 = new Note(Note.Pitches.Ds, 0, 6, 7, 1, 0);
    Note n5 = new Note(Note.Pitches.E, 0, 8, 9, 1, 0);

    assertEquals(n1.getStartingTime(), 0);
    assertEquals(n2.getStartingTime(), 2);
    assertEquals(n3.getStartingTime(), 4);
    assertEquals(n4.getStartingTime(), 6);
    assertEquals(n5.getStartingTime(), 8);
  }

  @Test
  public void testGetPitchVal() {
    Note n1 = new Note(Note.Pitches.C, 0, 0, 1, 1, 0);
    Note n2 = new Note(Note.Pitches.Cs, 0, 2, 3, 1, 0);
    Note n3 = new Note(Note.Pitches.D, 1, 4, 5, 1, 0);
    Note n4 = new Note(Note.Pitches.Ds, 1, 6, 7, 1, 0);
    Note n5 = new Note(Note.Pitches.E, 3, 8, 9, 1, 0);

    assertEquals(n1.getPitchVal(), 0);
    assertEquals(n2.getPitchVal(), 1);
    assertEquals(n3.getPitchVal(), 14);
    assertEquals(n4.getPitchVal(), 15);
    assertEquals(n5.getPitchVal(), 40);
  }

  @Test
  public void testGetOctave() {
    Note n1 = new Note(Note.Pitches.C, 0, 0, 1, 1, 0);
    Note n2 = new Note(Note.Pitches.Cs, 0, 2, 3, 1, 0);
    Note n3 = new Note(Note.Pitches.D, 1, 4, 5, 1, 0);
    Note n4 = new Note(Note.Pitches.Ds, 1, 6, 7, 1, 0);
    Note n5 = new Note(Note.Pitches.E, 3, 8, 9, 1, 0);

    assertEquals(n1.getOctave(), 0);
    assertEquals(n2.getOctave(), 0);
    assertEquals(n3.getOctave(), 1);
    assertEquals(n4.getOctave(), 1);
    assertEquals(n5.getOctave(), 3);
  }

  @Test
  public void testGetEndingTime() {
    Note n1 = new Note(Note.Pitches.C, 0, 0, 1, 1, 0);
    Note n2 = new Note(Note.Pitches.Cs, 0, 2, 3, 1, 0);
    Note n3 = new Note(Note.Pitches.D, 0, 4, 5, 1, 0);
    Note n4 = new Note(Note.Pitches.Ds, 0, 6, 7, 1, 0);
    Note n5 = new Note(Note.Pitches.E, 0, 8, 9, 1, 0);

    assertEquals(n1.getEndingTime(), 1);
    assertEquals(n2.getEndingTime(), 3);
    assertEquals(n3.getEndingTime(), 5);
    assertEquals(n4.getEndingTime(), 7);
    assertEquals(n5.getEndingTime(), 9);
  }
}
