import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Witch here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Witch extends Enemy
{
    GreenfootImage image;
    
    List<Vector> targets;
    Random rand;
    int attackInterval = 0;

    
    public Witch() {
        image = new GreenfootImage("witch.png");
        image.scale(150,225);
        setImage(image);
        
        targets = new ArrayList<Vector>();
        targets.add(new Vector(100, 100));
        targets.add(new Vector(900, 100));
        targets.add(new Vector(500, 350));
        targets.add(new Vector(100, 200));
        targets.add(new Vector(800, 250));
        targets.add(new Vector(700, 400));
        targets.add(new Vector(500, 450));
        targets.add(new Vector(100, 300));
        targets.add(new Vector(800, 450));
        
        maxSpeed = 3;
        strength = 15;
        health = 350;
        rand = new Random(454857);
    }
    
    /**
     * Act - do whatever the Witch wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (BlockingDialog.paused) {
            return;
        }
        
        super.act();
        
        
        if (target == null || (Math.abs(worldPos.x - target.x) < 20 && Math.abs(worldPos.y - target.y) < 20))
        {
            Vector t = targets.get(rand.nextInt(targets.size()));
            setTarget(t.x, t.y, 15);
        }
        
        if (attackInterval-- < 1)
        {
            attackInterval = rand.nextInt(50) + 50;
            for (Hero hero : getObjectsInRange(800, Hero.class)) {
                
                float x = hero.getX() - getX();
                float y = hero.getY() - getY();
                float hyp = (int)Math.sqrt(x * x + y * y);
                
                float nx = x / hyp;
                float ny = y / hyp;
                
                getWorld().addObject(new Orb((int)(nx * 7),  (int)(ny * 7), this), getX(), getY());
            }
            
        }
        

        
    }  
    
    @Override
    public void beAttacked(BattleActor actor)
    {
        Vector t = targets.get(rand.nextInt(targets.size()));
        worldPos.x = t.x;
        worldPos.y = t.y;
        setTarget(t.x, t.y);
        super.beAttacked(actor);
       
    }
    
    @Override
    protected void fall()
    {
        
    }
    
    @Override
    protected void checkBlocked()
    {
        
    }
}
