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
        addObject(new Grass(25, 575), 0, 0);
        addObject(new Grass(75, 575), 0, 0);
        addObject(new Grass(125, 575), 0, 0);
        addObject(new Grass(175, 575), 0, 0);
        addObject(new Grass(225, 575), 0, 0);
        addObject(new Grass(275, 575), 0, 0);
        addObject(new Grass(325, 575), 0, 0);
        addObject(new Grass(375, 575), 0, 0);
        addObject(new Grass(425, 575), 0, 0);
        addObject(new Grass(475, 575), 0, 0);
        addObject(new Grass(525, 575), 0, 0);
        addObject(new Grass(575, 575), 0, 0);
        addObject(new Grass(625, 575), 0, 0);
        addObject(new Grass(675, 575), 0, 0);
        addObject(new Grass(725, 575), 0, 0);
        addObject(new Grass(775, 575), 0, 0);
        addObject(new Grass(825, 575), 0, 0);
        addObject(new Grass(875, 575), 0, 0);
        addObject(new Grass(925, 575), 0, 0);
        addObject(new Grass(975, 575), 0, 0);
        addObject(new Hero(0, 0), 0, 0);
        setBackground("images/day.jpg");
    }
}
