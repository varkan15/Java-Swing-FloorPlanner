package buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import frame.MyFrame;

public class OpenFile extends JButton implements ActionListener{
	
	public OpenFile(int x, int y) {
        this.setText("Open File");
        this.addActionListener(this);
        this.setBounds(x, y, 100, 50);
    }
	
	public void actionPerformed(ActionEvent e) {
		Clear.clearFrame();
		MyFrame.frame.openFile();
		
		
	}
}
