package panels;

import java.awt.Color;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import interactions.Overlap;
import rCLick.AddDoor;

public class HotPockets extends JPanel {
	private MyPanels panel;
	
	public int wrtHPX;
	public int wrtHPY;
	
	private Point p1;
	private Point p2;
	
	private int prev1;
	private int prev2;
	
	private int original1;
	private int original2;
	
	public HotPockets(MyPanels panel) {
		this.panel = panel;
		this.setBounds(panel.getWidth()-10, panel.getHeight()-10, 10,10);
		this.setBackground(Color.BLACK);
		
		Listener4 listener = new Listener4();        
        this.addMouseListener(listener);
        this.addMouseMotionListener(listener);
	}
	
	public class Listener4 extends MouseAdapter{
		
		public void mousePressed(MouseEvent e) {			
			p1 = e.getPoint();
			original1 = HotPockets.this.getX();
			original2 = HotPockets.this.getY();
			
			 prev1 = panel.getWidth();
			 prev2 = panel.getHeight();
		}
		
		public void mouseDragged(MouseEvent e) {
			
			ArrayList<DoorPanel> removables = new ArrayList<DoorPanel>();
        	
        	for(DoorPanel el : AddDoor.data1) {
    			if( panel.getBounds().intersects(el.getBounds())) {
    				Container parent = el.getParent();
    	    		removables.add(el);
    	    		parent.remove(el);
    	    		parent.repaint();
    			}
    		}
        	
        	AddDoor.data1.removeAll(removables);
			
			p2 = e.getPoint();

            int transX = (int)(p2.getX() - p1.getX());
            int transY = (int)(p2.getY() - p1.getY());

            int newX = HotPockets.this.getX() + transX;
            int newY = HotPockets.this.getY() + transY;
            
            HotPockets.this.setLocation(newX,newY);
            panel.setBounds(panel.getX(), panel.getY(), panel.getWidth()+transX,panel.getHeight()+transY );
		}
		
		public void mouseReleased(MouseEvent e) {
			 Overlap overlap = new Overlap(panel);
	         if(overlap.overlapCheck()) {
	        	 JOptionPane.showMessageDialog(null,"Overlap Alert", "WARNING", JOptionPane.WARNING_MESSAGE);
	        	 panel.setBounds(panel.getX(), panel.getY(),  prev1,  prev2);
	        	 HotPockets.this.setLocation(original1, original2);
	         }
		}
	}
	
}
