import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


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
    
    public EvilWerewolf() 
    {
        walkingAnimation = new Animation("WerewolfWalk/WerewolfWalking_%05d.png", 7);
        idleAnimation = new Animation("WerewolfIdle/WerewolfIdle_%05d.png", 2);
        attackAnimation = new Animation("WerewolfAttack/Werewolf_ClawLeft_%05d.png", 4);
    }
    /**
     * Act - do whatever the EvilWerewolf wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
    }    
}
