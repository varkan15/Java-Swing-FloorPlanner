package buttons;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JLayeredPane;
import javax.swing.border.Border;

import frame.MyFrame;
import interactions.Overlap;
import panels.MyPanels;

public class BedRoom extends MyButton{
    
    public BedRoom(int x, int y, MyFrame topFrame) {
        super(x, y, topFrame);
        this.setText("Bedroom");
        this.addActionListener(this);

    }
    
    @Override
    public void actionPerformed(ActionEvent e){
    	
    	for(int j = 120; j <700; j = j+120) {
    		for(int i = 210; i< 1520; i = i+220){
            MyPanels bedpanel = new MyPanels(i, j, 200, 100, new Color(85, 107, 47));
            Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 5);
            bedpanel.setBorder(lineBorder);
            
            Overlap overlap = new Overlap(bedpanel);
            if(!overlap.overlapCheck()) {
            JLayeredPane layeredPane = topFrame.getLayeredPane();
            topFrame.add(bedpanel);
            data.add(bedpanel);
            layeredPane.add(bedpanel, Integer.valueOf(2));
            topFrame.repaint();
            System.out.println("You have made a bedroom");
            return;
            }
    		}
    	}
        
    }
    
}
