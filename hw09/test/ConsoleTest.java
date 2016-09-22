import org.junit.Test;

import cs3500.music.model.Playable;
import cs3500.music.model.Song;
import cs3500.music.model.SongEditor;
import cs3500.music.view.ConsoleView;

import static org.junit.Assert.*;

/**
 * Created by Oliver on 11/13/2015.
 */

/**
 * Tests for the ConsoleView
 */
public class ConsoleTest {
  @Test
  public void consoleTest1() {
    Appendable out = new StringBuilder();
    SongEditor song1 = new Song();
    song1.addNote(Playable.Pitches.C, 4, 0, 2, 1, 1);
    ConsoleView cv = new ConsoleView(song1, out);
    cv.render();
    StringBuilder buildOutput1 = new StringBuilder();
    buildOutput1.append("  ");
    buildOutput1.append("C4  \n");
    buildOutput1.append("0 ");
    buildOutput1.append("X   \n");
    buildOutput1.append("1 ");
    buildOutput1.append("|   \n");
    assertEquals(out.toString(), buildOutput1.toString());

    song1.addNote(Playable.Pitches.C, 5, 0, 2, 1, 1);
    StringBuilder out2 = new StringBuilder();
    ConsoleView cv2 = new ConsoleView(song1, out2);
    cv2.render();
    StringBuilder buildOutput2 = new StringBuilder();
    buildOutput2.append("  ");
    for (int i = 4; i < 6; i++) {
      for (int j = 0; j < 12; j++) {
        String currVal = Playable.noteValToName(i * 12 + j);
        while (currVal.length() < 4) {
          currVal += " ";
        }
        buildOutput2.append(currVal);
        if (i == 5) {
          j = 12;
          i = 7;
        }
      }
    }
    buildOutput2.append("\n");
    buildOutput2.append("0 ");
    for (int i = 5; i < 7; i++) {
      for (int j = 0; j < 12; j++) {
        if (j == 0) {
          buildOutput2.append("X   ");
        } else {
          buildOutput2.append("    ");
        }
        if (i == 6) {
          i = 7;
          j = 12;
        }
      }
    }
    buildOutput2.append("\n1 ");
    for (int i = 5; i < 7; i++) {
      for (int j = 0; j < 12; j++) {
        if (j == 0) {
          buildOutput2.append("|   ");
        } else {
          buildOutput2.append("    ");
        }
        if (i == 6) {
          i = 7;
          j = 12;
        }
      }
    }
    buildOutput2.append("\n");
    assertEquals(out2.toString(), buildOutput2.toString());

    song1.addNote(Playable.Pitches.G, 4, 1, 3, 1, 1);
    StringBuilder out3 = new StringBuilder();
    ConsoleView cv3 = new ConsoleView(song1, out3);
    cv3.render();
    StringBuilder build3 = new StringBuilder();
    build3.append("  ");
    for (int i = 4; i < 6; i++) {
      for (int j = 0; j < 12; j++) {
        String currVal = Playable.noteValToName(i * 12 + j);
        while (currVal.length() < 4) {
          currVal += " ";
        }
        build3.append(currVal);
        if (i == 5) {
          j = 12;
          i = 7;
        }
      }
    }
    build3.append("\n");
    build3.append("0 ");
    for (int i = 5; i < 8; i++) {
      for (int j = 0; j < 13; j++) {
        if ((j % 12 == 0 && i == 5) || (j % 12 == 7 && i == 6)) {
          build3.append("X   ");
        } else if ((j % 12 == 0 && i == 6) || (j % 12 == 7 && i == 7)) {
          build3.append("|   ");
        } else {
          build3.append("    ");
        }
      }
      if (i != 7) {
        build3.append("\n" + (i - 4) + " ");
      } else {
        build3.append("\n");
      }

    }
    assertEquals(out3.toString(), build3.toString());
  }

  @Test
  public void consoleTest3() {
    Appendable out = new StringBuilder();
    SongEditor song1 = new Song();
    song1.addNote(Playable.Pitches.C, 3, 0, 2, 1, 1);
    song1.addNote(Playable.Pitches.Cs, 3, 1, 3, 1, 1);
    song1.addNote(Playable.Pitches.D, 3, 2, 4, 1, 1);
    song1.addNote(Playable.Pitches.Ds, 3, 4, 6, 1, 1);
    song1.addNote(Playable.Pitches.E, 3, 6, 8, 1, 1);
    song1.addNote(Playable.Pitches.F, 3, 9, 11, 1, 1);
    ConsoleView cv = new ConsoleView(song1, out);
    cv.render();
    StringBuilder buildOutput1 = new StringBuilder();
    buildOutput1.append("   C3  C#3 D3  D#3 E3  F3  \n");
    buildOutput1.append("0  X                       \n");
    buildOutput1.append("1  |   X                   \n");
    buildOutput1.append("2      |   X               \n");
    buildOutput1.append("3          |               \n");
    buildOutput1.append("4              X           \n");
    buildOutput1.append("5              |           \n");
    buildOutput1.append("6                  X       \n");
    buildOutput1.append("7                  |       \n");
    buildOutput1.append("8                          \n");
    buildOutput1.append("9                      X   \n");
    buildOutput1.append("10                     |   \n");
    assertEquals(out.toString(), buildOutput1.toString());
  }

