package midi;


public class Melody {
	
	/**
	 * @param args
	 */
	public static void main (String [] args) {
		if (args.length != 1) {
			//TODO
			System.err.println("Usage ");
			System.exit(0);
		}
		String str = args[0];
		Note start;
		try {
			try {
				int semitones = Integer.parseInt(str);
				start = new Note(semitones);
			} catch (NumberFormatException e) {
				try {
					double frequency = Double.parseDouble(str);
					start = new Note(frequency);
				} catch (NumberFormatException e1) {
					start = new Note(str);
				}
			}
		} catch (IllegalArgumentException e1) {
			System.out.println("Error: " + e1.getMessage());
			start = null;
			System.exit(0);
		}
		
		Piano p = new Piano();
		
		Note n = new Note(start);
		while (!(n.formOctave(start))) {
			p.playNote(n, 500);
			n.modifyNoteBySemitones(1);
		}
		
	}
	
}
