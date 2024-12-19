	package panels;

import java.awt.Color;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import frame.MyFrame;
import furniture.MyFurniture;
import interactions.Overlap;
import rCLick.AddDoor;
import rCLick.AddFurniture;
import rCLick.AddWindow;
import rCLick.ClickFrame;
import buttons.MyButton;

public class MyPanels extends JPanel {
	
	int x;
	int y;
	int a;
	int b;
	public Color color;
	
	protected Point prevPoint;
	protected Point currentPoint;
    
    private int originalX;
    private int originalY;
    
    public boolean widthAligned = false;
    public boolean heightAligned = false;
    
    public HotPockets pocket;
    
    public static ArrayList<MyFurniture> movables = new ArrayList<MyFurniture>();
    
    public MyPanels(int a, int b, int x, int y, Color color) {
    	this.a = a;
    	this.b = b;
    	this.x = x;
    	this.y = y;
    	this.color = color;
    	
    	this.setBounds(a, b, x, y);
    	this.setBackground(color);
    	this.setLayout(null);
        
        Listener listener = new Listener();        
        this.addMouseListener(listener);
        this.addMouseMotionListener(listener);
        
        pocket = new HotPockets(this);
        this.add(pocket);
        
        
        
    }
    
public class Listener extends MouseAdapter{
    	
    	
        @Override
        public void mousePressed(MouseEvent e){
        	MyFrame topFrame = (MyFrame) MyPanels.this.getTopLevelAncestor();
        	JLayeredPane layeredPane = ((MyFrame) topFrame).getLayeredPane();
        	layeredPane.setLayer(MyPanels.this, Integer.valueOf(2));
        	
        	
            prevPoint = e.getPoint();
            originalX = MyPanels.this.getX();
            originalY = MyPanels.this.getY();
            

            
            for(MyFurniture nt : AddFurniture.data3) {
            	if( MyPanels.this.getBounds().contains(nt.getBounds())) {
            		 nt.wrtX = nt.getX() - MyPanels.this.getX();
            		 nt.wrtY = nt.getY() - MyPanels.this.getY();
            		 movables.add(nt);
            	}
            }
        }
    
