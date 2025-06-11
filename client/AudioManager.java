package client;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Manages sound effects and background audio.
 * Plays feedback cues like correct guess, timer warning, or round start.
 */

// static class
// Utility for playing sound effects; no instance needed.


public class AudioManager {
    
    private AudioManager() {}

    public static void playMusic(String musicFilePath) {
        File audioFile = new File(musicFilePath); 
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY); // repeats song
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void playSound(String soundFilePath) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(soundFilePath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
