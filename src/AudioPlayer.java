import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.io.File;

public class AudioPlayer {
    
    private Clip clip;
    private AudioInputStream audioInputStream;

    public AudioPlayer(String fileName) {

        //opening a file using try catch statement
        try {
            File fileInput = new File(fileName);
            audioInputStream = AudioSystem.getAudioInputStream(fileInput);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e) {
            System.out.println(fileName + " is not found");
        }
    }

    //a method to play the music
    public void play() {
        if (clip != null) {
            clip.start();
        }
    }

    //a method to loop the music
    public void loop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    //a method to stop the music
    public void stop() {
        if (clip != null) {
            clip.stop();
            clip.setFramePosition(0);
        }
    }

}
