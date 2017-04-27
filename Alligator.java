import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Alligator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Alligator extends Enemy
{
     public Alligator(int x, int y) {
        super(x, y);
    }
    
    /**
     * Act - do whatever the Alligator wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
    }    
    public int getSpeed() {
        return 1;
    }
    
    public GreenfootImage getCurrentAnimationFrame() {
        return getImage();
    }
}
