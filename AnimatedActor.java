import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class AnimatedActor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class AnimatedActor extends Actor
{
    Animation animation;  

    protected boolean faceLeft = false;

    private int offsetX = 0;


    public AnimatedActor() {
        super();
    }
    
    /**
     * Act - do whatever the AnimatedActor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
        if (animation != null)
        {
            setImage(animation.getNextFrame());
        }

        
    }
    
    public void setAnimation(Animation anim)
    {
        if (animation != null)
        {
            if (faceLeft)
            {
                setLocation(getX() + this.animation.offsetX, getY());
            }
            else
            {
                setLocation(getX() - this.animation.offsetX, getY());
            }
        }
        
        this.animation = anim;
        if (faceLeft)
        {
            setLocation(getX() - this.animation.offsetX, getY());
        }
        else
        {
            setLocation(getX() + this.animation.offsetX, getY());
        }
    }
    
    public Animation getAnimation()
    {
        return animation;
    }
    
    public boolean inRangeOf(AnimatedActor actor, int r) 
    {
        return Math.abs(actor.getX() - getX()) <= r;
    }
    
    public Actor getNearest(List<? extends Actor> actors) 
    {
        if (actors.size() == 0) return null;
        
        Actor nearest = actors.get(0);
        int nearestDist = Math.abs(getX() - nearest.getX());
        
        for (Actor actor : actors)
        {
            int dist = Math.abs(getX() - actor.getX());
            if (dist < nearestDist)
            {
                nearest = actor;
                nearestDist = dist;
            }
        }
        
        return nearest;
    }
    
}