  @Test
  public void consoleTest4() {
    Appendable out = new StringBuilder();
    SongEditor song1 = new Song();
    song1.addNote(Playable.Pitches.G, 1, 0, 11, 1, 1);
    song1.addNote(Playable.Pitches.Gs, 1, 0, 10, 1, 1);
    song1.addNote(Playable.Pitches.A, 1, 0, 9, 1, 1);
    song1.addNote(Playable.Pitches.As, 1, 0, 8, 1, 1);
    song1.addNote(Playable.Pitches.B, 1, 0, 7, 1, 1);
    song1.addNote(Playable.Pitches.C, 2, 0, 6, 1, 1);
    ConsoleView cv = new ConsoleView(song1, out);
    cv.render();
    StringBuilder buildOutput1 = new StringBuilder();
    buildOutput1.append("   G1  G#1 A1  A#1 B1  C2  \n");
    buildOutput1.append("0  X   X   X   X   X   X   \n");
    buildOutput1.append("1  |   |   |   |   |   |   \n");
    buildOutput1.append("2  |   |   |   |   |   |   \n");
    buildOutput1.append("3  |   |   |   |   |   |   \n");
    buildOutput1.append("4  |   |   |   |   |   |   \n");
    buildOutput1.append("5  |   |   |   |   |   |   \n");
    buildOutput1.append("6  |   |   |   |   |       \n");
    buildOutput1.append("7  |   |   |   |           \n");
    buildOutput1.append("8  |   |   |               \n");
    buildOutput1.append("9  |   |                   \n");
    buildOutput1.append("10 |                       \n");
    assertEquals(out.toString(), buildOutput1.toString());
  }

  @Test
  public void consoleTest5() {
    Appendable out = new StringBuilder();
    SongEditor song1 = new Song();
    song1.addNote(Playable.Pitches.G, 1, 0, 1, 1, 1);
    song1.addNote(Playable.Pitches.G, 1, 1, 2, 1, 1);
    song1.addNote(Playable.Pitches.G, 1, 3, 4, 1, 1);
    song1.addNote(Playable.Pitches.G, 1, 5, 6, 1, 1);
    song1.addNote(Playable.Pitches.G, 1, 7, 11, 1, 1);
    song1.addNote(Playable.Pitches.Gs, 1, 0, 1, 1, 1);
    song1.addNote(Playable.Pitches.Gs, 1, 1, 2, 1, 1);
    song1.addNote(Playable.Pitches.Gs, 1, 3, 4, 1, 1);
    song1.addNote(Playable.Pitches.Gs, 1, 5, 6, 1, 1);
    song1.addNote(Playable.Pitches.Gs, 1, 7, 11, 1, 1);
    song1.addNote(Playable.Pitches.A, 1, 0, 1, 1, 1);
    song1.addNote(Playable.Pitches.A, 1, 2, 3, 1, 1);
    song1.addNote(Playable.Pitches.A, 1, 3, 4, 1, 1);
    song1.addNote(Playable.Pitches.A, 1, 5, 6, 1, 1);
    song1.addNote(Playable.Pitches.A, 1, 7, 11, 1, 1);
    song1.addNote(Playable.Pitches.C, 2, 0, 11, 1, 1);
    ConsoleView cv = new ConsoleView(song1, out);
    cv.render();
    StringBuilder buildOutput1 = new StringBuilder();
    buildOutput1.append("   G1  G#1 A1  A#1 B1  C2  \n");
    buildOutput1.append("0  X   X   X           X   \n");
    buildOutput1.append("1  X   X               |   \n");
    buildOutput1.append("2          X           |   \n");
    buildOutput1.append("3  X   X   X           |   \n");
    buildOutput1.append("4                      |   \n");
    buildOutput1.append("5  X   X   X           |   \n");
    buildOutput1.append("6                      |   \n");
    buildOutput1.append("7  X   X   X           |   \n");
    buildOutput1.append("8  |   |   |           |   \n");
    buildOutput1.append("9  |   |   |           |   \n");
    buildOutput1.append("10 |   |   |           |   \n");
    assertEquals(out.toString(), buildOutput1.toString());
  }

