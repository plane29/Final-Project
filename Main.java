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
	Label l;
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
		addMouseListener(this);
		r = new Room();
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		//Thread mainThread = new Thread(new Runner());
		//mainThread.start();
		/*l = new Label();
		l.setBounds(20,50,100,20);
		add(l);
		setSize(100,300);
		setLayout(null);
		setVisible(true);*/
	}

	public void keyPressed(KeyEvent e) {
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

    public void mouseClicked(MouseEvent e) {  
    	//l.setText("Mouse Clicked");  
    	int x = e.getX();
    	int y = e.getY();
    	Pair toCheck = new Pair(x,y);
    	if(checkPair(toCheck,r)){
    		if(inPuzzle){
    			if(checkBottom(toCheck,r)){
    				System.out.println("hello my dude");
    				inMyArea=true;
    				inPuzzle=false;
    				repaint();
    			}
    		}
    		else if(checkLeft(toCheck,r)){
    			leftClicked = true;
    			repaint();
    			//System.out.println("hhellloooooo");
    			//r.currentArea = r.areas[1];
    			//repaint();
     		}
    		else if(checkRight(toCheck,r)){
    			//System.out.println("whats good");
    			rightClicked = true;
    			repaint();
    		}
    		else if(checkHitBox(toCheck,r)){
    			hitBoxClicked = true;
    			if(r.currentArea.num == 1){
    				System.out.println("helloooooo");
    				Area.myInventory.flowerClicked=true;
    				repaint();
    			}
    			else{

    				inPuzzle = true;
    				repaint();
    			}
    		}

    	}
    	//l.setText(x+ " ");

    }  
    public void mouseEntered(MouseEvent e) {  
        //l.setText("Mouse Entered");  
    }  
    public void mouseExited(MouseEvent e) {  
        //l.setText("Mouse Exited");  
    }  
    public void mousePressed(MouseEvent e) {  
        //l.setText("Mouse Pressed");  
    }  
    public void mouseReleased(MouseEvent e) {  
        //l.setText("Mouse Released");  
    }  

    @Override    
    public void paintComponent(Graphics g) {
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
        //rect.rectangleDraw(g);
    }
	public static void main(String[] args) {  
    	Main hello = new Main();  
    	JFrame frame = new JFrame("DrawToScreen");
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

	/*class Runner implements Runnable{
	public void run()  //runs to update the spheres and rectangles
	{
        while(true){
		//world.updateSpheres(1.0 / (double)FPS);
        //world.updateRectangles(1.0/ (double)FPS);
		repaint(); //paints again
		try{
		    Thread.sleep(1000/FPS);
		}
		catch(InterruptedException e){}
	    }
    
	}
	}*/



}