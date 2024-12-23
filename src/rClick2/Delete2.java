package rClick2;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import furniture.MyFurniture;
import rCLick.AddFurniture;

public class Delete2 extends JMenuItem implements ActionListener {
	
private MyFurniture panel;
	
	public Delete2(MyFurniture panel) {
		this.panel = panel;
		this.setText("Delete");
		this.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e){
		
        	Container parent = panel.getParent();
    		parent.remove(panel);
    		AddFurniture.data3.remove(panel);
    		parent.repaint();
    		System.out.println("You have removed panel");
    		panel = null;
    }
	
}



