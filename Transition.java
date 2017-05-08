import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Transition here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Transition extends Actor
{
    private int length;
    private int frame;
    private boolean running;
    private boolean state;
    private boolean fadeIn;
    private BlockingDialog finished;
    
    public Transition(int len, boolean in) {
        frame = 0;
        length = len;
        fadeIn = in;
        
        GreenfootImage box = new GreenfootImage(1000,600);
        box.setColor(new Color(0,0,0));
        box.fillRect(0,0,1000,600);
        
        if (!fadeIn) {
            box.setTransparency(0);
        }
        setImage(box);
        state = BlockingDialog.paused;
        BlockingDialog.paused = true;
        running = true;
    }
    
    /**
     * Act - do whatever the Transition wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (running) {
            int offset = 255 / length;
            if (fadeIn) {
                offset *= -1;
            }
            else {
                ((CameraWorld)getWorld()).backgroundMusic.setVolume(100 * ((length - frame) / length));
            }
            
            getImage().setTransparency(Math.min(getImage().getTransparency() + offset, 255));
            frame++;
            if (frame == length) {
                running = false;
                if (fadeIn) {
                    getWorld().removeObject(this);
                }
                if (finished != null) {
                    finished.display(getWorld());
                }
                BlockingDialog.paused = state;
            }
           
        }
    }
    
    public boolean isFinished() {
        return !running;
    }
    
    public void onFinished(BlockingDialog toShow) {
        finished = toShow;
    }
}
