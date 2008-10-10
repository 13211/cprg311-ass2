/**
 * @author Alex Peterson
 * @version 2008OC05
 * 
 * This code is available under the terms of the GNU General Public License v3.0
 * (http://www.gnu.org/licenses/gpl-3.0.txt)
 * 
 * The content is licenced under the Creative Commons Attribution + ShareAlike 2.5 [BY-SA] (Canada)
 * (http://creativecommons.org/licenses/by-sa/2.5/ca)
 */

package midi;

/**
 * Represents a musical event which is either a note or a rest.
 * The event has a specified duration that the event should last
 */
public class Event {
	
	/* Fields */
	
	private final EventType type;
	private final int duration;
	private final Note n;
	
	/* END Fields */
	
	
	/* Constructors */
	
	/**
	 * Constructs a <code>Event</code> of a specific type with a <code>Note</code> and duration.
	 * @param type the type of <code>Event</code>
	 * @param duration the duration
	 * @param n the <code>Note</code>
	 * @throws IllegalArgumentException if the constructor is passed a <code>EventType</code> of <code>NOTE</code> and the <code>Note</code> is unspecified (<code>null</code>).
	 */
	public Event (EventType type, int duration, Note n) throws IllegalArgumentException {
		if (type == EventType.NOTE && n == null)
			throw new IllegalArgumentException("You must specify a note with this EventType");
		
		this.type = type;
		this.duration = duration;
		this.n = n;
	}
	
	/**
	 * Constructs a <code>Event</code> from a <code>String</code>.
	 * The <code>String</code> is of the format:
	 * <pre>{noteString | "r"}["-"]</pre>
	 * Where <code>noteString</code> is a valid string for the <code>parseNoteString(String str)</code> of <code>Note</code>.
	 * @see Note#parseNoteString(String)
	 * @param note the <code>String</code> to parse
	 * @throws IllegalArgumentException if the <code>String</code> is of the wrong format
	 */
	public Event (String note) throws IllegalArgumentException {
		String newnote;
		if (note.charAt(note.length() - 1) == '-') {
			duration = 400;
			newnote = note.substring(0, note.length() - 1);
		} else {
			duration = 200;
			newnote = note;
		}
		
		if (newnote.toLowerCase().equals("r")) {
			this.type = EventType.REST;
			this.n = null;
		} else {
			this.type = EventType.NOTE;
			this.n = Note.parseNoteString(newnote);
		}
	}
	
	/* END Constructors */
	
	
	/* Methods */
	
	/**
	 * Plays this note item on an <code>Instrument</code>.
	 * @param i the <code>Instrument</code> to play
	 */
	public void play (Instrument i) {
		if (type == EventType.NOTE)
			i.playNote(n, duration);
		else if (type == EventType.REST)
			i.rest(duration);
	}
	
	/* END Methods */
	
	
	/* Enumerators */
	
	/**
	 * Represents what this <code>EventType</code> encodes.
	 */
	public static enum EventType {
		/**
		 * Represents a musical note.
		 */
		NOTE,
		
		/**
		 * Represents a rest.
		 */
		REST
	}
	
	/* END Enumerators */
	
}
