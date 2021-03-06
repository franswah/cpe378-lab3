import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StaticActor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StaticActor extends AnimatedActor
{
    private int initialX;
    private int initialY;
    
    public StaticActor() {
        super();
    }
        

    /**
     * Act - do whatever the StaticActor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
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
