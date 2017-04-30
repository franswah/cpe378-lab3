import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Hero here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hero extends BattleActor
{
    Animation walkingAnimation;
    Animation idleAnimation;
    public final int speed = 7;
    private final int jump = 30;
    private final int attackRange = 150;

    public static final int scrollWidth = 250;
    
    private DialogModal healthDialog;
    
    private boolean faceLeft = false;
    private boolean isJumping = false;
    private boolean isFalling = false;
    private int maxJump = 10;
    private int jumpCount = 0;

    public Hero()
    {        
        walkingAnimation = new Animation("WerewolfWalk/WerewolfWalking_%05d.png", 7);
        idleAnimation = new Animation("WerewolfIdle/WerewolfIdle_%05d.png", 2);
        strength = 10;
        defense = 3;
        attackDelay = 20;
    }
    
    @Override public void addedToWorld(World world) {
        super.addedToWorld(world);
        healthDialog = new DialogModal("Health: " + health);
        world.addObject(healthDialog, healthDialog.getImage().getWidth() / 2, healthDialog.getImage().getHeight() / 2);
    }

    /**
     * Act - do whatever the Hero wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
      
        move();
        jump();
        attack();
        scroll();
    }    
    
    private void move() {
        if (Greenfoot.isKeyDown("d"))
        {
            faceLeft = false;
            setVX(speed);
        }
        else if (Greenfoot.isKeyDown("a")) 
        {
            faceLeft = true;
            setVX(-speed);
        }
        else
        {
            setVX(0);
        }
        
        if (isMoving)
        {
            setAnimation(walkingAnimation);
        }
        else
        {
            setAnimation(idleAnimation);
        }
        
        getAnimation().setFlipped(faceLeft);
    }
    
    private void scroll() {
        CameraWorld world = (CameraWorld)getWorld();
         if(getX() < scrollWidth)
        {
            world.setCameraX(world.getCameraX() - (scrollWidth - getX()));
            setLocation(scrollWidth, getY());
        }
        else if(getX() > world.WINDOW_WIDTH - scrollWidth)
        {
            world.setCameraX(world.getCameraX() + (scrollWidth - (world.WINDOW_WIDTH - getX())));
            setLocation(world.WINDOW_WIDTH - scrollWidth, getY());
        }
    }
  
    private void jump() {
 
        if(Greenfoot.getKey() == "space" && super.isGrounded()) {
            vY = -jump; }

    }
    
    public int getSpeed()
    {
        return speed;
    }
    
    @Override
    public void attack() {
        super.attack();
        if (Greenfoot.isKeyDown("j") && attackFrame == attackDelay) {
            for (BattleActor enemy : getObjectsInRange(attackRange, BattleActor.class)) {
                if (enemy.getX() > getX() && !faceLeft) {
                    enemy.damage(strength);
                }
                else if (enemy.getX() < getX() && faceLeft) {
                    enemy.damage(strength);
                }
                else if (enemy.getX() == getX()) {
                    enemy.damage(strength);
                }
            }
            attackFrame = 0;
        }
    }
    
    @Override
    public void damage(int dmg) {
        health = health - (dmg - defense);
        healthDialog.setText("Health: " + Math.max(health, 0));
        
        // Play damage animation?
      
        if (health <= 0) {
            World wrld = getWorld();
            wrld.addObject(new DialogModal("You died.\nR.I.P. Lukas"), getX(), wrld.getHeight()/2);
            wrld.removeObject(this);
        }
    }
}
