import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javafx.scene.shape.Ellipse;
import java.util.*;


/**
 * Write a description of class EvilWerewolf here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EvilWerewolf extends Enemy
{
    Animation walkingAnimation;
    Animation idleAnimation;
    Animation attackAnimation;
    
    public static boolean attackHero = false;
    
    private static GreenfootSound[] damageSounds = new GreenfootSound[3];
    
    private float SCALE = .5f;

    private int knockedBack = 15;
    
    public EvilWerewolf() 
    {
        walkingAnimation = new Animation("WerewolfWalk/WerewolfWalking_%05d.png", 7, SCALE);
        idleAnimation = new Animation("WerewolfIdle/WerewolfIdle_%05d.png", 2, SCALE);
        attackAnimation = new Animation("WerewolfAttack/Werewolf_ClawLeft_%05d.png", 4, SCALE);
    
        attackAnimation.offsetX = 30;
    
        walkingAnimation.tint(-55, -120, -170);
        idleAnimation.tint(-55, -120, -170);
        attackAnimation.tint(-55, -120, -170);
        
        setAnimation(Status.WALKING, walkingAnimation);
        setAnimation(Status.IDLE, idleAnimation);
        setAnimation(Status.ATTACKING, attackAnimation);
        
        maxSpeed = 6;
        
        if (damageSounds[0] == null) {
           damageSounds[0] = new GreenfootSound("growl1.wav");
           damageSounds[1] = new GreenfootSound("growl2.wav");
           damageSounds[2] = new GreenfootSound("growl4.wav");
           damageSounds[0].setVolume(80);
           damageSounds[1].setVolume(80);
           damageSounds[2].setVolume(80);
        }
    }
    /**
     * Act - do whatever the EvilWerewolf wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    @Override
    public void act() 
    {
        if (BlockingDialog.paused) {
            return;
        }
        
        super.act();
        
        
        if (attackHero)
        {
            for (Hero hero : getObjectsInRange(800, Hero.class)) {
                //if (hero.worldPos.y < worldPos.y -20 && isGrounded())
                //{
                    //jump();
                //}
                if (inRangeOf(hero, 60)) 
                {
                    removeTarget();
                    if (worldPos.x < hero.worldPos.x) 
                    {
                        faceLeft = false;
                    }
                    else
                    {
                        faceLeft = true;
                    }
                    attack(Hero.class);
                }
                else 
                {
                    setTarget(hero.worldPos.x, hero.getY(), 60);
                }
            }
        }
        else
        {
            chaseVillagers();
        }
        
    }
    
    public void chaseVillagers()
    {
        List<Villager> villagers = getObjectsInRange(1500, Villager.class);
        Villager villager = (Villager)getNearest(villagers);
        if (villager != null)
        {
            if (inRangeOf(villager, 60)) 
            {
                removeTarget();
                if (worldPos.x < villager.worldPos.x) 
                {
                    faceLeft = false;
                }
                else
                {
                    faceLeft = true;
                }
                attack(Villager.class);
            }
            else 
            {
                setTarget(villager.worldPos.x, 0, 60);
            }
        }
        else
        {
            
        }
    }
    
    

    @Override
    public void beAttacked(BattleActor actor)
    {
        if (actor.getX() > getX())
            setVX(-knockedBack);
        else
            setVX(knockedBack);

        setVY(-knockedBack);
        
        damageSounds[Greenfoot.getRandomNumber(3)].play();

        super.beAttacked(actor);
    }   
}
