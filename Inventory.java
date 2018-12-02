import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.FontMetrics;


public class Inventory{
	Font arial = new Font("Arial", Font.BOLD, 400);
	int x;
	int y;
	int width;
	int height;

	public Inventory(int initWidth, int initHeight){
		//this.setPreferredSize(new Dimension(width, height));
		//x = initX;
		//y = initY;
		width = initWidth;
		height = initHeight;
	}

	public void drawInventory(Graphics g){
		FontMetrics metrics = g.getFontMetrics(arial);
		g.drawString("Inventory", 130, height-100);
	}
}
