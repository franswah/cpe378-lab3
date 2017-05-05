import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Witch here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Witch extends NPC
{
    GreenfootImage image;
    
    public Witch() {
        image = new GreenfootImage("witch.png");
        image.scale(150,225);
        setImage(image);
    }
    
    /**
     * Act - do whatever the Witch wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
    }    
}
