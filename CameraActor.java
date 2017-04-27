import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CameraActor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class CameraActor extends Actor
{
    protected int actualX;
    protected int actualY;
    int clock = 0;
    
    public CameraActor(int x, int y) {
        actualX = x;
        actualY = y;
    }
    
    /**
     * Act - do whatever the CameraActor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
        if(clock == getSpeed()) {
            CameraWorld camWorld = (CameraWorld)getWorld();
            setLocation(actualX - (camWorld.getCameraX() / getSpeed()), actualY - camWorld.getCameraY());
            clock = 1;
        } else {
            clock++;
        }
    }
    
    abstract public int getSpeed();
}
