/*
Michael Justis
03/31/2022
Assignment5
'Polymorphism implemented and pots and boomerangs added, pots break when colliding with Link or a boomerang'
*/

import java.awt.Graphics;

public abstract class Sprite 
{
    int x, y, w, h, dest_x, dest_y;

    public Sprite()
    {
        x = 0;
        y = 0;
        w = 0;
        h = 0;
    }

    //abstract public void drawYourself(Graphics g);

    abstract public void update();

    abstract Json marshal();

    abstract public void drawYourself(Graphics g);

    abstract public void isBroke();

    abstract public boolean getDelay();

    public boolean isLink()
    {
        return false;
    }

    public boolean isBrick()
    {
        return false;
    }

    public boolean isPot()
    {
        return false;
    }
}
