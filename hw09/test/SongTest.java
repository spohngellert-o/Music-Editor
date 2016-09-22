import org.junit.Test;

import cs3500.music.model.Note;
import cs3500.music.model.Song;

import static org.junit.Assert.*;

/**
 * Created by Mike on 11/2/2015.
 */

/**
 * Tests for the Song class
 */
public class SongTest {

  @Test
  public void testDisplay() {
    Song s = new Song();
    s.addNote(Note.Pitches.C, 3, 0, 101, 1, 0);
    s.addNote(Note.Pitches.Cs, 3, 3, 5, 1, 0);
    s.addNote(Note.Pitches.E, 3, 0, 13, 1, 0);
    s.addNote(Note.Pitches.C, 4, 0, 5, 1, 0);
    s.addNote(Note.Pitches.F, 4, 0, 5, 1, 0);
    s.addNote(Note.Pitches.B, 4, 0, 5, 1, 0);
    s.addNote(Note.Pitches.As, 3, 12, 12, 1, 0);
    s.display();
  }

  @Test
  public void addNoteTest() {
    Song s = new Song();
    s.addNote(Note.Pitches.C, 0, 0, 100, 1, 1);
    s.addNote(Note.Pitches.Cs, 0, 0, 5, 1, 1);
    s.addNote(Note.Pitches.D, 0, 0, 5, 1, 1);
    s.addNote(Note.Pitches.Ds, 0, 0, 5, 1, 1);
    s.addNote(Note.Pitches.E, 0, 0, 5, 1, 1);
    s.addNote(Note.Pitches.F, 0, 0, 5, 1, 1);
    s.addNote(Note.Pitches.Fs, 0, 0, 5, 1, 1);
    s.addNote(Note.Pitches.G, 0, 0, 5, 1, 1);
    s.addNote(Note.Pitches.Gs, 0, 0, 5, 1, 1);
    s.addNote(Note.Pitches.A, 0, 0, 5, 1, 1);
    s.addNote(Note.Pitches.As, 0, 0, 5, 1, 1);
    s.addNote(Note.Pitches.B, 0, 0, 5, 1, 1);
    s.addNote(Note.Pitches.C, 1, 0, 5, 1, 1);
    s.addNote(Note.Pitches.Cs, 1, 0, 5, 1, 1);
    assertEquals(s.getBeatsNotes(0).get(0).getStartingTime(), 0);
    assertEquals(s.getBeatsNotes(0).get(0).getEndingTime(), 100);
    assertEquals(s.getBeatsNotes(0).get(0).getPitchVal(), 0);
    assertEquals(s.getBeatsNotes(0).get(1).getStartingTime(), 0);
    assertEquals(s.getBeatsNotes(0).get(1).getEndingTime(), 5);
    assertEquals(s.getBeatsNotes(0).get(1).getPitchVal(), 1);
    assertEquals(s.getBeatsNotes(0).get(2).getStartingTime(), 0);
    assertEquals(s.getBeatsNotes(0).get(2).getEndingTime(), 5);
    assertEquals(s.getBeatsNotes(0).get(2).getPitchVal(), 2);
    assertEquals(s.getBeatsNotes(0).get(3).getStartingTime(), 0);
    assertEquals(s.getBeatsNotes(0).get(3).getEndingTime(), 5);
    assertEquals(s.getBeatsNotes(0).get(3).getPitchVal(), 3);
    assertEquals(s.getBeatsNotes(0).get(4).getStartingTime(), 0);
    assertEquals(s.getBeatsNotes(0).get(4).getEndingTime(), 5);
    assertEquals(s.getBeatsNotes(0).get(4).getPitchVal(), 4);
    assertEquals(s.getBeatsNotes(0).get(5).getStartingTime(), 0);
    assertEquals(s.getBeatsNotes(0).get(5).getEndingTime(), 5);
    assertEquals(s.getBeatsNotes(0).get(5).getPitchVal(), 5);
    assertEquals(s.getBeatsNotes(0).get(6).getStartingTime(), 0);
    assertEquals(s.getBeatsNotes(0).get(6).getEndingTime(), 5);
    assertEquals(s.getBeatsNotes(0).get(6).getPitchVal(), 6);
    assertEquals(s.getBeatsNotes(0).get(7).getStartingTime(), 0);
    assertEquals(s.getBeatsNotes(0).get(7).getEndingTime(), 5);
    assertEquals(s.getBeatsNotes(0).get(7).getPitchVal(), 7);
    assertEquals(s.getBeatsNotes(0).get(8).getStartingTime(), 0);
    assertEquals(s.getBeatsNotes(0).get(8).getEndingTime(), 5);
    assertEquals(s.getBeatsNotes(0).get(8).getPitchVal(), 8);
    assertEquals(s.getBeatsNotes(0).get(9).getStartingTime(), 0);
    assertEquals(s.getBeatsNotes(0).get(9).getEndingTime(), 5);
    assertEquals(s.getBeatsNotes(0).get(9).getPitchVal(), 9);
    assertEquals(s.getBeatsNotes(0).get(10).getStartingTime(), 0);
    assertEquals(s.getBeatsNotes(0).get(10).getEndingTime(), 5);
    assertEquals(s.getBeatsNotes(0).get(10).getPitchVal(), 10);
    assertEquals(s.getBeatsNotes(0).get(11).getStartingTime(), 0);
    assertEquals(s.getBeatsNotes(0).get(11).getEndingTime(), 5);
    assertEquals(s.getBeatsNotes(0).get(11).getPitchVal(), 11);
    assertEquals(s.getBeatsNotes(0).get(12).getStartingTime(), 0);
    assertEquals(s.getBeatsNotes(0).get(12).getEndingTime(), 5);
    assertEquals(s.getBeatsNotes(0).get(12).getPitchVal(), 12);
    assertEquals(s.getBeatsNotes(0).get(13).getStartingTime(), 0);
    assertEquals(s.getBeatsNotes(0).get(13).getEndingTime(), 5);
    assertEquals(s.getBeatsNotes(0).get(13).getPitchVal(), 13);
  }

