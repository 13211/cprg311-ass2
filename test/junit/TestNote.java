/**
 * @author Alex Peterson
 * @version 2008OC16
 * 
 * This code is available under the terms of the GNU General Public License v3.0
 * (http://www.gnu.org/licenses/gpl-3.0.txt)
 * 
 * The content is licenced under the Creative Commons Attribution + ShareAlike 2.5 [BY-SA] (Canada)
 * (http://creativecommons.org/licenses/by-sa/2.5/ca)
 */

package junit;

import midi.*;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit 4 test case for <code>Note</code>.
 */
public class TestNote extends TestCase {
	
	/* Fields */
	
	private Note one, two, three, four, five, six, seven;
	
	/* END Fields */
	
	
	/* Set Up */
	
	@Before
	@Override
	public void setUp () {
		//test constructors
		
		try {
			one = new Note(440.00001);
		} catch (IllegalArgumentException e) {
			fail("Raised an IllegalArgumentException");
		}
		
		try {
			two = new Note(8);
		} catch (IllegalArgumentException e) {
			fail("Raised an IllegalArgumentException");
		}
		
		try {
			three = new Note("A5");
		} catch (IllegalArgumentException e) {
			fail("Raised an IllegalArgumentException");
		}
		
		try {
			four = new Note(-12);
		} catch (IllegalArgumentException e) {
			fail("Raised an IllegalArgumentException");
		}
		
		try {
			five = new Note("D3#");
		} catch (IllegalArgumentException e) {
			fail("Raised an IllegalArgumentException");
		}
		
		try {
			six = new Note(five);
		} catch (NullPointerException e) {
			fail("Raised a NullPointerException");
		}
		
		try {
			seven = new Note(six);
		} catch (NullPointerException e) {
			fail("Raised a NullPointerException");
		}
	}
	
	/* END Set Up */
	
	
	/* Tear Down */
	
	@After
	@Override
	public void tearDown () {
		one = null;
		two = null;
		three = null;
		four = null;
		five = null;
		six = null;
		seven = null;
	}
	
	/* END Tear Down */
	
	
	/* Test getFrequencyInHz() */
	
	/**
	 * Tests the <code>getFrequencyInHz()</code> method.
	 */
	@Test
	public void testGetFrequencyInHz () {
		assertEquals("Frequency not expected", 440.00, Note.roundFrequency(one.getFrequencyInHz()));
		assertEquals("Frequency not expected", 698.46, Note.roundFrequency(two.getFrequencyInHz()));
		assertEquals("Frequency not expected", 880.00, Note.roundFrequency(three.getFrequencyInHz()));
		assertEquals("Frequency not expected", 220.00, Note.roundFrequency(four.getFrequencyInHz()));
		assertEquals("Frequency not expected", 155.56, Note.roundFrequency(five.getFrequencyInHz()));
		assertEquals("Frequency not expected", 155.56, Note.roundFrequency(six.getFrequencyInHz()));
		assertEquals("Frequency not expected", 155.56, Note.roundFrequency(seven.getFrequencyInHz()));
	}
	
