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

    private static final int SCROLL_WIDTH = 160;
    private int absoluteScroll, initialXPosition, initialYPosition;
    
    private boolean faceLeft = false;

    public Hero()
    {        
        anim_walking = new Animation("WerewolfWalk/WerewolfWalking_%05d.png", 7);
        anim_idle = new Animation("WerewolfIdle/WerewolfIdle_%05d.png", 2);
    }
    
    @Override
    protected void addedToWorld(World world)
    {
        initialXPosition = getX();
        initialYPosition = getY();
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

        world.setCameraX(0);
        world.setCameraY(0);

        // Scrolling
        if((getX() < SCROLL_WIDTH) && (world.getX() < world.getWidth() / 2 - 5))
        {
            world.setCameraX(SCROLL_WIDTH - getX());
            absoluteScroll += SCROLL_WIDTH - getX();
        }
        else if((getX() > world.getWidth() - SCROLL_WIDTH) && (world.getX() > -690))
        {
            world.setCameraX(world.getWidth() - SCROLL_WIDTH - getX());
            absoluteScroll += world.getWidth() - SCROLL_WIDTH - getX();
        }
    }    
    
    
    public int getSpeed()
    {
        return SPEED;
    }
}
