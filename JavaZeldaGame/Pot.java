/*
Michael Justis
03/31/2022
Assignment5
'Polymorphism implemented and pots and boomerangs added, pots break when colliding with Link or a boomerang'
*/
import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Pot extends Sprite
{
    BufferedImage pot_image;
    BufferedImage pot_broken;
    Boolean broke = false;
    int deleteDelay;

    public Pot(int pot_x, int pot_y)
    {
        this.x = pot_x;
        this.y = pot_y;
        w = 25;
        h = 25;
        deleteDelay = -1;

        pot_image = View.loadImage("pot.png");
        pot_broken = View.loadImage("pot_broken.png");
    }

    public Pot(Json ob)
    {
        w = 25;
        h = 25;
        x = (int)ob.getLong("potx");
        y = (int)ob.getLong("poty");
        deleteDelay = -1;
        
        pot_image = View.loadImage("pot.png");
        pot_broken = View.loadImage("pot_broken.png");
    }

    public boolean amIClickingOnYou(int userx, int usery)
    {
        if(userx >= x && userx <= x+w && usery >= y && usery <= y+h)
        {
            return true;
        }
        return false;
    }
    
    Json marshal()
    {
        Json ob = Json.newObject();
        ob.add("potx", x);
        ob.add("poty", y);
        return ob;
    }

    public void update()
    {

    }

    public void drawYourself(Graphics g)
    {
        if (!broke){
            g.drawImage(pot_image, x - View.scrollposx, y - View.scrollposy, null);
        }else {
            g.drawImage(pot_broken, x - View.scrollposx, y - View.scrollposy, null);
            //System.out.println(x + " " + y);
        }
    }

    public void isBroke()
    {
        broke = true;
        deleteDelay = 15;
    }

    public boolean getDelay()
    {
        if (deleteDelay == 0){
            deleteDelay = -1;
            return true;
        }else {
            deleteDelay -= 1;
            return false;
        }
    }

    @Override
    public boolean isPot()
    {
        return true;
    }
}
