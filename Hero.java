import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Hero here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hero extends MovingActor
{
    List<GreenfootImage> mL_frames;
    List<GreenfootImage> mR_frames;
    List<GreenfootImage> idleL_frames;
    List<GreenfootImage> idleR_frames;
    List<GreenfootImage> attackL_frames;
    List<GreenfootImage> attackR_frames;
    List<GreenfootImage> upL_frames;
    List<GreenfootImage> upR_frames;
    List<GreenfootImage> downL_frames;
    List<GreenfootImage> downR_frames;
    
    final int ANIMATION_OFFSET = 2;
    final int SPEED = 5;
    
    int currentFrame = 0;
    int offset = ANIMATION_OFFSET;
    
    public Hero()
    {
        super();
        
        mR_frames = loadAnimationFrames("WerewolfWalk/WerewolfWalk_%05d.png", 0, 17);
        mL_frames = loadAnimationFrames("WerewolfWalk/WerewolfWalk_%05d.png", 0, 17);
        mirrorFramesHorizontally(mL_frames);
        
        idleR_frames = loadAnimationFrames("WerewolfIdle/WerewolfIdle_%05d.png", 0, 17);
        idleL_frames = loadAnimationFrames("WerewolfIdle/WerewolfIdle_%05d.png", 0, 17);
        mirrorFramesHorizontally(idleL_frames);
    }
    
    
    /**
     * Act - do whatever the Hero wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
        if (--offset < 0)
        {
            currentFrame++;
            offset = ANIMATION_OFFSET;
        }
        
    }    
    
    public GreenfootImage getCurrentAnimationFrame()
    {
        if (currentFrame >= mL_frames.size())
        {
            currentFrame = 0;
        }
        
        return mR_frames.get(currentFrame);
    }
    
    public int getSpeed()
    {
        return SPEED;
    }
}
