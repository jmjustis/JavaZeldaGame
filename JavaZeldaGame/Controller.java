/*
Michael Justis
03/31/2022
Assignment5
'Polymorphism implemented and pots and boomerangs added, pots break when colliding with Link or a boomerang'
*/

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Controller implements ActionListener, MouseListener, KeyListener
{
	View view;
	Brick brick;
    Model model;
    Link link;
    Pot pot;
	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;
    boolean editMode = false;
    static int direction;
    
	Controller(Model m, Link l)
	{
        model = m;
        link = l;
	}	

	public void setView(View v)
	{
		view = v;
	}

	public void actionPerformed(ActionEvent e)
	{
	}


	//Mouse controls
	public void mousePressed(MouseEvent e)
	{
        if (editMode == true){
            model.isThereABrick(e.getX() + View.scrollposx, e.getY() + View.scrollposy);
        }
	}

	public void mouseReleased(MouseEvent e) {    }

	public void mouseEntered(MouseEvent e) {    }

	public void mouseExited(MouseEvent e) {    }

	public void mouseClicked(MouseEvent e) {

    }


	//Keyboard controls	
	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
            
                case KeyEvent.VK_RIGHT: if (editMode == false){keyRight = true;} break;
                case KeyEvent.VK_LEFT: if (editMode == false){keyLeft = true;} break;
                case KeyEvent.VK_UP: if (editMode == false){keyUp = true;} break;
                case KeyEvent.VK_DOWN: if (editMode == false){keyDown = true;} break;
                
		}
	}

	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
                case KeyEvent.VK_RIGHT: keyRight = false; break;
                case KeyEvent.VK_LEFT: keyLeft = false; break;
                case KeyEvent.VK_UP: keyUp = false; break;
                case KeyEvent.VK_DOWN: keyDown = false; break;
            case KeyEvent.VK_Q: System.exit(0);
            case KeyEvent.VK_ESCAPE: System.exit(0);
            case KeyEvent.VK_CONTROL:
                model.throwBoomerang();
		}
        char c = e.getKeyChar();
        if (c == 'r')
        {
            //reset
        }
        if (c == 's')
        {
            Json saveObject = model.marshal();
            saveObject.save("map.json");
            System.out.println("File saved");
        }
        if (c == 'l')
        {
            loadFile();
        }
        if (c == 'e')
        {
            if (editMode == true){
                editMode = false;
                System.out.println("Edit mode disabled");
            }else if(editMode == false){
                editMode = true;
                System.out.println("Edit mode enabled");
            }
        }
        
        if (editMode == true){
            if (c == 'p')
            {
                model.potSelector = !model.potSelector;
            }
            if (c == 'w')
            {
                if (View.scrollposy != 0){
                    View.scrollposy = 0;
                }
            }
            if (c == 'a')
            {
                if (View.scrollposx != 0){
                    View.scrollposx = 0;
                }
            }
            if (c == 'x'){
                if (View.scrollposy != 500){
                    View.scrollposy = 500;
                }
            }
            if (c == 'd'){
                if (View.scrollposx != 1000){
                    View.scrollposx = 1000;
                }
            }
        }
	}
    
    public void loadFile()
    {
        Json j = Json.load("map.json");
        model.unmarshal(j);
        System.out.println("File loaded.");
    }

	public void keyTyped(KeyEvent e)
	{
	}

    
	void update()
	{
        ((Link)link).savePrevLocation();
        //link = new Link(10,10);
        if(keyRight){
            link.dest_x = link.dest_x + 4;
            direction = 30;
            if (link.image_number < 10){link.image_number++;}else{link.image_number = 1;}
        }
        if(keyLeft){
            link.dest_x = link.dest_x - 4;
            direction = 10;
            if (link.image_number < 10){link.image_number++;}else{link.image_number = 1;}
        }
        if(keyDown){
            link.dest_y = link.dest_y + 4;
            direction = 0;
            if (link.image_number < 10){link.image_number++;}else{link.image_number = 1;}
        }
        if(keyUp){
            link.dest_y = link.dest_y - 4;
            direction = 20;
            if (link.image_number < 10){link.image_number++;}else{link.image_number = 1;}
        }
    }
}
