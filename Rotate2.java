package rClick2;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import furniture.MyFurniture;
import rCLick.AddFurniture;

public class Rotate2 extends JMenuItem implements ActionListener {
	
	private MyFurniture panel;
	private MyFurniture piece;
	int n = 0;
	
		public Rotate2 (MyFurniture panel) {
			this.panel = panel;
			this.setText("Rotate");
			this.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e){
		
		if ("bed".equals(panel.roomType)) {
			int s = MyFurniture.bedList.indexOf(panel.imagePath);
			n = (s+1)%4;
            piece = new MyFurniture(panel.getX(), panel.getY(), panel.getHeight(), panel.getWidth(), MyFurniture.bedList.get(n),"bed");
        }
		
		if ("chair".equals(panel.roomType)) {
			int s = MyFurniture.chairList.indexOf(panel.imagePath);
			n = (s+1)%4;
            piece = new MyFurniture(panel.getX(), panel.getY(), panel.getHeight(), panel.getWidth(), MyFurniture.chairList.get(n),"chair");
        }
		
		if ("CupBoard".equals(panel.roomType)) {
			int s = MyFurniture.cupboardList.indexOf(panel.imagePath);
			n = (s+1)%4;
            piece = new MyFurniture(panel.getX(), panel.getY(), panel.getHeight(), panel.getWidth(), MyFurniture.cupboardList.get(n),"CupBoard");
        }
		
		if ("Desk".equals(panel.roomType)) {
			int s = MyFurniture.deskList.indexOf(panel.imagePath);
			n = (s+1)%4;
            piece = new MyFurniture(panel.getX(), panel.getY(), panel.getHeight(), panel.getWidth(), MyFurniture.deskList.get(n),"Desk");
        }
		
		if ("KitchenTop".equals(panel.roomType)) {
			int s = MyFurniture.kitchentopList.indexOf(panel.imagePath);
			n = (s+1)%4;
            piece = new MyFurniture(panel.getX(), panel.getY(), panel.getHeight(), panel.getWidth(), MyFurniture.kitchentopList.get(n),"KitchenTop");
        }
		
		if ("Shower".equals(panel.roomType)) {
			int s = MyFurniture.showerList.indexOf(panel.imagePath);
			n = (s+1)%4;
            piece = new MyFurniture(panel.getX(), panel.getY(), panel.getHeight(), panel.getWidth(), MyFurniture.showerList.get(n),"Shower");
        }
		
		if ("Sink".equals(panel.roomType)) {
			int s = MyFurniture.sinkList.indexOf(panel.imagePath);
			n = (s+1)%4;
            piece = new MyFurniture(panel.getX(), panel.getY(), panel.getHeight(), panel.getWidth(), MyFurniture.sinkList.get(n),"Sink");
        }
		
		if ("Sofa".equals(panel.roomType)) {
			int s = MyFurniture.sofaList.indexOf(panel.imagePath);
			n = (s+1)%4;
            piece = new MyFurniture(panel.getX(), panel.getY(), panel.getHeight(), panel.getWidth(), MyFurniture.sofaList.get(n),"Sofa");
        }
		
		if ("Table".equals(panel.roomType)) {
			int s = MyFurniture.tableList.indexOf(panel.imagePath);
			n = (s+1)%4;
            piece = new MyFurniture(panel.getX(), panel.getY(), panel.getHeight(), panel.getWidth(), MyFurniture.tableList.get(n),"Table");
        }
		
		if ("Toilet".equals(panel.roomType)) {
			int s = MyFurniture.toiletList.indexOf(panel.imagePath);
			n = (s+1)%4;
            piece = new MyFurniture(panel.getX(), panel.getY(), panel.getHeight(), panel.getWidth(), MyFurniture.toiletList.get(n),"Toilet");
        }
		
		if ("TV".equals(panel.roomType)) {
			int s = MyFurniture.tvList.indexOf(panel.imagePath);
			n = (s+1)%4;
            piece = new MyFurniture(panel.getX(), panel.getY(), panel.getHeight(), panel.getWidth(), MyFurniture.tvList.get(n),"TV");
        }
		
		if(piece != null) {
		Container parent = panel.getParent();
		AddFurniture.data3.remove(panel);
	    AddFurniture.data3.add(piece);
		
		for(MyFurniture el: AddFurniture.data3) {
    	if(el!=piece && piece.getBounds().intersects(el.getBounds())) {
    		JOptionPane.showMessageDialog(null,"Overlap Alert", "WARNING", JOptionPane.WARNING_MESSAGE);
    		AddFurniture.data3.remove(piece);
    	    AddFurniture.data3.add(panel);
    	    return;
    	}
    }
		((JLayeredPane) parent).add(piece, Integer.valueOf(4));
		parent.remove(panel);
		parent.repaint();
	    
		}
		else {
			System.out.println("piece null");
		}
	}
		
		
}