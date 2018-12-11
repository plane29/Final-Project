
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Font;
import java.awt.FontMetrics;


public class Room{  //main inspiration came from world in pong/keyboard spheres
    Graphics g;
    Font arial = new Font("Arial", Font.BOLD, 100);
    Area currentArea;
    Area areas[];
    Rectangle rectangles[];
    public Inventory myInventory;
    Image leftArrow;
    Image rightArrow;
    Image transparentLeft;
    int numAreas = 12;
    Area supArea[];
    Area previousArea;

    public Room(double initWidth, double initHeight){
    areas = new Area[numAreas];
    areas[0] = new Area(g, "LockedDoor.jpg",0, "DoorUnlocked.jpg");
    areas[1] = new Area(g, "DoorLeft.jpg",1);
    areas[2] = new Area(g, "FireplaceRight.jpg",2);
    areas[3] = new Area(g, "Fireplace.jpg",3);
    areas[4] = new Area(g, "FireplaceLeft.jpg",4);
    areas[5] = new Area(g, "Sofa.jpg", 5);
    areas[6] = new Area(g, "WindowRight.jpg",6);
    areas[7] = new Area(g, "Window.jpg",7, "WindowSolved.jpg");
    areas[8] = new Area(g, "WindowLeft.jpg",8 , "WindowLeftSolved.jpg");
    areas[9] = new Area(g, "ArmChairOff.jpg",9, "ArmChairOn.jpg");
    areas[10] = new Area(g, "Wall.jpg",10);
    areas[11] = new Area(g, "Desk.jpg",11);


    int k;
    int j;
    for(int i = 0; i<areas.length; i++){ //the -1 is because we added the puzzle
        k = (i+1)%numAreas;
        j = ((i-1)+numAreas)%numAreas;
        areas[i].setRightNeighbor(areas[j]);
        areas[i].setLeftNeighbor(areas[k]);
    }    
   /* areas[0].createHitBox((int)(initWidth-initWidth/2),(int)(initHeight/2), (int)(initWidth/9),(int)(initHeight/9));//this is the right hitbox
    areas[1].createHitBox((int)(initWidth/9),(int)(initHeight/3),100,100); //this is the left hitbox
    areas[4] = (new Area(g,"computer.jpg",6));
    areas[0].puzzle = areas[4];
    areas[4].rect.add(new Rectangle(0,640-100, 960, 100));
    areas[4].myArea=areas[0];
    */
    areas[3].createHitBox((int)(120*(initWidth/960.0)),(int)(380*(initHeight/640.0)),(int)(200*(initWidth/960.0)), (int)(150* (initHeight/640.0)));
    areas[4].createHitBox((int)(650*(initWidth/960.0)),(int)(400*(initHeight/640.0)),(int)(220*(initWidth/960.0)), (int)(200* (initHeight/640.0)));
    areas[5].createHitBox((int)(350*(initWidth/960.0)),(int)(360*(initHeight/640.0)),(int)(100*(initWidth/960.0)), (int)(80* (initHeight/640.0)));
    areas[7].createHitBox((int)(160*(initWidth/960.0)),(int)(310*(initHeight/640.0)),(int)(75*(initWidth/960.0)), (int)(70* (initHeight/640.0)));
    areas[8].createHitBox((int)(440*(initWidth/960.0)),(int)(350*(initHeight/640.0)),(int)(75*(initWidth/960.0)), (int)(75* (initHeight/640.0)));
    areas[9].createHitBox((int)(530*(initWidth/960.0)),(int)(385*(initHeight/640.0)),(int)(90*(initWidth/960.0)), (int)(95* (initHeight/640.0)));
    
    supArea = new Area[6];
    supArea[0] = new Area(g, "ChessPuzzle.jpg",12);
    areas[8].puzzle = supArea[0];
    areas[7].puzzle = supArea[0];
    supArea[1] = new Puzzle(g, "PianoPuzzle.jpg",13, "Keyboard.png");
    areas[3].puzzle = supArea[1];
    supArea[1].createHitBox((int)(225*(initWidth/960.0)),(int)(440*(initHeight/640.0)),(int)(25*(initWidth/960.0)), (int)(90* (initHeight/640.0)));
    supArea[1].createHitBox((int)(250*(initWidth/960.0)),(int)(440*(initHeight/640.0)),(int)(25*(initWidth/960.0)), (int)(90* (initHeight/640.0)));
    supArea[1].createHitBox((int)(275*(initWidth/960.0)),(int)(440*(initHeight/640.0)),(int)(25*(initWidth/960.0)), (int)(90* (initHeight/640.0)));
    supArea[1].createHitBox((int)(300*(initWidth/960.0)),(int)(440*(initHeight/640.0)),(int)(25*(initWidth/960.0)), (int)(90* (initHeight/640.0)));
    supArea[1].createHitBox((int)(325*(initWidth/960.0)),(int)(440*(initHeight/640.0)),(int)(25*(initWidth/960.0)), (int)(90* (initHeight/640.0)));
    supArea[1].createHitBox((int)(350*(initWidth/960.0)),(int)(440*(initHeight/640.0)),(int)(25*(initWidth/960.0)), (int)(90* (initHeight/640.0)));
    supArea[1].createHitBox((int)(375*(initWidth/960.0)),(int)(440*(initHeight/640.0)),(int)(25*(initWidth/960.0)), (int)(90* (initHeight/640.0)));
    supArea[1].rect.add(new Rectangle(0,640-100, 960, 100));
    areas[4].puzzle = supArea[1];
    supArea[2] = new Puzzle(g, "LightbulbOff.jpg", 14, "LightbulbOn.jpg");
    areas[9].puzzle = supArea[2];
    supArea[2].rect.add(new Rectangle(0,640-100, 960, 100));
    supArea[3] = new Puzzle(g, "RiddleQuestion.jpg",15, "RiddleCode.jpg");
    areas[5].puzzle = supArea[3];
    supArea[3].rect.add(new Rectangle(0,640-100, 960, 100));
    currentArea = areas[0];
    supArea[4] = new Puzzle(g, "ChessUnsolved.jpg",16, "ChessSolved.jpg");
    supArea[0].puzzle = supArea[4];
    supArea[0].createHitBox((int)(395*(initWidth/960.0)),(int)(360*(initHeight/640.0)),(int)(150*(initWidth/960.0)), (int)(100* (initHeight/640.0)));
    supArea[0].rect.add(new Rectangle(0,640-100, 960, 100));
    supArea[4].createHitBox((int)(430*(initWidth/960.0)),(int)(210*(initHeight/640.0)),(int)(50*(initWidth/960.0)), (int)(75* (initHeight/640.0)));
    supArea[4].createHitBox((int)(480*(initWidth/960.0)),(int)(195*(initHeight/640.0)),(int)(50*(initWidth/960.0)), (int)(40* (initHeight/640.0)));
    supArea[4].rect.add(new Rectangle(0,640-100, 960, 100));
    //supArea[5] = new Area (g, "ChessSolved.jpg", 17);
    //supArea[5].rect.add(new Rectangle(0,640-100, 960, 100));
    leftArrow = currentArea.loadArea(g,"LeftArrow.png");
    rightArrow = currentArea.loadArea(g, "RightArrow.png");
    //transparentLeft = currentArea.loadArea(g, "transparentRect.jpg");

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

    public void drawLeftArrow(Graphics g){
        g.drawImage(leftArrow, 20, 260, 90, 90, null);
    }

    public void drawRightArrow(Graphics g){
        g.drawImage(rightArrow, 820, 260, 90, 90, null);

    }





}
