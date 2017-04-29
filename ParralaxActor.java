import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Parralax here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
abstract class ParralaxActor extends Actor
{
    private int initialX;
    private int initialY;
    
    ParralaxActor () {
        super();
    }
    /**
     * Act - do whatever the Parralax wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
        
        CameraWorld world = (CameraWorld) getWorld();
        setLocation(initialX - (world.getCameraX() / getRatio()), initialY - (world.getCameraY() / getRatio()));
    }
    
    @Override
    protected void addedToWorld(World world)
    {
        super.addedToWorld(world);
        initialX = getX();
        initialY = getY();
    }
    
    abstract public int getRatio();
}
