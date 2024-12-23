package buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import frame.MyFrame;

public class SaveFile extends JButton implements ActionListener{
	
	public SaveFile(int x, int y) {
        this.setText("Save File");
        this.addActionListener(this);
        this.setBounds(x, y, 100, 50);
    }
	
	public void actionPerformed(ActionEvent e) {
		MyFrame.frame.saveFile();
	}
}
