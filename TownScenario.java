import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TownScenario here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TownScenario extends CameraWorld
{

    /**
     * Constructor for objects of class TownScenario.
     * 
     */
    public TownScenario()
    {
        super(2000, 600);
       
        
        
    }
    
    @Override
    public void prepare() {
        addObject(new DarkMountain(), 400,300);
        addObject(new DarkMountainFlipped(), 1400, 300);
        
        addObject(new Town(), 500, 300);
        addObject(new Town(), 1400, 300);

        
        insertGround(0, getWidth(), 600);
        
        setBackground("images/sky.jpg");
        
        addObject(new Hero(), 300, 500);
        
        addObject(new Villager(), 600, 500);
        addObject(new Villager(false), 900, 500);
        addObject(new Villager(), 1600, 500);
        addObject(new Villager(false), 1900, 500);
        addObject(new Villager(), 1700, 500);
        addObject(new Villager(), 1100, 500);
        
        addObject(new EvilWerewolf(), 300, 500);
        addObject(new EvilWerewolf(), 800, 500);
        addObject(new EvilWerewolf(), 1000, 500);
        addObject(new EvilWerewolf(), 1800, 500);
    }
    
    private void insertGround(int start, int end, int height)
    {
        GreenfootImage block = new Ground().getImage();

        for (int i = start; i < end; i+= block.getWidth())
        {
            addObject(new Dirt(), i, height);
        }
    }
}