        @Override
        public void mouseDragged(MouseEvent e){
        	
        	ArrayList<JPanel> removables1 = new ArrayList<JPanel>();
        	
        	for(DoorPanel el : AddDoor.data1) {
    			if( MyPanels.this.getBounds().intersects(el.getBounds())) {
    				Container parent = el.getParent();
    				
    				if (parent != null) {
    		            removables1.add(el);
    		            parent.remove(el);
    		            parent.repaint();
    		        } else {
    		            System.out.println("Warning: Parent container is null for element: " + el);
    		        }
    	    		
    			}
    		}
        	
        	ArrayList<JPanel> removables2 = new ArrayList<JPanel>();
        	for(WindowPanel nt : AddWindow.data2) {
    			if( MyPanels.this.getBounds().intersects(nt.getBounds())) {
    				Container parent = nt.getParent();
    				
    				if (parent != null) {
    		            removables2.add(nt);
    		            parent.remove(nt);
    		            parent.repaint();
    		        } else {
    		            System.out.println("Warning: Parent container is null for element: " + nt);
    		        }
    	    		
    			}
    		}
        	AddDoor.data1.removeAll(removables1);
        	AddWindow.data2.removeAll(removables2);
                
            currentPoint = e.getPoint();

            int transX = (int)(currentPoint.getX() - prevPoint.getX());
            int transY = (int)(currentPoint.getY() - prevPoint.getY());

            int newX = MyPanels.this.getX() + transX;
            int newY = MyPanels.this.getY() + transY;
            
            
            if (!MyPanels.this.widthAligned && !MyPanels.this.heightAligned) {
                MyPanels.this.setLocation(newX, newY);
            }
            
            for(MyFurniture el : movables) {        	
            	el.setLocation(el.wrtX + MyPanels.this.getX(), el.wrtY+ MyPanels.this.getY());
            }
            

            if (Math.abs(transY) > 10) MyPanels.this.widthAligned = false;
            if (Math.abs(transX) > 10) MyPanels.this.heightAligned = false;
           
            	
            	if (!MyPanels.this.widthAligned) {
            		
            		 for(MyPanels el : MyButton.data) {
            	
                     	if(MyPanels.this != el) {
                     //right alignment
            		if((Math.abs(newX - (el.getX()+ el.getWidth())) <=10)&&
            			((el.getY()<= newY && newY <= el.getY() + el.getHeight())||
            			 (el.getY()<= newY + MyPanels.this.getHeight() && newY+MyPanels.this.getHeight() <= el.getY() + el.getHeight())||
            			 (newY<=el.getY() && el.getY()+el.getHeight()<newY + MyPanels.this.getHeight()))){
            			MyPanels.this.setLocation(el.getX()+ el.getWidth(), newY);
            			MyPanels.this.widthAligned = true;
            			el.widthAligned = true;
            			break;
            		}
            		//left alignment
            		if((Math.abs( el.getX() - (MyPanels.this.getWidth() + newX)) <= 10)&&
            			((el.getY()<= newY && newY <= el.getY() + el.getHeight())||
            			 (el.getY()<= newY + MyPanels.this.getHeight() && newY+MyPanels.this.getHeight() <= el.getY() + el.getHeight())||
            			 (newY<=el.getY() && el.getY()+el.getHeight()<newY + MyPanels.this.getHeight()))) {
            			MyPanels.this.setLocation(el.getX() - MyPanels.this.getWidth(), newY);
            			MyPanels.this.widthAligned = true;
            			el.widthAligned = true;
            			break;
            		}
            	}
            }
       }
            
            if(!MyPanels.this.heightAligned) {
            	
            	 for(MyPanels el : MyButton.data) {
            		
                 	if(MyPanels.this != el) {
            		//bottom alignment
            		if((Math.abs(newY - (el.getY()+ el.getHeight()))<= 10)&&
            			((el.getX()<= newX && newX <= el.getX() + el.getWidth())||
            			 (el.getX()<= newX + MyPanels.this.getWidth() && newX+MyPanels.this.getWidth() <= el.getX() + el.getWidth())||
            			 (newX<=el.getX() && el.getX()+el.getWidth()<newX + MyPanels.this.getWidth()))) {
            			MyPanels.this.setLocation(newX, el.getY()+el.getHeight());
            			MyPanels.this.heightAligned = true;
            			el.heightAligned = true;
            			break;
            		}
            		//top alignment
            		if((Math.abs(el.getY() - (newY + MyPanels.this.getHeight())) <= 10)&&
            			((el.getX()<= newX && newX <= el.getX() + el.getWidth())||
            			(el.getX()<= newX + MyPanels.this.getWidth() && newX+MyPanels.this.getWidth() <= el.getX() + el.getWidth())||
            			(newX<=el.getX() && el.getX()+el.getWidth()<newX + MyPanels.this.getWidth()))) {
            			MyPanels.this.setLocation(newX, el.getY() - MyPanels.this.getHeight());
            			MyPanels.this.heightAligned = true;
            			el.heightAligned = true;
            			break;
            		}
            		
            	}
            }
         }
        }
        
