/**
 * @author Alex Peterson
 * @version 2008OC10
 * 
 * This code is available under the terms of the GNU General Public License v3.0
 * (http://www.gnu.org/licenses/gpl-3.0.txt)
 * 
 * The content is licenced under the Creative Commons Attribution + ShareAlike 2.5 [BY-SA] (Canada)
 * (http://creativecommons.org/licenses/by-sa/2.5/ca)
 */

package midi;

import noteGeneration.*;

import java.util.HashMap;


/**
 * A class representing a musical note.
 */
public class Note extends NoteADT {
		
	/* Static */
		
		/* Public Static Constants */
		
		/**
		 * Represents the precision to round (and accept) frequencies to.
		 */
		private static final int ROUNDING_PRECISION = 2;
		
		/* END Public Static Constants */
	
		/* Static Constants */
		
		private static final int DEFAULT_OCTAVE = 4;
		private static final double ROUNDING_FACTOR;
		
		/* END Static Constants */
		
		
		/* Static Fields */
		
		private static HashMap<Integer, Double> midiTable;
		private static HashMap<Double, Integer> freqTable;
		private static HashMap<Character, Integer> letterTable;
		
		/* END Static Fields */
		
		
		/* Static Initialiser */
		
		static {
			ROUNDING_FACTOR = Math.pow(10, ROUNDING_PRECISION);
			
			midiTable = new HashMap<Integer, Double>(128);
			freqTable = new HashMap<Double, Integer>(128);
			for (int i = 0; i < 128; i++) {
				double val = HZ_CONCERT_PITCH * Math.pow(2,(i - MIDI_CONCERT_PITCH) / 12.0);
				midiTable.put(Integer.valueOf(i), val);
				freqTable.put(Note.roundFrequency(val), Integer.valueOf(i));			}
			
			letterTable = new HashMap<Character, Integer>(7);
			letterTable.put(Character.valueOf('C'), Integer.valueOf(0));
			letterTable.put(Character.valueOf('D'), Integer.valueOf(2));
			letterTable.put(Character.valueOf('E'), Integer.valueOf(4));
			letterTable.put(Character.valueOf('F'), Integer.valueOf(5));
			letterTable.put(Character.valueOf('G'), Integer.valueOf(7));
			letterTable.put(Character.valueOf('A'), Integer.valueOf(9));
			letterTable.put(Character.valueOf('B'), Integer.valueOf(11));
		}
		
		/* END Static Initialiser */
		
		
		/* Static Methods */
		
		/**
		 * Used for debugging only.
		 */
		protected static void printTables () {
			System.out.println(freqTable.toString());
		}
		
		/* END Static Methods */
		
	/* END Static */
	
		
	/* Fields */
	
	/**
	 * Stores this note's MIDI value (from 0 through 127).
	 */
	private int midi;
	
	/* END Fields */
	
	
	/* Constructors */
	
	//Cloning Constructor
	/**
	 * Constructs a <code>Note</code> by cloning another <code>Note</code>.
	 * @param n the <code>Note</code> to clone.
	 */
	public Note (Note n) {
		this(n.getHalfSteps());
	}
	
	/**
	 * Constructs a <code>Note</code> with a specified frequency.
	 * @param frequency the frequency to use
	 * @throws IllegalArgumentException if the frequency is not a half tone frequency or is not in the range 0.0 through 13000.0 inclusive.
	 */
	public Note (double frequency) throws IllegalArgumentException {
		super(frequency);
		double dFreq = Note.roundFrequency(frequency);
		if (!midiTable.containsValue(dFreq))
			throw new IllegalArgumentException("The frequency must be a valid half tone frequency between 0.0 and 13000.0 inclusive.");
		
		midi = freqTable.get(dFreq);
	}
	
	/**
	 * Constructs a <code>Note</code> which represents a note that is a certain
	 * number of semitones from A4 (which is 440 Hz).
	 * @param semitones the number of semitones from A4
	 * @throws IllegalArgumentException if the number of steps from A4 is out of the range of -69 through 58 inclusive
	 */
	public Note (int semitones) throws IllegalArgumentException {
		super(semitones);
		if (semitones < LOW_MIDI_ABSOLUTE_NUMBER - MIDI_CONCERT_PITCH || semitones > HIGH_MIDI_ABSOLUTE_NUMBER - MIDI_CONCERT_PITCH)
			throw new IllegalArgumentException("The number of steps from A4 must be between -69 and 58 inclusive.");
		midi = semitones + MIDI_CONCERT_PITCH;
	}
	
