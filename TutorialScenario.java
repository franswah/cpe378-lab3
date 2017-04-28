import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TutorialScenario here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TutorialScenario extends CameraWorld
{

    /**
     * Constructor for objects of class TutorialScenario.
     * 
     */
    public TutorialScenario()
    {
    }
    
    @Override()
    public void prepare() {
        setBackground("images/sky.jpg");
        addObject(new DarkMountain(), 0, 0);
        addObject(new Hero(), 200, 200);
        

    }
}