        @Override
        public void mouseReleased(MouseEvent e){

            if(MyPanels.this.widthAligned) {
            	for(MyPanels el : MyButton.data) {
            		
                 	if(MyPanels.this != el) {
            		//bottom alignment
            		if((Math.abs(MyPanels.this.getY() - (el.getY()+ el.getHeight()))<= 20)&&
            			((el.getX()<= MyPanels.this.getX() && MyPanels.this.getX() <= el.getX() + el.getWidth())||
            			 (el.getX()<= MyPanels.this.getX() + MyPanels.this.getWidth() && MyPanels.this.getX()+MyPanels.this.getWidth() <= el.getX() + el.getWidth())||
            			 (MyPanels.this.getX()<=el.getX() && el.getX()+el.getWidth()<MyPanels.this.getX() + MyPanels.this.getWidth()))) {
            			MyPanels.this.setLocation(MyPanels.this.getX(), el.getY()+el.getHeight());
            			MyPanels.this.heightAligned = true;
            			el.heightAligned = true;
            			break;
            		}
            		//top alignment
            		if((Math.abs(el.getY() - (MyPanels.this.getY() + MyPanels.this.getHeight())) <= 20)&&
            			((el.getX()<= MyPanels.this.getX() && MyPanels.this.getX() <= el.getX() + el.getWidth())||
            			(el.getX()<= MyPanels.this.getX() + MyPanels.this.getWidth() && MyPanels.this.getX()+MyPanels.this.getWidth() <= el.getX() + el.getWidth())||
            			(MyPanels.this.getX()<=el.getX() && el.getX()+el.getWidth()<MyPanels.this.getX() + MyPanels.this.getWidth()))) {
            			MyPanels.this.setLocation(MyPanels.this.getX(), el.getY() - MyPanels.this.getHeight());
            			MyPanels.this.heightAligned = true;
            			el.heightAligned = true;
            			break;
            		}
            		
            	}
            }
            }
            
            if(MyPanels.this.heightAligned) {
            	for(MyPanels el : MyButton.data) {
                	
                 	if(MyPanels.this != el) {
                 //right alignment
        		if((Math.abs(MyPanels.this.getX() - (el.getX()+ el.getWidth())) <=20)&&
        			((el.getY()<= MyPanels.this.getY() && MyPanels.this.getY() <= el.getY() + el.getHeight())||
        			 (el.getY()<= MyPanels.this.getY() + MyPanels.this.getHeight() && MyPanels.this.getY()+MyPanels.this.getHeight() <= el.getY() + el.getHeight())||
        			 (MyPanels.this.getY()<=el.getY() && el.getY()+el.getHeight()<MyPanels.this.getY() + MyPanels.this.getHeight()))){
        			MyPanels.this.setLocation(el.getX()+ el.getWidth(), MyPanels.this.getY());
        			MyPanels.this.widthAligned = true;
        			el.widthAligned = true;
        			break;
        		}
        		//left alignment
        		if((Math.abs( el.getX() - (MyPanels.this.getWidth() + MyPanels.this.getX())) <= 20)&&
        			((el.getY()<= MyPanels.this.getY() && MyPanels.this.getY() <= el.getY() + el.getHeight())||
        			 (el.getY()<= MyPanels.this.getY() + MyPanels.this.getHeight() && MyPanels.this.getY()+MyPanels.this.getHeight() <= el.getY() + el.getHeight())||
        			 (MyPanels.this.getY()<=el.getY() && el.getY()+el.getHeight()<MyPanels.this.getY() + MyPanels.this.getHeight()))) {
        			MyPanels.this.setLocation(el.getX() - MyPanels.this.getWidth(), MyPanels.this.getY());
        			MyPanels.this.widthAligned = true;
        			el.widthAligned = true;
        			break;
        		}
        	}
        }
            }
            
            Overlap overlap = new Overlap(MyPanels.this);
            
    		if(overlap.overlapCheck()) {
    			JOptionPane.showMessageDialog(null,"Overlap Alert", "WARNING", JOptionPane.WARNING_MESSAGE);
    			MyPanels.this.setLocation(originalX,originalY);
    		}
    		
    		MyFrame topFrame = (MyFrame) MyPanels.this.getTopLevelAncestor();
            JLayeredPane layeredPane = ((MyFrame) topFrame).getLayeredPane();
            layeredPane.setLayer(MyPanels.this, Integer.valueOf(0));
            
            for(MyFurniture el : movables) {        	
            	el.setLocation(el.wrtX + MyPanels.this.getX(), el.wrtY+ MyPanels.this.getY());
            }
            
            movables.removeAll(movables);
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
        	if(e.getButton() == MouseEvent.BUTTON3) {
        		ClickFrame cframe = new ClickFrame(MyPanels.this);
        		cframe.show(MyPanels.this, e.getX(), e.getY());
        	
        	}
        }
    
    }
}
