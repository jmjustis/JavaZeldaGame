/*
Michael Justis
03/31/2022
Assignment5
'Polymorphism implemented and pots and boomerangs added, pots break when colliding with Link or a boomerang'
*/

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;
import java.util.ArrayList;

class View extends JPanel
{
	Brick brick;
    Model model;
    Link link;
    Pot pot;
    Controller controller;
    BufferedImage brick_image;
    BufferedImage link_image;
    static int scrollposx;
    static int scrollposy;

	View(Controller c, Model m, Link l)
	{
        model = m;
        link = l;
        controller = c;
		//brick = b;
		c.setView(this);
	}
    
    
    static BufferedImage loadImage(String filename){
        BufferedImage image = null;
        try{
            image = ImageIO.read(new File(filename));
            System.out.println(filename + " was loaded.");
        } catch(Exception e) {
            System.out.println("Error loading image"); 
        }
        return image;
    }
    

	public void paintComponent(Graphics g)
	{
        //link = new Link(10,10);
        //pot = new Pot(100, 100);
		g.setColor(new Color(255, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        

        //g.drawImage(link.link_images[link.image_number + controller.direction], link.x, link.y, null);
        //g.drawImage(pot.pot_image, pot.x - scrollposx, pot.y - scrollposy, null);
        //pot.drawYourself(g);

        if (model.isBoomerang){
            model.boomerang.drawYourself(g);
        }

        link.drawYourself(g);
        for(int i = 0; i < model.sprites.size(); i++)
        {
            //Brick b = (Brick)model.sprites.get(i);
            model.sprites.get(i).drawYourself(g);
        }

        g.setColor(Color.RED);
        g.setFont(new Font("TimesRoman", 1, 50));
        g.drawString("Score: " + model.score, 800, 45);
	}
}
