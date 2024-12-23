package rCLick;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import frame.MyFrame;
import furniture.MyFurniture;
import panels.MyPanels;

public class AddFurniture extends JMenuItem implements ActionListener {
	
public static ArrayList<MyFurniture> data3 = new ArrayList<MyFurniture>();

private MyPanels panel;
private MyFrame topFrame;
	
	public AddFurniture(MyPanels panel, MyFrame topFrame) {
		this.panel = panel;
		this.topFrame = topFrame;
		this.setText("Add Furniture");
		this.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e){

		String[] FurnitureType = {"Bed", "Chair", "CupBoard", "Desk","Kitchen Top", "Shower","Sink","Sofa","Table"
				,"Toilet","TV"};
		JComboBox<String> ftype = new JComboBox<>(FurnitureType);
		
		JPanel myPanel = new JPanel();
		myPanel.add(ftype);
		
		int result = JOptionPane.showConfirmDialog(null, myPanel,"Please specify door", JOptionPane.OK_CANCEL_OPTION);
		if(result == JOptionPane.OK_OPTION) {
			String selectedType = (String)ftype.getSelectedItem();
			
			if("Bed".equals(selectedType)){
				MyFurniture bed = new MyFurniture(panel.getX()+5, panel.getY()+5, 50, 60, MyFurniture.bedList.get(0),"bed");
				//bed.roomType = "bed";
				JLayeredPane layeredPane = topFrame.getLayeredPane();
                layeredPane.add(bed, Integer.valueOf(6)); 
                data3.add(bed);
                topFrame.repaint();
			}
			
			if("Chair".equals(selectedType)){
				MyFurniture bed = new MyFurniture(panel.getX()+5, panel.getY()+5, 30, 30, MyFurniture.chairList.get(0), "chair");
				JLayeredPane layeredPane = topFrame.getLayeredPane();
                layeredPane.add(bed, Integer.valueOf(6)); 
                data3.add(bed);
                topFrame.repaint();
			}
			
			if("CupBoard".equals(selectedType)){
				MyFurniture bed = new MyFurniture(panel.getX()+5, panel.getY()+5, 60, 40, "/furniture/oops project images/Cupboard/Cupboard.png","CupBoard");
				JLayeredPane layeredPane = topFrame.getLayeredPane();
                layeredPane.add(bed, Integer.valueOf(6)); 
                data3.add(bed);
                topFrame.repaint();
			}
			
			if("Desk".equals(selectedType)){
				MyFurniture bed = new MyFurniture(panel.getX()+5, panel.getY()+5, 60, 40, "/furniture/oops project images/Desk/Desk.png", "Desk");
				JLayeredPane layeredPane = topFrame.getLayeredPane();
                layeredPane.add(bed, Integer.valueOf(6)); 
                data3.add(bed);
                topFrame.repaint();
			}
			
			if("Kitchen Top".equals(selectedType)){
				MyFurniture bed = new MyFurniture(panel.getX()+5, panel.getY()+5, 150, 30, "/furniture/oops project images/Kitchentop/Kitchentop.png", "KitchenTop");
				JLayeredPane layeredPane = topFrame.getLayeredPane();
                layeredPane.add(bed, Integer.valueOf(6)); 
                data3.add(bed);
                topFrame.repaint();
			}
			
			if("Shower".equals(selectedType)){
				MyFurniture bed = new MyFurniture(panel.getX()+5, panel.getY()+5, 30, 60, "/furniture/oops project images/Shower/Shower.png","Shower");
				JLayeredPane layeredPane = topFrame.getLayeredPane();
                layeredPane.add(bed, Integer.valueOf(6)); 
                data3.add(bed);
                topFrame.repaint();
			}
			
			if("Sink".equals(selectedType)){
				MyFurniture bed = new MyFurniture(panel.getX()+5, panel.getY()+5, 50, 40, "/furniture/oops project images/Sink/Sink.png","Sink");
				JLayeredPane layeredPane = topFrame.getLayeredPane();
                layeredPane.add(bed, Integer.valueOf(6)); 
                data3.add(bed);
                topFrame.repaint();
			}
			
			if("Sofa".equals(selectedType)){
				MyFurniture bed = new MyFurniture(panel.getX()+5, panel.getY()+5, 80, 60, "/furniture/oops project images/Sofa/Sofa.png","Sofa");
				JLayeredPane layeredPane = topFrame.getLayeredPane();
                layeredPane.add(bed, Integer.valueOf(6)); 
                data3.add(bed);
                topFrame.repaint();
			}
			
			if("Table".equals(selectedType)){
				MyFurniture bed = new MyFurniture(panel.getX()+5, panel.getY()+5, 80, 60, "/furniture/oops project images/Table/Table.png","Table");
				JLayeredPane layeredPane = topFrame.getLayeredPane();
                layeredPane.add(bed, Integer.valueOf(6)); 
                data3.add(bed);
                topFrame.repaint();
			}
			
			if("Toilet".equals(selectedType)){
				MyFurniture bed = new MyFurniture(panel.getX()+5, panel.getY()+5, 80, 60, "/furniture/oops project images/Toilet/Toilet.png","Toilet");
				JLayeredPane layeredPane = topFrame.getLayeredPane();
                layeredPane.add(bed, Integer.valueOf(6)); 
                data3.add(bed);
                topFrame.repaint();
			}
			
			if("TV".equals(selectedType)){
				MyFurniture bed = new MyFurniture(panel.getX()+5, panel.getY()+5, 80, 60, "/furniture/oops project images/TV/TV.png","TV");
				JLayeredPane layeredPane = topFrame.getLayeredPane();
                layeredPane.add(bed, Integer.valueOf(6)); 
                data3.add(bed);
                topFrame.repaint();
			}
		}
	}
}
