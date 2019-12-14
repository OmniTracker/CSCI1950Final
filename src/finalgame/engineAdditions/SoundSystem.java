package finalgame.engineAdditions;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
		Runnable helloRunnable = new Runnable() {
		    public void run() {
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
		};

		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(helloRunnable, 0, 3, TimeUnit.MINUTES);
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
