/*
Michael Justis
03/31/2022
Assignment5
'Polymorphism implemented and pots and boomerangs added, pots break when colliding with Link or a boomerang'
*/
import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Boomerang extends Sprite
{
    BufferedImage boomerang_image;

    public Boomerang(int boomerang_x, int boomerang_y)
    {
        this.x = boomerang_x;
        this.y = boomerang_y;
        w = 25;
        h = 25;

        boomerang_image = View.loadImage("boomerang.png");
    }

    public void update()
    {

    }

    public void drawYourself(Graphics g)
    {
        g.drawImage(boomerang_image, x - View.scrollposx, y - View.scrollposy, null);
    }

    public void isBroke(){
    }

    public boolean getDelay(){
        return false;
    }

    Json marshal()
    {
        Json ob = Json.newObject();
        ob.add("boomerangx", x);
        ob.add("boomerangy", y);
        return ob;
    }
}
