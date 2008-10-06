package test;
import music.NoteADT;


public class Tester {

	/**
	 * @param args
	 */
	public static void main (String [] args) {
		NoteADT n = new NoteADT(440.00010);
		System.out.println(n.asFrequency());
	}

}
