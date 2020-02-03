

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.sound.sampled.*;


public class Sound {
    private Clip clip;
    public Sound(String fileName) {
       
        try {
            File file = new File(fileName);
            if (file.exists()) {
                AudioInputStream sound = AudioSystem.getAudioInputStream(file);
                clip = AudioSystem.getClip();
                clip.open(sound);
            }
            else {
                throw new RuntimeException("Sound: file not found: " + fileName);
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Malformed URL: " + e);
        }
        catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Unsupported Audio File: " + e);
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Input/Output Error: " + e);
        }
        catch (LineUnavailableException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Line Unavailable Exception Error: " + e);
        }

    }
    
    //Plays the clip from the beginning
    public void play(){
        clip.setFramePosition(0);
        clip.start();
    }
    
    //Enables the clip to loop continuously
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    //Stops the clip
    public void stop(){
            clip.stop();
        }
    }