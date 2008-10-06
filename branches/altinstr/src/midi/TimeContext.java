package midi;


public class TimeContext {
	
	private TimeSignature timeSignature;
	private int bpm;
	
	public TimeContext (TimeSignature timeSignature, int bpm) {
		this.timeSignature = timeSignature;
		this.bpm = bpm;
	}

	
	public TimeSignature getTimeSignature () {
		return timeSignature;
	}

	
	public int getBpm () {
		return bpm;
	}
	
}
