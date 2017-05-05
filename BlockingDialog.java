import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.lang.String;

/**
 * Write a description of class BlockingDialog here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BlockingDialog extends DialogModal
{
    protected static ArrayList<BlockingDialog> queue = new ArrayList<BlockingDialog>();
    public static boolean paused = false;
    protected int xPosition, yPosition;
    
    public BlockingDialog(String text, int x, int y) {
       super(text);
       xPosition = x;
       yPosition = y;
    }
    
    public void display(World world) {
        queue.add(this);
        world.addObject(this, xPosition, yPosition);
        paused = true;
    }
    
    public static void addNext(BlockingDialog nextDialog) {
        queue.add(nextDialog);
    }
    
    public static void addNext(String text, int x, int y) {
        queue.add(new BlockingDialog(text, x, y));
    }
    
    /**
     * Act - do whatever the BlockingDialog wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        String key = Greenfoot.getKey();
        if (Greenfoot.mouseClicked(null) || key == "j" || key == "space" || key == "t") {
            close();
        }
        else if (key == "enter") {
            Greenfoot.setWorld(new IntroScenario());
        }
        super.act();
    }
    
    @Override
    public void dismiss() {
        queue.remove(0);
        if (queue.isEmpty()) {
            paused = false;
        }
        else {
            getWorld().addObject(queue.get(0), queue.get(0).xPosition, queue.get(0).yPosition);
        }
        
        super.dismiss();
    }
}
