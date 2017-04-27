import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class AnimatedActor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class AnimatedActor extends CameraActor
{
    public AnimatedActor(int x, int y) {
        super(x, y);
    }
    
    /**
     * Act - do whatever the AnimatedActor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
        setImage(getCurrentAnimationFrame());
    }
    
    /**
     * Filename should include a format string "%0nd" where n is the number of total digits in
     * each filename.
     * 
     * Screw Greenfoot
     * Min inclusive, max exclusive
     */
    public List<GreenfootImage> loadAnimationFrames(String genName, int min, int max)
    {
        List<GreenfootImage> frames = new ArrayList<GreenfootImage>();
        for (int i = min; i < max; i++)
        {
            String fileName = String.format(genName, i);
            GreenfootImage image = new GreenfootImage(fileName);
            int height = image.getHeight();
            
            int width = image.getWidth();
            int ratio = width / 100;
            
            image.scale(100,  Integer.valueOf((image.getHeight() / ratio)));
            frames.add(image);
        }
        
        return frames;
    }
    
    public void mirrorFramesHorizontally(List<GreenfootImage> frames)
    {
        for (GreenfootImage frame : frames)
        {
            frame.mirrorHorizontally();
        }
    }
    
    public abstract GreenfootImage getCurrentAnimationFrame();
}
