package panels;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import buttons.MyButton;
import rCLick.AddDoor;
import rCLick.AddWindow;

public class DoorPanel extends JPanel {
	protected Point prevPoint;
	protected Point currentPoint;
    
    private int originalX;
    private int originalY;
    private MyPanels panel;
	
	public Color color1;
	public Color color2;
	public String direction;
	public int startO;
	public int extra;
	public int end;
	public int startD;
	
	public DoorPanel(String direction, Color color1, Color color2, int startO, int end, int startD, int extra) {
		this.direction = direction;
		this.color1 = color1;
		this.color2 = color2;
		this.startO = startO;
		this.end = end;
		this.extra = extra;
		this.startD = startD;
		
		if(("North".equals(direction))||("South".equals(direction))){
			DoorPanel.this.setBounds(startD,extra,60,10);
		}
		
		if(("East".equals(direction))||("West".equals(direction))){
			DoorPanel.this.setBounds(extra,startD,10,60);
		}
		
		 Listener1 listener = new Listener1();        
	     this.addMouseListener(listener);
	     this.addMouseMotionListener(listener);
	}
	
	 
	protected void paintComponent(Graphics g) {
		if("North".equals(direction)){
		        super.paintComponent(g);
		        
		        int width = DoorPanel.this.getWidth();
		        int height = DoorPanel.this.getHeight();
		        Graphics2D g2d = (Graphics2D) g;
		        g2d.setColor(color2);
		        g2d.fillRect(0, 0, width, height/2);
		        g2d.setColor(color1);
		        g2d.fillRect(0, height/2, width, height/2);
		    }
		
		if("South".equals(direction)){
	        super.paintComponent(g);
	        
	        int width = DoorPanel.this.getWidth();
	        int height = DoorPanel.this.getHeight();
	        Graphics2D g2d = (Graphics2D) g;
	        g2d.setColor(color1);
	        g2d.fillRect(0, 0, width, height/2);
	        g2d.setColor(color2);
	        g2d.fillRect(0, height/2, width, height/2);
	    }
		
		if ("East".equals(direction)) {
		    super.paintComponent(g);
		    
		    int width = DoorPanel.this.getWidth();
		    int height = DoorPanel.this.getHeight();
		    Graphics2D g2d = (Graphics2D) g;
		    g2d.setColor(color1);
		    g2d.fillRect(0, 0, width / 2, height);
		    g2d.setColor(color2);
		    g2d.fillRect(width / 2, 0, width / 2, height);
		}

		
		if("West".equals(direction)){
	        super.paintComponent(g);
	        
	        int width = DoorPanel.this.getWidth();
	        int height = DoorPanel.this.getHeight();
	        Graphics2D g2d = (Graphics2D) g;
	        g2d.setColor(color2);
	        g2d.fillRect(0, 0, width/2, height);
	        g2d.setColor(color1);
	        g2d.fillRect(width / 2, 0, width / 2, height);
	    }
	}
	
	public class Listener1 extends MouseAdapter{
		
		 @Override
	        public void mousePressed(MouseEvent e){
	        	
	            prevPoint = e.getPoint();
	            originalX = DoorPanel.this.getX();
	            originalY = DoorPanel.this.getY();
	            
	            if (DoorPanel.this.color2== Color.DARK_GRAY) {
				        for (MyPanels el : MyButton.data) {
				            if (DoorPanel.this.getBounds().intersects(el.getBounds())) {
				                panel = el;
				               break; // Exit after handling the overlap
				            }
				        }
				    }
	        }
		 
		 
		 public void mouseDragged(MouseEvent e){
			 currentPoint = e.getPoint();

	            int transX = (int)(currentPoint.getX() - prevPoint.getX());
	            int transY = (int)(currentPoint.getY() - prevPoint.getY());

	            int newX = DoorPanel.this.getX() + transX;
	            int newY = DoorPanel.this.getY() + transY;
	            
	            if(("North".equals(direction))||("South".equals(direction))) {
	            	if(startO+3<newX && newX+DoorPanel.this.getWidth()<end-3) {
	            		DoorPanel.this.setLocation(newX, originalY);
	            		DoorPanel.this.startD = newX;
	            	}
	            }
	            
	            if(("East".equals(direction))||("West".equals(direction))) {
	            	if(startO+3<newY && newY+DoorPanel.this.getHeight()<end-3) {
	            		DoorPanel.this.setLocation(originalX, newY);
	            		DoorPanel.this.startD = newY;
	            	}
	            }
		 	}
		 
		 public void mouseReleased(MouseEvent e) {
			 System.out.println("mouse released");
			 
			 
			 if (DoorPanel.this.color2== Color.DARK_GRAY) {
				 System.out.println("this is a main door");
			        for (MyPanels el : MyButton.data) {
			            if (DoorPanel.this.getBounds().intersects(el.getBounds())&&el!=panel) {
			                DoorPanel.this.setLocation(originalX, originalY);
			                return;
			            }
			        }
			    }
			 
			 for(WindowPanel el : AddWindow.data2) {
				 if(DoorPanel.this.getBounds().intersects(el.getBounds())) {
					 DoorPanel.this.setLocation(originalX, originalY);
				 }
			 }

		 }
		 
		 public void mouseClicked(MouseEvent e) {
	        	if(e.getButton() == MouseEvent.BUTTON3) {
	        		Container parent = DoorPanel.this.getParent();
	        		parent.remove(DoorPanel.this);
	        		AddDoor.data1.remove(DoorPanel.this);
	        		parent.repaint();
	        	}
	        }
	}
}