  @Test
  public void addNoteTest2() {
    Song s = new Song();
    s.addNote(Note.Pitches.G, 5, 0, 5, 1, 1);
    s.addNote(Note.Pitches.G, 5, 6, 12, 1, 1);
    s.addNote(Note.Pitches.G, 5, 14, 15, 1, 0);
    s.addNote(Note.Pitches.G, 5, 16, 18, 1, 0);
    s.addNote(Note.Pitches.G, 5, 101, 104, 1, 0);
    assertEquals(s.getBeatsNotes(0).get(0).getStartingTime(), 0);
    assertEquals(s.getBeatsNotes(0).get(0).getEndingTime(), 5);
    assertEquals(s.getBeatsNotes(0).get(0).getPitchVal(), 67);
    assertEquals(s.getBeatsNotes(6).get(0).getStartingTime(), 6);
    assertEquals(s.getBeatsNotes(6).get(0).getEndingTime(), 12);
    assertEquals(s.getBeatsNotes(6).get(0).getPitchVal(), 67);
    assertEquals(s.getBeatsNotes(7).get(0).getStartingTime(), 6);
    assertEquals(s.getBeatsNotes(7).get(0).getEndingTime(), 12);
    assertEquals(s.getBeatsNotes(7).get(0).getPitchVal(), 67);
    assertEquals(s.getBeatsNotes(14).get(0).getStartingTime(), 14);
    assertEquals(s.getBeatsNotes(14).get(0).getEndingTime(), 15);
    assertEquals(s.getBeatsNotes(14).get(0).getPitchVal(), 67);
    assertEquals(s.getBeatsNotes(16).get(0).getStartingTime(), 16);
    assertEquals(s.getBeatsNotes(16).get(0).getEndingTime(), 18);
    assertEquals(s.getBeatsNotes(16).get(0).getPitchVal(), 67);
    assertEquals(s.getBeatsNotes(101).get(0).getStartingTime(), 101);
    assertEquals(s.getBeatsNotes(101).get(0).getEndingTime(), 104);
    assertEquals(s.getBeatsNotes(101).get(0).getPitchVal(), 67);
  }

