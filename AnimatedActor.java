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
    Animation animation;

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
        if (animation != null)
        {
            setImage(animation.getNextFrame());
        }

    }
    
    public void setAnimation(Animation anim)
    {
        this.animation = anim;
    }
}
