import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CameraActor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class ScrollingActor extends AnimatedActor
{   
    private int initialX;
    private int initialY;

    public ScrollingActor() {
        super();
    }

    public void act() 
    {
        super.act();
        CameraWorld world = (CameraWorld) getWorld();
        setLocation(initialX - world.getCameraX(), initialY - world.getCameraY());
    }

    @Override
    protected void addedToWorld(World world)
    {
        super.addedToWorld(world);
        initialX = getX();
        initialY = getY();
    }
}
