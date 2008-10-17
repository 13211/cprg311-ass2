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

import java.util.*;

import javax.sound.midi.*;


/**
 * Represents an instrument which can play notes.
 */
public class Instrument {
	
	/* Static */
	
	private static HashMap<String, Integer> namedInstruments;
	
	static {
		namedInstruments = new HashMap<String, Integer>(8);
		
		namedInstruments.put("piano", 1);
		namedInstruments.put("organ", 20);
		namedInstruments.put("violin", 41);
		namedInstruments.put("trumpet", 57);
		namedInstruments.put("guitar", 26);
		namedInstruments.put("flute", 74);
		namedInstruments.put("oboe", 69);
		namedInstruments.put("marimba", 13);
	}
	
	/* END Static */
	
	
	/* Fields */
	
	protected final MidiChannel channel;
	
	/* END Fields */
	
	
	/* Constructors */
	
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
	    int i;
		for (i = 0; i < channels.length; i++)
	    	if (channels[i] != null)
	    		break;
	    
	    if (i < channels.length)
	    	channel = channels[i];
	    else
	    	throw new MidiUnavailableException();
			
	    channel.programChange(patch);
	}
	
	/* END Constructors */
	
	
	/* Methods */
	
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
	
	/* END Methods */
	
	
	/* Static Methods */
	
	/**
	 * Constructs an <code>Instrument</code> from a <code>String</code>.
	 * If the <code>String</code> is parsable into an <code>int</code> then the <code>Instrument</code>
	 * will be created with <code>Instrument(int)</code>, where the <code>int</code> is the patch number to use.
	 * The <code>String</code> can also be one of the following case insensitive strings (in the first column):
	 * <table>
	 * <tr><th>Instrument</th><th>Patch</th></tr>
	 * <tr><td>piano</td><td>1</td></tr>
	 * <tr><td>organ</td><td>20</td></tr>
	 * <tr><td>violin</td><td>41</td></tr>
	 * <tr><td>trumpet</td><td>57</td></tr>
	 * <tr><td>guitar</td><td>26</td></tr>
	 * <tr><td>flute</td><td>74</td></tr>
	 * <tr><td>oboe</td><td>69</td></tr>
	 * <tr><td>marimba</td><td>13</td></tr>
	 * </table>
	 * @param str a <code>String</code> to be parsed into an <code>Instrument</code>
	 * @return an <code>Instrument</code> parsed from the str
	 * @throws IllegalArgumentException if str cannot be parsed into an <code>Instrument</code>
	 * @throws MidiUnavailableException if MIDI playback is unavailable
	 */
	public static Instrument parseInstrumentString (String str) throws IllegalArgumentException, MidiUnavailableException {
		Instrument instr;
		try {
			//Construct an instrument using the (int) constructor
			instr = new Instrument(Integer.parseInt(str));
		} catch (NumberFormatException e) {
			str = str.toLowerCase();
			if (namedInstruments.containsKey(str))
				instr = new Instrument(namedInstruments.get(str));
			else
				throw new IllegalArgumentException("Cannot find instrument " + str);
		}
		return instr;
	}
	
	/**
	 * Returns a <code>Vector&lt;String&gt;</code> of available named instruments.
	 * @return a list of named instruments
	 */
	public static Vector<String> getNamedInstruments () {
		Vector<String> v = new Vector<String>(10);
	    Iterator<String> i = namedInstruments.keySet().iterator();
	    while (i.hasNext())
	    	v.add(i.next());
	    return v;
	}
	
	/* END Static Methods */
	
}
