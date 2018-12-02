
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Area extends JPanel{
	public static final int BOX_WIDTH = 960;
	public static final int BOX_HEIGHT = 640;
    public Image image;
    public static Graphics g;
    public Rectangle rect;
    public Area(Graphics g, String filename){
    	this.setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));
    	image = loadArea(g, filename);
    	rect = new Rectangle(550, 300, 100, 75);
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

    public void drawArea(Graphics g, Image img, int width, int height){
    	g.drawImage(img,0,0,width,height,null);
    }

    @Override    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  
        drawArea(g, image, BOX_WIDTH, BOX_HEIGHT);
        rect.rectangleDraw(g);
    }

    public static void main(String args[]){
    	JFrame frame = new JFrame("DrawToScreen");
        Area area1 = new Area(g,"conference.jpg");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(area1);
        frame.pack();
        frame.setVisible(true);
    }
}

