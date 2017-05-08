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

    ControlSequence endSequence;
    private boolean secondPhase = false;
    EvilWerewolf wolf1;
    EvilWerewolf wolf2;
    
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

        EvilWerewolf.attackHero = true;
        insertGround(-400, getWidth() + 500, 600);
        
        insertGround(-10, 300, 400);
        insertGround(850, 1100, 400);
        
        wolf1 = new EvilWerewolf();
        wolf2 = new EvilWerewolf();
        
        setBackground("images/night.jpg");
        
        Hero hero = new Hero();
        hero.scrollWidth = 10;
        addObject(hero, 200, 500);
        
        Witch witch = new Witch();
        addObject(witch, 500, 300); 
       
        BlockingDialog splash = new BlockingDialog("...",200,370);
        splash.display(this);

        BlockingDialog.addNext("Wha.. Who are you?",200,370);
        BlockingDialog.addNext("Silly puppy, you don't remember me?",600,180);
        BlockingDialog.addNext("Wait.\nYou are the one who turned us\ninto these beasts?",200,370);
        BlockingDialog.addNext("Aw, I wouldn't call you a beast dearie.\nYou are all so fluffy and cute\nnow, unlike those disgusting humans.",600,180);
        BlockingDialog.addNext("I don't care what you call me!\nChange me back!",200,370);
        BlockingDialog.addNext("Now why would I do that?\nNone of your friends seem to mind.",600,180);
        BlockingDialog.addNext("Maybe I should show you\nhow dangerous you have made me!",200,370);
        BlockingDialog.addNext("Hehehehehehe.\nWell I suppose I'll have plenty more pets\nafter I put you down.",600,180);
        
        if (backgroundMusic != null) {
            backgroundMusic.stop();
        }
        backgroundMusic = new GreenfootSound("boss.wav");
        backgroundMusic.setVolume(95);
        backgroundMusic.playLoop();
    }
    
    @Override
    public void act()
    {
        super.act();
        
        if (endSequence != null)
        {
            endSequence.act();
        }
        else
        {
            
            List<Witch> witches = getObjects(Witch.class);
            
            if (witches.size() > 0)
            {
                if (witches.get(0).health < 100 && !secondPhase)
                {
                    secondPhase = true;
                    addObject(wolf1, 100, 200);
                    addObject(wolf2, 800, 200);
                }
            }
            else
            {
                List<ControlStep> steps = new ArrayList<ControlStep>();
                
                steps.add(new ControlStep() 
                {
                   public int getDuration() { return 50; }
                   
                   public void act(World world) 
                   {
                       for (EvilWerewolf wolf : getObjects(EvilWerewolf.class))
                       {
                           removeObject(wolf);
                       }
                   }
                });
                    
                steps.add(new ControlStep() 
                {
                   public int getDuration() { return 1; }
                   
                   public void act(World world) 
                   {
                       BlockingDialog dialog = new BlockingDialog("It is done.", 500, 300);
                       dialog.display(world);
                       dialog.addNext("Here we go.",500,300);
                       dialog.addNext("Time to change back.",500,300);
                       dialog.addNext("Any second now.",500,300);
                       dialog.addNext("Or not...",500,300);
                       dialog.addNext("Hmm... \nI guess violence doesn't solve everything.",500,300);
                       dialog.addNext("This beast form must be getting to me.",500,300);
                       dialog.addNext("Or...",500,300);
                       dialog.addNext("Perhaps an artist out there\ndidn't have time to draw\nmy transformation..",500,300);
                       dialog.addNext("One can only hope.",500,300);
                       dialog.addNext("THE END",500,300);
                   }
                });
                
                steps.add(new ControlStep() 
                {
                   public int getDuration() { return 1; }
                   
                   public void act(World world) 
                   {
                       Greenfoot.stop();
                   }
                });
                
                endSequence = new ControlSequence(this, steps);
                    
            }
        }
    }
}
