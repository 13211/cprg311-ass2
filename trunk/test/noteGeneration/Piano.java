package noteGeneration;

import javax.sound.midi.*;

public class Piano {

    public void playNote() {
       Synthesizer synthesizer = null;
       try
       {
          synthesizer = MidiSystem.getSynthesizer();
	    synthesizer.open();
       }
       catch(MidiUnavailableException mue)
       {
       }
       Instrument [] instruments = synthesizer.getDefaultSoundbank().getInstruments();
       synthesizer.loadInstrument(instruments[30]);
       MidiChannel [] channels = synthesizer.getChannels();
       channels[1].noteOn(30, 127);
       try
	 {
	    Thread.sleep(1500);
       }
       catch(InterruptedException ie)
       {
       }
       channels[1].noteOff(30,127);
    }
    public static void main(String [] args)
    {
       Piano p = new Piano();
       p.playNote();
       System.exit(0);
    }
}
