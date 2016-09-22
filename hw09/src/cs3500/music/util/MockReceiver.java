package cs3500.music.util;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

/**
 * Created by Oliver on 11/12/2015.
 */

/**
 * Mocks the receiver for testing the MIDI View
 */
public class MockReceiver implements Receiver {
  StringBuilder sb;

  public MockReceiver() {
    sb = new StringBuilder();
  }

  @Override
  public void send(MidiMessage message, long timeStamp) {
    ShortMessage sm = (ShortMessage) message;
    sb.append(sm.getChannel() + " ");
    sb.append(sm.getCommand() + " ");
    sb.append(sm.getData1() + " ");
    sb.append(sm.getData2() + "\n");
  }

  @Override
  public void close() {

  }

  /**
   * Returns the receiver in String form
   *
   * @return the String representing the receiver
   */
  public String getMock() {
    return sb.toString();
  }

}
