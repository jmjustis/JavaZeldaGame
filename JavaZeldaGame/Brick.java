/*
Michael Justis
03/31/2022
Assignment5
'Polymorphism implemented and pots and boomerangs added, pots break when colliding with Link or a boomerang'
*/
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class Brick extends Sprite
{
    BufferedImage brick_image;

	public Brick(int brick_x, int brick_y)
	{
        w = 25;
        h = 25;
        this.x = brick_x;
        this.y = brick_y;


        brick_image = View.loadImage("brick.png");
	}
    
    public Brick(Json ob)
    {
        w = 25;
        h = 25;
        x = (int)ob.getLong("brickx");
        y = (int)ob.getLong("bricky");
        
        brick_image = View.loadImage("brick.png");
    }
    
    
    
    public void drawYourself(Graphics g)
    {
        g.drawImage(brick_image, x - View.scrollposx, y - View.scrollposy, null);
    }
/*
    void unmarshal(Json ob)
    {
        //sprites = new ArrayList<Sprite>();
        Json tmpList = ob.get("brick");
        for(int i = 0; i < tmpList.size(); i++){
            sprites.add(new Brick(tmpList.get(i)));
        }
    }
*/
    Json marshal()
    {
        Json ob = Json.newObject();
        ob.add("brickx", x);
        ob.add("bricky", y);
        return ob;
    }

    public void update(){
        
    }

    public void isBroke(){
    }

    public boolean getDelay(){
        return false;
    }
	
    @Override
    public boolean isBrick()
    {
        return true;
    }
}
