package rCLick;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import interactions.Overlap;
import panels.MyPanels;

public class Rotate extends JMenuItem implements ActionListener {
	private MyPanels panel;

	
	public Rotate(MyPanels panel) {
		this.panel = panel;
		this.setText("Rotate");
		this.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
						
			panel.setBounds(panel.getX(), panel.getY(), panel.getHeight(), panel.getWidth());
    		panel.pocket.setBounds(panel.getWidth()-10, panel.getHeight()-10, 10,10);
       		Overlap overlap = new Overlap(panel);
    		
    		if(overlap.overlapCheck()) {
    			
    			JOptionPane.showMessageDialog(null,"Rotation not Allowed", "WARNING", JOptionPane.WARNING_MESSAGE);
    			panel.setBounds(panel.getX(), panel.getY(), panel.getHeight(), panel.getWidth());
        		panel.pocket.setBounds(panel.getWidth()-10, panel.getHeight()-10, 10,10);
    		}
	}
}
