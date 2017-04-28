import java.util.*;
import greenfoot.*;

/**
 * Write a description of class Animation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Animation  
{
    private List<GreenfootImage> currentFrames;
    private List<GreenfootImage> frames;
    private List<GreenfootImage> flippedFrames;

    private int numFrames;

    private int delay = 5;

    private int currentFrame = 0;
    private int counter = delay;

    /** 
     * The formattedPath should include a format string "%0nd" where n is the number of total digits in
     * each file for the animation.
     * 
     * maxNum is the max number on the file name inclusive. Frames are assumed numbered 0 to n
     */
    public Animation(String formattedPath, int maxNum)
    {
        this.numFrames = maxNum + 1;

        frames = new ArrayList<GreenfootImage>();
        for (int i = 0; i <= maxNum; i++)
        {
            String fileName = String.format(formattedPath, i);
            frames.add(new GreenfootImage(fileName));
        }

        flippedFrames = new ArrayList<GreenfootImage>();
        for (GreenfootImage frame : frames)
        {
            GreenfootImage flipped = new GreenfootImage(frame);
            flipped.mirrorHorizontally();
            flippedFrames.add(flipped);
        }

        currentFrames = frames;
    }

    public Animation setFlipped(boolean flip)
    {
        if (flip)
        {
            currentFrames = flippedFrames;
        }
        else 
        {
            currentFrames = frames;
        }

        return this;
    }

    public void setDelay(int d)
    {
        this.delay = d;
    }

    /**
      * Advances the current frame depending on counter and speed
     */
    public GreenfootImage getNextFrame()
    {
        if (--counter < 1) 
        {
            counter = delay;
            if (++currentFrame >= numFrames)
            {
                currentFrame = 0;
            }
        }

        return currentFrames.get(currentFrame);
    }

    public GreenfootImage getCurrentFrame()
    {
        return currentFrames.get(currentFrame);
    }
}
