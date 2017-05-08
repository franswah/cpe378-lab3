import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Orb here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Orb extends Projectile
{
    BattleActor attacker;
    
    public Orb(int vX, int vY, BattleActor attacker)
    {
       super(vX, vY, 500); 
       
       this.attacker = attacker;
    }
    /**
     * Act - do whatever the Orb wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
       boolean attacked = false;
       for (Hero hero : getIntersectingObjects(Hero.class))
       {
           if (attacker != null)
            hero.beAttacked(attacker);
           attacked = true;
       }
       
       if (attacked)
       {
           getWorld().removeObject(this);
       }
       else {
          super.act();
       }
       
        
    }    
}
