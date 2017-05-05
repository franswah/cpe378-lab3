import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javafx.scene.shape.Ellipse;


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
    
    private GreenfootSound[] damageSounds = new GreenfootSound[2];

    private int knockedBack = 15;
    
    public EvilWerewolf() 
    {
        walkingAnimation = new Animation("WerewolfWalk/WerewolfWalking_%05d.png", 7);
        idleAnimation = new Animation("WerewolfIdle/WerewolfIdle_%05d.png", 2);
        attackAnimation = new Animation("WerewolfAttack/Werewolf_ClawLeft_%05d.png", 4);
        
        attackAnimation.offsetX = 30;
        
        walkingAnimation.tint(-55, -120, -170);
        idleAnimation.tint(-55, -120, -170);
        attackAnimation.tint(-55, -120, -170);
        
        setAnimation(Status.WALKING, walkingAnimation);
        setAnimation(Status.IDLE, idleAnimation);
        setAnimation(Status.ATTACKING, attackAnimation);
        
        damageSounds[0] = new GreenfootSound("growl1.wav");
        damageSounds[1] = new GreenfootSound("growl2.wav");
        damageSounds[0].setVolume(72);
        damageSounds[1].setVolume(72);
    }
    /**
     * Act - do whatever the EvilWerewolf wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    @Override
    public void act() 
    {
        super.act();
        
        for (Hero hero : getObjectsInRange(500, Hero.class)) {
            if (inRangeOf(hero, 60)) 
            {
                removeTarget();
                if (getX() < hero.getX()) 
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
                setTarget(hero.getX(), hero.getY(), 60);
            }
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
        
        damageSounds[Greenfoot.getRandomNumber(2)].play();

        super.beAttacked(actor);
    }   
}
