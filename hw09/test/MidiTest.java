import org.junit.Test;

import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

import cs3500.music.model.Playable;
import cs3500.music.model.Song;
import cs3500.music.model.SongEditor;
import cs3500.music.util.MockReceiver;
import cs3500.music.util.MockSynthesizer;
import cs3500.music.view.MidiViewImpl;

import static org.junit.Assert.*;

/**
 * Created by Oliver on 11/12/2015.
 */

/**
 * Tests the Midi View
 */
public class MidiTest {

  @Test
  public void testMidi1() {
    SongEditor s = new Song();
    s.addNote(Playable.Pitches.C, 5, 0, 50, 52, 1);
    Synthesizer synth = new MockSynthesizer();
    MidiViewImpl mv = new MidiViewImpl(s, synth);
    MockReceiver r;
    try {
      r = (MockReceiver) synth.getReceiver();
      mv.renderForMidi();
      assertEquals(r.getMock(), "3 144 60 1\n3 128 60 1\n");
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }

  }

  @Test
  public void testMidi2() {
    SongEditor s = new Song();
    s.addNote(Playable.Pitches.C, 5, 0, 50, 52, 1);
    s.addNote(Playable.Pitches.C, 4, 0, 50, 52, 1);
    Synthesizer synth = new MockSynthesizer();
    MidiViewImpl mv = new MidiViewImpl(s, synth);
    MockReceiver r;
    try {
      r = (MockReceiver) synth.getReceiver();
      mv.renderForMidi();
      assertEquals(r.getMock(), "3 144 60 1\n3 128 60 1\n3 144 48 1\n3 128 48 1\n");
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testMidi3() {
    SongEditor s = new Song();
    s.addNote(Playable.Pitches.C, 5, 25, 50, 52, 1);
    s.addNote(Playable.Pitches.C, 4, 0, 50, 52, 1);
    Synthesizer synth = new MockSynthesizer();
    MidiViewImpl mv = new MidiViewImpl(s, synth);
    MockReceiver r;
    try {
      r = (MockReceiver) synth.getReceiver();
      mv.renderForMidi();
      assertEquals(r.getMock(), "3 144 48 1\n3 128 48 1\n3 144 60 1\n" +
              "3 128 60 1\n");
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testMidi4() {
    SongEditor s = new Song();
    s.addNote(Playable.Pitches.A, 5, 25, 75, 52, 1);
    s.addNote(Playable.Pitches.C, 4, 0, 50, 52, 1);
    s.addNote(Playable.Pitches.D, 6, 12, 20, 40, 64);
    Synthesizer synth = new MockSynthesizer();
    MidiViewImpl mv = new MidiViewImpl(s, synth);
    MockReceiver r;
    try {
      r = (MockReceiver) synth.getReceiver();
      mv.renderForMidi();
      assertEquals(r.getMock(), "3 144 48 1\n" +
              "3 128 48 1\n7 144 74 64\n" +
              "7 128 74 64\n3 144 69 1\n" +
              "3 128 69 1\n");
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testMidi5() {
    SongEditor s = new Song();
    s.addNote(Playable.Pitches.G, 10, 25, 75, 52, 1);
    s.addNote(Playable.Pitches.C, 0, 0, 50, 52, 1);
    s.addNote(Playable.Pitches.D, 6, 12, 20, 40, 64);
    Synthesizer synth = new MockSynthesizer();
    MidiViewImpl mv = new MidiViewImpl(s, synth);
    MockReceiver r;
    try {
      r = (MockReceiver) synth.getReceiver();
      mv.renderForMidi();
      assertEquals(r.getMock(), "3 144 0 1\n" +
              "3 128 0 1\n" +
              "7 144 74 64\n" +
              "7 128 74 64\n" +
              "3 144 127 1\n" +
              "3 128 127 1\n");
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testMidi6() {
    SongEditor s = new Song();
    s.addNote(Playable.Pitches.G, 10, 25, 75, 52, 1);
    s.addNote(Playable.Pitches.C, 0, 0, 50, 52, 1);
    s.addNote(Playable.Pitches.D, 6, 12, 20, 40, 64);
    Synthesizer synth = new MockSynthesizer();
    MidiViewImpl mv = new MidiViewImpl(s, synth);
    MockReceiver r;
    try {
      r = (MockReceiver) synth.getReceiver();
      mv.renderForMidi();
      assertEquals(r.getMock(), "3 144 0 1\n" +
              "3 128 0 1\n" +
              "7 144 74 64\n" +
              "7 128 74 64\n" +
              "3 144 127 1\n" +
              "3 128 127 1\n");
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testMidi7() {
    SongEditor s = new Song();
    s.addNote(Playable.Pitches.G, 1, 0, 11, 1, 1);
    s.addNote(Playable.Pitches.Gs, 1, 0, 10, 1, 1);
    s.addNote(Playable.Pitches.A, 1, 0, 9, 1, 1);
    s.addNote(Playable.Pitches.As, 1, 0, 8, 1, 1);
    s.addNote(Playable.Pitches.B, 1, 0, 7, 1, 1);
    s.addNote(Playable.Pitches.C, 2, 0, 6, 1, 1);
    Synthesizer synth = new MockSynthesizer();
    MidiViewImpl mv = new MidiViewImpl(s, synth);
    MockReceiver r;
    try {
      r = (MockReceiver) synth.getReceiver();
      mv.renderForMidi();
      Appendable buildOutput1 = new StringBuilder();
      assertEquals(r.getMock(),
              "0 144 19 1\n" +
                      "0 128 19 1\n" +
                      "0 144 20 1\n" +
                      "0 128 20 1\n" +
                      "0 144 21 1\n" +
                      "0 128 21 1\n" +
                      "0 144 22 1\n" +
                      "0 128 22 1\n" +
                      "0 144 23 1\n" +
                      "0 128 23 1\n" +
                      "0 144 24 1\n" +
                      "0 128 24 1\n");
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testMidi8() {
    SongEditor s = new Song();
    s.addNote(Playable.Pitches.C, 3, 0, 2, 1, 1);
    s.addNote(Playable.Pitches.Cs, 3, 1, 3, 1, 1);
    s.addNote(Playable.Pitches.D, 3, 2, 4, 1, 1);
    s.addNote(Playable.Pitches.Ds, 3, 4, 6, 1, 1);
    s.addNote(Playable.Pitches.E, 3, 6, 8, 1, 1);
    s.addNote(Playable.Pitches.F, 3, 9, 11, 1, 1);
    Synthesizer synth = new MockSynthesizer();
    MidiViewImpl mv = new MidiViewImpl(s, synth);
    MockReceiver r;
    try {
      r = (MockReceiver) synth.getReceiver();
      mv.renderForMidi();
      Appendable buildOutput1 = new StringBuilder();
      assertEquals(r.getMock(),
              "0 144 36 1\n" +
                      "0 128 36 1\n" +
                      "0 144 37 1\n" +
                      "0 128 37 1\n" +
                      "0 144 38 1\n" +
                      "0 128 38 1\n" +
                      "0 144 39 1\n" +
                      "0 128 39 1\n" +
                      "0 144 40 1\n" +
                      "0 128 40 1\n" +
                      "0 144 41 1\n" +
                      "0 128 41 1\n");
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testMidi9() {
    SongEditor s = new Song();
    s.addNote(Playable.Pitches.G, 1, 0, 1, 1, 1);
    s.addNote(Playable.Pitches.G, 1, 1, 2, 1, 1);
    s.addNote(Playable.Pitches.G, 1, 3, 4, 1, 1);
    s.addNote(Playable.Pitches.G, 1, 5, 6, 1, 1);
    s.addNote(Playable.Pitches.G, 1, 7, 11, 1, 1);
    Synthesizer synth = new MockSynthesizer();
    MidiViewImpl mv = new MidiViewImpl(s, synth);
    MockReceiver r;
    try {
      r = (MockReceiver) synth.getReceiver();
      mv.renderForMidi();
      Appendable buildOutput1 = new StringBuilder();
      assertEquals(r.getMock(),
              "0 144 19 1\n" +
                      "0 128 19 1\n" +
                      "0 144 19 1\n" +
                      "0 128 19 1\n" +
                      "0 144 19 1\n" +
                      "0 128 19 1\n" +
                      "0 144 19 1\n" +
                      "0 128 19 1\n" +
                      "0 144 19 1\n" +
                      "0 128 19 1\n");
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testMidi10() {
    SongEditor s = new Song();
    s.addNote(Playable.Pitches.G, 10, 0, 120, 1, 1);
    s.addNote(Playable.Pitches.Fs, 10, 0, 120, 1, 1);
    Synthesizer synth = new MockSynthesizer();
    MidiViewImpl mv = new MidiViewImpl(s, synth);
    MockReceiver r;
    try {
      r = (MockReceiver) synth.getReceiver();
      mv.renderForMidi();
      Appendable buildOutput1 = new StringBuilder();
      assertEquals(r.getMock(),
              "0 144 127 1\n" +
                      "0 128 127 1\n" +
                      "0 144 126 1\n" +
                      "0 128 126 1\n");
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testMidi11() {
    SongEditor s = new Song();
    Synthesizer synth = new MockSynthesizer();
    MidiViewImpl mv = new MidiViewImpl(s, synth);
    MockReceiver r;
    try {
      r = (MockReceiver) synth.getReceiver();
      mv.renderForMidi();
      Appendable buildOutput1 = new StringBuilder();
      assertEquals(r.getMock(),
              "");
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testMidi12() {
    SongEditor s = new Song();
    s.addNote(Playable.Pitches.G, 1, 0, 2, 1, 1);
    s.addNote(Playable.Pitches.C, 2, 0, 2, 1, 1);
    s.addNote(Playable.Pitches.Cs, 3, 0, 2, 1, 1);
    Synthesizer synth = new MockSynthesizer();
    MidiViewImpl mv = new MidiViewImpl(s, synth);
    MockReceiver r;
    try {
      r = (MockReceiver) synth.getReceiver();
      mv.renderForMidi();
      Appendable buildOutput1 = new StringBuilder();
      assertEquals(r.getMock(),
              "0 144 19 1\n" +
                      "0 128 19 1\n" +
                      "0 144 24 1\n" +
                      "0 128 24 1\n" +
                      "0 144 37 1\n" +
                      "0 128 37 1\n");
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testMidi13() {
    SongEditor s = new Song();
    s.addNote(Playable.Pitches.C, 0, 0, 2, 1, 1);
    Synthesizer synth = new MockSynthesizer();
    MidiViewImpl mv = new MidiViewImpl(s, synth);
    MockReceiver r;
    try {
      r = (MockReceiver) synth.getReceiver();
      mv.renderForMidi();
      Appendable buildOutput1 = new StringBuilder();
      assertEquals(r.getMock(),
              "0 144 0 1\n" +
                      "0 128 0 1\n");
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testMidi14() {
    SongEditor s = new Song();
    s.addNote(Playable.Pitches.G, 10, 0, 2, 1, 1);
    Synthesizer synth = new MockSynthesizer();
    MidiViewImpl mv = new MidiViewImpl(s, synth);
    MockReceiver r;
    try {
      r = (MockReceiver) synth.getReceiver();
      mv.renderForMidi();
      Appendable buildOutput1 = new StringBuilder();
      assertEquals(r.getMock(),
              "0 144 127 1\n" +
                      "0 128 127 1\n");
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testMidi15() {
    SongEditor s = new Song();
    s.addNote(Playable.Pitches.G, 10, 0, 2, 1, 1);
    s.addNote(Playable.Pitches.G, 10, 0, 2, 2, 1);
    s.addNote(Playable.Pitches.G, 10, 0, 2, 3, 1);
    s.addNote(Playable.Pitches.G, 10, 0, 2, 50, 1);
    s.addNote(Playable.Pitches.G, 10, 0, 2, 100, 1);
    Synthesizer synth = new MockSynthesizer();
    MidiViewImpl mv = new MidiViewImpl(s, synth);
    MockReceiver r;
    try {
      r = (MockReceiver) synth.getReceiver();
      mv.renderForMidi();
      Appendable buildOutput1 = new StringBuilder();
      assertEquals(r.getMock(),
              "0 144 127 1\n" +
                      "0 128 127 1\n" +
                      "1 144 127 1\n" +
                      "1 128 127 1\n" +
                      "2 144 127 1\n" +
                      "2 128 127 1\n" +
                      "1 144 127 1\n" +
                      "1 128 127 1\n" +
                      "3 144 127 1\n" +
                      "3 128 127 1\n");
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testMidi16() {
    SongEditor s = new Song();
    s.addNote(Playable.Pitches.F, 3, 0, 2, 1, 1);
    s.addNote(Playable.Pitches.E, 3, 1, 3, 1, 1);
    s.addNote(Playable.Pitches.Ds, 3, 2, 4, 1, 1);
    s.addNote(Playable.Pitches.D, 3, 4, 6, 1, 1);
    s.addNote(Playable.Pitches.Cs, 3, 6, 8, 1, 1);
    s.addNote(Playable.Pitches.C, 3, 9, 11, 1, 1);
    Synthesizer synth = new MockSynthesizer();
    MidiViewImpl mv = new MidiViewImpl(s, synth);
    MockReceiver r;
    try {
      r = (MockReceiver) synth.getReceiver();
      mv.renderForMidi();
      Appendable buildOutput1 = new StringBuilder();
      assertEquals(r.getMock(),
              "0 144 41 1\n" +
                      "0 128 41 1\n" +
                      "0 144 40 1\n" +
                      "0 128 40 1\n" +
                      "0 144 39 1\n" +
                      "0 128 39 1\n" +
                      "0 144 38 1\n" +
                      "0 128 38 1\n" +
                      "0 144 37 1\n" +
                      "0 128 37 1\n" +
                      "0 144 36 1\n" +
                      "0 128 36 1\n");
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testMidi17() {
    SongEditor s = new Song();
    s.addNote(Playable.Pitches.G, 10, 0, 2, 1, 0);
    s.addNote(Playable.Pitches.G, 10, 0, 2, 2, 20);
    s.addNote(Playable.Pitches.G, 10, 0, 2, 3, 40);
    s.addNote(Playable.Pitches.G, 10, 0, 2, 4, 70);
    s.addNote(Playable.Pitches.G, 10, 0, 2, 5, 127);
    Synthesizer synth = new MockSynthesizer();
    MidiViewImpl mv = new MidiViewImpl(s, synth);
    MockReceiver r;
    try {
      r = (MockReceiver) synth.getReceiver();
      mv.renderForMidi();
      Appendable buildOutput1 = new StringBuilder();
      assertEquals(r.getMock(),
              "0 144 127 0\n" +
                      "0 128 127 0\n" +
                      "1 144 127 20\n" +
                      "1 128 127 20\n" +
                      "2 144 127 40\n" +
                      "2 128 127 40\n" +
                      "3 144 127 70\n" +
                      "3 128 127 70\n" +
                      "4 144 127 127\n" +
                      "4 128 127 127\n");
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
  }
}
