import javax.net.ssl.ExtendedSSLSession;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

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
    
    protected int maxSpeed = 5;
    protected int accel = 1;
    
    protected boolean scrolls = true;

    private Vector target = null;
    private int targetR;
    
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
            if (target != null)
            {
                if (target.x - targetR > getX())
                {
                    v.x += accel;
                    if (v.x > maxSpeed) v.x = maxSpeed;

                    if (target.x < getX() + v.x)
                        v.x = target.x - getX();
                }
                else if (target.x + targetR < getX())
                {
                    v.x -= accel;
                    if (v.x < -maxSpeed) v.x = -maxSpeed;

                    if (target.x > getX() - v.x)
                        v.x = target.x - getX();
                }
                else 
                {
                    v.x = 0;
                }
            }
            worldPos.add(v);

            CameraWorld world = (CameraWorld) getWorld();
           
                setLocation(worldPos.x - world.getCameraX(), worldPos.y - world.getCameraY());
            
        }
        else
        {
            if((rightIsBlocked() && !faceLeft) || (leftIsBlocked() && faceLeft)) {
                setLocation(getX(), getY() + v.y);
            }
            else {
                setLocation(getX() + v.x, getY() + v.y);
            }
            
        }
        
        fall();
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

    public void setTarget(int x, int y)
    {
        setTarget(x, y, 0);
    }

    public void setTarget(int x, int y, int r)
    {
        target = new Vector(x, y);
        targetR = r;
    }

    public void removeTarget() 
    {
        target = null;
        v.x = 0;
        v.y = 0;
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
    
    public boolean rightIsBlocked() {
        Actor actor = getOneObjectAtOffset(5 + getImage().getWidth() / 2, 0, Platform.class);
        return actor != null;
    }
    
    public boolean leftIsBlocked() {
        Actor actor = getOneObjectAtOffset(-(5 + getImage().getWidth() / 2), 0, Platform.class);
        return actor != null;
    }
    
     @Override
    protected void addedToWorld(World world)
    {
        super.addedToWorld(world);
        worldPos = new Vector(getX(), getY());
    }
}
