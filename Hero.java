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
    Animation anim_walking;
    Animation anim_idle;
    final int ANIMATION_OFFSET = 2;
    final int SPEED = 5;

    int offset = ANIMATION_OFFSET;

    public Hero(int x, int y)
    {
        super(x, y);
        
        anim_walking = new Animation("WerewolfWalk/WerewolfWalk_%05d.png", 16);
        anim_idle = new Animation("WerewolfIdle/WerewolfIdle_%05d.png", 2);
    }
    
    
    /**
     * Act - do whatever the Hero wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
        
        CameraWorld world = (CameraWorld)getWorld();
        
        if (--offset < 0)
        {
            offset = ANIMATION_OFFSET;
        }
        
        actualX = world.getCameraX() + 500;
        actualY = world.getCameraY() + 300;
        
        if (Greenfoot.isKeyDown("d"))
        {
            ((CameraWorld)getWorld()).moveX(SPEED);
            setAnimation(anim_walking.setFlipped(false));
        }
        else if (Greenfoot.isKeyDown("a")) 
        {
            ((CameraWorld)getWorld()).moveX(-SPEED);
            setAnimation(anim_walking.setFlipped(true));
        }
        else
        {
            setAnimation(anim_idle);
        }
    }    
    
    
    public int getSpeed()
    {
        return SPEED;
    }
}
