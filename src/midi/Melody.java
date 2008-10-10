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

package midi;

import javax.sound.midi.MidiUnavailableException;

/**
 * Play a chromatic scale up from the note specified by the first argument.
 * The note can be specified as any value which validly constructs a <code>Note</code> with {@link Note#parseNoteString(String)}.
 */
public class Melody {
	
	/* Main Method */
	
	/**
	 * The <code>main</code> method for <code>Melody</code>.
	 * @see Melody
	 * @param args the list of arguments
	 */
	public static void main (String [] args) {
		if (args.length != 1) {
			System.err.println("Usage: Melody <startnote>");
			System.exit(0);
		}
		
		String str = args[0];
		try {
			Note start = Note.parseNoteString(str);
			Piano p = new Piano();
			for (Note n = new Note(start); !(n.formOctave(start)); n.modifyNoteBySemitones(1))
				p.playNote(n, 200);
		} catch (IllegalArgumentException e) {
			System.out.println("Could not create note from string");
			System.exit(0);
		} catch (MidiUnavailableException e) {
			System.out.println("MIDI playback is unavailable");
			System.exit(0);
		}
	}
	
	/* END Main Method */
	
}