  @Test
  public void addNoteTest3() {
    Song s = new Song();
    s.addNote(Note.Pitches.C, 0, 0, 1, 1, 0);
    s.addNote(Note.Pitches.Cs, 0, 2, 3, 1, 0);
    s.addNote(Note.Pitches.D, 0, 4, 5, 1, 0);
    s.addNote(Note.Pitches.Ds, 0, 6, 7, 1, 0);
    s.addNote(Note.Pitches.E, 0, 8, 9, 1, 0);
    s.addNote(Note.Pitches.F, 0, 10, 11, 1, 1);
    s.addNote(Note.Pitches.Fs, 0, 12, 13, 1, 1);
    s.addNote(Note.Pitches.G, 0, 14, 15, 1, 1);
    s.addNote(Note.Pitches.Gs, 0, 16, 17, 1, 1);
    s.addNote(Note.Pitches.A, 0, 18, 19, 1, 1);
    s.addNote(Note.Pitches.As, 0, 20, 21, 1, 1);
    s.addNote(Note.Pitches.B, 0, 22, 23, 1, 1);
    s.addNote(Note.Pitches.C, 1, 24, 25, 1, 1);
    assertEquals(s.getBeatsNotes(0).get(0).getStartingTime(), 0);
    assertEquals(s.getBeatsNotes(0).get(0).getEndingTime(), 1);
    assertEquals(s.getBeatsNotes(0).get(0).getPitchVal(), 0);
    assertEquals(s.getBeatsNotes(2).get(0).getStartingTime(), 2);
    assertEquals(s.getBeatsNotes(2).get(0).getEndingTime(), 3);
    assertEquals(s.getBeatsNotes(2).get(0).getPitchVal(), 1);
    assertEquals(s.getBeatsNotes(4).get(0).getStartingTime(), 4);
    assertEquals(s.getBeatsNotes(4).get(0).getEndingTime(), 5);
    assertEquals(s.getBeatsNotes(4).get(0).getPitchVal(), 2);
    assertEquals(s.getBeatsNotes(6).get(0).getStartingTime(), 6);
    assertEquals(s.getBeatsNotes(6).get(0).getEndingTime(), 7);
    assertEquals(s.getBeatsNotes(6).get(0).getPitchVal(), 3);
    assertEquals(s.getBeatsNotes(8).get(0).getStartingTime(), 8);
    assertEquals(s.getBeatsNotes(8).get(0).getEndingTime(), 9);
    assertEquals(s.getBeatsNotes(8).get(0).getPitchVal(), 4);
    assertEquals(s.getBeatsNotes(10).get(0).getStartingTime(), 10);
    assertEquals(s.getBeatsNotes(10).get(0).getEndingTime(), 11);
    assertEquals(s.getBeatsNotes(10).get(0).getPitchVal(), 5);
    assertEquals(s.getBeatsNotes(12).get(0).getStartingTime(), 12);
    assertEquals(s.getBeatsNotes(12).get(0).getEndingTime(), 13);
    assertEquals(s.getBeatsNotes(12).get(0).getPitchVal(), 6);
    assertEquals(s.getBeatsNotes(14).get(0).getStartingTime(), 14);
    assertEquals(s.getBeatsNotes(14).get(0).getEndingTime(), 15);
    assertEquals(s.getBeatsNotes(14).get(0).getPitchVal(), 7);
    assertEquals(s.getBeatsNotes(16).get(0).getStartingTime(), 16);
    assertEquals(s.getBeatsNotes(16).get(0).getEndingTime(), 17);
    assertEquals(s.getBeatsNotes(16).get(0).getPitchVal(), 8);
    assertEquals(s.getBeatsNotes(18).get(0).getStartingTime(), 18);
    assertEquals(s.getBeatsNotes(18).get(0).getEndingTime(), 19);
    assertEquals(s.getBeatsNotes(18).get(0).getPitchVal(), 9);
    assertEquals(s.getBeatsNotes(20).get(0).getStartingTime(), 20);
    assertEquals(s.getBeatsNotes(20).get(0).getEndingTime(), 21);
    assertEquals(s.getBeatsNotes(20).get(0).getPitchVal(), 10);
    assertEquals(s.getBeatsNotes(22).get(0).getStartingTime(), 22);
    assertEquals(s.getBeatsNotes(22).get(0).getEndingTime(), 23);
    assertEquals(s.getBeatsNotes(22).get(0).getPitchVal(), 11);
    assertEquals(s.getBeatsNotes(24).get(0).getStartingTime(), 24);
    assertEquals(s.getBeatsNotes(24).get(0).getEndingTime(), 25);
    assertEquals(s.getBeatsNotes(24).get(0).getPitchVal(), 12);
  }