  @Test
  public void consoleTest6() {
    Appendable out = new StringBuilder();
    SongEditor song1 = new Song();
    song1.addNote(Playable.Pitches.G, 10, 0, 120, 1, 1);
    song1.addNote(Playable.Pitches.Fs, 10, 0, 120, 1, 1);
    ConsoleView cv = new ConsoleView(song1, out);
    cv.render();
    StringBuilder buildOutput1 = new StringBuilder();
    buildOutput1.append("    F#10G10 \n");
    for (int i = 0; i < 120; i++) {
      if (i == 0) {
        buildOutput1.append(i + "   X   X   \n");
      } else if (i > 0 && i < 10) {
        buildOutput1.append(i + "   |   |   \n");
      } else if (i >= 10 && i < 100) {
        buildOutput1.append(i + "  |   |   \n");
      } else {
        buildOutput1.append(i + " |   |   \n");
      }
    }
    assertEquals(out.toString(), buildOutput1.toString());
  }

  @Test
  public void consoleTest7() {
    Appendable out = new StringBuilder();
    SongEditor song1 = new Song();
    ConsoleView cv = new ConsoleView(song1, out);
    cv.render();
    StringBuilder buildOutput1 = new StringBuilder();
    buildOutput1.append("   \n");
    assertEquals(out.toString(), buildOutput1.toString());
  }

  @Test
  public void consoleTest8() {
    Appendable out = new StringBuilder();
    SongEditor song1 = new Song();
    song1.addNote(Playable.Pitches.G, 1, 0, 2, 1, 1);
    song1.addNote(Playable.Pitches.C, 2, 0, 2, 1, 1);
    song1.addNote(Playable.Pitches.Cs, 3, 0, 2, 1, 1);
    ConsoleView cv = new ConsoleView(song1, out);
    cv.render();
    StringBuilder buildOutput1 = new StringBuilder();
    buildOutput1.append("  G1  G#1 A1  A#1 B1  C2  C#2 D2  D#2 E2" +
            "  F2  F#2 G2  G#2 A2  A#2 B2  C3  C#3 \n");
    buildOutput1.append("0 X                   X                 " +
            "                                  X   \n");
    buildOutput1.append("1 |                   |                 " +
            "                                  |   \n");
    assertEquals(out.toString(), buildOutput1.toString());
  }

  @Test
  public void consoleTest9() {
    Appendable out = new StringBuilder();
    SongEditor song1 = new Song();
    song1.addNote(Playable.Pitches.C, 0, 0, 2, 1, 1);
    ConsoleView cv = new ConsoleView(song1, out);
    cv.render();
    StringBuilder buildOutput1 = new StringBuilder();
    buildOutput1.append("  C0  \n");
    buildOutput1.append("0 X   \n");
    buildOutput1.append("1 |   \n");
    assertEquals(out.toString(), buildOutput1.toString());
  }

  @Test
  public void consoleTest10() {
    Appendable out = new StringBuilder();
    SongEditor song1 = new Song();
    song1.addNote(Playable.Pitches.G, 10, 0, 2, 1, 1);
    ConsoleView cv = new ConsoleView(song1, out);
    cv.render();
    StringBuilder buildOutput1 = new StringBuilder();
    buildOutput1.append("  G10 \n");
    buildOutput1.append("0 X   \n");
    buildOutput1.append("1 |   \n");
    assertEquals(out.toString(), buildOutput1.toString());
  }

  @Test
  public void consoleTest11() {
    Appendable out = new StringBuilder();
    SongEditor song1 = new Song();
    song1.addNote(Playable.Pitches.G, 10, 0, 2, 1, 1);
    song1.addNote(Playable.Pitches.G, 10, 0, 2, 2, 1);
    song1.addNote(Playable.Pitches.G, 10, 0, 2, 3, 1);
    song1.addNote(Playable.Pitches.G, 10, 0, 2, 50, 1);
    song1.addNote(Playable.Pitches.G, 10, 0, 2, 51, 1);
    ConsoleView cv = new ConsoleView(song1, out);
    cv.render();
    StringBuilder buildOutput1 = new StringBuilder();
    buildOutput1.append("  G10 \n");
    buildOutput1.append("0 X   \n");
    buildOutput1.append("1 |   \n");
    assertEquals(out.toString(), buildOutput1.toString());
  }

