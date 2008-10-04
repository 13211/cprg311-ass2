package midi;

import noteGeneration.NoteADT;

public class Note extends NoteADT {
	
	public Note (double frequency) {
		super(frequency);
		// TODO Auto-generated constructor stub
	}
	
	public NoteADT(int semitones) {
		super(semitones);
		//TODO
	}

	public NoteADT(String strNote) throws IllegalArgumentException {
		//You must provide the code for this
	}
	
	@Override
	public int compareTo (Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean formOctave (NoteADT note) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getFrequencyInHz () {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHalfSteps () {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMIDIAbsoluteNumber () {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void modifyNoteBySemitones (int numberOfSemitones) {
		// TODO Auto-generated method stub

	}

}
