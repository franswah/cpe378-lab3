import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class IntroScenario here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IntroScenario extends CameraWorld
{

    /**
     * Constructor for objects of class IntroScenario.
     * 
     */
    public IntroScenario()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(25000, 600);
    }
    
    protected void prepare() {
        
        addObject(new DarkMountain(), 400,300);
        addObject(new DarkMountainFlipped(), 1400, 300);
        
        addObject(new DarkForest(), 400, 350);
        addObject(new DarkForestFlipped(), 1400, 350);
        addObject(new DarkForest(), 2400, 350);

        
        EvilWerewolf.attackHero = true;
        
        setBackground("images/sky-dark.png");
        
        addObject(new Witch(), 11500 ,495);

        // Hill 1
        insertGround(0, 2000, 600);
        insertGrass(1000, 2000, 550);
        insertGrass(1050, 2000, 500);
        insertGrass(1150, 2000, 450);
        insertGrass(1300, 1900, 400);
        insertGrass(1400, 1700, 350);
        
        addObject(new EvilWerewolf(), 1500, 200);
        
        // Drop
        insertGround(2150, 4000, 600);
        insertGround(2150, 4000, 550);
        insertGrass(2150, 2500, 500);
        insertGrass(2150, 2350, 450);
        
        insertGround(4000, 6000, 600);
        insertGrass(4000, 5000, 550);
        insertGrass(4000, 5000, 500);
        
        addObject(new EvilWerewolf(), 4500, 300);
        addObject(new EvilWerewolf(), 4700, 300);
        
        // Hook
        insertGrass(5100, 5500, 400);
        insertGround(5500, 5700, 400);
        insertGround(5500, 5900, 450);
        insertGround(5500, 6000, 500);
        insertGround(5000, 6000, 550);
        
        
        addObject(new EvilWerewolf(), 5600, 300);
        
        
        // Clif
        insertGround(6100, 8000, 600);
        insertGround(6100, 6300, 550);
        insertGround(6700, 8000, 550);
        insertGrass(7000, 8000, 500);
        insertGrass(7250, 8000, 450);
        insertGrass(7350, 8000, 400);
        insertGrass(7500, 8000, 350);
        insertGrass(7600, 8000, 300);
        insertGrass(8000, 8050, 500);
        
        addObject(new EvilWerewolf(), 7000, 400);
        addObject(new EvilWerewolf(), 7200, 400);
        
        
        insertGround(8150, 8300, 550);
        insertGrass(8150, 8300, 500);
        insertGrass(8150, 8300, 450);
        insertGrass(8150, 8300, 400);
        insertGrass(8150, 8300, 350);
        insertGrass(8150, 8300, 300);
        
        
        addObject(new EvilWerewolf(), 8500, 300);
        
        insertGround(8450, 8550, 600);      
        insertGround(8450, 8550, 550);
        insertGrass(8450, 8550, 500);
        insertGrass(8450, 8550, 450);
        insertGrass(8450, 8550, 400);
        insertGrass(8450, 8550, 350);
        
        insertGround(8700, 8750, 600); 
        insertGround(8700, 8750, 550);
        insertGrass(8700, 8750, 500);
        insertGrass(8700, 8750, 450);
        insertGrass(8700, 8850, 400);
 
    
       
        insertGround(9000, 10000, 600);
        insertGround(10000, 11000, 500);
        insertGrass(10500, 11000, 450);
        
        insertGround(10750, 12000, 600);
        
        EvilWerewolf evilWolf = new EvilWerewolf();
        evilWolf.setImage(Animation.getMirroredImage(evilWolf.idleAnimation.getCurrentFrame()));
        addObject(evilWolf, 600, 500);
        addObject(new Hero(), 300, 500);
        //super.setCameraX(9000);
        
        
        // Plays music the entire time this scene is running. Uncomment if you want to hear the same minute looped over and over.
        GreenfootSound loop = new GreenfootSound("battle.wav");
        loop.setVolume(5);
        loop.playLoop();
        
        BlockingDialog splash = new BlockingDialog("How could you turn on us?\nWe are family!",600,350);
        splash.display(this);

        BlockingDialog.addNext("I never wanted this life.",300,350);
        BlockingDialog.addNext("You need to tell me how\nI can change back!",300,350);
        BlockingDialog.addNext("Why would you want to? You're much stronger now.\nPlus, when we take over the kingdom you'll\nprobably want to be on the winning side.",600,350);
        BlockingDialog.addNext("I don't have time for this!\nI need to find a way to change back!",300,350);
        BlockingDialog.addNext("Fine, have it your way, but there's\nno way I can let you escape.",600,350);
    }
}
