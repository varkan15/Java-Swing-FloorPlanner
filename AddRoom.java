package rCLick;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import buttons.MyButton;
import frame.MyFrame;
import panels.MyPanels;

public class AddRoom extends JMenuItem implements ActionListener{
	
	private MyPanels panel;
	private MyFrame topFrame;
	private int Xcoord;
	private int Ycoord;
	private int width;
	private int height;
	
	public AddRoom(MyPanels panel, MyFrame topFrame) {
		this.panel = panel;
		this.topFrame = topFrame;
		this.setText("Add Room");
		this.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		String[] Room = {"Kitchen", "Bedroom", "Bathroom"};
		JComboBox<String> room = new JComboBox<>(Room);
		
		String[] Direction = {"North", "South", "East", "West"};
		JComboBox<String> direction = new JComboBox<>(Direction);

		JTextField widthField = new JTextField(5);
	    JTextField heightField = new JTextField(5);

		
		JPanel myPanel = new JPanel();
		myPanel.add(room);
		myPanel.add(direction);
		myPanel.add(widthField);
		myPanel.add(heightField);
		
		
		
		
		int result = JOptionPane.showConfirmDialog(null, myPanel,"Please add room", JOptionPane.OK_CANCEL_OPTION);
		if(result == JOptionPane.OK_OPTION) {
			String selectedRoom = (String) room.getSelectedItem();
			String selectedDirection = (String) direction.getSelectedItem();
			 width = Integer.parseInt(widthField.getText());
			 height = Integer.parseInt(heightField.getText());
			 
			//North and South alignments
			 
			if("North".equals(selectedDirection)) {
				String[] Alignment = {"Width Center", "Left", "Right"};
				JComboBox<String> alignment = new JComboBox<>(Alignment);
				JPanel myPanel1 = new JPanel();
				myPanel.add(alignment);
				int result1 = JOptionPane.showConfirmDialog(null, myPanel,"Please specify alignment", JOptionPane.OK_CANCEL_OPTION);
				if(result == JOptionPane.OK_OPTION) {
					String selectedAlignment = (String) alignment.getSelectedItem();
					
					if("Width Center".equals(selectedAlignment)) {
						Xcoord = panel.getX() + (panel.getWidth())/2 - (width)/2;
					}
					
					if("Left".equals(selectedAlignment)) {
						Xcoord = panel.getX();
					}
					
					if("Right".equals(selectedAlignment)) {
						Xcoord = panel.getX() + panel.getWidth() - width;
					}
				}
				
				Ycoord = panel.getY() - height;
			}
			
			if("South".equals(selectedDirection)) {
				
				String[] Alignment = {"Width Center", "Left", "Right"};
				JComboBox<String> alignment = new JComboBox<>(Alignment);
				JPanel myPanel1 = new JPanel();
				myPanel.add(alignment);
				int result1 = JOptionPane.showConfirmDialog(null, myPanel,"Please specify alignment", JOptionPane.OK_CANCEL_OPTION);
				if(result == JOptionPane.OK_OPTION) {
					String selectedAlignment = (String) alignment.getSelectedItem();
					
					if("Width Center".equals(selectedAlignment)) {
						Xcoord = panel.getX() + (panel.getWidth())/2 - (width)/2;
					}
					
					if("Left".equals(selectedAlignment)) {
						Xcoord = panel.getX();
					}
					
					if("Right".equals(selectedAlignment)) {
						Xcoord = panel.getX() + panel.getWidth() - width;
					}
				}
				
				Ycoord = panel.getY() + panel.getHeight();
			}
			
			//East and West ALignments
			
			if("West".equals(selectedDirection)) {
				String[] Alignment = {"Height Center", "Top", "Bottom"};
				JComboBox<String> alignment = new JComboBox<>(Alignment);
				JPanel myPanel1 = new JPanel();
				myPanel.add(alignment);
				int result1 = JOptionPane.showConfirmDialog(null, myPanel,"Please specify alignment", JOptionPane.OK_CANCEL_OPTION);
				if(result == JOptionPane.OK_OPTION) {
					String selectedAlignment = (String) alignment.getSelectedItem();
					
					if("Height Center".equals(selectedAlignment)) {
						Ycoord = panel.getY() + (panel.getHeight())/2 - (height)/2;
					}
					
					if("Top".equals(selectedAlignment)) {
						Ycoord = panel.getY();
					}
					
					if("Bottom".equals(selectedAlignment)) {
						Ycoord = panel.getY() + panel.getHeight() - height;
					}
				}
				Xcoord = panel.getX() - width;
			}
			
			if("East".equals(selectedDirection)) {
				
				String[] Alignment = {"Height Center", "Top", "Bottom"};
				JComboBox<String> alignment = new JComboBox<>(Alignment);
				JPanel myPanel1 = new JPanel();
				myPanel1.add(alignment);
				int result1 = JOptionPane.showConfirmDialog(null, myPanel,"Please specify alignment", JOptionPane.OK_CANCEL_OPTION);
				if(result1 == JOptionPane.OK_OPTION) {
					String selectedAlignment = (String) alignment.getSelectedItem();
					
					if("Height Center".equals(selectedAlignment)) {
						Ycoord = panel.getY() + (panel.getHeight())/2 - (height)/2;
					}
					
					if("Top".equals(selectedAlignment)) {
						Ycoord = panel.getY();
					}
					
					if("Bottom".equals(selectedAlignment)) {
						Ycoord = panel.getY() + panel.getHeight() - height;
					}
				}
				Xcoord = panel.getX() + panel.getWidth();
			}
			
			MyPanels panel = null;
			if("Kitchen".equals(selectedRoom)) {
				panel = new MyPanels(Xcoord, Ycoord, width, height, Color.red);
			}
			
			if("Bathroom".equals(selectedRoom)) {
				panel = new MyPanels(Xcoord, Ycoord, width, height, Color.blue);
			}
			
			if("Bedroom".equals(selectedRoom)) {
				panel = new MyPanels(Xcoord, Ycoord, width, height, Color.green);
			}
			
			Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 3);
			panel.setBorder(lineBorder);
            
            if("North".equals(selectedDirection)||("South".equals(selectedDirection))) {
            	panel.widthAligned = true;
            }
            if("East".equals(selectedDirection)||("West".equals(selectedDirection))) {
            	panel.heightAligned = true;
            }
            
			JLayeredPane layeredPane = topFrame.getLayeredPane();
			topFrame.add(panel);
			layeredPane.add(panel, Integer.valueOf(2));
			MyButton.data.add(panel);
			topFrame.repaint();
		}		
	}
}
