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

package song;

import midi.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import javax.sound.midi.MidiUnavailableException;


/**
 * Plays a song from note strings in a file which is specified by the first argument.
 * The second argument optionally specifies the instrument to use, either as a patch number or
 * one of the named instruments available within <code>Instrument</code>.
 */
public class Song {
	
	/* Main Method */
	
	/**
	 * The <code>main</code> method for <code>Song</code>.
	 * @see Song
	 * @param args the list of arguments
	 */
	public static void main (String [] args) {
		if (args.length < 1 || args.length > 2) {
			System.out.println("Usage: Song filename [instrument]\n");
			System.out.println("Available instruments:");
			Iterator<String> i = Instrument.getNamedInstruments().iterator();
			while (i.hasNext())
				System.out.println('\t' + i.next());
			System.exit(0);
		}
		
		//get instrument
		Instrument instr;
		try {
			if (args.length == 2) {
					try {
						instr = Instrument.parseInstrumentString(args[1]);
					} catch (IllegalArgumentException e) {
						System.out.println(e.getMessage());
						System.out.println("Using piano (patch 001) as default instrument");
						instr = new Instrument(1);
					}
				
			} else {
				instr = new Instrument(1);
			}
		} catch (MidiUnavailableException e) {
			System.out.println("MIDI playback is unavailable");
			instr = null;
			System.exit(0);
		}
		
		try {
			playNotes(readFile(args[0]), instr);
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			System.exit(0);
		}
	}
	
	/* END Main Method */
	
	
	/* Local Methods */
	
	/**
	 * Reads notes and rests from a file and returns the
	 * @param filename the file to read
	 * @return a <code>Vector&lt;Event&gt;</code> listing all of the events read from the file
	 * @throws FileNotFoundException if the file does not exist or is not accessible
	 */
	private static Vector<MusicEvent> readFile (String filename) throws FileNotFoundException {
		Scanner s = new Scanner(new File(filename));
		StringBuffer sb = new StringBuffer();
		while (s.hasNext())
			sb.append(s.nextLine() + ",");
		
		StringTokenizer st = new StringTokenizer(sb.toString(), ",");
		Vector<MusicEvent> notes = new Vector<MusicEvent>(st.countTokens());
		for (int i = 0; st.hasMoreTokens(); i++) {
			String str = st.nextToken().trim();
			try {
				notes.add(new MusicEvent(str));
			} catch (IllegalArgumentException e) {
				System.out.println("Note string \"" + str + "\" was ignored");
			}
		}
		return notes;
	}
	
	/**
	 * Plays notes and rests stored in a <code>Vector&lt;Event&gt;</code>.
	 * @param notes the notes to play
	 * @throws MidiUnavailableException if MIDI playback is unavailable
	 */
	private static void playNotes (Vector<MusicEvent> notes, Instrument instr) {
		for (int i = 0; i < notes.size(); i++)
			notes.get(i).play(instr);
		instr.rest(1500);
	}
	
	/* END Local Methods */
	
}
