import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Villager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Villager extends Enemy
{
    private int knockedBack = 10;
    Animation anim_running;
    private boolean left = true;
    private static GreenfootSound[] damaged = new GreenfootSound[2];
    
    
    public Villager()
    {
        this(true);
    }
    
    public Villager(boolean left)
    {
        super();
        anim_running = new Animation("VillagerRun/Villager_Running_%05d.png", 7, .4f);
        maxSpeed = 7;
        setImage(anim_running.getCurrentFrame());
        this.left = left;
        
        if (damaged[0] == null) {
            damaged[0] = new GreenfootSound("scream1.mp3");
            damaged[1] = new GreenfootSound("scream2.mp3");
            damaged[0].setVolume(90);
            damaged[1].setVolume(90);
        }
    }
    
    /**
     * Act - do whatever the Villager wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
         if (BlockingDialog.paused) {
            return;
        }
        
        setAnimation(anim_running.setFlipped(faceLeft));
        
        if (left)
        {
            setTarget(0, 0);
            if (worldPos.x < 150) 
            {
                left = false;
            }
        }
        else
        {
            setTarget(getWorld().getWidth(), 0);
            if (worldPos.x >= getWorld().getWidth() - 15) 
            {
                left = true;
            }
        }
        
        super.act();
    }  
    
    @Override
    public void beAttacked(BattleActor actor)
    {
       
        setVY(-knockedBack);
        
        damaged[Greenfoot.getRandomNumber(2)].play();

        super.beAttacked(actor);
    } 
}
