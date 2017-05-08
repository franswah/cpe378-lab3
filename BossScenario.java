import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class BossScenario here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BossScenario extends CameraWorld
{

    /**
     * Constructor for objects of class BossScenario.
     * 
     */
    public BossScenario()
    {
        super(1000, 600);
    }
    
    @Override
    public void prepare()
    {
        addObject(new DarkMountain(), 400,300);
        addObject(new DarkMountainFlipped(), 1400, 300);

        
        insertGround(-400, getWidth() + 500, 600);
        
        insertGround(-10, 300, 400);
        insertGround(850, 1100, 400);
        
        setBackground("images/night.jpg");
        
        Hero hero = new Hero();
        hero.scrollWidth = 10;
        addObject(hero, 200, 500);
        
        Witch witch = new Witch();
        addObject(witch, 500, 300); 
       
        BlockingDialog splash = new BlockingDialog("Wha.. Who are you?",200,370);
        splash.display(this);

        BlockingDialog.addNext("Silly puppy, you don't remember me?",600,180);
        BlockingDialog.addNext("Wait.\nYou are the one who turned us\ninto these beasts?",200,370);
        BlockingDialog.addNext("Aw, I wouldn't call you a beast dearie.\nYou are all so fluffy and cute\nnow, unlike those disgusting humans.",600,180);
        BlockingDialog.addNext("I don't care what you call me!\nChange me back!",200,370);
        BlockingDialog.addNext("Now why would I do that?\nNone of your friends seem to mind.",600,180);
        BlockingDialog.addNext("Maybe I should show you\nhow dangerous you have made me!",200,370);
        BlockingDialog.addNext("Hehehehehehe.\nWell I suppose I'll have plenty more pets\nafter I put you down.",600,180);
    }
    
    @Override
    public void act()
    {
        super.act();
        
        
    }
}
