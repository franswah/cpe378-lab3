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

    public int scrollWidth = 250;
    public boolean enabled = true;
    
    private float SCALE = .5f;
    
    private DialogModal healthDialog;
    
    private GreenfootSound[] damageSounds = new GreenfootSound[2];

    public Hero()
    {        
        super();

        walkingAnimation = new Animation("WerewolfWalk/WerewolfWalking_%05d.png", 7, SCALE);
        idleAnimation = new Animation("WerewolfIdle/WerewolfIdle_%05d.png", 2, SCALE);
        attackAnimation = new Animation("WerewolfAttack/Werewolf_ClawLeft_%05d.png", 4, SCALE);

        attackAnimation.offsetX = 25;

        setAnimation(Status.WALKING, walkingAnimation);
        setAnimation(Status.IDLE, idleAnimation);
        setAnimation(Status.ATTACKING, attackAnimation);
        
        strength = 15;
        defense = 6;
        jumpV = 29;
        
        damageSounds[0] = new GreenfootSound("growl3.wav");
        damageSounds[1] = new GreenfootSound("growl5.wav");
        damageSounds[0].setVolume(80);
        damageSounds[1].setVolume(80);
        scrolls = false;
        setImage(idleAnimation.getCurrentFrame());
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
        if (BlockingDialog.paused) {
            return;
        }
        
        super.act();
       if (enabled)
       {
            move();
            checkJump();
            checkAttack();
            scroll();
        }
        
        if(getY() > getWorld().getHeight()) {
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
 
        if(Greenfoot.isKeyDown("space") && super.isGrounded()) {
           jump(); }

    }
    
    public int getSpeed()
    {
        return speed;
    }
    
    public void checkAttack() {
        if (Greenfoot.isKeyDown("j")) {
            attack(Enemy.class);
        }
    }
    
    public void kill() {
        World wrld = getWorld();
        BlockingDialog killDialog = new BlockingDialog("You died\nR.I.P. Lukas\n\nPress ENTER to restart", getX(), wrld.getHeight()/2, false);
        killDialog.display(wrld);
        wrld.removeObject(this);
    }
    
    @Override
    public <T extends BattleActor> void executeAttack(Class<T> type)
    {
        int vX = 5;
        int offsetX = 20;
        int offsetY = 10;
        if (faceLeft) 
        {
            vX = -vX;
            offsetX = -offsetX;
        }
        getWorld().addObject(new Slash(vX, 0, this), getX() + offsetX, getY() + offsetY);
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
