package buttons;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLayeredPane;
import javax.swing.border.Border;

import frame.MyFrame;
import interactions.Overlap;
import panels.MyPanels;

public class Bathroom extends MyButton{
    
    public Bathroom(int x, int y, MyFrame topFrame) {
        super(x, y, topFrame);
        this.setText("Bathroom");
        this.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
    	
    	for(int j = 120; j <700; j = j+120) {
    		for(int i = 210; i< 1520; i = i+220){
            MyPanels bathpanel = new MyPanels(i, j, 200, 100, Color.blue);
            Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 5);
            bathpanel.setBorder(lineBorder);
            
            Overlap overlap = new Overlap(bathpanel);
            if(!overlap.overlapCheck()){
            JLayeredPane layeredPane = topFrame.getLayeredPane();
            topFrame.add(bathpanel);
            data.add(bathpanel);
            layeredPane.add(bathpanel, Integer.valueOf(2));
            topFrame.repaint();
            System.out.println("You have made a bathroom");
            return;
            
            }
    	}
    }
    	
    	 topFrame.addMouseListener(new MouseAdapter() {
 			public void mouseClicked(MouseEvent e) {
 				System.out.println("Mouse clicked!");
 			}
         });
        
   }
    
}

