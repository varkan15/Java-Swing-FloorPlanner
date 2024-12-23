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

public class WindowPanel extends JPanel {
	protected Point prevPoint;
	protected Point currentPoint;
    
    private int originalX;
    private int originalY;
    private MyPanels panel;
	
	public Color color;
	public String direction;
	public int startOW;
	public int extra;
	public int startDW;
	public int end;
	
	public WindowPanel(String direction, Color color, int startOW, int end, int startDW, int extra) {
		this.direction = direction;
		this.color = color;
		this.startOW = startOW;
		this.end = end;
		this.startDW = startDW;
		this.extra = extra;
		
		if(("North".equals(direction))||("South".equals(direction))){
			WindowPanel.this.setBounds(startDW+5,extra,60,10);
		}
		
		if(("East".equals(direction))||("West".equals(direction))){
			WindowPanel.this.setBounds(extra,startDW+5,10,60);
		}
		
		 Listener2 listener = new Listener2();        
	     this.addMouseListener(listener);
	     this.addMouseMotionListener(listener);
	}
	
	protected void paintComponent(Graphics g) {
		if("North".equals(direction)){
		        super.paintComponent(g);
		        
		        int width = WindowPanel.this.getWidth();
		        int height = WindowPanel.this.getHeight();
		        Graphics2D g2d = (Graphics2D) g;
		        for(int i = 0; i<49; i += 12) {
		        	g2d.setColor(color);
		        	g2d.fillRect(i, height/2, i+6, height/2);
		        	g2d.setColor(Color.black);
		        	g2d.fillRect(i+6, height/2, i+12, height/2);
		        }
		        g2d.setColor(Color.DARK_GRAY);
		        g2d.fillRect(0, 0, width, height/2);
		    }
		
		if("South".equals(direction)){
			super.paintComponent(g);
	        
	        int width = WindowPanel.this.getWidth();
	        int height = WindowPanel.this.getHeight();
	        Graphics2D g2d = (Graphics2D) g;
	        for(int i = 0; i<49; i += 12) {
	        	g2d.setColor(color);
	        	g2d.fillRect(i, 0, i+6, height/2);
	        	g2d.setColor(Color.black);
	        	g2d.fillRect(i+6, 0, i+12, height/2);
	        }
	        g2d.setColor(Color.DARK_GRAY);
	        g2d.fillRect(0, height/2, width, height/2);
	    }
		
		if("East".equals(direction)){
	        super.paintComponent(g);
	        
	        int width = WindowPanel.this.getWidth();
	        int height = WindowPanel.this.getHeight();
	        Graphics2D g2d = (Graphics2D) g;
	        for(int j=0; j<49; j+=12) {
	        	g2d.setColor(color);
	        	g2d.fillRect(0, j, width/2, j+6);
	        	g2d.setColor(Color.black);
	        	g2d.fillRect(0, j+6, width/2, j+12);	        	
	        }	        
	        g2d.setColor(Color.DARK_GRAY);
	        g2d.fillRect(width/2, 0, width/2, height);
	    }

		
		if("West".equals(direction)){
	        super.paintComponent(g);
	        
	        int width = WindowPanel.this.getWidth();
	        int height = WindowPanel.this.getHeight();
	        Graphics2D g2d = (Graphics2D) g;
	        for(int j=0; j<49; j+=12) {
	        	g2d.setColor(color);
	        	g2d.fillRect(width/2, j, width/2, j+6);
	        	g2d.setColor(Color.black);
	        	g2d.fillRect(width/2, j+6, width/2, j+12);	        	
	        }	        
	        g2d.setColor(Color.DARK_GRAY);
	        g2d.fillRect(0, 0, width/2, height);
	    }
	}
	
	public class Listener2 extends MouseAdapter{
		
		 @Override
	        public void mousePressed(MouseEvent e){
	        	
	            prevPoint = e.getPoint();
	            originalX = WindowPanel.this.getX();
	            originalY = WindowPanel.this.getY();
	            
			        for (MyPanels el : MyButton.data) {
			            if (WindowPanel.this.getBounds().intersects(el.getBounds())) {
			                panel = el;
			               break; // Exit after handling the overlap
			            }
			        }
			    
	        }
		 
		 
		 public void mouseDragged(MouseEvent e){
			 currentPoint = e.getPoint();

	            int transX = (int)(currentPoint.getX() - prevPoint.getX());
	            int transY = (int)(currentPoint.getY() - prevPoint.getY());

	            int newX = WindowPanel.this.getX() + transX;
	            int newY = WindowPanel.this.getY() + transY;
	            
	            if(("North".equals(direction))||("South".equals(direction))) {
	            	if(startOW+3<newX && newX+WindowPanel.this.getWidth()<end-3) {
	            		WindowPanel.this.setLocation(newX, originalY);
	            		WindowPanel.this.startDW = newX;
	            	}
	            }
	            
	            if(("East".equals(direction))||("West".equals(direction))) {
	            	if(startOW+3<newY && newY+WindowPanel.this.getHeight()<end-3) {
	            		WindowPanel.this.setLocation(originalX, newY);
	            		WindowPanel.this.startDW = newY;
	            	}
	            }
	            
		 	}
		 
		 public void mouseReleased(MouseEvent e) {
			 

				// System.out.println("this is a main door");
			        for (MyPanels el : MyButton.data) {
			            if (WindowPanel.this.getBounds().intersects(el.getBounds())&&el!=panel) {
			                WindowPanel.this.setLocation(originalX, originalY);
			                return;
			            }
			        }
			    
			 
			 for(DoorPanel el : AddDoor.data1) {
				 if(WindowPanel.this.getBounds().intersects(el.getBounds())) {
					 WindowPanel.this.setLocation(originalX, originalY);
				 }
			 }

		 }
		 
		 public void mouseClicked(MouseEvent e) {
	        	if(e.getButton() == MouseEvent.BUTTON3) {
	        		Container parent = WindowPanel.this.getParent();
	        		parent.remove(WindowPanel.this);
	        		AddWindow.data2.remove(WindowPanel.this);
	        		parent.repaint();
	        	}
	        }
	}
}

