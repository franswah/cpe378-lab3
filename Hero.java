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
    final int SPEED = 7;

    private static final int SCROLL_WIDTH = 250;
    
    private boolean faceLeft = false;

    public Hero()
    {        
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
        
        
        if (Greenfoot.isKeyDown("d"))
        {
            faceLeft = false;
            setVX(SPEED);
        }
        else if (Greenfoot.isKeyDown("a")) 
        {
            faceLeft = true;
            setVX(-SPEED);
        }
        else
        {
            setVX(0);
        }
        
        if (isMoving)
        {
            setAnimation(anim_walking);
        }
        else
        {
            setAnimation(anim_idle);
        }

        getAnimation().setFlipped(faceLeft);

        // Scrolling
        if(getX() < SCROLL_WIDTH)
        {
            world.setCameraX(world.getCameraX() - (SCROLL_WIDTH - getX()));
            setLocation(SCROLL_WIDTH, getY());
        }
        else if(getX() > world.WINDOW_WIDTH - SCROLL_WIDTH)
        {
            world.setCameraX(world.getCameraX() + (SCROLL_WIDTH - (world.WINDOW_WIDTH - getX())));
            setLocation(world.WINDOW_WIDTH - SCROLL_WIDTH, getY());
        }
    }    
    
    
    public int getSpeed()
    {
        return SPEED;
    }
}
