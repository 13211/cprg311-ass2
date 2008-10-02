/**
 * @author Alex Peterson
 * @version 2008OC02
 */

package music;

public abstract class NoteADT implements Comparable<NoteADT> {
	
	private double frequency;
	
	public NoteADT (double frequency) {
		//TODO complete
	}
	
	public NoteADT (int steps) {
		//TODO complete
	}
	
	public NoteADT (String note) {
		//TODO complete
	}
	
	public double asFrequency () {
		//TODO complete
	}
	
	public int asHalfSteps () {
		//TODO complete
	}
	
	public int asMIDI () {
		//TODO complete
	}
	
	public int octaveWith () {
		//TODO complete
	}
	
	public void transpose (int semitones) {
		//TODO complete
	}
	
	/* (Comparable) Methods */
	
	@Override
	public int compareTo (NoteADT o) {
		// TODO complete
		return 0;
	}
	
	/* END (Comparable) Methods */
	
}
