/**
 * @author Alex Peterson
 * @version 2008OC02
 */

package music;

import java.util.HashMap;

/**
 * An abstract data type (ADT) which represents a musical note.
 * The range for the note is C-1 (MIDI 0) through G9 (MIDI 127).
 */
public abstract class NoteADT implements Comparable<NoteADT> {
	
	/* Static */
		
		/* Static Constants */
		
		private static final double BASE_A4 = 440.0;
		private static final int DEFAULT_OCTAVE = 4;
		
		/* END Static Constants */
		
		
		/* Static Fields */
		
		private static HashMap<Integer, Double> midiTable;
		private static HashMap<Double, Integer> freqTable;
		private static HashMap<Character, Integer> letterTable;
		
		/* END Static Fields */
		
		
		/* Static Initializer */
		
		static {
			midiTable = new HashMap<Integer, Double>(128);
			freqTable = new HashMap<Double, Integer>(128);
			for (int i = 0; i < 128; i++) {
				double val = BASE_A4 * Math.pow(2,(i - 69) / 12.0);
				midiTable.put(Integer.valueOf(i), val);
				freqTable.put(val, Integer.valueOf(i));
			}
			
			letterTable = new HashMap<Character, Integer>(7);
			letterTable.put(Character.valueOf('C'), Integer.valueOf(0));
			letterTable.put(Character.valueOf('D'), Integer.valueOf(2));
			letterTable.put(Character.valueOf('E'), Integer.valueOf(4));
			letterTable.put(Character.valueOf('F'), Integer.valueOf(5));
			letterTable.put(Character.valueOf('G'), Integer.valueOf(7));
			letterTable.put(Character.valueOf('A'), Integer.valueOf(9));
			letterTable.put(Character.valueOf('B'), Integer.valueOf(11));
		}
		
		/* END Static Initializer */
		
		
		/* Static Methods */
		