	/**
	 * Constructs a <code>Note</code> from a <code>String</code>.
	 * The <code>String</code> should contain the three following elements (accidental and octave in any order):
	 * <pre>note [accidental] [octave]</pre>
	 * Where:
	 * <ul>
	 * <li><code>note</code> is required and is a single letter in the range 'A' through 'G'
	 * (case sensitive) and represents the note name</li>
	 * <li><code>accidental</code> is optional and is one of '#', 'x', 'b', or 'bb' and
	 * signifies that the note is sharp, double sharp, flat, or double flat respectively.
	 * If not specified then the note is assumed to be natural (not accidental)</li>
	 * <li><code>octave</code> is optional and is an integer in the range -1 through 9
	 * and represents the octave this note is in.  If this is not specified then the note is
	 * assumed to be in the octave specified by the constant <code>DEFAULT_OCTAVE</code></li>
	 * Some examples of valid inputs are:
	 * <pre>
	 * G
	 * A4
	 * C#
	 * B#4
	 * D2bb
	 * C-1x
	 * Eb2
	 * </pre>
	 * @param strNote the note as represented by a <code>String</code>
	 * @throws IllegalArgumentException if the <code>String</code> is not in a valid format
	 */
	public Note (String strNote) throws IllegalArgumentException {
		super(strNote);
		
		int letter = parseNote(strNote);
		if (strNote.length() > 1)
			strNote = strNote.substring(1);
		int acc = parseAccidental(strNote);
		int octave = parseOctave(strNote);
		
		int test = (octave + 1) * 12 + letter + acc;
		if (test < LOW_MIDI_ABSOLUTE_NUMBER || test > HIGH_MIDI_ABSOLUTE_NUMBER)
			throw new IllegalArgumentException("The note was parsed but was out of range.");
		
		midi = test;
	}
	
	/* END Constructors */
	
	
	/* Accessors */
	
	@Override
	/**
	 * Returns the frequency that this note represents.
	 * @return the frequency
	 */
	public double getFrequencyInHz () {
		return midiTable.get(midi);
	}
	
	@Override
	/**
	 * Returns the number of semitones this note is from A4 (440 Hz).
	 * If this note is lower than A4 then the result is negative.
	 * If this note is higher than A4 then the result is positive.
	 * If this note is A4 then the result is 0.
	 * @return the number of semitones this note is from A4
	 */
	public int getHalfSteps () {
		return getMIDIAbsoluteNumber() - MIDI_CONCERT_PITCH;
	}
	
	@Override
	/**
	 * Returns the MIDI index that this note represents.
	 * This is an <code>int</code> in the range 0 through 127 inclusive.
	 * @return the MIDI index
	 */
	public int getMIDIAbsoluteNumber () {
		return midi;
	}
	
	/* END Accessors */
	
	
	/* Methods */
	
	@Override
	/**
	 * Compares a <code>Note</code> to this <code>NoteADT</code> and determines if the two notes form an octave.
	 * @param n the <code>Note</code> to compare to this <code>NoteADT</code>
	 * @return <code>true</code> if these notes form an octave
	 */
	public boolean formOctave (NoteADT n) {
		return Math.abs(n.getMIDIAbsoluteNumber() - this.midi) == 12;
	}
	
	@Override
	/**
	 * Transposes this <code>Note</code> by a specified number of numberOfSemitones.
	 * @param numberOfSemitones the number of numberOfSemitones to transpose this note (this can be positive or negative)
	 * @throws IllegalArgumentException if the transposition would place the note out of range
	 */
	public void modifyNoteBySemitones (int numberOfSemitones) throws IllegalArgumentException {
		if (midi + numberOfSemitones < LOW_MIDI_ABSOLUTE_NUMBER || midi + numberOfSemitones > HIGH_MIDI_ABSOLUTE_NUMBER)
		throw new IllegalArgumentException("The offset in semitones must be between " +
					(LOW_MIDI_ABSOLUTE_NUMBER - midi) + " and " + (HIGH_MIDI_ABSOLUTE_NUMBER - midi) + " inclusive for this note.");
		midi += numberOfSemitones;
	}
	
