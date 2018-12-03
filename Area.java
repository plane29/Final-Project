
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;

public class Area extends JPanel{
	public static final int BOX_WIDTH = 960;
	public static final int BOX_HEIGHT = 640;
    public Image image;
    public static Graphics g;
    public int num;
    //public Rectangle rect[];
    public Area leftNeighbor;
    public Area rightNeighbor;
    public Area forwardNeighbor;
    public Area backwardNeighbor;
    public static Inventory myInventory = new Inventory(BOX_WIDTH,BOX_HEIGHT, "flower.jpg");
    public ArrayList<Rectangle> rect;
    public Area(Graphics g, String filename, int index){
    	this.setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));
    	image = loadArea(g, filename);
    	rect = new ArrayList<Rectangle>(2);
    	rect.add(0, new Rectangle(0,0, 100, BOX_HEIGHT));
    	rect.add(1, new Rectangle(860,0,100,640));
    	num = index;
    	//myInventory = new Inventory(BOX_WIDTH,BOX_HEIGHT, "flower.jpg");
    	//rect[2] = new Rectangle()
    	//rect[3] =
    	//rect[2] = new Rectangle(550, 300, 100, 75);
    }

    public Image loadArea(Graphics g, String filename){
    	Image image;
    	try{
        	image = ImageIO.read(new File(filename));
    	} 
    	catch (IOException e) { 
    		image = createImage(100, 100);
    	}
    	return image;
    }

    public void drawArea(Graphics g){
    	//g.drawRect( 10, 10, 100, 100);
    	g.drawImage(image,0,0,BOX_WIDTH,BOX_HEIGHT,null);
    	myInventory.drawInventory(g);
    }

    @Override    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  
        drawArea(g);
        //rect.rectangleDraw(g);
    }

    public static void main(String args[]){
    	JFrame frame = new JFrame("DrawToScreen");
       Area area1 = new Area(g,"kitchen.jpg",0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(area1);
        frame.pack();
        frame.setVisible(true);
    }

    public void setRightNeighbor(Area neighbor){
    	this.rightNeighbor = neighbor;
    }

    public void setLeftNeighbor(Area neighbor){
    	this.leftNeighbor = neighbor;
    }

    public void setForwardNeighbor(Area neighbor){
    	forwardNeighbor = neighbor;
    }

    public void setBackwardNeighbor(Area neighbor){
    	backwardNeighbor = neighbor;
    }

    public void createHitBox(int x, int y, int width, int height){
    	rect.add(new Rectangle(x, y, width, height));
    }

}

