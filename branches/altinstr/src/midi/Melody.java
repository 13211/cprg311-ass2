package midi;

import javax.sound.midi.MidiUnavailableException;


public class Melody {
	
	/**
	 * @param args
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
			System.out.println("Could not construct note from string");
			System.exit(0);
		} catch (MidiUnavailableException e) {
			System.out.println("MIDI playback is unavailable");
			System.exit(0);
		}
		
	}
	
}
