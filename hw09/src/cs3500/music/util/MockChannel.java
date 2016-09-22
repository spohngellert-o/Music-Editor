package cs3500.music.util;

import javax.sound.midi.MidiChannel;

/**
 * Created by Oliver on 11/13/2015.
 */

/**
 * Mocks a channel, for testing MIDI view
 */
public class MockChannel implements MidiChannel {
  private int program;

  /**
   * Constructs a MockChannel.
   */
  public MockChannel() {
    this.program = 0;
  }

  @Override
  public void noteOn(int noteNumber, int velocity) {

  }

  @Override
  public void noteOff(int noteNumber, int velocity) {

  }

  @Override
  public void noteOff(int noteNumber) {

  }

  @Override
  public void setPolyPressure(int noteNumber, int pressure) {

  }

  @Override
  public int getPolyPressure(int noteNumber) {
    return 0;
  }

  @Override
  public void setChannelPressure(int pressure) {

  }

  @Override
  public int getChannelPressure() {
    return 0;
  }

  @Override
  public void controlChange(int controller, int value) {

  }

  @Override
  public int getController(int controller) {
    return 0;
  }

  @Override
  public void programChange(int program) {

  }

  @Override
  public void programChange(int bank, int program) {

  }

  @Override
  public int getProgram() {
    return program;
  }

  @Override
  public void setPitchBend(int bend) {

  }

  @Override
  public int getPitchBend() {
    return 0;
  }

  @Override
  public void resetAllControllers() {

  }

  @Override
  public void allNotesOff() {

  }

  @Override
  public void allSoundOff() {

  }

  @Override
  public boolean localControl(boolean on) {
    return false;
  }

  @Override
  public void setMono(boolean on) {

  }

  @Override
  public boolean getMono() {
    return false;
  }

  @Override
  public void setOmni(boolean on) {

  }

  @Override
  public boolean getOmni() {
    return false;
  }

  @Override
  public void setMute(boolean mute) {

  }

  @Override
  public boolean getMute() {
    return false;
  }

  @Override
  public void setSolo(boolean soloState) {

  }

  @Override
  public boolean getSolo() {
    return false;
  }
}
