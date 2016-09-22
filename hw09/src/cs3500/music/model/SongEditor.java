package cs3500.music.model;

import java.util.List;

/**
 * Created by Mike on 11/2/2015.
 */

/**
 * The interface for the music compositions
 */
public interface SongEditor {

  /**
   * Returns the current beat at which the song is at.
   *
   * @return The respective beat.
   */
  public int getBeat();

  /**
   * Sets the beat of the song to the given input.
   *
   * @param beat - The beat desired to change to.
   */
  public void setBeat(int beat);

  /**
   * Adds a new note into the song
   *
   * @param pitch      the pitch of the new note being added
   * @param octave     the octave of the new note being aded
   * @param start      the starting beat of the new note
   * @param end        the ending beat of the new note
   * @param instrument the instrument playing this note
   * @param volume     the volume of the note being added
   * @throws IllegalArgumentException when a note is being added to a song that already contains a
   *                                  note of the same pitch and that starts on the same beat or
   *                                  starts during a preexisting note's duration
   * @throws IllegalArgumentException when a note is being added to a song and its duration is
   *                                  observed to overlap over a note of the same pitch
   */
  void addNote(Note.Pitches pitch, int octave, int start, int end, int instrument, int volume);

  /**
   * Removes the given note from the song
   *
   * @param pitch      the pitch of the note desired to be removed
   * @param octave     the octave of the note desired to be removed
   * @param start      the starting beat of the note being removed
   * @param end        the ending beat of the note being removed
   * @param instrument the instrument of the note being removed
   * @param volume     the volume of the note being removed
   * @throws IllegalArgumentException when the note attempted to be removed is not in the song
   */
  void removeNote(Note.Pitches pitch, int octave, int start, int end, int instrument, int volume);

  /**
   * A variant of the removeNote function that returns the note that is removed.
   *
   * @param pitchVal   The pitch value of the note wished to be removed.
   * @param beat       The beat where the node that is wished to be removed is at.
   * @param instrument The instrument of the note wished to be removed.
   * @return The note that is being removed.
   */
  Playable removeNote(int pitchVal, int beat, int instrument);

  /**
   * Edits the given note in the song
   *
   * @param oldPitch      the pitch of the note desired to be changed
   * @param oldOctave     the octave of the note desired to be changed
   * @param oldStart      the starting beat of the note being changed
   * @param oldEnd        the ending beat of the note being changed
   * @param oldInstrument the instrument of the note being changed
   * @param oldVolume     the volume of the note being changed
   * @param newPitch      the new pitch of the chosen note
   * @param newOctave     the new octave of the chosen note
   * @param newStart      the new starting beat of the chosen note
   * @param newEnd        the new ending beat of the chosen note
   * @param newInstrument the new instrument of the chosen note
   * @param newVolume     the new volume of the chosen note
   * @return -- the note that was removed
   * @throws IllegalArgumentException when the note attempted to be edited is not in the song
   * @throws IllegalArgumentException when the note is attempted to be changed to a position and
   *                                  pitch that is already occupied by another note
   * @throws IllegalArgumentException when the note is attempted to be changed in such a way that
   *                                  its duration will overlap over a preexisting note
   * @throws IllegalArgumentException when the note is being changed but still has characteristics
   *                                  of its old state including pitch value and position
   */
  void changeNote(Note.Pitches oldPitch, int oldOctave, int oldStart,
                  int oldEnd, int oldInstrument, int oldVolume,
                  Note.Pitches newPitch, int newOctave, int newStart,
                  int newEnd, int newInstrument, int newVolume);

  /**
   * Sets the tempo of the song
   *
   * @param tempo the desired tempo represented as an integer
   */
  void setTempo(int tempo);

  /**
   * Finds all of the notes at a given beat in a song
   *
   * @param beat the beat at which notes will be looked for
   * @throws IllegalArgumentException when the beat is either negative or not in the song
   */
  List<Playable> getBeatsNotes(int beat);

  /**
   * Gets the last beat
   *
   * @return the last beat
   */
  int getSongLength();

  /**
   * Gets the highest pitch
   *
   * @return the highest pitch
   */
  int getHighestPitch();

  /**
   * Gets the lowest pitch
   *
   * @return the lowest pitch
   */
  int getLowestPitch();

  /**
   * Gets the tempo of the piece
   *
   * @return the tempo
   */
  int getTempo();

  /**
   * Gets all notes that start at the given beat
   *
   * @param beat -- the beat where the notes start
   * @return a list of playable that start at the given beat
   */
  List<Playable> getStartNotes(int beat);

  /**
   * sets the lowest pitch to the given pitch value
   *
   * @param pitch -- the pitch to set
   * @throws IllegalArgumentException -- if the pitch is higher than the current lowest pitch
   */
  void setLowestPitch(int pitch);

  /**
   * sets the highest pitch to the given pitch value
   *
   * @param pitch -- the pitch to set
   * @throws IllegalArgumentException -- if the pitch is lower than the current highest pitch
   */
  void setHighestPitch(int pitch);

  /**
   * sets the song length
   *
   * @param length -- the new length
   * @throws IllegalArgumentException -- if the length given is shorter than the given song length
   */
  void setSongLength(int length);

  /**
   * Adds the given repeatable to the song
   *
   * @param repeatable -- repeatable to add
   * @throws IllegalArgumentException -- If the given repeatable overlaps
   */
  void addRepeatable(Repeatable repeatable);

  /**
   * Removes the given repeatable from the song
   *
   * @param repeatable -- the repeatable to remove
   */
  void removeRepeatable(Repeatable repeatable);

  /**
   * Gets the repeatable at the given beat
   * @param beat -- The beat to check whether there is a repeatable
   * @return -- Whether there is a repeatable at that beat
   */
  boolean isRepeatableAtBeat(int beat);

  /**
   * Gets the repeatable at the given beat
   * @param beat -- the beat to get the repeatable
   * @return -- the repeatable at the beat
   */
  Repeatable getRepeatableAtBeat(int beat);

  /**
   * Gets all repeatables in the song as returns as an immutable list
   * @return -- The immutable list of all Repeatables
   */
  List<Repeatable> getRepeatables();
}