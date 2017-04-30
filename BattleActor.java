import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BattleActor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class BattleActor extends MovingActor
{
    protected int health = 100;
    protected int strength = 1;
    protected int defense = 1;
    protected int attackDelay = 10;
    protected int attackFrame = 0;
    
    /**
     * Act - do whatever the BattleActor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
    }
    
    public void damage(int dmg) {
        health = health - (dmg - defense);
        
        // Play damage animation?
        
        if (health <= 0) {
            // Play dying animation?
            getWorld().removeObject(this);
        }
    }
    
    public void heal(int amnt) {
        health = health + amnt;
    }
    
    public void attack() {
        if (attackFrame < attackDelay) {
            attackFrame++;
        }
    }
}