	/**
	 * Tests the <code>static</code> frequency calculations.
	 * Tests all possible frequencies to check for a rounding error.
	 */
	@Test
	public void testFrequencyCalculation () {
		//tests frequency calculation against accepted values
		assertEquals(Note.roundFrequency(8.1757989156), Note.roundFrequency((new Note(0 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(8.6619572180), Note.roundFrequency((new Note(1 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(9.1770239974), Note.roundFrequency((new Note(2 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(9.7227182413), Note.roundFrequency((new Note(3 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(10.3008611535), Note.roundFrequency((new Note(4 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(10.9133822323), Note.roundFrequency((new Note(5 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(11.5623257097), Note.roundFrequency((new Note(6 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(12.2498573744), Note.roundFrequency((new Note(7 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(12.9782717994), Note.roundFrequency((new Note(8 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(13.7500000000), Note.roundFrequency((new Note(9 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(14.5676175474), Note.roundFrequency((new Note(10 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(15.4338531643), Note.roundFrequency((new Note(11 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(16.3515978313), Note.roundFrequency((new Note(12 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(17.3239144361), Note.roundFrequency((new Note(13 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(18.3540479948), Note.roundFrequency((new Note(14 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(19.4454364826), Note.roundFrequency((new Note(15 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(20.6017223071), Note.roundFrequency((new Note(16 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(21.8267644646), Note.roundFrequency((new Note(17 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(23.1246514195), Note.roundFrequency((new Note(18 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(24.4997147489), Note.roundFrequency((new Note(19 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(25.9565435987), Note.roundFrequency((new Note(20 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(27.5000000000), Note.roundFrequency((new Note(21 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(29.1352350949), Note.roundFrequency((new Note(22 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(30.8677063285), Note.roundFrequency((new Note(23 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(32.7031956626), Note.roundFrequency((new Note(24 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(34.6478288721), Note.roundFrequency((new Note(25 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(36.7080959897), Note.roundFrequency((new Note(26 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(38.8908729653), Note.roundFrequency((new Note(27 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(41.2034446141), Note.roundFrequency((new Note(28 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(43.6535289291), Note.roundFrequency((new Note(29 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(46.2493028390), Note.roundFrequency((new Note(30 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(48.9994294977), Note.roundFrequency((new Note(31 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(51.9130871975), Note.roundFrequency((new Note(32 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(55.0000000000), Note.roundFrequency((new Note(33 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(58.2704701898), Note.roundFrequency((new Note(34 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(61.7354126570), Note.roundFrequency((new Note(35 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(65.4063913251), Note.roundFrequency((new Note(36 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(69.2956577442), Note.roundFrequency((new Note(37 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(73.4161919794), Note.roundFrequency((new Note(38 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(77.7817459305), Note.roundFrequency((new Note(39 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(82.4068892282), Note.roundFrequency((new Note(40 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(87.3070578583), Note.roundFrequency((new Note(41 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(92.4986056779), Note.roundFrequency((new Note(42 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(97.9988589954), Note.roundFrequency((new Note(43 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(103.8261743950), Note.roundFrequency((new Note(44 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(110.0000000000), Note.roundFrequency((new Note(45 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(116.5409403795), Note.roundFrequency((new Note(46 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(123.4708253140), Note.roundFrequency((new Note(47 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(130.8127826503), Note.roundFrequency((new Note(48 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(138.5913154884), Note.roundFrequency((new Note(49 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(146.8323839587), Note.roundFrequency((new Note(50 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(155.5634918610), Note.roundFrequency((new Note(51 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(164.8137784564), Note.roundFrequency((new Note(52 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(174.6141157165), Note.roundFrequency((new Note(53 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(184.9972113558), Note.roundFrequency((new Note(54 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(195.9977179909), Note.roundFrequency((new Note(55 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(207.6523487900), Note.roundFrequency((new Note(56 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(220.0000000000), Note.roundFrequency((new Note(57 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(233.0818807590), Note.roundFrequency((new Note(58 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(246.9416506281), Note.roundFrequency((new Note(59 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(261.6255653006), Note.roundFrequency((new Note(60 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(277.1826309769), Note.roundFrequency((new Note(61 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(293.6647679174), Note.roundFrequency((new Note(62 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(311.1269837221), Note.roundFrequency((new Note(63 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(329.6275569129), Note.roundFrequency((new Note(64 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(349.2282314330), Note.roundFrequency((new Note(65 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(369.9944227116), Note.roundFrequency((new Note(66 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(391.9954359817), Note.roundFrequency((new Note(67 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(415.3046975799), Note.roundFrequency((new Note(68 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(440.0000000000), Note.roundFrequency((new Note(69 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(466.1637615181), Note.roundFrequency((new Note(70 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(493.8833012561), Note.roundFrequency((new Note(71 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(523.2511306012), Note.roundFrequency((new Note(72 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(554.3652619537), Note.roundFrequency((new Note(73 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(587.3295358348), Note.roundFrequency((new Note(74 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(622.2539674442), Note.roundFrequency((new Note(75 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(659.2551138257), Note.roundFrequency((new Note(76 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(698.4564628660), Note.roundFrequency((new Note(77 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(739.9888454233), Note.roundFrequency((new Note(78 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(783.9908719635), Note.roundFrequency((new Note(79 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(830.6093951599), Note.roundFrequency((new Note(80 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(880.0000000000), Note.roundFrequency((new Note(81 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(932.3275230362), Note.roundFrequency((new Note(82 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(987.7666025122), Note.roundFrequency((new Note(83 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(1046.5022612024), Note.roundFrequency((new Note(84 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(1108.7305239075), Note.roundFrequency((new Note(85 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(1174.6590716696), Note.roundFrequency((new Note(86 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(1244.5079348883), Note.roundFrequency((new Note(87 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(1318.5102276515), Note.roundFrequency((new Note(88 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(1396.9129257320), Note.roundFrequency((new Note(89 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(1479.9776908465), Note.roundFrequency((new Note(90 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(1567.9817439270), Note.roundFrequency((new Note(91 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(1661.2187903198), Note.roundFrequency((new Note(92 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(1760.0000000000), Note.roundFrequency((new Note(93 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(1864.6550460724), Note.roundFrequency((new Note(94 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(1975.5332050245), Note.roundFrequency((new Note(95 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(2093.0045224048), Note.roundFrequency((new Note(96 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(2217.4610478150), Note.roundFrequency((new Note(97 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(2349.3181433393), Note.roundFrequency((new Note(98 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(2489.0158697766), Note.roundFrequency((new Note(99 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(2637.0204553030), Note.roundFrequency((new Note(100 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(2793.8258514640), Note.roundFrequency((new Note(101 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(2959.9553816931), Note.roundFrequency((new Note(102 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(3135.9634878540), Note.roundFrequency((new Note(103 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(3322.4375806396), Note.roundFrequency((new Note(104 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(3520.0000000000), Note.roundFrequency((new Note(105 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(3729.3100921447), Note.roundFrequency((new Note(106 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(3951.0664100490), Note.roundFrequency((new Note(107 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(4186.0090448090), Note.roundFrequency((new Note(108 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(4434.9220956300), Note.roundFrequency((new Note(109 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(4698.6362866780), Note.roundFrequency((new Note(110 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(4978.0317395530), Note.roundFrequency((new Note(111 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(5274.0409106050), Note.roundFrequency((new Note(112 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(5587.6517029280), Note.roundFrequency((new Note(113 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(5919.9107633860), Note.roundFrequency((new Note(114 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(6271.9269757080), Note.roundFrequency((new Note(115 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(6644.8751612790), Note.roundFrequency((new Note(116 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(7040.0000000000), Note.roundFrequency((new Note(117 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(7458.6202347560), Note.roundFrequency((new Note(118 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(7902.1328346580), Note.roundFrequency((new Note(119 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(8372.0180896192), Note.roundFrequency((new Note(120 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(8869.8441912599), Note.roundFrequency((new Note(121 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(9397.2725733570), Note.roundFrequency((new Note(122 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(9956.0634791066), Note.roundFrequency((new Note(123 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(10548.0818212118), Note.roundFrequency((new Note(124 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(11175.3034058561), Note.roundFrequency((new Note(125 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(11839.8215267723), Note.roundFrequency((new Note(126 - 69)).getFrequencyInHz()));
		assertEquals(Note.roundFrequency(12543.8539514160), Note.roundFrequency((new Note(127 - 69)).getFrequencyInHz()));
	}
	
	/* END Test getFrequencyInHz() */
	
	
	/* Test getHalfSteps() */
	
	/**
	 * Tests the <code>getHalfSteps()</code> method.
	 */
	@Test
	public void testGetHalfSteps () {
		assertEquals("Half steps not expected", 0, one.getHalfSteps());
		assertEquals("Half steps not expected", 8, two.getHalfSteps());
		assertEquals("Half steps not expected", 12, three.getHalfSteps());
		assertEquals("Half steps not expected", -12, four.getHalfSteps());
		assertEquals("Half steps not expected", -18, five.getHalfSteps());
		assertEquals("Half steps not expected", -18, six.getHalfSteps());
		assertEquals("Half steps not expected", -18, seven.getHalfSteps());
	}
	
	/* END Test getHalfSteps() */
	
	
	/* Test getMIDIAbsoluteNumber() */
	
	/**
	 * Tests the <code>getMIDIAbsoluteNumber()</code> method.
	 */
	@Test
	public void testGetMIDIAbsoluteNumber () {
		assertEquals("Absolute MIDI number not expected", 69, one.getMIDIAbsoluteNumber());
		assertEquals("Absolute MIDI number not expected", 77, two.getMIDIAbsoluteNumber());
		assertEquals("Absolute MIDI number not expected", 81, three.getMIDIAbsoluteNumber());
		assertEquals("Absolute MIDI number not expected", 57, four.getMIDIAbsoluteNumber());
		assertEquals("Absolute MIDI number not expected", 51, five.getMIDIAbsoluteNumber());
		assertEquals("Absolute MIDI number not expected", 51, six.getMIDIAbsoluteNumber());
		assertEquals("Absolute MIDI number not expected", 51, seven.getMIDIAbsoluteNumber());
	}
	
	/* END Test getMIDIAbsoluteNumber() */
	
	
	/* Test modifyNoteBySemitones(int numberOfSemitones) */
	
	/**
	 * Tests the <code>modifyNoteBySemitones(int numberOfSemitones)</code> method.
	 * Tests with an <code>int</code> value of 0.
	 */
	@Test
	public void testModifyNoteBySemitones1 () {
		try {
			one.modifyNoteBySemitones(0);
		} catch (IllegalArgumentException e) {
			fail("Raised an IllegalArgumentException");
		}
		
		try {
			two.modifyNoteBySemitones(0);
		} catch (IllegalArgumentException e) {
			fail("Raised an IllegalArgumentException");
		}
		
		try {
			three.modifyNoteBySemitones(0);
		} catch (IllegalArgumentException e) {
			fail("Raised an IllegalArgumentException");
		}
		
		try {
			four.modifyNoteBySemitones(0);
		} catch (IllegalArgumentException e) {
			fail("Raised an IllegalArgumentException");
		}
		
		try {
			five.modifyNoteBySemitones(0);
		} catch (IllegalArgumentException e) {
			fail("Raised an IllegalArgumentException");
		}
		
		try {
			six.modifyNoteBySemitones(0);
		} catch (IllegalArgumentException e) {
			fail("Raised an IllegalArgumentException");
		}
		
		try {
			seven.modifyNoteBySemitones(0);
		} catch (IllegalArgumentException e) {
			fail("Raised an IllegalArgumentException");
		}
		
		assertEquals("Modify by 0 semitones failed", 0, one.getHalfSteps());
		assertEquals("Modify by 0 semitones failed", 8, two.getHalfSteps());
		assertEquals("Modify by 0 semitones failed", 12, three.getHalfSteps());
		assertEquals("Modify by 0 semitones failed", -12, four.getHalfSteps());
		assertEquals("Modify by 0 semitones failed", -18, five.getHalfSteps());
		assertEquals("Modify by 0 semitones failed", -18, six.getHalfSteps());
		assertEquals("Modify by 0 semitones failed", -18, seven.getHalfSteps());
	}
	
	/**
	 * Tests the <code>modifyNoteBySemitones(int numberOfSemitones)</code> method.
	 * Tests with an <code>int</code> up and down the same number of semitones.
	 */
	@Test
	public void testModifyNoteBySemitones2 () {
		int oneval = 1;
		int twoval = 2;
		int threeval = 3;
		int fourval = 4;
		int fiveval = 5;
		int sixval = 6;
		int sevenval = 7;
		
		try {
			one.modifyNoteBySemitones(oneval);
			one.modifyNoteBySemitones(-oneval);
		} catch (IllegalArgumentException e) {
			fail("Raised an IllegalArgumentException");
		}
		
		try {
			two.modifyNoteBySemitones(twoval);
			two.modifyNoteBySemitones(-twoval);
		} catch (IllegalArgumentException e) {
			fail("Raised an IllegalArgumentException");
		}
		
		try {
			three.modifyNoteBySemitones(threeval);
			three.modifyNoteBySemitones(-threeval);
		} catch (IllegalArgumentException e) {
			fail("Raised an IllegalArgumentException");
		}
		
		try {
			four.modifyNoteBySemitones(fourval);
			four.modifyNoteBySemitones(-fourval);
		} catch (IllegalArgumentException e) {
			fail("Raised an IllegalArgumentException");
		}
		
		try {
			five.modifyNoteBySemitones(fiveval);
			five.modifyNoteBySemitones(-fiveval);
		} catch (IllegalArgumentException e) {
			fail("Raised an IllegalArgumentException");
		}
		
		try {
			six.modifyNoteBySemitones(sixval);
			six.modifyNoteBySemitones(-sixval);
		} catch (IllegalArgumentException e) {
			fail("Raised an IllegalArgumentException");
		}
		
		try {
			seven.modifyNoteBySemitones(sevenval);
			seven.modifyNoteBySemitones(-sevenval);
		} catch (IllegalArgumentException e) {
			fail("Raised an IllegalArgumentException");
		}
		
		assertEquals("Modify by equal (up/down) semitones failed", 0, one.getHalfSteps());
		assertEquals("Modify by equal (up/down) semitones failed", 8, two.getHalfSteps());
		assertEquals("Modify by equal (up/down) semitones failed", 12, three.getHalfSteps());
		assertEquals("Modify by equal (up/down) semitones failed", -12, four.getHalfSteps());
		assertEquals("Modify by equal (up/down) semitones failed", -18, five.getHalfSteps());
		assertEquals("Modify by equal (up/down) semitones failed", -18, six.getHalfSteps());
		assertEquals("Modify by equal (up/down) semitones failed", -18, seven.getHalfSteps());
	}
	
	/**
	 * Tests the <code>modifyNoteBySemitones(int numberOfSemitones)</code> method.
	 * Tests with the same <code>int</code> for all tests.
	 */
	@Test
	public void testModifyNoteBySemitones3 () {
		int val = 6;
		
		try {
			one.modifyNoteBySemitones(val);
		} catch (IllegalArgumentException e) {
			fail("Raised an IllegalArgumentException");
		}
		
		try {
		two.modifyNoteBySemitones(val);
		} catch (IllegalArgumentException e) {
			fail("Raised an IllegalArgumentException");
		}
		
		try {
			three.modifyNoteBySemitones(val);
		} catch (IllegalArgumentException e) {
			fail("Raised an IllegalArgumentException");
		}
		
		try {
			four.modifyNoteBySemitones(val);
		} catch (IllegalArgumentException e) {
			fail("Raised an IllegalArgumentException");
		}
		
		try {
			five.modifyNoteBySemitones(val);
		} catch (IllegalArgumentException e) {
			fail("Raised an IllegalArgumentException");
		}
		
		try {
			six.modifyNoteBySemitones(val);
		} catch (IllegalArgumentException e) {
			fail("Raised an IllegalArgumentException");
		}
		
		try {
			seven.modifyNoteBySemitones(val);
		} catch (IllegalArgumentException e) {
			fail("Raised an IllegalArgumentException");
		}
		
		assertEquals("Modify by constant (" + val + ") semitones failed", 0 + val, one.getHalfSteps());
		assertEquals("Modify by constant (" + val + ") semitones failed", 8 + val, two.getHalfSteps());
		assertEquals("Modify by constant (" + val + ") semitones failed", 12 + val, three.getHalfSteps());
		assertEquals("Modify by constant (" + val + ") semitones failed", -12 + val, four.getHalfSteps());
		assertEquals("Modify by constant (" + val + ") semitones failed", -18 + val, five.getHalfSteps());
		assertEquals("Modify by constant (" + val + ") semitones failed", -18 + val, six.getHalfSteps());
		assertEquals("Modify by constant (" + val + ") semitones failed", -18 + val, seven.getHalfSteps());
	}
	
	/* END Test modifyNoteBySemitones(int numberOfSemitones) */
	
	
	/* Test formOctave() */
	
	/**
	 * Tests the <code>formOctave(Note n)</code> method.
	 */
	@Test
	public void testFormOctave () {
		assertEquals("Forms octave not expected", false, one.formOctave(one));
		assertEquals("Forms octave not expected", false, one.formOctave(two));
		assertEquals("Forms octave not expected", true, one.formOctave(three));
		assertEquals("Forms octave not expected", true, one.formOctave(four));
		assertEquals("Forms octave not expected", false, one.formOctave(five));
		assertEquals("Forms octave not expected", false, one.formOctave(six));
		assertEquals("Forms octave not expected", false, one.formOctave(seven));
	}
	
	/* END Test formOctave() */
	
	
	/* Test equals(Object o) */
	
	/**
	 * Tests the <code>equals(Object o)</code> method.
	 * Tests for reflexive equality.
	 */
	@Test
	public void testEquals1 () { 
	    assertEquals("Equals (reflaxive) not expected", true, one.equals(one));
	    assertEquals("Equals (reflaxive) not expected", true, two.equals(two));
	    assertEquals("Equals (reflaxive) not expected", true, three.equals(three));
	    assertEquals("Equals (reflaxive) not expected", true, four.equals(four));
	    assertEquals("Equals (reflaxive) not expected", true, five.equals(five));
	    assertEquals("Equals (reflaxive) not expected", true, six.equals(six));
	    assertEquals("Equals (reflaxive) not expected", true, seven.equals(seven));
	}
	
	/**
	 * Tests the <code>equals(Object o)</code> method.
	 * Tests for symmetric equality.
	 */
	@Test
	public void testEquals2 () { 
	    assertEquals("Equals (symmetric) not expected", false, one.equals(two));
	    assertEquals("Equals (symmetric) not expected", false, two.equals(one));
	    
	    assertEquals("Equals (symmetric) not expected", false, three.equals(four));
	    assertEquals("Equals (symmetric) not expected", false, four.equals(three));
	    
	    assertEquals("Equals (symmetric) not expected", true, five.equals(six));
	    assertEquals("Equals (symmetric) not expected", true, six.equals(five));
	}
	
	/**
	 * Tests the <code>equals(Object o)</code> method.
	 * Tests for transitive equality.
	 */
	@Test
	public void testEquals3 () { 
	    assertEquals("Equals (transitive) not expected", true, five.equals(six));
	    assertEquals("Equals (transitive) not expected", true, six.equals(seven));
	    assertEquals("Equals (transitive) not expected", true, five.equals(seven));
	}
	
	/**
	 * Tests the <code>equals(Object o)</code> method.
	 * Tests for equality (or lack thereof) to <code>null</code>.
	 */
	@Test
	public void testEquals4 () { 
	    assertEquals("Equals (null test) not expected", false, one.equals(null));
	    assertEquals("Equals (null test) not expected", false, two.equals(null));
	    assertEquals("Equals (null test) not expected", false, three.equals(null));
	    assertEquals("Equals (null test) not expected", false, four.equals(null));
	    assertEquals("Equals (null test) not expected", false, five.equals(null));
	    assertEquals("Equals (null test) not expected", false, six.equals(null));
	    assertEquals("Equals (null test) not expected", false, seven.equals(null));
	}
	
	/* END Test equals(Object o) */
	
	
	/* Test compareTo(Object o) */
	
	/**
	 * Tests the <code>compareTo(Object o)</code> method.
	 * Tests how the notes are ordered.
	 */
	@Test
	public void testCompareTo1 () { 
		assertEquals("Compare to (simple) not expected", -1, one.compareTo(two));
		assertEquals("Compare to (simple) not expected", -1, two.compareTo(three));
		assertEquals("Compare to (simple) not expected", 1, three.compareTo(four));
		assertEquals("Compare to (simple) not expected", 1, four.compareTo(five));
		assertEquals("Compare to (simple) not expected", 0, five.compareTo(six));
		assertEquals("Compare to (simple) not expected", 0, six.compareTo(seven));
	}
	
	/**
	 * Tests the <code>compareTo(Object o)</code> method.
	 * Tests if <code>compareTo(Object o)</code> is reflexive.
	 */
	@Test
	public void testCompareTo2 () { 
		assertEquals("Compare to (reflexive) not expected", 0, one.compareTo(one));
		assertEquals("Compare to (reflexive) not expected", 0, two.compareTo(two));
		assertEquals("Compare to (reflexive) not expected", 0, three.compareTo(three));
		assertEquals("Compare to (reflexive) not expected", 0, four.compareTo(four));
		assertEquals("Compare to (reflexive) not expected", 0, five.compareTo(five));
		assertEquals("Compare to (reflexive) not expected", 0, six.compareTo(six));
		assertEquals("Compare to (reflexive) not expected", 0, seven.compareTo(seven));
	}
	
	/**
	 * Tests the <code>compareTo(Object o)</code> method.
	 * Tests for congruence between <code>equals(Object o)</code> and <code>compareTo(Object o)</code>.
	 */
	@Test
	public void testCompareTo3 () {
		assertEquals("Compare to congruence to equals (not transitive) not expected", one.equals(two), one.compareTo(two) == 0);
		assertEquals("Compare to congruence to equals (not transitive) not expected", two.equals(three), two.compareTo(three) == 0);
		assertEquals("Compare to congruence to equals (not transitive) not expected", three.equals(four), three.compareTo(four) == 0);
		assertEquals("Compare to congruence to equals (not transitive) not expected", four.equals(five), four.compareTo(five) == 0);
		assertEquals("Compare to congruence to equals (not transitive) not expected", five.equals(six), five.compareTo(six) == 0);
		assertEquals("Compare to congruence to equals (not transitive) not expected", six.equals(seven), six.compareTo(seven) == 0);
	}
	
	/**
	 * Tests the <code>compareTo(Object o)</code> method.
	 * Tests for congruence between <code>equals(Object o)</code> and <code>compareTo(Object o)</code>
	 * in transitive relationships.
	 */
	@Test
	public void testCompareTo4 () {
	    assertEquals("Compare to congruence to equals (transitive) not expected", five.equals(six), five.compareTo(six) == 0);
	    assertEquals("Compare to congruence to equals (transitive) not expected", six.equals(seven), six.compareTo(seven) == 0);
	    assertEquals("Compare to congruence to equals (transitive) not expected", five.equals(seven), five.compareTo(seven) == 0);
	}
	
	/* END Test compareTo(Object o) */
	
	
	/* Exception Test Constructor Note(Note n) */
	
	/**
	 * Exception tests the <code>Note(Note n)</code> constructor.
	 * Tests for a <code>NullPointerException</code>.
	 */
	@Test
	public void testExceptionConstructor1 () {
		try {
			new Note((Note)null);
	        fail("Should raise a NullPointerException");
	    } catch (NullPointerException e) {}
	}
	
	/* END Exception Test Constructor Note(Note n) */
	
	
	/* Exception Test Constructor Note(double frequency) */
	
	/**
	 * Exception tests the <code>Note(double frequency)</code> constructor.
	 * Tests for an <code>IllegalArgumentException</code>.
	 */
	@Test
	public void testExceptionConstructor2 () {
		try {
			//a frequency cannot be negative
			new Note(-1.1);
	        fail("Should raise an IllegalArgumentException");
	    } catch (IllegalArgumentException e) {}
	    
	    try {
	    	//15000 is out of the acceptable range
			new Note(15000.0);
	        fail("Should raise an IllegalArgumentException");
	    } catch (IllegalArgumentException e) {}
	    
	    try {
	    	//9000 is not close enough to an actual frequency to be rounded
			new Note(9000.0);
	        fail("Should raise an IllegalArgumentException");
	    } catch (IllegalArgumentException e) {}
	}
	
	/* END Exception Test Constructor Note(double frequency) */
	
	
	/* Exception Test Constructor Note(int semitones) */
	
	/**
	 * Exception tests the <code>Note(int semitones)</code> constructor.
	 * Tests for an <code>IllegalArgumentException</code>.
	 */
	@Test
	public void testExceptionConstructor3 () {
		try {
			//200 is out of the acceptable range
			new Note(200);
	        fail("Should raise an IllegalArgumentException");
	    } catch (IllegalArgumentException e) {}
	    
	    try {
	    	//-80 is out of the acceptable range
			new Note(-80);
	        fail("Should raise an IllegalArgumentException");
	    } catch (IllegalArgumentException e) {}
	    
	    try {
	    	//59 is out of the acceptable range
			new Note(59);
	        fail("Should raise an IllegalArgumentException");
	    } catch (IllegalArgumentException e) {}
	}
	
	/* END Exception Test Constructor Note(int semitones) */
	
	
	/* Exception Test Constructor Note(String strNote) */
	
	/**
	 * Exception tests the <code>Note(String strNote)</code> constructor.
	 * Tests for an <code>IllegalArgumentException</code>.
	 */
	@Test
	public void testExceptionConstructor4 () {
		//all note strings here are invalid
		
	    try {
			new Note("X");
	        fail("Should raise an IllegalArgumentException");
	    } catch (IllegalArgumentException e) {}
	    
	    try {
			new Note("#4");
	        fail("Should raise an IllegalArgumentException");
	    } catch (IllegalArgumentException e) {}
	    
	    try {
			new Note("h4");
	        fail("Should raise an IllegalArgumentException");
	    } catch (IllegalArgumentException e) {}
	    
	    try {
			new Note("A10");
	        fail("Should raise an IllegalArgumentException");
	    } catch (IllegalArgumentException e) {}
	    
	    try {
			new Note("D-2");
	        fail("Should raise an IllegalArgumentException");
	    } catch (IllegalArgumentException e) {}
	}
	
	/* END Exception Test Constructor Note(String strNote) */
	
	
	/* Exception Test modifyNoteBySemitones(int numberOfSemitones)*/
	
	/**
	 * Exception tests the <code>modifyNoteBySemitones(int numberOfSemitones)</code> method.
	 * Tests for an <code>IllegalArgumentException</code>.
	 */
	@Test
	public void testExceptionModifyNoteBySemitones () {
	    //all integers here are out of range
		
		try {
			one.modifyNoteBySemitones(-80);
	        fail("Should raise an IllegalArgumentException");
	    } catch (IllegalArgumentException e) {}
	    
	    try {
			two.modifyNoteBySemitones(60);
	        fail("Should raise an IllegalArgumentException");
	    } catch (IllegalArgumentException e) {}
	    
	    try {
			three.modifyNoteBySemitones(50);
	        fail("Should raise an IllegalArgumentException");
	    } catch (IllegalArgumentException e) {}
	    
	    try {
			four.modifyNoteBySemitones(-60);
	        fail("Should raise an IllegalArgumentException");
	    } catch (IllegalArgumentException e) {}
	    
	    try {
			five.modifyNoteBySemitones(-80);
	        fail("Should raise an IllegalArgumentException");
	    } catch (IllegalArgumentException e) {}
	}
	
	/* END Exception Test modifyNoteBySemitones(int numberOfSemitones)*/
	
	
	/* Exception Test compareTo(Object o) */
	
	/**
	 * Exception tests the <code>compareTo(Object o)</code> method.
	 * Tests for a <code>NullPointerException</code>.
	 */
	@Test
	public void testExceptionCompareTo1 () {
		try {
			one.compareTo(null);
		    fail("Should raise a NullPointerException");
		} catch (NullPointerException e) {}
		
		try {
			two.compareTo(null);
		    fail("Should raise a NullPointerException");
		} catch (NullPointerException e) {}
		
		try {
			three.compareTo(null);
		    fail("Should raise a NullPointerException");
		} catch (NullPointerException e) {}
		
		try {
			four.compareTo(null);
		    fail("Should raise a NullPointerException");
		} catch (NullPointerException e) {}
		
		try {
			five.compareTo(null);
		    fail("Should raise a NullPointerException");
		} catch (NullPointerException e) {}
	}
	
	/**
	 * Exception tests the <code>compareTo(Object o)</code> method.
	 * Tests for an <code>IllegalArgumentException</code>.
	 */
	@Test
	public void testExceptionCompareTo2 () {
		try {
			one.compareTo("one");
		    fail("Should raise an IllegalArgumentException");
		} catch (IllegalArgumentException e) {}
		
		try {
			one.compareTo(new Integer(3));
		    fail("Should raise an IllegalArgumentException");
		} catch (IllegalArgumentException e) {}
		
		try {
			one.compareTo(new Object());
		    fail("Should raise an IllegalArgumentException");
		} catch (IllegalArgumentException e) {}
		
		try {
			one.compareTo(new Double(1.1));
		    fail("Should raise an IllegalArgumentException");
		} catch (IllegalArgumentException e) {}
		
		try {
			one.compareTo(new Character('s'));
		    fail("Should raise an IllegalArgumentException");
		} catch (IllegalArgumentException e) {}
	}
	
	/* END Exception Test compareTo(Object o) */
	
	
	/* Exception Test Note.parseNoteString(String str) */
	
	/**
	 * Exception tests the <code>Note.parseNoteString(String str)</code> pseudo-constructor.
	 * Tests for an <code>IllegalArgumentException</code>.
	 */
	@Test
	public void testParseNoteString () {
		try {
			//200 is out of the acceptable range
			Note.parseNoteString("200");
	        fail("Should raise an IllegalArgumentException");
	    } catch (IllegalArgumentException e) {}
	    
	    try {
	    	//-80 is out of the acceptable range
			Note.parseNoteString("-80");
	        fail("Should raise an IllegalArgumentException");
	    } catch (IllegalArgumentException e) {}
	    
	    try {
			Note.parseNoteString("X");
	        fail("Should raise an IllegalArgumentException");
	    } catch (IllegalArgumentException e) {}
	    
	    try {
			Note.parseNoteString("#4");
	        fail("Should raise an IllegalArgumentException");
	    } catch (IllegalArgumentException e) {}
	    
		try {
			//a frequency cannot be negative
			Note.parseNoteString("-1.1");
	        fail("Should raise an IllegalArgumentException");
	    } catch (IllegalArgumentException e) {}
	    
	    try {
	    	//15000 is out of the acceptable range
			Note.parseNoteString("15000.0");
	        fail("Should raise an IllegalArgumentException");
	    } catch (IllegalArgumentException e) {}
	}
	
	/* END Exception Test Note.parseNoteString(String str) */
	
}

