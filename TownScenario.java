import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class TownScenario here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TownScenario extends CameraWorld
{

    ControlSequence townSequence;
    private boolean started = false;
    
    /**
     * Constructor for objects of class TownScenario.
     * 
     */
    public TownScenario()
    {
        super(2000, 600);
       
        
        
    }
    
    @Override
    public void prepare() {
        addObject(new DarkMountain(), 400,300);
        addObject(new DarkMountainFlipped(), 1400, 300);
        
        addObject(new Town(), 500, 300);
        addObject(new Town(), 1400, 300);

        
        insertGround(0, getWidth() + 100, 600);
        
        setBackground("images/sky.jpg");
        
        addObject(new Hero(), 200, 500);
        
        List<ControlStep> steps = new ArrayList<ControlStep>();
        
        BlockingDialog dialog = new BlockingDialog("It was not long ago that Lukas was turned.\nNot even his coat of fur has fully matured.", 500, 300);
        dialog.display(this);
        dialog.addNext("Today, like most days, his pack is terrorizing the countryside,\nattacking villages that dared hunt the werewolves.", 500,300);

        Villager firstVillager = new Villager(false);
        EvilWerewolf firstWolf = new EvilWerewolf();
        
        steps.add(new ControlStep() 
        {
           public int getDuration() { return 90; }
           
           public void act(World world) 
           {
               addObject(firstWolf, 350, 500);
               addObject(firstVillager, 400, 500);
           }
        });
        
        steps.add(new ControlStep() 
        {
           public int getDuration() { return 50; }
           
           public void act(World world) 
           {
               removeObject(firstVillager);

           }
        });
        
        steps.add(new ControlStep() 
        {
           public int getDuration() { return 70; }
           
           public void act(World world) 
           {
               firstWolf.faceLeft = true;
           }
        });
        
        steps.add(new ControlStep() 
        {
           public int getDuration() { return 1; }
           
           public void act(World world) 
           {
               BlockingDialog dialog = new BlockingDialog("Hey Lukas!\n What's the hold up?",650,350);
               dialog.display(world);
               dialog.addNext("Oh, uh... just stretching..",300,350);
               dialog.addNext("Well hurry up,\nAnd I'll try to leave some left\nfor you to 'stretch' your claws into!",650,350);
           }
        });
        
        steps.add(new ControlStep() 
        {
           public int getDuration() { return 100; }
           
           public void act(World world) 
           {
               firstWolf.setTarget(1500, 0);
           }
        });
        
        steps.add(new ControlStep() 
        {
           public int getDuration() { return 1; }
           
           public void act(World world) 
           {
               BlockingDialog dialog = new BlockingDialog("Sigh. This is not right.",300,350);
               dialog.display(world);
               dialog.addNext("But there are so many in the pack.\nI'm not sure I have any other choice.",300,350);
               dialog.addNext("Wolf Blood\n\nUse the W, A, S, D, keys to move\nUse SPACE to jump\nUse T to talk"
           + "\nUse J to attack\n\nClick, SPACE, J, and T will advance the dialogue\n\nUse ENTER to restart", 500,300);
           dialog.addNext("Lukas has decided that he cannot\ngive in to his werewolf bloodlust.\n"
           + "\nThese villagers are helpless and need his help.", 500,300);
           }
        });
        
        townSequence = new ControlSequence(this, steps);  
    }
    
    @Override
    public void act()
    {
        super.act();
        if (!started)
        {
            if (townSequence.isFinished())
            {
                startAttack();
                started = true;
            }
            else
            {
                townSequence.act();
            }
        }
    }
    
    public void startAttack()
    {
        addObject(new Villager(false), 1300, 500);
        addObject(new Villager(), 1600, 500);
        addObject(new Villager(false), 1900, 500);
        addObject(new Villager(), 1700, 500);
        addObject(new Villager(), 1100, 500);
        
        addObject(new EvilWerewolf(), 1100, 500);
        addObject(new EvilWerewolf(), 1200, 500);
        addObject(new EvilWerewolf(), 1800, 500);
    }
    
    private void insertGround(int start, int end, int height)
    {
        GreenfootImage block = new Ground().getImage();

        for (int i = start; i < end; i+= block.getWidth())
        {
            addObject(new Dirt(), i, height);
        }
    }
}
