package rCLick;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import panels.MyPanels;
import buttons.MyButton;
import furniture.MyFurniture;


public class Delete extends JMenuItem implements ActionListener{
	
	private MyPanels panel;
	
	public Delete(MyPanels panel) {
		this.panel = panel;
		this.setText("Delete");
		this.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e){
		
        	Container parent = panel.getParent();
    		parent.remove(panel);
    		MyButton.data.remove(panel);
    		
    		for(MyFurniture el: AddFurniture.data3) {
    			if(panel.getBounds().contains(el.getBounds()))
    			parent.remove(el);
    		}
    		MyPanels.movables.removeAll(MyPanels.movables);
    		parent.repaint();
    		System.out.println("You have removed panel");
    		
    		panel = null;
    }
	
}
