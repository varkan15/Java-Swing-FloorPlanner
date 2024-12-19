package buttons;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JLayeredPane;
import javax.swing.border.Border;

import frame.MyFrame;
import interactions.Overlap;
import panels.MyPanels;

public class LivingRoom extends MyButton {
	
	public LivingRoom(int x, int y, MyFrame topFrame) {
        super(x, y, topFrame);
        this.setText("Living Room");
        this.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
    	
    	for(int j = 120; j <700; j = j+120) {
    		for(int i = 210; i< 1520; i = i+220){
            MyPanels livepanel = new MyPanels(i, j, 200, 100, Color.yellow);
            Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 5);
            livepanel.setBorder(lineBorder);
            
            Overlap overlap = new Overlap(livepanel);
            if(!overlap.overlapCheck()) {
            JLayeredPane layeredPane = topFrame.getLayeredPane();
            topFrame.add(livepanel);
            data.add(livepanel);
            layeredPane.add(livepanel, Integer.valueOf(2));
            topFrame.repaint();
            System.out.println("You have made a bedroom");
            return;
            }
    		}
    	}
        
    }
}
