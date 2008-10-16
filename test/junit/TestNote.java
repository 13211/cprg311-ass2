package junit;


import junit.framework.TestCase;
import midi.Note;
import noteGeneration.NoteADT;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestNote extends TestCase {
	
	private Note one;
	private Note two;
	private Note three;
	private Note four;
	private Note five;
	
	@Before
	public void setUp () throws Exception {
		one = new Note(8);
		two = new Note(440.00001);
		three = new Note("C4");
		four = new Note("D3#");
		five = new Note(four);
	}

	@After
	public void tearDown () throws Exception {
		
	}
	
	@Test
	public void testConstructors () {
		assertEquals(8, one.getHalfSteps());
		assertEquals(NoteADT.HZ_CONCERT_PITCH, two.getFrequencyInHz());
		assertEquals(60, three.getMIDIAbsoluteNumber()); //C4 is midi 60
		assertEquals(four, five);
	}
	
	
	
}
