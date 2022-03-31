/*
Michael Justis
03/31/2022
Assignment5
'Polymorphism implemented and pots and boomerangs added, pots break when colliding with Link or a boomerang'
*/
import java.util.ArrayList;
import java.util.zip.GZIPOutputStream;

class Model
{
    int dest_x;
    int dest_y;
    //ArrayList<Brick> bricks;
    ArrayList<Sprite> sprites;
    Sprite link;
    Sprite boomerang;
    View view;
    boolean active = false;
    boolean potSelector = false;
    int movePot = -1;
    boolean isBoomerang = false;
    int Direction;
    int score = 0;
    
	Model(Sprite l)
	{
        sprites = new ArrayList<Sprite>();
        link = l;

        //sprites.add(new Link(100, 200));
	}

    public void update()
    {
        
        link.update();

        for(int i = 0; i < sprites.size(); i++){
            if (isThereACollision(link, sprites.get(i))){
                System.out.println("COLLIDING");
                if (link.isLink()){
                    ((Link)link).getOutOfSprite(sprites.get(i));
                    if (sprites.get(i).isPot()){
                        Direction = Controller.direction;
                        movePot = i;
                    }
                }
            }
        }

        if (movePot >= 0)
        {
            

            if (Direction == 30){
                sprites.get(movePot).x += 10;
            } else if (Direction == 10){
                sprites.get(movePot).x -= 10;
            } else if (Direction == 0){
                sprites.get(movePot).y += 10;
            } else if (Direction == 20){
                sprites.get(movePot).y -= 10;
            }

            //sprites.get(movePot).x -= View.scrollposx;
            //sprites.get(movePot).y -= View.scrollposy;
            for (int i = 0; i < sprites.size(); i++)
            {
                if (isThereACollision(sprites.get(movePot), sprites.get(i)))
                {
                    if (sprites.get(i).isBrick()){
                        sprites.get(movePot).isBroke();
                        movePot = -1;
                        score++;
                        System.out.print("Hit!");
                    }
                    break;
                }
            }
        }

        for (int i = 0; i < sprites.size(); i++){
            if (sprites.get(i).isPot()){
                if (sprites.get(i).getDelay()){
                    sprites.remove(i);
                }
            }
        }

        if (isBoomerang){
            for (int i = 0; i < sprites.size(); i++)
            {
                if (isThereACollision(boomerang, sprites.get(i))){
                    if (sprites.get(i).isBrick()){
                        isBoomerang = false;
                    }else if (sprites.get(i).isPot()){
                        isBoomerang = false;
                        sprites.get(i).isBroke();
                        score++;
                    }
                } 
                
            }


            if (Direction == 30){
                boomerang.x += 10;
            } else if (Direction == 10){
                boomerang.x -= 10;
            } else if (Direction == 0){
                boomerang.y += 10;
            } else if (Direction == 20){
                boomerang.y -= 10;
            }
        }

        if(link.x < link.dest_x)
            link.x += Math.min(4, link.dest_x - link.x);
        else if(link.x > link.dest_x)
            link.x -= Math.max(4, link.dest_x - link.x);
        if(link.y < link.dest_y)
            link.y += Math.min(4, link.dest_y - link.y);
        else if(link.y > link.dest_y)
            link.y -= Math.max(4, link.dest_y - link.y);

        //System.out.println("x: " + link.x + "y: " + link.y);
    }
    
    public void setDestination(int x, int y)
    {
        link.dest_x = x;
        link.dest_y = y;
    }

    boolean isThereACollision (Sprite l, Sprite b)
    {
        //l.x = l.x + View.scrollposx;
        if (l.isLink()){
            if (l.x + View.scrollposx >= b.x + b.w + 25){
                return false;
            } else if (l.x + View.scrollposx + l.w <= b.x){
                return false;
            } else if (l.y + View.scrollposy >= b.y + b.h + 25){
                return false;
            } else if (l.y + View.scrollposy + l.h <= b.y){
                return false;
            }
            return true;
        } else {
            if (l.x >= b.x + b.w + 25){
                return false;
            } else if (l.x + l.w <= b.x){
                return false;
            } else if (l.y >= b.y + b.h + 25){
                return false;
            } else if (l.y + l.h <= b.y){
                return false;
            }
            return true;
        }
    }

    public void throwBoomerang()
    {
        boomerang = new Boomerang(link.x + 10 + View.scrollposx, link.y + 40 + View.scrollposy);
        isBoomerang = true;

        Direction = Controller.direction;
    }
    
    public void isThereABrick(int mousex, int mousey)
    {
        //System.out.println("You just clicket at (" + x + ", " + y + ")");
        int x = mousex - mousex % 50;
        int y = mousey - mousey % 50;
        boolean found = false;
        for (int i = 0; i < sprites.size(); i++)
        {
            if(x >= sprites.get(i).x && x <= sprites.get(i).x+sprites.get(i).w && y >= sprites.get(i).y && y <= sprites.get(i).y+sprites.get(i).h)
            {
                sprites.remove(sprites.get(i));
                found = true;
                break;
            }
        }
        
        if (!found){
            if (!potSelector){
                sprites.add(new Brick(x, y));
            }else {
                sprites.add(new Pot(x, y));
            }
        }
    }
    
    void unmarshal(Json ob)
    {
        sprites = new ArrayList<Sprite>();
        Json tmpList = ob.get("brick");
        for(int i = 0; i < tmpList.size(); i++){
            sprites.add(new Brick(tmpList.get(i)));
        }
        Json tmpList2 = ob.get("pot");
        for(int i = 0; i < tmpList2.size(); i++){
            sprites.add(new Pot(tmpList2.get(i)));
        }
    }
    
    Json marshal()
    {
        Json ob = Json.newObject();
        Json tmpList = Json.newList();
        ob.add("brick", tmpList);
        for(int i = 0; i < sprites.size(); i++){
            if (sprites.get(i).isBrick()){
                tmpList.add((sprites.get(i)).marshal());
            }
        }
        Json tmpList2 = Json.newList();
        ob.add("pot", tmpList2);
        for(int i = 0; i < sprites.size(); i++){
            if (sprites.get(i).isPot()){
                tmpList2.add((sprites.get(i)).marshal());
            }
        }
        return ob;
    }
}
