/*
 * Sound.java
 *
 * Created on 2010/3/30, 12:09
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package keshe;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.sound.midi.*;

/**
 *
 * @author 南_2
 */
public class Sound {
        String path=new String("musics\\");
	String  file=new String("一直很安静.mid");
	Sequence seq;
        Sequencer midi;
	boolean sign;
    /** Creates a new instance of Sound */
    public Sound() {
    }
    void loadSound()
	{
		try {
                        seq=MidiSystem.getSequence(new File(path+file));
                        midi=MidiSystem.getSequencer();
                        midi.open();
                        midi.setSequence(seq);
			midi.start();
			midi.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
        }
        catch (Exception ex) {ex.printStackTrace();}
		sign=true;
	}
	void mystop(){midi.stop();midi.close();}
//	boolean isplay(){return sign;}
	void setMusic(String e){file=e;}
}
    
