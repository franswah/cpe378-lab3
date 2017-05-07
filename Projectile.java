import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Porjectile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Projectile extends AnimatedActor
{
    protected int vX, vY, duration;
    
    public Projectile(int vX, int vY, int duration)
    {
        super();
        
        this.vX = vX;
        this.vY = vY;
        this.duration = duration;
    }
    
    /**
     * Act - do whatever the Porjectile wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
        if (duration-- <= 0)
        {
            getWorld().removeObject(this);
        }
        else
        {
            setLocation(getX() + vX, getY() + vY);
        }
    }    
}
