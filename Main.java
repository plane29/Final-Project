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
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.io.IOException;

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
    public boolean mouseInLeft;
    public boolean mouseInRight;
    public static Main hello;
    public Point p;
    public Pair mouseLocation = new Pair(0,0);
	public Main(){
	addMouseListener(this); //We got information and code about mouselistener from https://www.javatpoint.com/java-mouselistener.  This helped us know the methods and see an example.
	r = new Room(WIDTH, HEIGHT);
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
                else if(checkHitBox(toCheck,r)){
                hitBoxClicked = true;
                inPuzzle = true;
                r.currentArea = r.currentArea.puzzle;
                repaint();
                hitBoxClicked = false;

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
                System.out.println("its in the hitbox");
    			hitBoxClicked = true;
                //repaint();
                //System.out.println(r.currentArea.num != 8 && r.currentArea.num !=3);

    				inPuzzle = true;
                    r.previousArea = r.currentArea;
    				repaint();
                    //inPuzzle = false;
                hitBoxClicked = false;
    		}

    	}

    }  
    public static void listenToMouse(){
        try{
            hello.p = MouseInfo.getPointerInfo().getLocation();
        }
        catch(NullPointerException e){
            hello.p = new Point(300,300);
        }

        hello.mouseLocation =  new Pair(hello.p.x,hello.p.y);
        if(!hello.inPuzzle){
            if(hello.checkRight(hello.mouseLocation,r)){
                hello.mouseInRight = true;
                //System.out.println(" yo whats up 29");
                hello.repaint();
            }
            else if(hello.checkLeft(hello.mouseLocation,r)){
                hello.mouseInLeft = true;
                //System.out.println(" yo whats up eric");
                hello.repaint();
            }
            else{
                hello.repaint();
            }
        }

    }
    public void mouseEntered(MouseEvent e) {  //taken from website above necessary to implement mouselistener
        //System.out.println("mouse has entered listened area");
    }  
    public void mouseExited(MouseEvent e) {  
        //System.out.println("mouse has exited listened area");
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
        	r.currentArea = r.previousArea;
        	inMyArea=false;
        }
        //g.drawRect(r.currentArea.rect.get(2).x,r.currentArea.rect.get(2).y, r.currentArea.rect.get(2).width, r.currentArea.rect.get(2).height);
        r.drawRoom(g);
        if(mouseInLeft){
            r.drawLeftArrow(g);
            hello.mouseInLeft = false;
        }
        if(mouseInRight){
            r.drawRightArrow(g);
            hello.mouseInRight = false;
        }
        g.drawRect(395, 360, 150, 100);
    }

	public static void main(String[] args) {  //main idea taken from keyboard spheres/draw to screen
    	hello = new Main();  
    	JFrame frame = new JFrame("Escape Room");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(hello);
        Thread mainThread = new Thread(new Runner());
        mainThread.start();
        frame.pack();
        frame.setVisible(true);
	}  

	public boolean checkPair(Pair p, Room r){
		return r.isIn(p);
	}

	public boolean checkLeft(Pair p, Room r){
        if(!inPuzzle){
		  return r.currentArea.rect.get(0).isIn(p);
        }
        return false;
	}

	public boolean checkRight(Pair p, Room r){
        if(!inPuzzle){
		  return r.currentArea.rect.get(1).isIn(p);
        }
        return false;
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