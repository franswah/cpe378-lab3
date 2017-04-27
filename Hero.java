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
    List<GreenfootImage> m_LFrames;
    List<GreenfootImage> m_RFrames;
    
    final int ANIMATION_OFFSET = 2;
    final int SPEED = 5;
    
    int currentFrame = 0;
    int offset = ANIMATION_OFFSET;
    
    public Hero(int x, int y)
    {
        super(x, y);
        
        m_LFrames = loadAnimationFrames("WerewolfWalk_%05d.png", 0, 17);
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
        if (Greenfoot.isKeyDown("d")){
            ((CameraWorld)getWorld()).moveX(SPEED);
        }
        else if (Greenfoot.isKeyDown("a")) {
            ((CameraWorld)getWorld()).moveX(-SPEED);
        }
    }    
    
    public GreenfootImage getCurrentAnimationFrame()
    {
        if (currentFrame >= m_LFrames.size())
        {
            currentFrame = 0;
        }
        
        return m_LFrames.get(currentFrame);
    }
    
    public int getSpeed()
    {
        return SPEED;
    }
}
