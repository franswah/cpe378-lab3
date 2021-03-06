import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class BattleActor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class BattleActor extends MovingActor implements Animation.AnimationCompleteListener
{
    public int health = 100;
    protected int strength = 10;
    protected int defense = 1;
    protected int attackFrame = 0;
    protected int attackRange = 75;

    

    protected int DEFAULT_ATTACK_DELAY = 15;
    private int attackTimeout;

    // Used primarily for selecting animation
    public enum Status {IDLE, WALKING, FALLING, ATTACKING, JUMPING, ASCENDING}

    public Status currently = Status.IDLE;

    private HashMap<Status, Animation> animations = new HashMap<Status, Animation>();

    /**
     * Act - do whatever the BattleActor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        checkStatus();
        animate();
        
        //if(getY() == 600) {
        //    getWorld().removeObject(this);
        //}
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
    
    public <T extends BattleActor> void attack(Class<T> type) {
        if (currently != Status.ATTACKING)
        {
            currently = Status.ATTACKING;
            Animation anim = animations.get(Status.ATTACKING);
            anim.reset();
            if (anim != null)
                attackTimeout = anim.getLength();
            else
                attackTimeout = DEFAULT_ATTACK_DELAY;

            executeAttack(type);
        }
    }
    
    public <T extends BattleActor> void executeAttack(Class<T> type)
    {
        for (T enemy : getObjectsInRange(attackRange, type)) {
                if (enemy.getX() > getX() && !faceLeft) {
                    enemy.beAttacked(this);
                }
                else if (enemy.getX() < getX() && faceLeft) {
                    enemy.beAttacked(this);
                }
                else if (enemy.getX() == getX()) {
                    enemy.beAttacked(this);
                }
            }
    }

    public void beAttacked(BattleActor actor)
    {
        damage(actor.strength);
    }

    public void setAnimation(Status status, Animation anim)
    {
        animations.put(status, anim);
    }

    private void checkStatus()
    {   
        if (currently == Status.ATTACKING)
        {
            if (--attackTimeout <= 0) {
                currently = Status.IDLE;
            }
        }
        else if (getVX() > 0) {
            currently = Status.WALKING;
        }
        else if (getVX() < 0) {
            currently = Status.WALKING;
        }
        else {
            currently = Status.IDLE;
        }
    }

    private void animate()
    {
        Animation curAnim = animations.get(currently);
        if (curAnim == null)
        {
            curAnim = animations.get(Status.IDLE);
        }

        if (curAnim != null)
        {
            setAnimation(curAnim.setFlipped(faceLeft));
        }
    }

    @Override
    public void animationCompleted(Animation animation)
    {
        currently = Status.IDLE;
    }
}
