package midi;


public class TimeSignature {
	
	private final int beats;
	private final int value;
	
	public TimeSignature (int beats, int value) {
		this.beats = beats;
		this.value = value;
	}

	
	public int getBeats () {
		return this.beats;
	}

	
	public int getValue () {
		return this.value;
	}
	
	
}
