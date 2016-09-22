package cs3500.music.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs3500.music.util.CompositionBuilder;

/**
 * Created by Mike on 11/2/2015.
 */

/**
 * Represents a music composition
 */
public class Song implements SongEditor {
  private Map<Integer, ArrayList<Playable>> notes;
  private Map<Integer, Repeatable> repeats;
  private int songLength;
  private int lowestNote;
  private int highestNote;
  private int tempo;
  private int beat;


  // INVARIANT 1: notes will never overlap in a song
  // INVARIANT 2: the songLength can never be negative
  // INVARIANT 3: the lowestNote must always be in the range of [0,127]
  // INVARIANT 4: the highestNote must always be in range of [0,127]
  // INVARIANT 5: beat cannot be negative

  /**
   * Constructs a {@Link Song} that represents a collection of notes and keeps track of the total
   * length of the song as well as the lowest/highest notes that it contains
   */
  public Song() {
    notes = new HashMap<Integer, ArrayList<Playable>>();
    this.repeats = new HashMap<Integer, Repeatable>();
    songLength = 0;
    lowestNote = 128;
    highestNote = -1;
    tempo = 0;
    beat = 0;
  }

  @Override
  public void setTempo(int tempo) {
    this.tempo = tempo;
  }

  @Override
  public int getSongLength() {
    return this.songLength;
  }

  @Override
  public int getHighestPitch() {
    return this.highestNote;
  }

  @Override
  public int getLowestPitch() {
    return this.lowestNote;
  }

  @Override
  public int getTempo() {
    return this.tempo;
  }

  @Override
  public List<Playable> getStartNotes(int beat) {
    if (this.notes.containsKey(beat)) {
      return Collections.unmodifiableList(this.notes.get(beat));
    }
    return Collections.unmodifiableList(new ArrayList<Playable>());
  }

  @Override
  public void setLowestPitch(int pitch) {
    if (this.getLowestPitch() < pitch) {
      throw new IllegalArgumentException("pitch too high");
    }
    this.lowestNote = pitch;
  }

  @Override
  public void setHighestPitch(int pitch) {
    if (this.getHighestPitch() > pitch) {
      throw new IllegalArgumentException("pitch too low");
    }
    this.highestNote = pitch;
  }

  @Override
  public void setSongLength(int length) {
    if (this.getSongLength() > length) {
      throw new IllegalArgumentException("too short");
    }
    this.songLength = length;
  }

  @Override
  public void addRepeatable(Repeatable repeatable) {
    for(int i = 0; i < this.songLength; i++) {
      if(this.repeats.containsKey(i)) {
        Repeatable currRepeatable = this.repeats.get(i);
        if((repeatable.getRepeatTo() <= currRepeatable.getEnd() &&
                repeatable.getEnd() >= currRepeatable.getEnd()) ||
                (currRepeatable.getRepeatTo() <= repeatable.getEnd() &&
                currRepeatable.getEnd() >= repeatable.getEnd())) {
          throw new IllegalArgumentException("Repeatable overlap");
        }
      }
    }
    this.repeats.put(repeatable.getRepeatFrom(), repeatable);
  }

  @Override
  public void removeRepeatable(Repeatable repeatable) {
    this.repeats.remove(repeatable.getRepeatFrom());
  }

  @Override
  public boolean isRepeatableAtBeat(int beat) {
    return this.repeats.containsKey(beat);
  }

  @Override
  public Repeatable getRepeatableAtBeat(int beat) {
    if(this.repeats.containsKey(beat)) {
      return this.repeats.get(beat);
    }
    throw new IllegalArgumentException("No repeatable at beat");
  }

  @Override
  public List<Repeatable> getRepeatables() {
    return new ArrayList<Repeatable>(this.repeats.values());
  }

  /**
   * Updates the information of a song based on the notes inside including the song's runtime,
   * lowest and highest notes
   */
  private void updateSongInfo() {
    int songLength = this.songLength;
    this.songLength = 0;
    for (int i = 0; i < songLength; i++) {
      if (this.notes.containsKey(i)) {
        ArrayList<Playable> currNotes = this.notes.get(i);
        for (Playable n : currNotes) {
          if (this.songLength < n.getEndingTime()) {
            this.songLength = n.getEndingTime();
          }
          if (n.getPitchVal() < this.lowestNote) {
            this.lowestNote = n.getPitchVal();
          } else if (n.getPitchVal() > this.highestNote) {
            this.highestNote = n.getPitchVal();
          }
        }
      }
    }
  }

