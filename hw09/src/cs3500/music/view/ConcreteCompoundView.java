package cs3500.music.view;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.Objects;

/**
 * Created by Oliver on 11/20/2015.
 */

/**
 * A concrete implementation of the CompoundView for midi and gui views.
 */
public class ConcreteCompoundView implements CompoundMidiGuiView {
  private final MidiView midi;
  private final GuiView gui;

  /**
   * Constructs an instance of a CompoundView.
   *
   * @param midi The midi aspect of the CompoundView.
   * @param gui  The gui aspect of the CompoundView.
   */
  public ConcreteCompoundView(MidiView midi, GuiView gui) {
    this.midi = Objects.requireNonNull(midi);
    this.gui = Objects.requireNonNull(gui);
  }

  @Override
  public void movePosition(int factor, boolean orientation) {
    this.gui.movePosition(factor, orientation);
  }

  @Override
  public void goHome() {
    this.gui.goHome();
  }

  @Override
  public void render() {
    this.gui.render();

  }

  @Override
  public void addMouseListener(MouseListener listener) {
    this.gui.addMouseListener(listener);
  }

  @Override
  public void addKeyListener(KeyListener listener) {
    this.gui.addKeyListener(listener);
  }

  @Override
  public void pause() {
    this.gui.renderAtBeat();
    this.midi.pause();
  }

  @Override
  public void play() {
    this.midi.playBeat();
    this.gui.renderAtBeat();
  }

  @Override
  public void restart() {
    this.midi.restart();
  }

  @Override
  public void renderGui() {
    this.gui.renderAtBeat();
  }

  @Override
  public void adjustNotesToPlay() {
    this.midi.adjustNotesToPlay();
  }

  @Override
  public void goEnd() {
    this.gui.goEnd();
  }

}
