import java.awt.Font;
import java.awt.FontMetrics;
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


public class Inventory extends JPanel{
	Font arial = new Font("Arial", Font.BOLD, 30);
	int x;
	int y;
	int width;
	int height;
	Image flower;
	Graphics g;
	boolean flowerClicked = false;

	public Inventory(int initWidth, int initHeight, String filename){
		//this.setPreferredSize(new Dimension(width, height));
		//x = initX;
		//y = initY;
		flower = loadInventory(g, filename);
		width = initWidth;
		height = initHeight;
	}

    public Image loadInventory(Graphics g, String filename){
    	Image image;
    	try{
        	image = ImageIO.read(new File(filename));
    	} 
    	catch (IOException e) { 
    		image = createImage(100, 100);
    	}
    	return image;
    }



	public void drawInventory(Graphics g){
		g.setFont(arial);
		FontMetrics metrics = g.getFontMetrics(arial);
		g.setColor(Color.WHITE);
		g.fillRect(100,height-105,metrics.stringWidth("Inventory"),33);
		g.setColor(Color.BLACK);
		g.drawString("Inventory", 100, height-80);
		if(flowerClicked){
			g.drawImage(flower, 100, height-60, 30, 40, null);
		}
	}
}
