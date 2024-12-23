package rClick2;

import javax.swing.JPopupMenu;

import frame.MyFrame;
import furniture.MyFurniture;


public class ClickFrame2 extends JPopupMenu {
	
	public ClickFrame2(MyFurniture panel) {
		Delete2 delete = new Delete2(panel);
		Rotate2 rotate = new Rotate2(panel);
		
		this.add(delete);
		this.add(rotate);
		
	}
}
