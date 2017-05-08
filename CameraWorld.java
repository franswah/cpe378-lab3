import javax.net.ssl.ExtendedSSLSession;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CameraWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class CameraWorld extends World
{
    public static final int WINDOW_WIDTH = 1000;
    public static final int WINDOW_HEIGHT = 600;

    private int cameraX = 50;
    private int cameraY = 0;
    
    private int worldWidth;
    private int worldHeight;
    private int worldX;
    private int worldY;
    
    /**
     * Constructor for objects of class CameraWorld.
     * 
     */
    public CameraWorld(int worldWidth, int worldHeight)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(WINDOW_WIDTH, WINDOW_HEIGHT, 1, false);
        
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;

        prepare();
    }

    public void act()
    {
        super.act();
    }
    
    public void setWorldDimensions(int width, int height)
    {
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
    
    public void setCameraX(int x) 
    {
        // Make sure camera stays within bounds of the world.
        if (x < 0)
        {
            cameraX = 0;
        }
        else if (x > (worldWidth - WINDOW_WIDTH))
        {
            cameraX = worldWidth - WINDOW_WIDTH;
        }
        else
        {
            cameraX = x;
        }
    }
    
    public void setCameraY(int y) 
    {
        if (y < 0)
        {
            cameraY = 0;
        }
        else if (y > (worldHeight - WINDOW_HEIGHT))
        {
            cameraY = worldHeight - WINDOW_HEIGHT;
        }
        else
        {
            cameraY = y;
        }
    }

    public int getCameraWidth()
    {
        return WINDOW_WIDTH;
    }

    public int getCameraHeight()
    {
        return WINDOW_HEIGHT;
    }
    
    // Get the Actor's position relative to the entire world as opposed to relative to the camera
    public int getActorX(Actor a)
    {
        return a.getX() + cameraX;
    }
    
    public int getActorY(Actor a)
    {
        return a.getY() + cameraY;
    }
    
    public int getWidth()
    {
        return worldWidth;
    }
    
    public int getHeight()
    {
        return worldHeight;
    }
    
    protected void insertGround(int start, int end, int height)
    {
        GreenfootImage block = new Ground().getImage();

        for (int i = start; i < end; i+= block.getWidth())
        {
            addObject(new Ground(), i, height);
        }
    }
    
    protected void insertGrass(int start, int end, int height) {
    GreenfootImage block = new Grass().getImage();

        for (int i = start; i < end; i+= block.getWidth())
        {
            addObject(new Grass(), i, height);
        }
    }
    
    protected void insertDirt(int start, int end, int height) {
    GreenfootImage block = new Dirt().getImage();

        for (int i = start; i < end; i+= block.getWidth())
        {
            addObject(new Dirt(), i, height);
        }
    }
}
