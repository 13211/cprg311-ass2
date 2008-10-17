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

public class TimeContext {
	
	private TimeSignature timeSignature;
	private int bpm;
	
	public TimeContext (TimeSignature timeSignature, int bpm) {
		this.timeSignature = timeSignature;
		this.bpm = bpm;
	}

	
	public TimeSignature getTimeSignature () {
		return timeSignature;
	}

	
	public int getBpm () {
		return bpm;
	}
	
}
