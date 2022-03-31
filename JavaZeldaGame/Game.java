/*
Michael Justis
03/31/2022
Assignment5
'Polymorphism implemented and pots and boomerangs added, pots break when colliding with Link or a boomerang'
*/

import javax.swing.JFrame;
import org.w3c.dom.Text;
import java.awt.Toolkit;
import java.util.ArrayList;


public class Game extends JFrame
{
	Brick brick;
	Controller controller;
	View view;
    Model model;
    Link link;
	Pot pot;

	public Game()
	{
        link = new Link(200,100);
        model = new Model(link);
		controller = new Controller(model, link);
		view = new View(controller, model, link);
		this.setTitle("Run around Link");
		this.setSize(1000, 500);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		view.addMouseListener(controller);
		this.addKeyListener(controller);
        controller.loadFile();
	}
    
    void setModel(Model m)
    {
        model = m;
    }

	public static void main(String[] args)
	{
		Game g = new Game();
		g.run();
	}

	public void run()
	{
		while(true)
		{
			controller.update();
            model.update();
            //link.update();
			view.repaint(); // Indirectly calls View.paintComponent
			Toolkit.getDefaultToolkit().sync(); // Updates screen
			
			//link.savePrevLocation();

			if (link.x < 0 && View.scrollposx != 0){
				View.scrollposx = 0;
				link.x = 1000;
				link.dest_x = link.x;
			}
			if (link.x > 1000 && View.scrollposx != 1000){
				View.scrollposx = 1000;
				link.x = 1;
				link.dest_x = link.x;
			}
			if (link.y < 0 && View.scrollposy != 0){
				View.scrollposy = 0;
				link.y = 500;
				link.dest_y = link.y;
			}
			if (link.y > 500 && View.scrollposy != 500){
				View.scrollposy = 500;
				link.y = 1;
				link.dest_y = link.y;
			}

			// Go to sleep for 50 miliseconds
			try
			{
				Thread.sleep(50);
			} catch(Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
}
