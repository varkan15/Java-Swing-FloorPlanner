package interactions;

import panels.MyPanels;

import javax.swing.JPanel;

import buttons.MyButton;

public class Overlap {
	private JPanel panel;
	
	public Overlap(JPanel panel){
		this.panel = panel;
	}

	
	public boolean overlapCheck(){
		
		for(MyPanels el : MyButton.data) {
			if(panel != el && panel.getBounds().intersects(el.getBounds())) {
				return true;
			}
		}
		return false;
	}
}
