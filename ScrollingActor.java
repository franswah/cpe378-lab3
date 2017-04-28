import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CameraActor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class ScrollingActor extends Actor
{   
    public ScrollingActor() {
        super();
    }

    public void act() 
    {
        super.act();
        CameraWorld world = (CameraWorld) getWorld();
        setLocation(getX() + world.getCameraX(), getY() + world.getCameraY());
    }
}
