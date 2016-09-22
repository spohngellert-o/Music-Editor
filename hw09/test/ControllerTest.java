import org.junit.Test;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.security.Key;

import javax.sound.midi.MidiUnavailableException;
import javax.swing.*;

import cs3500.music.controller.ConcreteController;
import cs3500.music.controller.KeyboardHandler;
import cs3500.music.controller.MouseHandler;
import cs3500.music.model.Playable;
import cs3500.music.model.Song;
import cs3500.music.model.SongEditor;
import cs3500.music.util.MockMouseRunnable;
import cs3500.music.util.MockMouseRunnable2;
import cs3500.music.util.MockReceiver;
import cs3500.music.util.MockRunnable;
import cs3500.music.util.MockRunnable2;
import cs3500.music.util.MockSynthesizer;
import cs3500.music.view.CompoundView;
import cs3500.music.view.ConcreteCompoundView;
import cs3500.music.view.GuiView;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.MidiView;
import cs3500.music.view.MidiViewImpl;

import static org.junit.Assert.*;

/**
 * Created by Oliver on 11/23/2015.
 */

/**
 * Tests made for the Controller.
 */
public class ControllerTest {
  ConcreteController controller;
  MouseHandler mouseHandler;
  KeyboardHandler keyboardHandler;
  GuiView guiView;
  MidiView midiView;
  ConcreteCompoundView compoundView;
  SongEditor song1;
  MockSynthesizer mockSynth;
  MockReceiver mockReceive;

  /**
   * Initializes the components for testing.
   */
  public void initialize() throws MidiUnavailableException {
    keyboardHandler = new KeyboardHandler();
    mouseHandler = new MouseHandler();
    song1 = new Song();
    guiView = new GuiViewFrame(song1);
    this.mockSynth = new MockSynthesizer();
    this.mockReceive = (MockReceiver) mockSynth.getReceiver();
    midiView = new MidiViewImpl(song1, mockSynth);
    compoundView = new ConcreteCompoundView(midiView, guiView);
    controller = new ConcreteController(compoundView, song1, keyboardHandler, mouseHandler, 1);

  }

  @Test
  public void testKeyboardHandler() throws MidiUnavailableException {
    this.initialize();
    KeyboardHandler handler2 = new KeyboardHandler();
    StringBuilder testBuilder = new StringBuilder();
    handler2.addKeyEvent(KeyEvent.VK_SPACE, new MockRunnable(testBuilder), "pressed");
    handler2.keyPressed(new KeyEvent(new JPanel(), 0, (long) 0, 0, KeyEvent.VK_SPACE));
    assertEquals(testBuilder.toString(), "success");
    handler2.keyPressed(new KeyEvent(new JPanel(), 0, (long) 0, 0, KeyEvent.VK_SPACE));
    assertEquals(testBuilder.toString(), "successsuccess");
    handler2.addKeyEvent(KeyEvent.VK_0, new MockRunnable(testBuilder), "pressed");
    handler2.keyPressed(new KeyEvent(new JPanel(), 0, (long) 0, 0, KeyEvent.VK_0));
    assertEquals(testBuilder.toString(), "successsuccesssuccess");
    testBuilder = new StringBuilder();
    handler2.addKeyEvent(KeyEvent.VK_SPACE, new MockRunnable2(testBuilder), "typed");
    handler2.addKeyEvent(KeyEvent.VK_SPACE, new MockRunnable2(testBuilder), "pressed");
    handler2.keyPressed(new KeyEvent(new JPanel(), 0, (long) 0, 0, KeyEvent.VK_SPACE));
    handler2.keyTyped(new KeyEvent(new JPanel(), 0, (long) 0, 0, KeyEvent.VK_SPACE));
    assertEquals(testBuilder.toString(), "success2success2");
    handler2.addKeyEvent(KeyEvent.VK_0, new MockRunnable(testBuilder), "pressed");
    handler2.addKeyEvent(KeyEvent.VK_0, new MockRunnable2(testBuilder), "released");
    handler2.keyPressed(new KeyEvent(new JPanel(), 0, (long) 0, 0, KeyEvent.VK_0));
    handler2.keyReleased(new KeyEvent(new JPanel(), 0, (long) 0, 0, KeyEvent.VK_0));
    assertEquals(testBuilder.toString(), "success2success2successsuccess2");
    handler2.addKeyEvent(KeyEvent.VK_0, new MockRunnable(testBuilder), "released");
    handler2.keyPressed(new KeyEvent(new JPanel(), 0, (long) 0, 0, KeyEvent.VK_0));
    assertEquals(testBuilder.toString(), "success2success2successsuccess2success");
    handler2.keyPressed(new KeyEvent(new JPanel(), 0, (long) 0, 0, KeyEvent.VK_1));
    assertEquals(testBuilder.toString(), "success2success2successsuccess2success");
    handler2.keyTyped(new KeyEvent(new JPanel(), 0, (long) 0, 0, KeyEvent.VK_HOME));
    assertEquals(testBuilder.toString(), "success2success2successsuccess2success");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testKeyboardHandler2() throws MidiUnavailableException {
    initialize();
    keyboardHandler.addKeyEvent(4, new Runnable() {
      @Override
      public void run() {

      }
    }, "");
  }

  @Test
  public void testMouseHandler() throws MidiUnavailableException {
    initialize();
    StringBuilder builder = new StringBuilder();
    mouseHandler.addMouseEvent(0, new MockMouseRunnable(builder), "pressed");
    mouseHandler.addMouseEvent(1, new MockMouseRunnable2(builder), "pressed");
    mouseHandler.mousePressed(new MouseEvent(new JPanel(), 1, (long) 1, 1, 4,
            5, 1, false, 1));
    assertEquals(builder.toString(), "4 5");
    mouseHandler.mousePressed(new MouseEvent(new JPanel(), 1, (long) 1, 1, 4,
            5, 1, false, 2));
    assertEquals(builder.toString(), "4 55 4");
    mouseHandler.addMouseEvent(2, new MockMouseRunnable(builder), "clicked");
    mouseHandler.mouseClicked(new MouseEvent(new JPanel(), 1, (long) 1, 1, 4,
            5, 1, false, 3));
    assertEquals(builder.toString(), "4 55 44 5");
  }

  @Test
  public void testController() throws MidiUnavailableException {
    this.initialize();
    this.song1.addNote(Playable.Pitches.C, 5, 0, 10, 1, 100);
    this.compoundView.play();
    this.song1.setBeat(this.song1.getBeat() + 1);
    this.controller.pause();
    String received = this.mockReceive.getMock();
    assertEquals(received, "0 144 60 100\n" +
            "0 128 60 100\n");
    this.song1.addNote(Playable.Pitches.A, 5, 1, 5, 1, 100);
    this.compoundView.restart();
    assertEquals(this.mockReceive.getMock(), "0 144 60 100\n" +
            "0 128 60 100\n" +
            "0 144 60 100\n");
  }

  @Test
  public void testController2() throws MidiUnavailableException {
    this.initialize();
    this.song1.addNote(Playable.Pitches.C, 5, 0, 100, 1, 100);
    this.controller.setMouseHandler("R");
    this.mouseHandler.mousePressed(new MouseEvent(new JPanel(), 1, (long) 1, 1, 100,
            30, 1, false, 1));
    assertEquals(this.song1.getStartNotes(0).size(), 0);
    this.keyboardHandler.keyReleased(new KeyEvent(new JPanel(), 0, (long) 0, 0, KeyEvent.VK_9));
    assertEquals(this.song1.getSongLength(), 4);
  }
}