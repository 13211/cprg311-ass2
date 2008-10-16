/**
 * @author Alex Peterson
 * @version 2008OC07
 * 
 * This code is available under the terms of the GNU General Public License v3.0
 * (http://www.gnu.org/licenses/gpl-3.0.txt)
 * 
 * The content is licenced under the Creative Commons Attribution + ShareAlike 2.5 [BY-SA] (Canada)
 * (http://creativecommons.org/licenses/by-sa/2.5/ca)
 */

package midi.instr;

import javax.sound.midi.MidiUnavailableException;


/**
 * Represents a piano instrument.
 */
public class Organ extends Instrument {
	
	/**
	 * Constructs a <code>Strings</code>.
	 * @throws MidiUnavailableException if MIDI playback is unavailable
	 */
	public Organ () throws MidiUnavailableException {
		super(20);
	}
		
}
