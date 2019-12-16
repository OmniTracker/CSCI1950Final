package finalgame.engineAdditions;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Synthesizer;
import javax.sound.sampled.*;

import finalgame.maingameloop.FinalGameWorld;
import finalgame.maingameloop.gameworldmanager.MainGamePlay;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class SoundSystem extends GameSystem{
		
	private MediaPlayer lzrPlayer;
	private MainGamePlay _game;
	
	public SoundSystem(MainGamePlay game) {
		
		/* 
		_game=game;
		//final JFXPanel fxPanel = new JFXPanel();
		this.loadPlayers();
		String _filePath = "./resources/sounds/test.wav";
		Clip _clip = null;
		try {
			AudioInputStream _audioInputStream = AudioSystem.getAudioInputStream(new File(_filePath).getAbsoluteFile());

			_clip = AudioSystem.getClip();
		
			_clip.open(_audioInputStream);
		
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		FloatControl volume = (FloatControl)_clip.getControl(FloatControl.Type.MASTER_GAIN);
		volume.setValue(-20);
		
		
		_clip.loop(Clip.LOOP_CONTINUOUSLY);
		// _clip.start();

*/ 
		
		
		
	}
	@Override
	public void addObject(GameObject go) {
		if(go.hasComponent("SOUND")) {
			_objects.add(go);
		}
	}
	public void loadPlayers() {
		Media lzr = new Media(new File("resources/sounds/Punch.wav").toURI().toString());
		setLzrPlayer(new MediaPlayer(lzr));
	}
	public MediaPlayer getLzrPlayer() {
		return lzrPlayer;
	}
	public void setLzrPlayer(MediaPlayer lzrPlayer) {
		this.lzrPlayer = lzrPlayer;
	}
	
	
	
	
	

}
