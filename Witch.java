import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Witch here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Witch extends NPC
{
    GreenfootImage image;
    
    public Witch() {
        image = new GreenfootImage("witch.png");
        image.scale(150,225);
        setImage(image);
    }
    
    /**
     * Act - do whatever the Witch wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (BlockingDialog.paused) {
            return;
        }
        
        super.act();
        
        List<Hero> heroes = getObjectsInRange(100, Hero.class);
        if (Greenfoot.isKeyDown("t") && !heroes.isEmpty()) {
            BlockingDialog witchDialog = new BlockingDialog("Hello there, Wolfman.",getX(), getY() - 150);
            witchDialog.display(getWorld());
            BlockingDialog.addNext("Hmmm..\nWhy aren't you attacking me?",heroes.get(0).getX(), getY() - 150);
            BlockingDialog.addNext("Hee he hee!\nI know you're not evil.",getX(), getY() - 150);
            BlockingDialog.addNext("How?",heroes.get(0).getX(), getY() - 150);
            BlockingDialog.addNext("I can't tell you that!\nWhat I can tell you is that there's\na way for you to change back",getX(), getY() - 150);
            BlockingDialog.addNext("What's it going to cost me?\nI don't have anything.",heroes.get(0).getX(), getY() - 150);
            BlockingDialog.addNext("I do not have the cure.\nI can simply point you in the right direction.",getX(), getY() - 150);
            BlockingDialog.addNext("You must enter the town.\nYou'll find the next clue there.",getX(), getY() - 150);
            BlockingDialog.addNext("END OF BETA\nThanks for Playing!",getX(), getY() - 150);
        }
    }    
}
