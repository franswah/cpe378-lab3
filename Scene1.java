import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Scene1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Scene1 extends CameraWorld
{
                
    /**
     * Constructor for objects of class Scene1.
     * 
     */
    public Scene1()
    {
        super();
    }
    
    protected void prepare() {
        Hero hero = new Hero(276, 196);
        addObject(hero,0,0);
        Enemy aligatorMan = new Alligator(30, getHeight() - 10);
        addObject(aligatorMan, 0,0);
    }
}
