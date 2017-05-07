import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class ControlSequence here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ControlSequence
{
    World world;
    List<ControlStep> steps;
    private boolean finished = false;
    int last;
    int cur = 0;
    int dur = 0;
    
    public ControlSequence(World world, List<ControlStep> steps)
    {
        this.world = world;
        this.steps = steps;
        last = steps.size();
    }
    
    /**
     * Act - do whatever the ControlSequence wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (BlockingDialog.paused) {
            return;
        }
        
        if (dur-- <= 0)
        {
            if (cur < last)
            {
                dur = steps.get(cur).getDuration();
                steps.get(cur).act(world);
                cur++;
            }
            else
            {
                finished = true;
            }
        }
    }    
    
    public boolean isFinished()
    {
        return finished;
    }
}
