import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DialogModal here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DialogModal extends Actor
{
    
    private int height;
    private int width;
    private GreenfootImage image;
    private int padding = 25;
    private int defaultFontSize = 24;
    private GreenfootImage container;
    private int framesBeforeClose = 0;
    private int closeMax = 10;
    protected boolean isClosing = false;
    
    DialogModal(String text) {
        image = new GreenfootImage(text, defaultFontSize, Color.WHITE, Color.BLACK);
        int width = image.getWidth();
        int height = image.getHeight();
        
        container = new GreenfootImage(width + (padding * 2), height + (padding * 2));
        container.setColor(Color.BLACK);
        container.fill();
        container.drawImage(image, padding, padding);
        setImage(container);
    }
    
    public void dismiss() {
        getWorld().removeObject(this);
    }
    /**
     * Act - do whatever the DialogModal wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {   
        if(framesBeforeClose == closeMax) {
                dismiss();
        } else if (isClosing) {
            framesBeforeClose++;
            container.rotate(25);
            if(container.getWidth() - 10 > 0 && container.getHeight() - 10 > 0) {
                container.scale(container.getWidth() - 10, container.getHeight() - 10);  
            }
        }
    }
    
    public void close() {
        isClosing = true;
    }
    
    public void setText(String txt) {
        image = new GreenfootImage(txt, defaultFontSize, Color.WHITE, Color.BLACK);
        int width = image.getWidth();
        int height = image.getHeight();
        
        container.setColor(Color.BLACK);
        container.fill();
        container.drawImage(image, padding, padding);
        setImage(container);
    }
}
