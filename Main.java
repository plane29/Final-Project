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
    public Graphics g;
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
    public boolean correctPawn = false;
    public boolean cPressed = true;
    public boolean cClicked;
    public boolean eClicked;
    public boolean gClicked;
    public boolean bClicked;
    public boolean dClicked;
    public boolean fClicked;
	public Main(){
	    addMouseListener(this); //We got information and code about mouselistener from https://www.javatpoint.com/java-mouselistener.  This helped us know the methods and see an example.
        addKeyListener(this);
        setFocusable(true); //https://stackoverflow.com/questions/286727/unresponsive-keylistener-for-jframe
        r = new Room(WIDTH, HEIGHT);
	    this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        Thread mainThread = new Thread(new Runner());
        mainThread.start();
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
        if(r.currentArea.num == 15 && !r.currentArea.getSolved()){
            if(c == 'c'){
                cPressed = true;
                repaint();
            }
        }
    }

    public void mouseClicked(MouseEvent e) { //learned about mouse events on the website mentioned above. 
        int x = e.getX();
    	int y = e.getY();
    	Pair toCheck = new Pair(x,y);
    	if(checkPair(toCheck,r)){
    		if(inPuzzle){
                System.out.println("im in the puzzle");
    			if(checkBottom(toCheck,r)){
					inMyArea=true;
    				inPuzzle=false;
    				repaint();
    			}
                else if(r.currentArea.num == 13){
                    if(checkKeyboard(toCheck,r,2) || cClicked){
                        cClicked = true;
                        if(checkKeyboard(toCheck, r, 4) || eClicked){
                            eClicked = true;
                            if(checkKeyboard(toCheck, r, 6) || gClicked){
                                    gClicked = true;
                                if(checkKeyboard(toCheck, r, 8) || bClicked){
                                    bClicked = true;
                                    if(checkKeyboard(toCheck, r, 3) || dClicked){
                                        dClicked = true;
                                        if(checkKeyboard(toCheck, r, 5) || fClicked){
                                            fClicked = true;
                                            if(checkKeyboard(toCheck, r, 7)){
                                                r.currentArea.puzzleSolved();
                                                r.supArea[2].puzzleSolved();
                                                r.areas[9].puzzleSolved();
                                                repaint();
                                            }


                                        }

                                    }

                                }
                            }

                        }

                    }

                }
                else if(checkHitBox(toCheck,r)){
                hitBoxClicked = true;
                inPuzzle = true;
                //r.currentArea = r.currentArea.puzzle;
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
        if(!hello.inPuzzle && hello.r.currentArea.num!=16){
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
        //System.out.println("hello");
        int x = e.getX();
        int y = e.getY();
        Pair toCheck = new Pair(x,y); 
        if(r.currentArea.num == 16){
            if(!r.currentArea.getSolved() && checkChess(toCheck,r)){
                correctPawn = true;
            }
        }
    }  
    public void mouseReleased(MouseEvent e) {  
        //System.out.println("whats good");
        int x = e.getX();
        int y = e.getY();
        Pair toCheck = new Pair(x,y);
        if(r.currentArea.num == 16){
            if(!r.currentArea.getSolved() && checkChess2(toCheck,r) && correctPawn){
                repaint();
                //inPuzzle = true;
                //r.supArea[0].puzzle = r.supArea[5];


            System.out.println("whats good");
            }

        } 
       
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
        if(inPuzzle && r.currentArea.num !=16 && r.currentArea.num !=15 && r.currentArea.num != 13){
        	r.currentArea =r.currentArea.puzzle;
        }
        if(r.currentArea.num == 14 && r.currentArea.getSolved()){
            Area.myInventory.lockSolved = true;
        }
        if(correctPawn && !r.currentArea.getSolved()){
            System.out.println("were in this other thing");
            r.currentArea.puzzleSolved();
            r.areas[7].puzzleSolved();
            r.areas[8].puzzleSolved();
            r.areas[7].rect.remove(2);
            r.areas[8].rect.remove(2);
            Area.myInventory.pickSolved = true;
            correctPawn = false;
        }
        if(inMyArea){
        	r.currentArea = r.previousArea;
        	inMyArea=false;
        }
        //System.out.println(correctPawn);
        //System.out.println(rightClicked);
        //System.out.println(inPuzzle);
        //System.out.println(inMyArea);
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
        //g.drawRect(225, 440, 25, 90);
        //g.drawRect(250, 440, 25, 90);
        //g.drawRect(275, 440, 25, 90);
        //g.drawRect(300, 440, 25, 90);
        //g.drawRect(325, 440, 25, 90);
        //g.drawRect(350, 440, 25, 90);

    }

	public static void main(String[] args) {  //main idea taken from keyboard spheres/draw to screen
    	hello = new Main();  
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
		return r.currentArea.rect.get(r.currentArea.rect.size()-1).isIn(p);
	}

    public boolean checkChess(Pair p, Room r){
        return r.currentArea.rect.get(2).isIn(p);
    }

    public boolean checkChess2(Pair p, Room r){
        return r.currentArea.rect.get(3).isIn(p);
    }

    public boolean checkKeyboard(Pair p, Room r, int i){
        if(r.currentArea.rect.size()>i){    
            return r.currentArea.rect.get(i).isIn(p);
        }
        return false;
    }


    public void setFalse(){
        cClicked = false;
        eClicked = false;
        gClicked = false;
        bClicked = false;
        dClicked = false;
        fClicked = false;
        System.out.println("wrong code");
    }





}