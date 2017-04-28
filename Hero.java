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
    final int SPEED = 5;
    
    private boolean isWalking = false;
    private boolean faceLeft = false;

    public Hero(int x, int y)
    {
        super(x, y);
        
        anim_walking = new Animation("WerewolfWalk/WerewolfWalking_%05d.png", 7);
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
        
        actualX = world.getCameraX() + 500;
        actualY = world.getCameraY() + 300;
        
        if (Greenfoot.isKeyDown("d"))
        {
            ((CameraWorld)getWorld()).moveX(SPEED);
            isWalking = true;
            faceLeft = false;
        }
        else if (Greenfoot.isKeyDown("a")) 
        {
            ((CameraWorld)getWorld()).moveX(-SPEED);
            isWalking = true;
            faceLeft = true;
        }
        else
        {
            isWalking = false;
        }
        
        if (isWalking)
        {
            setAnimation(anim_walking);
        }
        else
        {
            setAnimation(anim_idle);
        }

        getAnimation().setFlipped(faceLeft);
    }    
    
    
    public int getSpeed()
    {
        return SPEED;
    }
}
