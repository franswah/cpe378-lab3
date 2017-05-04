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
        super(25000, 600);
    }
    
    protected void prepare() {
        
        addObject(new DarkMountain(), 400,300);
        addObject(new DarkMountainFlipped(), 1400, 300);
        
        addObject(new DarkForest(), 400, 350);
        addObject(new DarkForestFlipped(), 1400, 350);
        addObject(new DarkForest(), 2400, 350);
 
        
        DialogModal modal = new DialogModal("Hello");
        
        addObject(modal, 400, 400);
        
        
        addObject(new Hero(), 400, 0);
        addObject(new EvilWerewolf(), 1000, 0);
        setBackground("images/sky-dark.png");

        insertGround(0, getWidth(), 600);
        insertGround(400, getWidth() - 400, 500);
        
        
        
        // Plays music the entire time this scene is running. Uncomment if you want to hear the same minute looped over and over.
        //GreenfootSound loop = new GreenfootSound("noGuitar.wav");
        //loop.playLoop();
    }

    private void insertGround(int start, int end, int height)
    {
        GreenfootImage block = new Ground().getImage();

        for (int i = start; i < end; i+= block.getWidth())
        {
            addObject(new Ground(), i, height);
        }
    }
    
}
