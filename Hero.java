import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Hero here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hero extends BattleActor implements Animation.AnimationCompleteListener
{
    Animation walkingAnimation;
    Animation idleAnimation;
    Animation attackAnimation;
    public final int speed = 7;
    private final int jump = 30;

    public static final int scrollWidth = 250;
    
    private DialogModal healthDialog;
    
    
    

    private int maxJump = 10;
    private int jumpCount = 0;

    private enum Status {IDLE, WALKING, FALLING, ATTACKING, JUMPING, ASCENDING}

    private Status currently = Status.IDLE;

    public Hero()
    {        
        walkingAnimation = new Animation("WerewolfWalk/WerewolfWalking_%05d.png", 7);
        idleAnimation = new Animation("WerewolfIdle/WerewolfIdle_%05d.png", 2);
        attackAnimation = new Animation("WerewolfAttack/Werewolf_ClawLeft_%05d.png", 4);

        attackAnimation.setAnimationCompleteListener(this);
        
        strength = 10;
        defense = 3;
        attackDelay = 20;
        
        scrolls = false;
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
        checkAttack();
        scroll();
        animate();
    }    
    
    private void move() {
        if (Greenfoot.isKeyDown("d"))
        {
            setVX(speed);
            if (currently != Status.ATTACKING && currently != Status.JUMPING) 
            {
                currently = Status.WALKING;
            }
        }
        else if (Greenfoot.isKeyDown("a")) 
        {
            setVX(-speed);
            if (currently != Status.ATTACKING && currently != Status.JUMPING) 
            {
                currently = Status.WALKING;
            }
        }
        else
        {
            setVX(0);
            if (currently != Status.ATTACKING && currently != Status.JUMPING) 
            {
                currently = Status.IDLE;
            }
        }
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
            v.y = -jump; }

    }
    
    public int getSpeed()
    {
        return speed;
    }
    
    public void checkAttack() {
        if (Greenfoot.isKeyDown("j") && currently != Status.ATTACKING) {
            currently = Status.ATTACKING;
            attack(Enemy.class);
        }
    }

    public void animate()
    {
        switch(currently) 
        {
            case IDLE:
                setAnimation(idleAnimation);
                break;
            case WALKING:
                setAnimation(walkingAnimation);
                break;
            case ATTACKING:
                setAnimation(attackAnimation);
                break;
            default:
                setAnimation(idleAnimation);
                break;
        }

        getAnimation().setFlipped(faceLeft);
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

    @Override
    public void animationCompleted(Animation animation)
    {
        if (animation == attackAnimation) 
        {
            currently = Status.IDLE;
        }
    }
}
