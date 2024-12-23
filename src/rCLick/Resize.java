package rCLick;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import interactions.Overlap;
import panels.DoorPanel;
import panels.MyPanels;

public class Resize extends JMenuItem implements ActionListener{
	
	private MyPanels panel;
	
	public Resize(MyPanels panel) {
		this.panel = panel;
		this.setText("Resize");
		this.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		JTextField widthField = new JTextField(5);
	    JTextField heightField = new JTextField(5);

	      JPanel myPanel = new JPanel();
	      myPanel.add(new JLabel("width:"));
	      myPanel.add(widthField);
	      myPanel.add(new JLabel("height:"));
	      myPanel.add(heightField);

	      int result = JOptionPane.showConfirmDialog(null, myPanel, 
	               "Please enter new dimensions", JOptionPane.OK_CANCEL_OPTION);
	      if (result == JOptionPane.OK_OPTION) {
	    	  
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
	        	
	    	  int prevW = panel.getWidth();
	    	  int prevH = panel.getHeight();
	         panel.setBounds(panel.getX(), panel.getY(),  Integer.parseInt(widthField.getText()),  Integer.parseInt(heightField.getText()));
	         
	         Overlap overlap = new Overlap(panel);
	         if(overlap.overlapCheck()) {
	        	 JOptionPane.showMessageDialog(null,"Overlap Alert", "WARNING", JOptionPane.WARNING_MESSAGE);
	        	 panel.setBounds(panel.getX(), panel.getY(),  prevW,  prevH);
	         }
	         
	 
	      }
	}
}