  @Override
  public int getBeat() {
    return this.beat;
  }

  @Override
  public void setBeat(int beat) {
    this.beat = beat;
  }

  @Override
  public void addNote(Playable.Pitches pitch, int octave,
                      int start, int end, int instrument, int volume) {
    Note newNote = new Note(pitch, octave, start, end, instrument, volume);
    for (int i = 0; i <= this.songLength; i++) {
      if (this.notes.containsKey(i)) {
        ArrayList<Playable> notesAtBeat = this.notes.get(i);
        for (Playable n : notesAtBeat) {
          if (n.getPitchVal() == octave * 12 + pitch.getValue()
                  && n.getInstrument() == instrument) {
            if ((start >= n.getStartingTime() && start < n.getEndingTime()) ||
                    (n.getStartingTime() >= start && n.getStartingTime() < end)) {
              throw new IllegalArgumentException("Similar note already in song.");
            }
          }
        }
      }
    }
    if (end > this.songLength) {
      this.songLength = end;
    }
    if (newNote.getPitchVal() < this.lowestNote) {
      this.lowestNote = newNote.getPitchVal();
    }
    if (newNote.getPitchVal() > this.highestNote) {
      this.highestNote = newNote.getPitchVal();
    }
    if (!this.notes.containsKey(start)) {
      ArrayList<Playable> toAdd = new ArrayList<Playable>();
      toAdd.add(newNote);
      this.notes.put(start, toAdd);
      return;
    } else {
      ArrayList<Playable> editThis = this.notes.get(start);
      editThis.add(newNote);
      return;
    }
  }

  @Override
  public void removeNote(Note.Pitches pitch, int octave, int start, int end, int instrument
          , int volume) {
    Note remove = new Note(pitch, octave, start, end, instrument, volume);
    if (!this.notes.containsKey(start)) {
      throw new IllegalArgumentException("Note does not exist in the song.");
    } else {
      ArrayList<Playable> notesAtBeat = this.notes.get(start);
      for (Playable n : notesAtBeat) {
        if (n.equals(remove)) {
          notesAtBeat.remove(n);
          this.updateSongInfo();
          return;
        }
      }
      throw new IllegalArgumentException("Note does not exist in the song.");
    }
  }

  @Override
  public Playable removeNote(int pitchVal, int beat, int instrument) {
    List<Playable> notesAtBeat = this.getBeatsNotes(beat);
    for (Playable p : notesAtBeat) {
      if (p.getPitchVal() == pitchVal &&
              p.getInstrument() == instrument) {
        ArrayList<Playable> notes = this.notes.get(p.getStartingTime());
        for (int i = 0; i < notes.size(); i++) {
          if (notes.get(i).equals(p)) {
            Playable toRemove = notes.remove(i);
            this.updateSongInfo();
            return toRemove;
          }
        }

      }
    }
    return null;
  }

  @Override
  public void changeNote(Note.Pitches oldPitch, int oldOctave, int oldStart, int oldEnd,
                         int oldInstrument, int oldVolume,
                         Note.Pitches newPitch, int newOctave, int newStart, int newEnd,
                         int newInstrument, int newVolume) {
    this.removeNote(oldPitch, oldOctave, oldStart, oldEnd, oldInstrument, oldVolume);
    this.addNote(newPitch, newOctave, newStart, newEnd, newInstrument, newVolume);
  }

  @Override
  public List<Playable> getBeatsNotes(int beat) {
    if (beat < 0 || beat > this.songLength) {
      throw new IllegalArgumentException("Beat does not exist.");
    }
    ArrayList<Playable> result = new ArrayList<Playable>();
    for (int i = 0; i < this.songLength; i++) {
      if (this.notes.containsKey(i)) {
        ArrayList<Playable> currNotes = this.notes.get(i);
        for (Playable n : currNotes) {
          if (n.getStartingTime() <= beat && n.getEndingTime() > beat) {
            result.add(n);
          }
        }
      }
    }
    return Collections.unmodifiableList(result);
  }

