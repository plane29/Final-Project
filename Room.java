
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Room{
    Graphics g;
    
    Area currentArea;
    Area areas[];
    Rectangle rectangles[];

    public Room(){//int initWidth, int initHeight, int initNumSpheres, Pair[] initialPosition){
	//width = initWidth;
    areas = new Area[4];
    areas[0] = new Area(g, "conference.jpg");
    areas[1] = new Area(g, "image2.jpg");
    areas[2] = new Area(g, "image3.jpg");
    areas[3] = new Area(g, "image4.jpg");
    int k;
    int j;
    for(int i = 0; i<areas.length; i++){
        k = (i+1)%4;
        j = ((i-1)+4)%4;
        areas[i].setRightNeighbor(areas[j]);
        areas[i].setLeftNeighbor(areas[k]);
    }    
    areas[0].createHitBox(550,300,100,75);
    //areas[0].rect.add(new Rectangle(550, 300, 100, 75));
    currentArea = areas[0];
    

	/*numSpheres = initNumSpheres;
	spheres  = new Sphere[numSpheres];
    rectangles = new Rectangle[2];
	for (int i = 0; i < 2; i ++)
        {
        rectangles[i] = new Rectangle(initialPosition[i]);
        }

	for (int i = 0; i < numSpheres; i ++)
	    {
		spheres[i] = new Sphere();
	    }

    }

    public void drawSpheres(Graphics g){
	for (int i = 0; i < numSpheres; i++){
	    spheres[i].draw(g);
	}
    }

    public void drawRectangles(Graphics g){
        for(int i = 0; i<2; i++){
            rectangles[i].draw(g);
        }
    }

    public void updateSpheres(double time){
	for (int i = 0; i < numSpheres; i ++)
	    spheres[i].update(this, time);
    }

    public void updateRectangles(double time){
        for(int i = 0; i < 2; i++){
            rectangles[i].update(this, time);
        }
    }
*/
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