  @Test
  public void consoleTest12() {
    Appendable out = new StringBuilder();
    SongEditor song1 = new Song();
    song1.addNote(Playable.Pitches.C, 9, 0, 2, 1, 1);
    song1.addNote(Playable.Pitches.Cs, 9, 0, 2, 1, 30);
    song1.addNote(Playable.Pitches.D, 9, 0, 2, 1, 50);
    song1.addNote(Playable.Pitches.Ds, 9, 0, 2, 1, 60);
    song1.addNote(Playable.Pitches.E, 9, 0, 2, 1, 100);
    song1.addNote(Playable.Pitches.F, 9, 0, 2, 1, 127);
    song1.addNote(Playable.Pitches.Fs, 9, 0, 2, 1, 1);
    song1.addNote(Playable.Pitches.G, 9, 0, 2, 2, 1);
    song1.addNote(Playable.Pitches.Gs, 9, 0, 2, 2, 1);
    song1.addNote(Playable.Pitches.A, 9, 0, 2, 2, 1);
    song1.addNote(Playable.Pitches.As, 9, 0, 2, 2, 1);
    song1.addNote(Playable.Pitches.B, 9, 0, 2, 2, 1);
    ConsoleView cv = new ConsoleView(song1, out);
    cv.render();
    StringBuilder buildOutput1 = new StringBuilder();
    buildOutput1.append("  C9  C#9 D9  D#9 E9  F9  F#9 G9  G#9 A9  A#9 B9  \n");
    buildOutput1.append("0 X   X   X   X   X   X   X   X   X   X   X   X   \n");
    buildOutput1.append("1 |   |   |   |   |   |   |   |   |   |   |   |   \n");
    assertEquals(out.toString(), buildOutput1.toString());
  }

  @Test
  public void consoleTest13() {
    Appendable out = new StringBuilder();
    SongEditor song1 = new Song();
    song1.addNote(Playable.Pitches.F, 3, 9, 11, 1, 1);
    song1.addNote(Playable.Pitches.E, 3, 6, 8, 1, 1);
    song1.addNote(Playable.Pitches.Ds, 3, 4, 6, 1, 1);
    song1.addNote(Playable.Pitches.D, 3, 2, 4, 1, 1);
    song1.addNote(Playable.Pitches.Cs, 3, 1, 3, 1, 1);
    song1.addNote(Playable.Pitches.C, 3, 0, 2, 1, 1);
    ConsoleView cv = new ConsoleView(song1, out);
    cv.render();
    StringBuilder buildOutput1 = new StringBuilder();
    buildOutput1.append("   C3  C#3 D3  D#3 E3  F3  \n");
    buildOutput1.append("0  X                       \n");
    buildOutput1.append("1  |   X                   \n");
    buildOutput1.append("2      |   X               \n");
    buildOutput1.append("3          |               \n");
    buildOutput1.append("4              X           \n");
    buildOutput1.append("5              |           \n");
    buildOutput1.append("6                  X       \n");
    buildOutput1.append("7                  |       \n");
    buildOutput1.append("8                          \n");
    buildOutput1.append("9                      X   \n");
    buildOutput1.append("10                     |   \n");
    assertEquals(out.toString(), buildOutput1.toString());
  }

  @Test
  public void consoleTest14() {
    Appendable out = new StringBuilder();
    SongEditor song1 = new Song();
    song1.addNote(Playable.Pitches.F, 3, 0, 2, 1, 1);
    song1.addNote(Playable.Pitches.E, 3, 1, 3, 1, 1);
    song1.addNote(Playable.Pitches.Ds, 3, 2, 4, 1, 1);
    song1.addNote(Playable.Pitches.D, 3, 4, 6, 1, 1);
    song1.addNote(Playable.Pitches.Cs, 3, 6, 8, 1, 1);
    song1.addNote(Playable.Pitches.C, 3, 9, 11, 1, 1);
    ConsoleView cv = new ConsoleView(song1, out);
    cv.render();
    StringBuilder buildOutput1 = new StringBuilder();
    buildOutput1.append("   C3  C#3 D3  D#3 E3  F3  \n");
    buildOutput1.append("0                      X   \n");
    buildOutput1.append("1                  X   |   \n");
    buildOutput1.append("2              X   |       \n");
    buildOutput1.append("3              |           \n");
    buildOutput1.append("4          X               \n");
    buildOutput1.append("5          |               \n");
    buildOutput1.append("6      X                   \n");
    buildOutput1.append("7      |                   \n");
    buildOutput1.append("8                          \n");
    buildOutput1.append("9  X                       \n");
    buildOutput1.append("10 |                       \n");
    assertEquals(out.toString(), buildOutput1.toString());
  }
}
