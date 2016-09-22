package cs3500.music.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.midi.*;

import cs3500.music.model.Playable;
import cs3500.music.model.SongEditor;

/**
 * A MIDI playback view for the model
 */
public class MidiViewImpl implements MidiView {
  private final Synthesizer synth;
  private final Receiver receiver;
  private final SongEditor song;
  private final Map<Integer, ArrayList<Playable>> notesPlaying;

  /**
   * Constructs a MidiViewImpl
   *
   * @param song the SongEditor to be viewed
   */
  public MidiViewImpl(SongEditor song) {
    this.song = song;
    Synthesizer synth = null;
    Receiver receiver = null;
    try {
      synth = MidiSystem.getSynthesizer();
      receiver = synth.getReceiver();
      synth.open();
    } catch (MidiUnavailableException e) {

      synth = null;
      receiver = null;
      e.printStackTrace();
    } finally {
      this.notesPlaying = new HashMap<Integer, ArrayList<Playable>>();
      this.receiver = receiver;
      this.synth = synth;
    }
  }

  /**
   * Convenience constructor that takes in a synthsizer, used for testing
   *
   * @param song  the song wished to be viewed
   * @param synth the synthesizer wished to be used
   */
  public MidiViewImpl(SongEditor song, Synthesizer synth) {
    this.song = song;
    Synthesizer synth2 = null;
    Receiver receiver = null;
    try {
      synth2 = synth;
      receiver = synth.getReceiver();
      synth2.open();
    } catch (MidiUnavailableException e) {

      synth2 = null;
      receiver = null;
      e.printStackTrace();
    } finally {
      this.notesPlaying = new HashMap<Integer, ArrayList<Playable>>();
      this.receiver = receiver;
      this.synth = synth2;
    }
  }

  /**
   * Sends the given note to the receiver
   *
   * @param p the note to be played
   * @throws InvalidMidiDataException when the midi is invalid
   */
  private void playNote(Playable p) throws InvalidMidiDataException {
    MidiChannel[] channels = this.synth.getChannels();
    int instrument = p.getInstrument() - 1;
    channels[instrument % 16].programChange(instrument);
    MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, instrument % 16, p.getPitchVal(),
            p.getVolume());
    MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, instrument % 16, p.getPitchVal(),
            p.getVolume());

    this.receiver.send(start, this.synth.getMicrosecondPosition() +
            p.getStartingTime() * song.getTempo());
    this.receiver.send(stop, this.synth.getMicrosecondPosition() +
            p.getEndingTime() * this.song.getTempo());
  }

  /**
   * Sends the start message for the given note
   *
   * @param p -- The note to be started
   */
  private void startNote(Playable p, int beat) throws InvalidMidiDataException {
    MidiChannel[] channels = this.synth.getChannels();
    int instrument = p.getInstrument() - 1;
    channels[instrument % 16].programChange(instrument);
    MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, instrument % 16, p.getPitchVal(),
            p.getVolume());
    this.receiver.send(start, beat * song.getTempo());

  }

  /**
   * Sends the end message for the given note
   *
   * @param p    -- the note to be ended
   * @param beat -- the beat at which the note should end
   */
  private void endNote(Playable p, int beat) throws InvalidMidiDataException {
    MidiChannel[] channels = this.synth.getChannels();
    int instrument = p.getInstrument() - 1;
    channels[instrument % 16].programChange(instrument);
    MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, instrument % 16, p.getPitchVal(),
            p.getVolume());
    this.receiver.send(stop, beat * this.song.getTempo());

  }

  /**
   * Sends start messages for all notes at the given beat, and adds them to the hashmap of notes
   * currently playing.
   *
   * @param beat -- the beat at which the notes should start
   */
  public void startNotesAtBeat(int beat) {
    List<Playable> currentNotes = song.getStartNotes(beat);

    for (Playable p : currentNotes) {

      try {
        this.startNote(p, beat);

        if (this.notesPlaying.containsKey(p.getEndingTime())) {

          ArrayList<Playable> notesAtEnd = this.notesPlaying.get(p.getEndingTime());
          notesAtEnd.add(p);
        } else {
          ArrayList<Playable> notesAtEnd = new ArrayList<Playable>();
          notesAtEnd.add(p);
          this.notesPlaying.put(p.getEndingTime(), notesAtEnd);
        }
      } catch (InvalidMidiDataException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Sends the end messages for each note that ends at this beat
   *
   * @param beat -- the beat at which you need to send the end messages
   */
  public void endNotesAtBeat(int beat) {
    if (this.notesPlaying.containsKey(beat)) {
      ArrayList<Playable> toEnd = this.notesPlaying.get(beat);
      while (toEnd.size() > 0) {
        try {
          Playable currNoteToEnd = toEnd.remove(0);
          this.endNote(currNoteToEnd, beat);
        } catch (InvalidMidiDataException e) {
          e.printStackTrace();
        }

      }
    }
  }

  @Override
  public void playBeat() {
    int currBeat = this.song.getBeat();
    this.startNotesAtBeat(currBeat);
    this.endNotesAtBeat(currBeat);
    //CHECK FOR REPEATFROM
  }

  @Override
  public void pause() {

    int beat = this.song.getBeat();
    for (int i = beat; i <= this.song.getSongLength(); i++) {
      if (this.notesPlaying.containsKey(i)) {
        ArrayList<Playable> toEnd = this.notesPlaying.get(i);
        for (Playable p : toEnd) {
          try {
            this.endNote(p, beat);
          } catch (InvalidMidiDataException e) {
            e.printStackTrace();
          }

        }
      }
    }
  }

  @Override
  public void restart() {
    int beat = this.song.getBeat();
    for (int i = beat; i <= this.song.getSongLength(); i++) {
      if (this.notesPlaying.containsKey(i)) {
        ArrayList<Playable> toEnd = this.notesPlaying.get(i);
        for (Playable p : toEnd) {
          try {
            this.startNote(p, beat);
          } catch (InvalidMidiDataException e) {
            e.printStackTrace();
          }
        }
      }
    }
  }

  @Override
  public void adjustNotesToPlay() {
    this.notesPlaying.clear();
    List<Playable> notesPlaying = this.song.getBeatsNotes(this.song.getBeat());
    for (Playable p : notesPlaying) {
      if (this.notesPlaying.containsKey(p.getEndingTime())) {
        ArrayList<Playable> toAdd = this.notesPlaying.get(p.getEndingTime());
        toAdd.add(p);
      } else {
        ArrayList<Playable> toAdd = new ArrayList<Playable>();
        toAdd.add(p);
        this.notesPlaying.put(p.getEndingTime(), toAdd);
      }
    }
  }

  @Override
  public void render() {
    for (int i = 0; i < song.getSongLength(); i++) {

      this.playBeat();
    }
    Timer t = new Timer();
    t.schedule(new TimerTask() {
      @Override
      public void run() {

      }
    }, song.getTempo() / 1000);
  }

  @Override
  public void renderForMidi() {
    for (int i = 0; i < song.getSongLength(); i++) {
      List<Playable> currentNotes = song.getStartNotes(i);

      for (Playable p : currentNotes) {
        try {
          this.playNote(p);
        } catch (InvalidMidiDataException e) {
          e.printStackTrace();
        }
      }
    }
    receiver.close();
    Timer t = new Timer();
    t.schedule(new TimerTask() {
      @Override
      public void run() {

      }
    }, song.getSongLength() * song.getTempo() / 1000);
  }
}