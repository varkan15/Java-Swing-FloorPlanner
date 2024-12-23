package buttons;

import java.awt.Container;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import frame.MyFrame;
import rCLick.AddDoor;
import rCLick.AddFurniture;
import rCLick.AddWindow;


public class Clear extends JButton implements ActionListener{
	
	public Clear(int x, int y) {
        this.setText("New File");
        this.addActionListener(this);
        this.setBounds(x, y, 100, 50);
    }
	
	public void actionPerformed(ActionEvent e) {
		clearFrame();
		MyFrame.currentfile = null;
	}
		
	public static void clearFrame() {	
		for(int i = MyButton.data.size() - 1; i >= 0; i--) {
			Component component = MyButton.data.get(i);
			Container parent = component.getParent();

			parent.remove(component);
			MyButton.data.remove(component);
			parent.repaint();
		}
		
		for(int i = AddDoor.data1.size() - 1; i >= 0; i--) {
			Component component = AddDoor.data1.get(i);
			Container parent = component.getParent();

			parent.remove(component);
			AddDoor.data1.remove(component);
			parent.repaint();
		}
		
		for(int i = AddWindow.data2.size() - 1; i >= 0; i--) {
			Component component = AddWindow.data2.get(i);
			Container parent = component.getParent();

			parent.remove(component);
			AddWindow.data2.remove(component);
			parent.repaint();
		}
		
		for(int i = AddFurniture.data3.size() - 1; i >= 0; i--) {
			Component component = AddFurniture.data3.get(i);
			Container parent = component.getParent();

			parent.remove(component);
			AddFurniture.data3.remove(component);
			parent.repaint();
		}
	}
}
