/**
 * Write a description of class Vector here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Vector  
{
    // instance variables - replace the example below with your own
    public int x;
    public int y;

    /**
     * Constructor for objects of class Vector
     */
    public Vector(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void set(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void add(Vector v)
    {
        this.x += v.x;
        this.y += v.y;
    }

    public Vector deff(Vector v)
    {
        return new Vector(this.x - v.x, this.y - v.y);
    }

}
