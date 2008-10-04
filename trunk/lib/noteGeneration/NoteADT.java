package noteGeneration;

/**
  *
  *
  * This is the contract specification for a musical pitch or note.
  * A pitch can be completely specified as one of the following:
  * 1. A number of cycles per second or
  * 2. The number of half steps above a commonly agreed upon pitch
  *    (concert pitch=440Hz = A = MIDI69) or
  * 3. The common music note name (C, D, E, F, G, A, B) with the
  *    the suffix indicating the octave number [-1,9] and an additional
  *    suffix prefix '#' for sharp notes and 'b' for flat notes or
  * 4. The MIDI absolute note number [0,127] where 60 is middle C.
  *
  * Higher pitches have higher frequencies. Two pitches are an octave apart
  * (12 semitones) if one frequency is twice the other. A semitone is
  * aproximately an increase in pitch of 1.06 times higher.
  */

public abstract class NoteADT implements Comparable{


	protected int midiNoteValue = -1;

  /**
   * A semitone is aproximately an increase in pitch of 1.06 times higher.
   */
  public static final double SEMI_TONE_INCREASE_IN_PITCH = Math.pow(2.0,1.0/12.0);
  /**
   * The agreed upon pitch "modern concert pitch"
   */
  public static final double HZ_CONCERT_PITCH = 440.0; //Hz
  public static final int    MIDI_CONCERT_PITCH = 69;
  
  /**
   * The high and low limits on the range of midi numbers.
   */
  public static final int    LOW_MIDI_ABSOLUTE_NUMBER = 0;
  public static final int    HIGH_MIDI_ABSOLUTE_NUMBER = 127;

  
  /**
   * The constructors for a note accept a frequency as a double for Hz or
   * a number of semitones as an int above or below the concert pitch (440Hz)
   * or a music note as a String 
   * Imperfect frequencies are tuned to the nearest half pitch
   */

	public NoteADT(double frequency)
	{
		//You must provide the code for this
	}

	public NoteADT(int semitones)
	{
		//You must provide the code for this
	}

	public NoteADT(String strNote) throws IllegalArgumentException
	{
		//You must provide the code for this
	}

  /**
   * Returns the frequency of this note in cycles per second- Hertz (Hz)
   * @return double The frequency in Hz
   */
   public abstract double getFrequencyInHz();

  /**
   * Returns the frequency of this note in semitones (half steps) above or below
   * the concert pitch (440Hz)
   * @return int The frequency in Semitones or half steps
   */
   public abstract int getHalfSteps();

  /**
   * Returns the frequency of this note MIDI absolute Numbers
   * the numbers range from 0 (C-1) to 127 (G9)
   * @return int The frequency MIDI absolute Numbers
   */
   public abstract int getMIDIAbsoluteNumber();

   /**
    * If this note is 12 semitones above or 12 semitones below
    * the other note, then the notes form an octave
    * @param note The note that can form an Octave
    * @return boolean true if they do form an octave
    */
   public abstract boolean formOctave(NoteADT note);

   /**
    * Raise or lower the pitch by any number of semitones
    * @param numberOfSemitones Change int used to calculate the new note
    */
    public abstract void modifyNoteBySemitones(int numberOfSemitones);

   /**
    * The compareTo method will be implemented by the Comparable interface
    * it will adhere to the Comparable interface contract
    * public int comparteTo(Object obj);
    */
    public abstract int compareTo(Object obj);


}