  @Test
  public void addNoteTest4() {
    Song s = new Song();
    s.addNote(Note.Pitches.C, 0, 0, 50, 1, 1);
    s.addNote(Note.Pitches.G, 10, 0, 50, 1, 1);
    assertEquals(s.getBeatsNotes(0).get(0).getStartingTime(), 0);
    assertEquals(s.getBeatsNotes(0).get(0).getEndingTime(), 50);
    assertEquals(s.getBeatsNotes(0).get(0).getPitchVal(), 0);
    assertEquals(s.getBeatsNotes(0).get(1).getStartingTime(), 0);
    assertEquals(s.getBeatsNotes(0).get(1).getEndingTime(), 50);
    assertEquals(s.getBeatsNotes(0).get(1).getPitchVal(), 127);
  }

  @Test
  public void addNoteTest5() {
    Song s = new Song();
    s.addNote(Note.Pitches.C, 3, 0, 101, 1, 1);
    s.addNote(Note.Pitches.Cs, 3, 3, 5, 1, 1);
    s.addNote(Note.Pitches.E, 3, 0, 13, 1, 1);
    s.addNote(Note.Pitches.C, 4, 0, 5, 1, 1);
    s.addNote(Note.Pitches.F, 4, 0, 5, 1, 1);
    s.addNote(Note.Pitches.B, 4, 0, 5, 1, 1);
    s.addNote(Note.Pitches.As, 3, 12, 12, 1, 1);

    assertEquals(s.getBeatsNotes(0).get(0).getStartingTime(), 0);
    assertEquals(s.getBeatsNotes(0).get(0).getEndingTime(), 101);
    assertEquals(s.getBeatsNotes(0).get(0).getPitchVal(), 36);
    assertEquals(s.getBeatsNotes(3).get(1).getStartingTime(), 0);
    assertEquals(s.getBeatsNotes(3).get(1).getEndingTime(), 13);
    assertEquals(s.getBeatsNotes(3).get(1).getPitchVal(), 40);
  }

  @Test
  public void testRemoveNote1() {
    Song s = new Song();
    s.addNote(Note.Pitches.C, 0, 0, 1, 1, 1);
    s.addNote(Note.Pitches.Cs, 0, 2, 3, 1, 1);

    assertEquals(s.getBeatsNotes(0).get(0).getStartingTime(), 0);
    assertEquals(s.getBeatsNotes(0).get(0).getEndingTime(), 1);
    assertEquals(s.getBeatsNotes(0).get(0).getPitchVal(), 0);
    assertEquals(s.getBeatsNotes(2).get(0).getStartingTime(), 2);
    assertEquals(s.getBeatsNotes(2).get(0).getEndingTime(), 3);
    assertEquals(s.getBeatsNotes(2).get(0).getPitchVal(), 1);

    s.removeNote(Note.Pitches.C, 0, 0, 1, 1, 1);

    assertEquals(s.getBeatsNotes(2).get(0).getStartingTime(), 2);
    assertEquals(s.getBeatsNotes(2).get(0).getEndingTime(), 3);
    assertEquals(s.getBeatsNotes(2).get(0).getPitchVal(), 1);
  }

  @Test
  public void testRemoveNote2() {
    Song s = new Song();
    s.addNote(Note.Pitches.C, 0, 0, 1, 1, 1);
    s.addNote(Note.Pitches.Cs, 0, 2, 3, 1, 1);
    s.addNote(Note.Pitches.D, 0, 4, 5, 1, 1);

    assertEquals(s.getBeatsNotes(0).get(0).getStartingTime(), 0);
    assertEquals(s.getBeatsNotes(0).get(0).getEndingTime(), 1);
    assertEquals(s.getBeatsNotes(0).get(0).getPitchVal(), 0);
    assertEquals(s.getBeatsNotes(2).get(0).getStartingTime(), 2);
    assertEquals(s.getBeatsNotes(2).get(0).getEndingTime(), 3);
    assertEquals(s.getBeatsNotes(2).get(0).getPitchVal(), 1);
    assertEquals(s.getBeatsNotes(4).get(0).getStartingTime(), 4);
    assertEquals(s.getBeatsNotes(4).get(0).getEndingTime(), 5);
    assertEquals(s.getBeatsNotes(4).get(0).getPitchVal(), 2);

    s.removeNote(Note.Pitches.Cs, 0, 2, 3, 1, 1);

    assertEquals(s.getBeatsNotes(0).get(0).getStartingTime(), 0);
    assertEquals(s.getBeatsNotes(0).get(0).getEndingTime(), 1);
    assertEquals(s.getBeatsNotes(0).get(0).getPitchVal(), 0);
    assertEquals(s.getBeatsNotes(4).get(0).getStartingTime(), 4);
    assertEquals(s.getBeatsNotes(4).get(0).getEndingTime(), 5);
    assertEquals(s.getBeatsNotes(4).get(0).getPitchVal(), 2);
  }

