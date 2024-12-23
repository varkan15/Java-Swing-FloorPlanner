package rCLick;

import javax.swing.JPopupMenu;

import frame.MyFrame;
import panels.MyPanels;

public class ClickFrame extends JPopupMenu {
	
	
	public ClickFrame(MyPanels panel) {
		Delete delete = new Delete(panel);
		Rotate rotate = new Rotate(panel);
		AddRoom addRoom = new AddRoom(panel, MyFrame.frame);
		AddDoor addDoor = new AddDoor(panel, MyFrame.frame);
		AddWindow addWindow = new AddWindow(panel, MyFrame.frame);
		AddFurniture addFurniture = new AddFurniture(panel, MyFrame.frame);
		
		
		this.add(delete);
		this.add(rotate);
		this.add(addRoom);
		this.add(addDoor);
		this.add(addWindow);
		this.add(addFurniture);
	}
}
