import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MovingActor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class MovingActor extends AnimatedActor
{
     public MovingActor() {
        super();
    }
    
    private final float G = 9.8f;

    protected boolean isMoving = false;
    
    private int vX = 0;
    private int vY = 0;
    
    /**
     * Act - do whatever the MovingActor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();

        setLocation(getX() + vX, getY() + vY);
        
        Actor ground = getOneIntersectingObject(Ground.class);
        
        if (ground != null)
        {
            
        }

        if (vX != 0) isMoving = true;
        else isMoving = false;
    }
    
    
    
    public int getVX()
    {
        return vX;
    }
    
    public int getVY()
    {
        return vY;
    }

    public void setVX(int vX)
    {
        this.vX = vX;
    }

    public void setVY(int vY)
    {
        this.vY = vY;
    }
    
    public abstract int getSpeed();
}
