/**
 * @author Alex Peterson
 * @version 2008OC05
 */

package midi;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import javax.sound.midi.MidiUnavailableException;

/**
 * Plays a song from note strings in a file.
 */
public class Song {
	
	/**
	 * TODO
	 * @param args
	 */
	public static void main (String [] args) {
		if (args.length != 1) {
			System.out.println("Usage: Song <filename>");
			System.exit(0);
		}
		
		try {
			playNotes(readFile(args[0]));
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			System.exit(0);
		} catch (MidiUnavailableException e) {
			System.out.println("MIDI playback is unavailable");
			System.exit(0);
		}
			
	}
	
	private static Vector<Event> readFile (String filename) throws FileNotFoundException {
		Scanner s = new Scanner(new File(filename));
		StringBuffer sb = new StringBuffer();
		while (s.hasNext())
			sb.append(s.nextLine() + ",");
		
		StringTokenizer st = new StringTokenizer(sb.toString(), ",");
		Vector<Event> notes = new Vector<Event>(st.countTokens());
		for (int i = 0; st.hasMoreTokens(); i++) {
			String str = st.nextToken().trim();
			
			try {
				Event ni = new Event(str);
				notes.add(ni);
			} catch (IllegalArgumentException e) {
				System.out.println("Note string \"" + str + "\" was ignored");
			}
		}
		return notes;
	}
	
	private static void playNotes (Vector<Event> notes) throws MidiUnavailableException {
		Instrument piano = new Piano();
		for (int i = 0; i < notes.size(); i++)
			notes.get(i).play(piano);
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
		}
	}
}
