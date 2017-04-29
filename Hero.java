import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Hero here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hero extends MovingActor
{
    Animation walkingAnimation;
    Animation idleAnimation;
    public final int speed = 7;

    public static final int scrollWidth = 250;
    
    private boolean faceLeft = false;
    private boolean isJumping = false;
    private boolean isFalling = false;
    private int maxJump = 10;
    private int jumpCount = 0;

    public Hero()
    {        
        walkingAnimation = new Animation("WerewolfWalk/WerewolfWalking_%05d.png", 7);
        idleAnimation = new Animation("WerewolfIdle/WerewolfIdle_%05d.png", 2);
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
        fall();
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
    
    private void fall() {
        
        if (!super.isGrounded() && !isJumping)
        {   
            setLocation(getX(), getY() + 10);
            
        }
    }
  
    private void jump() {
        
        
        if(Greenfoot.getKey() == "space" && super.isGrounded()) {
            isJumping = true;
        }
        
        if(isJumping) {
            setLocation(getX(), getY() - 15);
            jumpCount++;
            
            if(jumpCount == maxJump) {
                isJumping = false;
                jumpCount = 0;
            }
        }
    }
    
    public int getSpeed()
    {
        return speed;
    }

}
