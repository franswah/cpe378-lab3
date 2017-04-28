import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class IntroScenario here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IntroScenario extends CameraWorld
{

    /**
     * Constructor for objects of class IntroScenario.
     * 
     */
    public IntroScenario()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super();
    }
    
    protected void prepare() {
        
        addObject(new DarkMountain(), 0,0);
        addObject(new DarkMountainFlipped(), 0, 0);
        
        addObject(new DarkForest(), 0, 0);
        addObject(new DarkForestFlipped(), 0, 0);

        
        setWorldDimensions(0,400,1000,800);
 

        addObject(new Hero(), 200, 200);
        setBackground("images/sky-dark.png");
    }
    
}
