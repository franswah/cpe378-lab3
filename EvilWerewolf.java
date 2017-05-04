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

    private int knockedBack = 15;
    
    public EvilWerewolf() 
    {
        walkingAnimation = new Animation("WerewolfWalk/WerewolfWalking_%05d.png", 7);
        idleAnimation = new Animation("WerewolfIdle/WerewolfIdle_%05d.png", 2);
        attackAnimation = new Animation("WerewolfAttack/Werewolf_ClawLeft_%05d.png", 4);

        walkingAnimation.tint(50, 0, 0);
        idleAnimation.tint(50, 0, 0);
        attackAnimation.tint(50, 0, 0);
        
        setAnimation(Status.WALKING, walkingAnimation);
        setAnimation(Status.IDLE, idleAnimation);
        setAnimation(Status.ATTACKING, attackAnimation);
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

        super.beAttacked(actor);
    }   
}
