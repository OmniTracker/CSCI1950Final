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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

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
		Runnable runSound = new Runnable() {
			public void run() {
				Sequencer sequencer = null;
				try {
					Sequence sequence = null;
					try {
						sequence = MidiSystem.getSequence(new File("resources/sounds/Avengers.mid"));
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
		};
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
