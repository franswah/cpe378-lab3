import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MovingActor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class MovingActor extends AnimatedActor
{
     
    
    protected final float G = 2.5f;
    
    protected Vector v;
    protected Vector worldPos;

    protected boolean faceLeft = false;
    
    protected int speed = 5;
    
    protected boolean scrolls = true;
    
    public MovingActor() {
        super();
        
        v = new Vector(0,0);
    }
    
    /**
     * Act - do whatever the MovingActor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();

        if (scrolls)
        {
            worldPos.add(v);

            CameraWorld world = (CameraWorld) getWorld();
            setLocation(worldPos.x - world.getCameraX(), worldPos.y - world.getCameraY());
        }
        else
        {
            setLocation(getX() + v.x, getY() + v.y);
        }
        
        fall();
       
        if (v.x > 0) {
            faceLeft = false;
        }
        else if (v.x < 0) {
            faceLeft = true;
        }
    }
    
    
    
    public Vector getV()
    {
        return v;
    }

    public void setVX(int vX)
    {
        this.v.x = vX;
    }

    public void setVY(int vY)
    {
        this.v.y = vY;
    }

    public void moveTo(int x, int y)
    {

    }
    
    private void fall() {
        
        if (!isGrounded())
        {   
            v.y += G;
            
        }
        else {
            Actor ground = getOneObjectAtOffset(0,5 + getImage().getHeight()/2, Ground.class);
            setLocation(getX(),ground.getY() - (ground.getImage().getHeight() / 2 + getImage().getHeight() / 2));
            v.y = 0;
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
        worldPos = new Vector(getX(), getY());
    }
}