		/**
		 * Used for debugging only.
		 */
		protected static void printTables () {
			for (int i = 0; i < 128; i++) {
				System.out.println(i + "\t" + midiTable.get(Integer.valueOf(i)));
			}
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
	
	/**
	 * Constructs a <code>NoteADT</code> with a specified frequency.
	 * @param frequency the frequency to use
	 * @throws IllegalArgumentException if the frequency is not a half tone frequency or is not in the range 0.0 through 13000.0 inclusive.
	 */
	public NoteADT (double frequency) throws IllegalArgumentException {
		if (!midiTable.containsValue(frequency))
			throw new IllegalArgumentException("The frequency must be a valid half tone frequency between 0.0 and 13000.0 inclusive.");
		
		midi = freqTable.get(frequency);
	}
	
	/**
	 * Construccts a <code>NoteADT</code> which represents a note that is a certain
	 * number of semitones from A4 (which is 440 Hz).
	 * @param stepsFromA4 the number of semitones from A4
	 * @throws IllegalArgumentException if the number of steps from A4 is out of the range of -69 through 58 inclusive
	 */
	public NoteADT (int stepsFromA4) throws IllegalArgumentException {
		if (stepsFromA4 < -69 || stepsFromA4 > 58)
			throw new IllegalArgumentException("The number of steps from A4 must be between -69 and 58 inclusive.");
		midi = stepsFromA4 + 69;
	}
	
	/**
	 * Constructs a <code>NoteADT</code> from a <code>String</code>.
	 * The <code>String</code> should follow the following format:
	 * <pre>note[occidental][octave]</pre>
	 * Where:
	 * <ul>
	 * <li><code>note</code> is required and is a single letter in the range 'A' through 'G'
	 * (case insensitive) and represents the note name</li>
	 * <li><code>occidental</code> is optional and is either of the characters '#' or 'b' and
	 * signifies that the note is sharp or flat respectively.  If not specified then the note
	 * is assumed to be natural (not occidental)</li>
	 * <li><code>octave</code> is optional and is an integer in the range -1 through 9
	 * and represents the octave this note is in.  If this is not specified then the note is
	 * assumed to be in the octave specified by the constant <code>DEFAULT_OCTAVE</code></li>
	 * Some examples of valid inputs are:
	 * <pre>
	 * C
	 * C4
	 * C#
	 * B#4
	 * A-1
	 * Eb2
	 * </pre>
	 * @param note the note as represented by a <code>String</code>
	 * @throws IllegalArgumentException if the <code>String</code> is not in a valid format
	 */
	public NoteADT (String note) throws IllegalArgumentException {
		if (!isParsable(note))
			throw new IllegalArgumentException("The note could not be parsed.");
		
		int letter = parseNote(note);
		int occ = parseOccidental(note);
		int octave = parseOctave(note);
		
		int test =(octave + 1) * 12 + letter + occ;
		if (test < 0 || test > 127)
			throw new IllegalArgumentException("The note was parsed but was out of range.");
		
		midi = test;
	}
	
	/* END Constructors */
	
	
	/* Accessors */
	
	/**
	 * Returns the frequency that this note represents.
	 * @return the frequency
	 */
	public double asFrequency () {
		return midiTable.get(midi);
	}
	
	/**
	 * Returns the number of semitones this note is from A4 (440 Hz).
	 * If this note is lower than A4 then the result is negative.
	 * If this note is higher than A4 then the result is positive.
	 * If this note is A4 then the result is 0.
	 * @return the number of semitones this note is from A4
	 */
	public int asSemitonesFromA4 () {
		return asMIDI() - 69;
	}
	
	/**
	 * Returns the MIDI index that sthis note represents.
	 * @return the MIDI index
	 * TODO
	 */
	public int asMIDI () {
		return midi;
	}
	
	/* END Accessors */
	
	
	/* Methods */
	
	public int octaveWith (NoteADT n) {
		if (n.midi - midi == 12)
			return 1;
		
		if (n.midi - midi == -12)
			return -1;
		
		return 0;
	}
	
	public void transpose (int semitones) throws IllegalArgumentException {
		if (midi + semitones < 0 || midi + semitones > 127)
			throw new IllegalArgumentException("The offset in semitones must be between " +
					-midi + " and " + (127 - midi) + " inclusive for this note.");
		midi += semitones;
	}
	
	/* END Methods */
	
	
	/* (Object) Methods */
	
	/**
	 * TODO
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals (Object o) {
		if (o == null)
			return false;
		
		if (!(o instanceof NoteADT))
			return false;
		
		NoteADT n = (NoteADT)o;
		return midi == n.midi;
	}
	
	/* END (Object) Methods */
	
	
	/* (Comparable) Methods */
	
	@Override
	public int compareTo (NoteADT n) {
		if (n == null)
			throw new NullPointerException();
		 
		if (midi > n.midi)
			return 1;
		if (midi < n.midi)
			return -1;
		return 0;
	}
	
	/* END (Comparable) Methods */
	
	
	/* Local Methods */
	
	private boolean isParsable (String str) {
		char first, second, third, fourth;
		switch (str.length()) {
			case 1:
				//Must be a single letter
				
				first = Character.toUpperCase(str.charAt(0));
				return first >= 'A' && first <= 'G';
				
			case 2:
				//Could be a letter and an occidental
				//Or a letter and an octave
				
				first = Character.toUpperCase(str.charAt(0));
				if (first < 'A' || first > 'G')
					return false;
				
				second = Character.toLowerCase(str.charAt(1));
				return second == 'b' || second == '#' ||
					   (second >= '0' && second <= '9');
				
			case 3:
				//Could be a letter with occidental and octave
				//Or a letter and an octave of "-1"
				first = Character.toUpperCase(str.charAt(0));
				if (first < 'A' || first > 'G')
					return false;
				
				second = Character.toLowerCase(str.charAt(1));
				third = Character.toLowerCase(str.charAt(2));
				return ((second == 'b' || second == '#') &&
					    (third >= '0' && third <= '9')) ||
					   (second == '-' && third == '1');
			
			case 4:
				//Must be a letter with occidental and
				//an octave of "-1"
				first = Character.toUpperCase(str.charAt(0));
				if (first < 'A' || first > 'G')
					return false;
				
				second = Character.toLowerCase(str.charAt(1));
				third = Character.toLowerCase(str.charAt(2));
				fourth = Character.toLowerCase(str.charAt(3));
				return (second == 'b' || second == '#') &&
					   third == '-' && fourth == '1';
				
			default:
				//Reject all other strings
				return false;
		}
	}
	
	private int parseNote (String str) {
		//C starts at 0 and a half step is an increment
		char letter = str.toUpperCase().charAt(0);
		return letterTable.get(letter).intValue();
		
	}
	
	private int parseOccidental (String str) {
		//if length is 1 there is no occidental
		if (str.length() == 1)
			return 0;
		
		//if it is flat return an offset of -1
		char second = str.toLowerCase().charAt(1);
		if (second == 'b')
			return -1;
		
		//if it is sharp return an offset of 1
		if (second == '#')
			return 1;
		
		//it is natural
		return 0;
	}
	
	private int parseOctave (String str) {
		//Example of all cases (# represent any occidental):
		//C
		//C#
		//C2
		//C#2
		//C-1
		//C#-1
		
		//if there is no octave specified then use the defalut octave
		char last = Character.toLowerCase(str.charAt(str.length() - 1));
		if (last < '0' && last > '9')
			return DEFAULT_OCTAVE;
		
		assert (str.length() > 1);
		//must be 2 - 4 digits
		char second = Character.toLowerCase(str.charAt(1));
		if (str.length() == 2)
			return Character.digit(second, 10);
		
		if (str.length() == 4)
			return -1;
		
		assert (str.length() == 3);
		//must be three digits
		
		if (second == '-')
			return -1;
		
		return Character.digit(last, 10);
		
	}
	
	/* END Local Methods */
	
}
