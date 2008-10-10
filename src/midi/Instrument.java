/**
 * @author Alex Peterson
 * @version 2008OC06
 * 
 * This code is available under the terms of the GNU General Public License v3.0
 * (http://www.gnu.org/licenses/gpl-3.0.txt)
 * 
 * The content is licenced under the Creative Commons Attribution + ShareAlike 2.5 [BY-SA] (Canada)
 * (http://creativecommons.org/licenses/by-sa/2.5/ca)
 */

package midi;

import javax.sound.midi.*;

/**
 * Represents an instrument which can play notes.
 */
public class Instrument {
	
	/* Fields */
	
	protected final MidiChannel channel;
	
	/* END Fields */
	
	
	/**
	 * Constructs an instrument from a patch number.
	 * @param patch the patch to use
	 * @throws IllegalArgumentException if patch is outside of the range of available patches
	 * @throws MidiUnavailableException if MIDI playback is unavailable
	 */
	public Instrument (int patch) throws IllegalArgumentException, MidiUnavailableException {
		Synthesizer synthesizer = null;
    	synthesizer = MidiSystem.getSynthesizer();
	    synthesizer.open();
	    javax.sound.midi.Instrument [] instruments = synthesizer.getDefaultSoundbank().getInstruments();
	    if (instruments == null)
	    	throw new MidiUnavailableException("MIDI unavailable. There is no default soundbank.");
		if (patch < 0 || patch > instruments.length)
			throw new IllegalArgumentException("The patch index must be between 0 and " + instruments.length + " inclusive.");
		
		synthesizer.loadInstrument(instruments[patch]);
		MidiChannel [] channels = synthesizer.getChannels();
	    channel = channels[1];
	}
	
	/**
	 * Make the instrument play a <code>Note</code> for the specified duration, and at the default (full) velocity (volume).
	 * This method is a convenience method for <code>playNote(n, duration, 127)</code>.
	 * @see #playNote(Note, int, int)
	 * @param n the <code>Note</code> to play
	 * @param duration the duration to play the <code>Note</code> (in milliseconds)
	 */
	public void playNote (Note n, int duration) {
		playNote(n, duration, 127);
	}
	
	/**
	 * Make the instrument play a <code>Note</code> for the specified duration, and at a specific velocity (volume).
	 * @param n the <code>Note</code> to play
	 * @param duration the duration to play the <code>Note</code> (in milliseconds)
	 * @param velocity the velocity (volume) to play the <code>Note</code>
	 */
	public void playNote (Note n, int duration, int velocity) {
		channel.noteOn(n.getMIDIAbsoluteNumber(), velocity);
		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
		}
		channel.noteOff(n.getMIDIAbsoluteNumber());
	}
	
	/**
	 * Make the instrument rest for the specified duration.
	 * @param duration the duration to rest (in milliseconds)
	 */
	public void rest (int duration) {
		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {}
	}
	
}
