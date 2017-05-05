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
     
    
    protected final float G = 1.5f;
    
    private Vector v;
    protected Vector worldPos;
    
    protected int maxSpeed = 5;
    protected int accel = 1;
    protected int jumpV = 25;
    
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
                    faceLeft = false;
                    v.x += accel;
                    if (v.x > maxSpeed) v.x = maxSpeed;

                    if (target.x < getX() + v.x)
                        v.x = target.x - getX();
                }
                else if (target.x + targetR < getX())
                {
                    faceLeft = true;
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

            if((rightIsBlocked() && !faceLeft) || (leftIsBlocked() && faceLeft)) {
                if (target != null) {
                    jump();
                }
                v.x = 0;
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
    
    
    
    public int getVX()
    {
        return v.x;
    }
    
    public int getVY()
    {
        return v.y;
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
    }
    
    public void jump()
    {
        v.y = -jumpV;
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
        Actor ground = getOneObjectAtOffset(0,2 + getImage().getHeight()/2, Ground.class);
        return ground != null;
    }
    
    public boolean rightIsBlocked() {
        Actor topActor = getOneObjectAtOffset(5 + getImage().getWidth() / 2, -(getImage().getHeight() / 2 - 5), Ground.class);
        Actor botActor = getOneObjectAtOffset(5 + getImage().getWidth() / 2, getImage().getHeight() / 2 - 5, Ground.class);
        return topActor != null || botActor != null;
    }
    
    public boolean leftIsBlocked() {
        Actor topActor = getOneObjectAtOffset(-(5 + getImage().getWidth() / 2), -(getImage().getHeight() / 2 - 5), Ground.class);
        Actor botActor = getOneObjectAtOffset(-(5 + getImage().getWidth() / 2), getImage().getHeight() / 2 - 5, Ground.class);
        return topActor != null || botActor != null;
    }
    
     @Override
    protected void addedToWorld(World world)
    {
        super.addedToWorld(world);
        worldPos = new Vector(getX(), getY());
    }
}
