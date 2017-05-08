import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Slash here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Slash extends Projectile
{
    BattleActor attacker;
    Animation anim_slash;
    
    public Slash(int vX, int vY, BattleActor attacker)
    {
        super(vX, vY, 15);
        
        this.attacker = attacker;
        anim_slash = new Animation("slash.png", 0);
        
    }
    
    /**
     * Act - do whatever the Slash wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
       setAnimation(anim_slash.setFlipped(vX < 0));
       List<Enemy> enemies = getIntersectingObjects(Enemy.class);
       
       for (Enemy enemy : enemies)
       {
           if (attacker != null)
            enemy.beAttacked(attacker);
       }
       
       if (enemies.size() > 0)
       {
           
           //getWorld().removeObject(this);
       }
       else 
       {
           super.act();
       
       }
    }    
}
