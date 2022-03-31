/*
Michael Justis
03/31/2022
Assignment5
'Polymorphism implemented and pots and boomerangs added, pots break when colliding with Link or a boomerang'
*/
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;

public class Link extends Sprite
{
    int px;
    int py;
    double speed;
    BufferedImage[] link_images;
    int image_number = 1;

	public Link(int link_x, int link_y)
	{
        w = 73;
        h = 86;
        this.x = link_x;
        this.y = link_y;
        this.dest_x = link_x;
        this.dest_y = link_y;
        
        link_images = new BufferedImage[45];
        link_images[0] = View.loadImage("link1.png");
        for (int i = 0; i < link_images.length; i++){
            link_images[i] = View.loadImage("link" + i + ".png");
        }
        link_images[41] = View.loadImage("linkf.png");
        link_images[42] = View.loadImage("linkb.png");
        link_images[43] = View.loadImage("linkl.png");
        link_images[44] = View.loadImage("linkr.png");
	}

    // images 1-10 facing forward
    // images 11-20 facing left
    // images 21-30 facing backward
    // images 31-40 facing right
    // 41 stationary forward
    // 42 stationary backward
    // 43 stationary left
    // 44 stationary right

    public Link(Json ob)
    {
        w = 73;
        h = 86;
        x = (int)ob.getLong("linkx");
        y = (int)ob.getLong("linky");
    }
    
    public void update()
    {
        savePrevLocation();
    }

    public void getOutOfSprite(Sprite b){
        
        if (x + w + View.scrollposx >= b.x && px + View.scrollposx <= b.x && y + h - 10 + View.scrollposy > b.y && y + 10 + View.scrollposy < b.y + 50){
            x = b.x - w - View.scrollposx;
            dest_x = x;
        }
        else if (x + View.scrollposx <= b.x + 50 && px + View.scrollposx >= b.x - 50 && y + h - 10 + View.scrollposy > b.y && y + 10 + View.scrollposy < b.y + 50){
            x = b.x + 50 - View.scrollposx;
            dest_x = x;
        }
        
        else if (y + h + View.scrollposy >= b.y && py + View.scrollposy <= b.y && x + w + View.scrollposx > b.x && x + View.scrollposx < b.x + 50){
            y = b.y - h - View.scrollposy;
            dest_y = y;
        }
        
        else if (y + View.scrollposy <= b.y + 50 && py + View.scrollposy >= b.y - 50 && x + w + View.scrollposx > b.x && x + View.scrollposx < b.x + 50){
            y = b.y + 50 - View.scrollposy;
            dest_y = y;
        }
        
    }

    public void savePrevLocation(){
        px = x;
        py = y;
    }

    public void drawYourself(Graphics g)
    {
        g.drawImage(link_images[image_number + Controller.direction], x, y, null);
    }
    
    Json marshal()
    {
        Json ob = Json.newObject();
        ob.add("linkx", x);
        ob.add("linky", y);
        return ob;
    }

    public void isBroke(){
    }

    public boolean getDelay(){
        return false;
    }
	
    @Override
    public boolean isLink()
    {
        return true;
    }
}
