package finalgame.engineAdditions;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

import finalgame.maingameloop.FinalGameWorld;

public class SoundSystem extends GameSystem{

    // to store current position 
    Long _currentFrame; 
    Clip _clip; 
      
    // current status of clip 
    String _status; 
      
    AudioInputStream _audioInputStream; 
    static String _filePath; 
	
	File _soundFile;
	
	public SoundSystem(FinalGameWorld finalGameWorld) {
		_filePath = "resources/sounds/test.wav";
		
		try {
		_audioInputStream = AudioSystem.getAudioInputStream(new File(_filePath).getAbsoluteFile());

		_clip = AudioSystem.getClip();
		
		_clip.open(_audioInputStream);
		
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		_clip.loop(_clip.LOOP_CONTINUOUSLY);
		_clip.start();
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
