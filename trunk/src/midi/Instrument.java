package midi;

import javax.sound.midi.*;

public class Instrument {
	
	/* Fields */
	
	protected final MidiChannel channel;
	
	/* END Fields */
	
	
	public Instrument (int patch) throws IllegalArgumentException {
		Synthesizer synthesizer = null;
	    try {
	    	synthesizer = MidiSystem.getSynthesizer();
		    synthesizer.open();
	    } catch(MidiUnavailableException mue) {
	    }
	    javax.sound.midi.Instrument [] instruments = synthesizer.getDefaultSoundbank().getInstruments();
		if (patch < 0 || patch > instruments.length)
			throw new IllegalArgumentException("The patch index must be between 0 and " + instruments.length + " inclusive.");
		
		synthesizer.loadInstrument(instruments[patch]);
		MidiChannel [] channels = synthesizer.getChannels();
	    channel = channels[1];
	}
	
	public void playNote (Note n, int duration) {
		playNote(n, duration, 127);
	}
	
	public void playNote (Note n, int duration, int velocity) {
		channel.noteOn(n.getMIDIAbsoluteNumber(), velocity);
		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
		}
		channel.noteOff(n.getMIDIAbsoluteNumber());
	}
	
	public void rest (int duration) {
		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
		}
	}
	
}