	/* END Methods */
	
	
	/* (Object) Methods */
	
	@Override
	/**
	 * Compares this <code>NoteADT</code> to another and returns <code>true</code> if these notes are equivalent.
	 * @param o the <code>Object</code> to compare this <code>NoteADT</code> against
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals (Object o) {
		if (o == null)
			return false;
		
		if (!(o instanceof NoteADT))
			return false;
		
		NoteADT n = (NoteADT)o;
		return midi == n.getMIDIAbsoluteNumber();
	}
	
	/* END (Object) Methods */
	
	
	/* (Comparable) Methods */
	
	@Override
	/**
	 * Compares this <code>Note</code> with the specified <code>Note</code> for order.
	 * For example, if there are two <code>Note</code> objects called <code>A4</code> and <code>B4</code>
	 * which represent the notes A4 and B4 respectively, then the following statements are true:
	 * <pre>
	 * (A4).compareTo(B4) == -1
	 * (B4).compareTo(A4) == 1
	 * (A4).compareTo(A4) == 0
	 * </pre>
	 * @param o the <code>Object</code> to compare this <code>NoteADT</code> against
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo (Object o) {
		if (o == null)
			throw new NullPointerException();
		if (!(o instanceof Note))
			throw new IllegalArgumentException();
		
		Note n = (Note)o;
		if (midi > n.getMIDIAbsoluteNumber())
			return 1;
		if (midi < n.getMIDIAbsoluteNumber())
			return -1;
		return 0;
	}
	
	/* END (Comparable) Methods */
	
	
	/* Local Methods */
	
	private int parseNote (String str) throws IllegalArgumentException {
		for (char c = 'A'; c <= 'G'; c++)
			if (str.toUpperCase().charAt(0) == c)
				return letterTable.get(c);
		throw new IllegalArgumentException("The note could not be parsed.");
	}
	
	private int parseAccidental (String str) {
		if (str.contains("bb"))
			return -2;
		
		if (str.contains("b"))
			return -1;
		
		if (str.contains("#"))
			return 1;
		
		if (str.contains("x"))
			return 2;
		
		return 0;
	}
	
	private int parseOctave (String str) throws IllegalArgumentException {
		for (int i = -1; i <= 9; i++) {
			String si = String.valueOf(i);
			int idx = str.indexOf(si);
			if (idx != -1) {
				String s = str.substring(0, idx) + str.substring(idx + si.length());
				for (int j = 0; j < s.length(); j++)
						if (Character.isDigit(s.charAt(j)) || s.charAt(j) == '-')
							throw new IllegalArgumentException("The octave is invalid.");
				return i;
			}
		}
		return DEFAULT_OCTAVE;
	}
	
	/* END Local Methods */
	
	
	/* Static Methods */
	
	/**
	 * Parses a <code>String</code> and returns a <code>Note</code> constructed with the appropriate constructor.
	 * @param str the <code>String</code> to parse.  This can be a <code>String</code> representation of an <code>int</code> or <code>double</code> or a note string
	 * @return a <code>Note</code> constructed from the <code>String</code>
	 * @throws IllegalArgumentException if the <code>String</code> is malformed
	 */
	public static Note parseNoteString (String str) throws IllegalArgumentException {
		Note note;
		try {
			int semitones = Integer.parseInt(str);
			note = new Note(semitones);
		} catch (NumberFormatException e) {
			try {
				double frequency = Double.parseDouble(str);
				note = new Note(frequency);
			} catch (NumberFormatException e1) {
				note = new Note(str);
			}
		}
		return note;
	}
	
	/**
	 * Rounds a frequency to the precision specified by <code>ROUNDING_PRECISION</code>.
	 * @param frequency the frequency to round
	 * @return the rounded frequency
	 */
	public static double roundFrequency (double frequency) {
		return Math.round(frequency * ROUNDING_FACTOR) / ROUNDING_FACTOR;
	}
	
	/* Static Methods */
	
}
