import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CameraWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class CameraWorld extends World
{
    private int cameraX = 0;
    private int cameraY = 0;
    
    private int worldWidth;
    private int worldHeight;
    private int worldX;
    private int worldY;
    
    /**
     * Constructor for objects of class CameraWorld.
     * 
     */
    public CameraWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1);
        prepare();
        
        worldWidth = getWidth();
        worldHeight = getHeight();
        worldX = 0;
        worldY = 0;
    }
    
    public void setWorldDimensions(int x, int y, int width, int height)
    {
        worldX = x;
        worldY = y;
        worldWidth = width;
        worldHeight = height;
    }
    
    protected abstract void prepare();
    
    public int getCameraX() {
        return cameraX;
    }
    
    public int getCameraY() {
        return cameraY;
    }
    
    public void setCameraX(int x) {
        cameraX = x;
    }
    
    public void setCameraY(int y) {
        cameraY = y;
    }
    
    public int getX()
    {
        return worldX;
    }
    
    public int getY()
    {
        return worldY;
    }
    
    public int getWidth()
    {
        return worldWidth;
    }
    
    public int getHeight()
    {
        return worldHeight;
    }
}
