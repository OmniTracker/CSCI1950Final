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

<<<<<<< HEAD
//	    Sequencer sequencer = null;
//		try {
//			Sequence sequence = null;
//			try {
//				sequence = MidiSystem.getSequence(new File("resources/sounds/Cantina.mid"));
//			} catch (InvalidMidiDataException | IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			sequencer = MidiSystem.getSequencer();
//			sequencer.open();
//			if (sequencer instanceof Synthesizer) {
//			      Synthesizer synthesizer = (Synthesizer) sequencer;
//			      MidiChannel[] channels = synthesizer.getChannels();
//			      
//			      channels[0].controlChange(10, 0);
//
//			      // gain is a value between 0 and 1 (loudest)
//			      double gain = 0.9D;
////			      for (int i = 0; i < channels.length; i++) {
////			        channels[i].controlChange(7, 0);
////			      }
//			    }
//		    try {
//				sequencer.setSequence(sequence);
//			} catch (InvalidMidiDataException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		} catch (MidiUnavailableException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	    
//	    // Start playing
//	    sequencer.start();
	    
//		Media song = new Media(new File("resources/sounds/test.wav").toURI().toString());
//	    
//	    
//	    MediaPlayer AudioObj = new MediaPlayer(song);
//	    AudioObj.play();
//	    
//	    AudioObj.setBalance(0);
=======
				} catch (MidiUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// Start playing
				sequencer.start();
			}
		};
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(runSound, 0, 50, TimeUnit.SECONDS);
>>>>>>> 583186cb23f9558515972ea4f323a54bc0722351
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
