import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CameraWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class CameraWorld extends World
{
    int cameraX = 0;
    int cameraY = 0;
    
    /**
     * Constructor for objects of class CameraWorld.
     * 
     */
    public CameraWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1);
        prepare();
    }
    
    protected abstract void prepare();
    
    public int getCameraX() {
        return cameraX;
    }
    
    public int getCameraY() {
        return cameraY;
    }
    
    public void moveX(int x) {
        cameraX += x;
    }
    
    public void moveY(int y) {
        cameraY += y;
    }
}
