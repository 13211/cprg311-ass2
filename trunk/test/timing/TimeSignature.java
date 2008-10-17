/**
 * @author Alex Peterson
 * @version 2008OC02
 * 
 * This code is available under the terms of the GNU General Public License v3.0
 * (http://www.gnu.org/licenses/gpl-3.0.txt)
 * 
 * The content is licenced under the Creative Commons Attribution + ShareAlike 2.5 [BY-SA] (Canada)
 * (http://creativecommons.org/licenses/by-sa/2.5/ca)
 */

package timing;

public class TimeSignature {
	
	private final int beats;
	private final int value;
	
	public TimeSignature (int beats, int value) {
		this.beats = beats;
		this.value = value;
	}

	
	public int getBeats () {
		return this.beats;
	}

	
	public int getValue () {
		return this.value;
	}
	
	
}