  @Test
  public void testChangeNote1() {
    Song s = new Song();
    s.addNote(Note.Pitches.C, 0, 0, 1, 1, 1);

    assertEquals(s.getBeatsNotes(0).get(0).getStartingTime(), 0);
    assertEquals(s.getBeatsNotes(0).get(0).getEndingTime(), 1);
    assertEquals(s.getBeatsNotes(0).get(0).getPitchVal(), 0);

    s.changeNote(Note.Pitches.C, 0, 0, 1, 1, 1, Note.Pitches.Cs, 0, 2, 3, 1, 1);

    assertEquals(s.getBeatsNotes(2).get(0).getStartingTime(), 2);
    assertEquals(s.getBeatsNotes(2).get(0).getEndingTime(), 3);
    assertEquals(s.getBeatsNotes(2).get(0).getPitchVal(), 1);
  }

  @Test
  public void testChangeNote2() {
    Song s = new Song();
    s.addNote(Note.Pitches.C, 0, 0, 50, 1, 1);
    s.addNote(Note.Pitches.G, 10, 0, 50, 1, 1);

    assertEquals(s.getBeatsNotes(0).get(0).getStartingTime(), 0);
    assertEquals(s.getBeatsNotes(0).get(0).getEndingTime(), 50);
    assertEquals(s.getBeatsNotes(0).get(0).getPitchVal(), 0);
    assertEquals(s.getBeatsNotes(0).get(1).getStartingTime(), 0);
    assertEquals(s.getBeatsNotes(0).get(1).getEndingTime(), 50);
    assertEquals(s.getBeatsNotes(0).get(1).getPitchVal(), 127);

    s.changeNote(Note.Pitches.C, 0, 0, 50, 1, 1, Note.Pitches.Cs, 0, 0, 50, 1, 1);
    s.changeNote(Note.Pitches.G, 10, 0, 50, 1, 1, Note.Pitches.Fs, 10, 0, 50, 1, 1);

    assertEquals(s.getBeatsNotes(0).get(0).getStartingTime(), 0);
    assertEquals(s.getBeatsNotes(0).get(0).getEndingTime(), 50);
    assertEquals(s.getBeatsNotes(0).get(0).getPitchVal(), 1);
    assertEquals(s.getBeatsNotes(0).get(1).getStartingTime(), 0);
    assertEquals(s.getBeatsNotes(0).get(1).getEndingTime(), 50);
    assertEquals(s.getBeatsNotes(0).get(1).getPitchVal(), 126);
  }


