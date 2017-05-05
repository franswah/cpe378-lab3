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
    
    private int knockedBack = 10;

    public static final int scrollWidth = 250;
    
    private DialogModal healthDialog;
    
    private GreenfootSound[] damageSounds = new GreenfootSound[2];

    public Hero()
    {        
        super();

        walkingAnimation = new Animation("WerewolfWalk/WerewolfWalking_%05d.png", 7);
        idleAnimation = new Animation("WerewolfIdle/WerewolfIdle_%05d.png", 2);
        attackAnimation = new Animation("WerewolfAttack/Werewolf_ClawLeft_%05d.png", 4);

        attackAnimation.offsetX = 25;

        setAnimation(Status.WALKING, walkingAnimation);
        setAnimation(Status.IDLE, idleAnimation);
        setAnimation(Status.ATTACKING, attackAnimation);

        attackAnimation.setAnimationCompleteListener(this);
        
        strength = 15;
        defense = 6;
        
        damageSounds[0] = new GreenfootSound("growl1.wav");
        damageSounds[1] = new GreenfootSound("growl2.wav");
        damageSounds[0].setVolume(80);
        damageSounds[1].setVolume(80);
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
        checkJump();
        checkAttack();
        scroll();
        
        if(isAtEdge()) {
            kill();
        }
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
        
        if (currently == Status.ATTACKING && isGrounded())
        {
            setVX(0);
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
  
    private void checkJump() {
 
        if(Greenfoot.getKey() == "space" && super.isGrounded()) {
           jump(); }

    }
    
    public int getSpeed()
    {
        return speed;
    }
    
    public void checkAttack() {
        if (Greenfoot.isKeyDown("j") && currently != Status.ATTACKING) {
            attack(Enemy.class);
        }
    }
    
    public void kill() {
        World wrld = getWorld();
        wrld.addObject(new DialogModal("You died.\nR.I.P. Lukas"), getX(), wrld.getHeight()/2);
        wrld.removeObject(this);
    }
    
    @Override
    public void damage(int dmg) {
        health = health - (dmg - defense);
        healthDialog.setText("Health: " + Math.max(health, 0));
        
        // Play damage animation?
        damageSounds[Greenfoot.getRandomNumber(2)].play();
      
        if (health <= 0) {
           kill();
        }
    }
    
    @Override
    public void beAttacked(BattleActor actor)
    {
        setVY(-knockedBack);
        
        super.beAttacked(actor);
        
    }

    
}
