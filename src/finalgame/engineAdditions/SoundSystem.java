package finalgame.engineAdditions;

import java.io.File;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.sampled.*;

import finalgame.maingameloop.FinalGameWorld;

public class SoundSystem extends GameSystem{

    // to store current position 
    Long _currentFrame; 
    Clip _clipMusic; 
      
    // current status of clip 
    String _status; 
    
   
      
    AudioInputStream _audioInputStreamMusic; 
    static String _filePathMusic; 
	
	File _soundFile;
	
	
	public SoundSystem(FinalGameWorld finalGameWorld) {
//		_filePathMusic = "resources/sounds/test.wav";
//		String filePathLzer = "resources/sounds/Laser_Shoot_Long1.wav";
//		Clip lazclip = null;
//		try {
//		_audioInputStreamMusic = AudioSystem.getAudioInputStream(new File(_filePathMusic).getAbsoluteFile());
//
//		_clipMusic = AudioSystem.getClip();
//		
//		_clipMusic.open(_audioInputStreamMusic);
//		
//		FloatControl gainControl = (FloatControl) _clipMusic.getControl(FloatControl.Type.MASTER_GAIN);
//		
//		gainControl.setValue(-20);
//		
//		AudioInputStream lazStream = AudioSystem.getAudioInputStream(new File(filePathLzer).getAbsoluteFile());
//		lazclip = AudioSystem.getClip();
//		lazclip.open(lazStream);
//		
//		FloatControl lzcon = (FloatControl) lazclip.getControl(FloatControl.Type.MASTER_GAIN);
//		
//		lzcon.setValue(-30);
//		
//		}
//		catch(Exception e) {
//			System.out.println(e.getMessage());
//		}
//		
//		_clipMusic.loop(_clipMusic.LOOP_CONTINUOUSLY);
//		_clipMusic.start();
//		lazclip.start();
		
		


	    Sequencer sequencer = null;
		try {
			Sequence sequence = null;
			try {
				sequence = MidiSystem.getSequence(new File("resources/sounds/Cantina.mid"));
			} catch (InvalidMidiDataException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
		    try {
				sequencer.setSequence(sequence);
			} catch (InvalidMidiDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    // Start playing
	    sequencer.start();
	}
	
	@Override
	public void addObject(GameObject go) {
		if(go.hasComponent("SOUND")) {
			this.addObject(go);
		}
	}
	
	public void loadFiles() {
		_soundFile = new File("resources/sounds/test.wav");
	}

}