  /**
   * Takes in a note's pitch value ranging from 0 to 127 and returns its respective name (i.e. C#4,
   * D8, D#11)
   *
   * @param val the pitch value to be converted
   * @return the converted pitch value
   */
  private String noteValToName(int val) {
    int noteName = val % 12;
    int octave = Math.floorDiv(val, 12);
    String result = "";
    if (noteName == 0) {
      result = "C";
    } else if (noteName == 1) {
      result = "C#";
    } else if (noteName == 2) {
      result = "D";
    } else if (noteName == 3) {
      result = "D#";
    } else if (noteName == 4) {
      result = "E";
    } else if (noteName == 5) {
      result = "F";
    } else if (noteName == 6) {
      result = "F#";
    } else if (noteName == 7) {
      result = "G";
    } else if (noteName == 8) {
      result = "G#";
    } else if (noteName == 9) {
      result = "A";
    } else if (noteName == 10) {
      result = "A#";
    } else if (noteName == 11) {
      result = "B";
    }
    result = result + Integer.toString(octave);
    return result;
  }

  /**
   * Prints the portion that shows when and at what pitches notes are being played
   *
   * @param range the amount of total spaces being displayed by the view
   * @param beat  the beat where the notes will be read from
   * @return a string representation of which notes play at a beat
   */
  private String noteLine(int range, int beat) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < range; i++) {
      sb.append("    ");
    }
    for (Playable n : this.getBeatsNotes(beat)) {
      if (n.getStartingTime() == beat) {
        sb.setCharAt((n.getPitchVal() - this.getLowestPitch()) * 4, 'X');
      } else if (n.getStartingTime() < beat && n.getEndingTime() > beat) {
        sb.setCharAt((n.getPitchVal() - this.getLowestPitch()) * 4, '|');
      }
    }
    return sb.toString();
  }

  /**
   * Displays to the console a representation of the song
   */
  public void display() {
    int beatPadding = Integer.toString(this.songLength - 1).length();
    int range = this.highestNote - this.lowestNote + 1;


    String firstRow = " ";
    while (firstRow.length() < beatPadding + 1) {
      firstRow = firstRow + " ";
    }

    int notesPast = 0;
    for (int i = this.lowestNote; i <= this.highestNote; i++) {
      String temp = "";
      temp = this.noteValToName(this.lowestNote + notesPast);
      while (temp.length() < 4) {
        temp = temp + " ";
      }
      firstRow = firstRow + temp;
      notesPast++;
    }
    System.out.println(firstRow);

    for (int i = 0; i < this.songLength; i++) {
      String line = Integer.toString(i);
      while (line.length() < beatPadding) {
        line = line + " ";
      }
      line = line + " " + this.noteLine(range, i);
      System.out.println(line);
    }
  }

  /**
   * Builds a song using editing methods
   */
  public static final class SongBuilder implements CompositionBuilder<SongEditor> {
    SongEditor song;

    /**
     * Constructs the SongBuilder
     */
    public SongBuilder() {
      song = new Song();
    }

    @Override
    public SongEditor build() {
      return song;
    }

    @Override
    public SongBuilder setTempo(int tempo) {
      this.song.setTempo(tempo);
      return this;
    }

    @Override
    public SongBuilder addNote(int start, int end, int instrument, int pitch, int volume) {
      int octave = Math.floorDiv(pitch, 12);
      Playable.Pitches noteType = null;
      int noteTypeInt = pitch % 12;
      if (noteTypeInt == 0) {
        noteType = Playable.Pitches.C;
      } else if (noteTypeInt == 1) {
        noteType = Playable.Pitches.Cs;
      } else if (noteTypeInt == 2) {
        noteType = Playable.Pitches.D;
      } else if (noteTypeInt == 3) {
        noteType = Playable.Pitches.Ds;
      } else if (noteTypeInt == 4) {
        noteType = Playable.Pitches.E;
      } else if (noteTypeInt == 5) {
        noteType = Playable.Pitches.F;
      } else if (noteTypeInt == 6) {
        noteType = Playable.Pitches.Fs;
      } else if (noteTypeInt == 7) {
        noteType = Playable.Pitches.G;
      } else if (noteTypeInt == 8) {
        noteType = Playable.Pitches.Gs;
      } else if (noteTypeInt == 9) {
        noteType = Playable.Pitches.A;
      } else if (noteTypeInt == 10) {
        noteType = Playable.Pitches.As;
      } else if (noteTypeInt == 11) {
        noteType = Playable.Pitches.B;
      }
      this.song.addNote(noteType, octave, start, end, instrument, volume);
      return this;
    }
  }
}