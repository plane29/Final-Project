
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Room{  //main inspiration came from world in pong/keyboard spheres
    Graphics g;
    
    Area currentArea;
    Area areas[];
    Rectangle rectangles[];
    public Inventory myInventory;

    public Room(){
    areas = new Area[5];
    areas[0] = new Area(g, "conference.jpg",0);
    areas[1] = new Area(g, "kitchen.jpg",1);
    areas[2] = new Area(g, "image3.jpg",2);
    areas[3] = new Area(g, "image4.jpg",3);
    int k;
    int j;
    for(int i = 0; i<areas.length-1; i++){ //the -1 is because we added the puzzle
        k = (i+1)%4;
        j = ((i-1)+4)%4;
        areas[i].setRightNeighbor(areas[j]);
        areas[i].setLeftNeighbor(areas[k]);
    }    
    areas[0].createHitBox(550,300,100,75);
    areas[1].createHitBox(100,250,100,100);
    areas[4] = (new Area(g,"computer.jpg",6));
    areas[0].puzzle = areas[4];
    areas[4].rect.add(new Rectangle(0,640-100, 960, 100));
    areas[4].myArea=areas[0];
    currentArea = areas[0];

        }

        public boolean isIn(Pair p){
            for(int i =0; i<currentArea.rect.size();i++){
                if(currentArea.rect.get(i).isIn(p)) return true;
            }
            return false;
        }

        public void drawRoom(Graphics g){
            currentArea.drawArea(g);
        }

        public void updateRoom(Graphics g){

        }
}
