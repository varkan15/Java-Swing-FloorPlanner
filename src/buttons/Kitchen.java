package buttons;

import java.awt.Color;
import java.awt.event.ActionEvent;

import frame.MyFrame;
import interactions.Overlap;

import javax.swing.*;
import javax.swing.border.Border;

import panels.HotPockets;
import panels.MyPanels;

public class Kitchen extends MyButton {

    public Kitchen(int x, int y, MyFrame topFrame) {
        super(x, y, topFrame);
        this.setText("Kitchen");
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
    	
    	for(int j = 120; j <700; j = j+120) {
    		for(int i =210; i< 1520; i = i+220){    		
            MyPanels kitpanel  = new MyPanels(i, j, 200, 100, Color.red);
            //HotPockets pocket = new HotPockets(kitpanel);
            Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 5);
            kitpanel.setBorder(lineBorder);
            
            Overlap overlap = new Overlap(kitpanel);
            if(!overlap.overlapCheck()) {
            JLayeredPane layeredPane = topFrame.getLayeredPane();
            topFrame.add(kitpanel);
            data.add(kitpanel);
            layeredPane.add(kitpanel, Integer.valueOf(2));
            //layeredPane.add(pocket, Integer.valueOf(3));
            topFrame.repaint();
            System.out.println("You have made a kitchen");
            return;
            }
    	}
    }
            
   }
}

