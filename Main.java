import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.JFrame;

public class Main extends JPanel implements KeyListener, MouseListener{
	public int WIDTH = 960;
	public int HEIGHT = 640;
	public int FPS = 60;
	public static Room r;
	public boolean rightClicked;
	public boolean leftClicked;
	public boolean hitBoxClicked;
	public boolean inPuzzle;
	public boolean inMyArea;
	public Main(){
	addMouseListener(this); //We got information and code about mouselistener from https://www.javatpoint.com/java-mouselistener.  This helped us know the methods and see an example.
	r = new Room();
	this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}

	public void keyPressed(KeyEvent e) { //we copied this code from keyboard spheres
        char c=e.getKeyChar();
		System.out.println("You pressed down: " + c);

	}

    
    public void keyReleased(KeyEvent e) {
        char c=e.getKeyChar();
		System.out.println("\tYou let go of: " + c);
	
    }


    public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		System.out.println("You typed: " + c);
    }

    public void mouseClicked(MouseEvent e) { //learned about mouse events on the website mentioned above. 
    	int x = e.getX();
    	int y = e.getY();
    	Pair toCheck = new Pair(x,y);
    	if(checkPair(toCheck,r)){
    		if(inPuzzle){
    			if(checkBottom(toCheck,r)){
					inMyArea=true;
    				inPuzzle=false;
    				repaint();
    			}
    		}
    		else if(checkLeft(toCheck,r)){
    			leftClicked = true;
    			repaint();

     		}
    		else if(checkRight(toCheck,r)){
    			rightClicked = true;
    			repaint();
    		}
    		else if(checkHitBox(toCheck,r)){
    			hitBoxClicked = true;
    			if(r.currentArea.num == 1){
    				Area.myInventory.flowerClicked=true;
    				repaint();
    			}
    			else{

    				inPuzzle = true;
    				repaint();
    			}
    		}

    	}

    }  
    public void mouseEntered(MouseEvent e) {  //taken from website above necessary to implement mouselistener

    }  
    public void mouseExited(MouseEvent e) {  

    }  
    public void mousePressed(MouseEvent e) {  
  
    }  
    public void mouseReleased(MouseEvent e) {  

    }  

    @Override    
    public void paintComponent(Graphics g) { //main idea of paint component taken from keyboard spheres
        super.paintComponent(g);
        if(rightClicked){
        	r.currentArea = r.currentArea.rightNeighbor;
        	rightClicked = false;
        }
        if(leftClicked){
        	r.currentArea = r.currentArea.leftNeighbor;
        	leftClicked = false;
        }
        if(inPuzzle){
        	r.currentArea =r.currentArea.puzzle;
        }
        if(inMyArea){
        	r.currentArea = r.currentArea.myArea;
        	inMyArea=false;
        }
        r.drawRoom(g);
    }

	public static void main(String[] args) {  //main idea taken from keyboard spheres/draw to screen
    	Main hello = new Main();  
    	JFrame frame = new JFrame("Escape Room");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(hello);
        frame.pack();
        frame.setVisible(true);
	}  

	public boolean checkPair(Pair p, Room r){
		return r.isIn(p);
	}

	public boolean checkLeft(Pair p, Room r){
		return r.currentArea.rect.get(0).isIn(p);
	}

	public boolean checkRight(Pair p, Room r){
		return r.currentArea.rect.get(1).isIn(p);
	}

	public boolean checkHitBox(Pair p, Room r){
		if(r.currentArea.rect.size()>2){	
			return r.currentArea.rect.get(2).isIn(p);
		}
		return false;
	}

	public boolean checkBottom(Pair p, Room r){
		return r.currentArea.rect.get(2).isIn(p);
	}



}