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

        
        insertDirt(-400, getWidth() + 500, 600);
        
        setBackground("images/night.jpg");
        
        Hero hero = new Hero();
        addObject(hero, 200, 500);
        
        List<ControlStep> steps = new ArrayList<ControlStep>();
        
        Villager firstVillager = new Villager(false);
        EvilWerewolf firstWolf = new EvilWerewolf();
        hero.enabled = false;
        
        BlockingDialog.paused = false;
        
        steps.add(new ControlStep() 
        {
           public int getDuration() { return 150; }
           
           public void act(World world) 
           {
               
               addObject(new Transition(200, true),500,300);
               if (backgroundMusic != null) {
                    backgroundMusic.stop();
               }
               else {
                   backgroundMusic = new GreenfootSound("towndark.wav");
               }
               backgroundMusic.setVolume(95);
               backgroundMusic.playLoop();
           }
        });
        
        steps.add(new ControlStep() 
        {
           public int getDuration() { return 1; }
           
           public void act(World world) 
           {
                BlockingDialog dialog = new BlockingDialog("It was not long ago that Lukas was turned.\nNot even his coat of fur has fully matured.", 500, 300);
                dialog.display(world);
                dialog.addNext("Today, like most days, his pack is terrorizing the countryside,\nattacking villages that dared hunt the werewolves.", 500,300);
           }
        });
        
        steps.add(new ControlStep() 
        {
           public int getDuration() { return 160; }
           
           public void act(World world) 
           {
               addObject(firstWolf, -100, 500);
               addObject(firstVillager, -70, 500);
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
               BlockingDialog dialog = new BlockingDialog("...",650,350);
               dialog.display(world);
               dialog.addNext("Hey Lukas!\n What's the hold up?",650,350);
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
               dialog.addNext("Wolf Blood\n\nUse the A and D, keys to move\nUse SPACE to jump"
           + "\nUse J to attack\n\nClick, SPACE, and J will advance the dialogue\n", 500,300);
           dialog.addNext("Lukas has decided that he cannot\ngive in to his werewolf bloodlust.\n"
           + "\nThese villagers are helpless and need his help.", 500,300);
           hero.enabled = true;
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
        else
        {
            List<Villager> villagers = getObjects(Villager.class);
            List<EvilWerewolf> wolves = getObjects(EvilWerewolf.class);
            
            if (villagers.size() == 0)
            {
                BlockingDialog dialog = new BlockingDialog("All of the innocent villagers are dead. Maybe you should just stay a werewolf." +
                "\n (Press Enter to Restart)", 500, 300);
                dialog.display(this);
            }
            else if (wolves.size() == 0)
            {
                
                
                List<ControlStep> steps = new ArrayList<ControlStep>();
                
                steps.add(new ControlStep() 
                {
                   public int getDuration() { return 1; }
                   
                   public void act(World world) 
                   {
                       BlockingDialog dialog = new BlockingDialog("The villagers are safe for now.\n But Lukas is not.", 500, 300);
                       dialog.display(world);
                       dialog.addNext("It is time to find a way to reverse the curse.",500,300);
                   }
                });
                
                steps.add(new ControlStep() 
                {
                   public int getDuration() { return 100; }
                   
                   public void act(World world) 
                   {
                       addObject(new Transition(100,false), 500, 300);
                   }
                });
                
                steps.add(new ControlStep() 
                {
                   public int getDuration() { return 1; }
                   
                   public void act(World world) 
                   {
                       Greenfoot.setWorld(new IntroScenario());
                   }
                });
                townSequence = new ControlSequence(this, steps); 
                started = false;
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
        addObject(new Villager(false), -150, 500);
        addObject(new Villager(false), -100, 500);
        
        addObject(new EvilWerewolf(), 1100, 500);
        addObject(new EvilWerewolf(), 1200, 500);
        addObject(new EvilWerewolf(), 1800, 500);
        addObject(new EvilWerewolf(), -300, 500);
        addObject(new EvilWerewolf(), -200, 500);
        addObject(new EvilWerewolf(), 1500, 500);
    }
    
    
}