  // TEST ERRORS IN ADDNOTE
  @Test(expected = IllegalArgumentException.class)
  public void testBadAddNote1() {
    Song s = new Song();
    s.addNote(Note.Pitches.C, 1, 4, 100, 1, 1);
    s.addNote(Note.Pitches.C, 1, 1, 5, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadAddNote2() {
    Song s = new Song();
    s.addNote(Note.Pitches.C, 1, 5, 100, 1, 1);
    s.addNote(Note.Pitches.C, 1, 4, 6, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadAddNote3() {
    Song s = new Song();
    s.addNote(Note.Pitches.C, 1, 5, 6, 1, 1);
    s.addNote(Note.Pitches.C, 1, 1, 10, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadAddNote4() {
    Song s = new Song();
    s.addNote(Note.Pitches.C, 1, 5, 100, 1, 1);
    s.addNote(Note.Pitches.C, 1, 5, 5, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadAddNote5() {
    Song s = new Song();
    s.addNote(Note.Pitches.C, 1, 5, 100, 1, 1);
    s.addNote(Note.Pitches.C, 1, 99, 99, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadAddNote6() {
    Song s = new Song();
    s.addNote(Note.Pitches.C, 0, 0, 100, 1, 1);
    s.addNote(Note.Pitches.Cs, 0, 0, 5, 1, 1);
    s.addNote(Note.Pitches.D, 0, 0, 5, 1, 1);
    s.addNote(Note.Pitches.Ds, 0, 0, 5, 1, 1);
    s.addNote(Note.Pitches.E, 0, 0, 5, 1, 1);
    s.addNote(Note.Pitches.C, 1, 4, 100, 1, 1);
    s.addNote(Note.Pitches.C, 1, 99, 99, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadAddNote7() {
    Song s = new Song();
    s.addNote(Note.Pitches.C, 1, 5, 100, 1, 1);
    s.addNote(Note.Pitches.C, 1, 25, 30, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadAddNote8() {
    Song s = new Song();
    s.addNote(Note.Pitches.C, 1, 5, 100, 1, 1);
    s.addNote(Note.Pitches.C, 1, 25, 110, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadAddNote9() {
    Song s = new Song();
    s.addNote(Note.Pitches.C, 1, 5, 100, 1, 1);
    s.addNote(Note.Pitches.C, 1, 5, 100, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadAddNote10() {
    Song s = new Song();
    s.addNote(Note.Pitches.C, 1, 5, 100, 5, 1);
    s.addNote(Note.Pitches.C, 1, 25, 110, 5, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadAddNote11() {
    Song s = new Song();
    s.addNote(Note.Pitches.C, 1, 5, 100, 0, 1);
  }

  // TEST ERRORS IN REMOVENOTE
  @Test(expected = IllegalArgumentException.class)
  public void testBadRemoveNote1() {
    Song s = new Song();
    s.removeNote(Note.Pitches.A, 3, 23, 25, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadRemoveNote2() {
    Song s = new Song();
    s.addNote(Note.Pitches.C, 0, 0, 100, 1, 1);
    s.addNote(Note.Pitches.Cs, 0, 0, 5, 1, 1);
    s.removeNote(Note.Pitches.A, 3, 23, 25, 1, 1);
  }

  // TEST ERRORS IN CHANGENOTE
  @Test(expected = IllegalArgumentException.class)
  public void testBadChangeNote1() {
    Song s = new Song();
    s.addNote(Note.Pitches.C, 0, 0, 50, 1, 1);
    s.addNote(Note.Pitches.G, 10, 0, 50, 1, 1);

    s.changeNote(Note.Pitches.C, 0, 0, 50, 1, 1, Note.Pitches.G, 10, 0, 50, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadChangeNote2() {
    Song s = new Song();
    s.removeNote(Note.Pitches.A, 3, 23, 25, 1, 1);

    s.changeNote(Note.Pitches.A, 3, 23, 25, 1, 1, Note.Pitches.A, 3, 23, 25, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadChangeNote3() {
    Song s = new Song();
    s.removeNote(Note.Pitches.A, 3, 23, 25, 1, 1);

    s.changeNote(Note.Pitches.A, 3, 23, 25, 1, 1, Note.Pitches.A, 3, 24, 25, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadChangeNote4() {
    Song s = new Song();
    s.addNote(Note.Pitches.C, 0, 0, 50, 1, 1);
    s.addNote(Note.Pitches.G, 10, 5, 50, 1, 1);

    s.changeNote(Note.Pitches.C, 0, 0, 50, 1, 1, Note.Pitches.G, 10, 0, 50, 1, 1);
  }

  // TEST ERRORS IN GETBEATSNOTES
  @Test(expected = IllegalArgumentException.class)
  public void testBadGetBeatsNotes1() {
    Song s = new Song();
    s.addNote(Note.Pitches.C, 0, 0, 100, 1, 1);
    s.getBeatsNotes(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadGetBeatsNotes2() {
    Song s = new Song();
    s.addNote(Note.Pitches.C, 0, 0, 100, 1, 1);
    s.getBeatsNotes(101);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadGetBeatsNotes3() {
    Song s = new Song();
    s.getBeatsNotes(101);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadGetBeatsNotes4() {
    Song s = new Song();
    s.getBeatsNotes(-1);
  }
}
