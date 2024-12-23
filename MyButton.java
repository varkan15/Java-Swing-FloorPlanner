package buttons;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import frame.MyFrame;
import panels.DoorPanel;
import panels.MyPanels;


public abstract class MyButton extends JButton implements ActionListener {
	
	public static ArrayList<MyPanels> data = new ArrayList<MyPanels>();
	protected MyFrame topFrame;
	
    public MyButton(int x, int y, MyFrame topFrame){
        this.setBounds(x, y, 100, 50);
        this.topFrame = topFrame;
    }

    @Override
    public abstract void actionPerformed(ActionEvent e);
}
