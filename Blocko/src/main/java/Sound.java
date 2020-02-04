

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.sound.sampled.*;


public class Sound {
    private Clip clip;
    FloatControl gain;
                
    
    public Sound(String fileName) {
       
        try {
            File file = new File(fileName);
            if (file.exists()) {
                AudioInputStream sound = AudioSystem.getAudioInputStream(file);
                clip = AudioSystem.getClip();
                clip.open(sound);
                gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
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
    
    public void changeVolume(int i){
        //if i==1, then decrease volume
        if(i==1 && gain.getValue() > (-78.0) )
        {
            gain.setValue(gain.getValue()-2.0f); // Reduce volume by 2 decibels.
            System.out.println(gain.getValue());
        }
        else if(i==2 && gain.getValue() < 4.0)
        {
            gain.setValue(gain.getValue()+2.0f); // Increase volume by 2 decibels.
            System.out.println(gain.getValue());
        }
    }
    
    
}