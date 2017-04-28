import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MainScenario extends CameraWorld
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MainScenario()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(2000, 600); 
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    protected void prepare()
    {
        Hero hero = new Hero();
        addObject(hero,200,200);

        insertGround(0, getWidth());
    }

    private void insertGround(int start, int end)
    {
        GreenfootImage block = new Ground().getImage();

        for (int i = start; i < end; i+= block.getWidth())
        {
            addObject(new Ground(), i, 400);
        }
    }
}
