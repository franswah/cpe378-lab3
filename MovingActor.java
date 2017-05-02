import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MovingActor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class MovingActor extends AnimatedActor
{
     public MovingActor() {
        super();
    }
    
    protected final float G = 2.5f;

    protected boolean isMoving = false;
    
    private int vX = 0;
    protected int vY = 0;
    
    protected int worldX;
    protected int worldY;
    
    private int speed = 0;
    
    protected boolean scrolls = true;
    
    /**
     * Act - do whatever the MovingActor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();

        if (scrolls)
        {
            worldX += vX;
            worldY += vY;
            CameraWorld world = (CameraWorld) getWorld();
            setLocation(worldX - world.getCameraX(), worldY - world.getCameraY());
        }
        else
        {
            setLocation(getX() + vX, getY() + vY);
        }
        
        fall();
       
        if (vX != 0) {
            isMoving = true;
        }
        else {
            isMoving = false;
        }
    }
    
    
    
    public int getVX()
    {
        return vX;
    }
    
    public int getVY()
    {
        return vY;
    }

    public void setVX(int vX)
    {
        this.vX = vX;
    }

    public void setVY(int vY)
    {
        this.vY = vY;
    }
    
    public abstract int getSpeed();
    
    private void fall() {
        
        if (!isGrounded())
        {   
            vY += G;
            
        }
        else {
            Actor ground = getOneObjectAtOffset(0,5 + getImage().getHeight()/2, Ground.class);
            setLocation(getX(),ground.getY() - (ground.getImage().getHeight() / 2 + getImage().getHeight() / 2));
            vY = 0;
        }
    }
    
    public boolean isGrounded() {
        Actor ground = getOneObjectAtOffset(0,5 + getImage().getHeight()/2, Ground.class);
        return ground != null;
    }
    
     @Override
    protected void addedToWorld(World world)
    {
        super.addedToWorld(world);
        worldX = getX();
        worldY = getY();
    }
}
