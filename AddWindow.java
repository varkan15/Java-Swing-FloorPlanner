package rCLick;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import buttons.MyButton;
import frame.MyFrame;
import panels.DoorPanel;
import panels.MyPanels;
import panels.WindowPanel;

public class AddWindow extends JMenuItem implements ActionListener {
	
	public static ArrayList<WindowPanel> data2 = new ArrayList<WindowPanel>();
	
	private MyPanels panel;
	private MyPanels sub;
	private WindowPanel wind;
	private MyFrame topFrame;
	private Point clickPoint;
	private MouseAdapter mouseAdapter;
	
	public AddWindow(MyPanels panel, MyFrame topFrame) {
		this.panel = panel;
		this.topFrame = topFrame;
		this.setText("Add Window");
		this.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		String[] Direction = {"North", "South", "East", "West"};
		JComboBox<String> direction = new JComboBox<>(Direction);
		
		JPanel myPanel = new JPanel();
		myPanel.add(direction);
		
		int result = JOptionPane.showConfirmDialog(null, myPanel,"Please specify door", JOptionPane.OK_CANCEL_OPTION);
		if(result == JOptionPane.OK_OPTION) {
			String selectedDirection = (String) direction.getSelectedItem();
			
if("North".equals(selectedDirection)) {
				
				JComponent glassPane = (JComponent) topFrame.getGlassPane();
				glassPane.setVisible(true);
				
				glassPane.addMouseListener(mouseAdapter = new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        clickPoint = e.getPoint();

                        if(clickPoint != null) {
                        glassPane.removeMouseListener(mouseAdapter);
                        glassPane.setVisible(false);

                        
                        System.out.println(clickPoint.getX());
                        
                        for (MyPanels el : MyButton.data) {
                        	Point newPoint = SwingUtilities.convertPoint(glassPane, clickPoint, el);
                            if (el.contains(newPoint)) {
                            	
                                sub = el;
                                break;
                            }
                        }
                        
                        
                        if(sub==null) {
                        WindowPanel window = new WindowPanel("North", panel.getBackground(), panel.getX(), panel.getX()+panel.getWidth(), panel.getX(), panel.getY()-5);
                    	data2.add(window);
                    	JLayeredPane layeredPane = topFrame.getLayeredPane();
                        layeredPane.add(window, Integer.valueOf(3)); 
                        topFrame.repaint();
                        }
                        
                   }
                }
          });
	}

if("South".equals(selectedDirection)) {
	
	JComponent glassPane = (JComponent) topFrame.getGlassPane();
	glassPane.setVisible(true);
	
	glassPane.addMouseListener(mouseAdapter = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            clickPoint = e.getPoint();

            if(clickPoint != null) {
            glassPane.removeMouseListener(mouseAdapter);
            glassPane.setVisible(false);

            
            System.out.println(clickPoint.getX());
            
            for (MyPanels el : MyButton.data) {
            	Point newPoint = SwingUtilities.convertPoint(glassPane, clickPoint, el);
                if (el.contains(newPoint)) {
                	
                    sub = el;
                    break;
                }
            }
            if(sub==null) {
            WindowPanel window = new WindowPanel("South", panel.getBackground(), panel.getX(), panel.getX()+panel.getWidth(),panel.getX(), panel.getY() + panel.getHeight()-5);
        	data2.add(window);
        	JLayeredPane layeredPane = topFrame.getLayeredPane();
            layeredPane.add(window, Integer.valueOf(3)); 
            topFrame.repaint();
            }
            
       }
    }
});
}

if("West".equals(selectedDirection)) {
	
	JComponent glassPane = (JComponent) topFrame.getGlassPane();
	glassPane.setVisible(true);
	
	glassPane.addMouseListener(mouseAdapter = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            clickPoint = e.getPoint();

            if(clickPoint != null) {
            glassPane.removeMouseListener(mouseAdapter);
            glassPane.setVisible(false);

            
            System.out.println(clickPoint.getX());
            
            for (MyPanels el : MyButton.data) {
            	Point newPoint = SwingUtilities.convertPoint(glassPane, clickPoint, el);
                if (el.contains(newPoint)) {
                	
                    sub = el;
                    break;
                }
            }
            if(sub==null) {
            WindowPanel window = new WindowPanel("West", panel.getBackground(), panel.getY(), panel.getY()+panel.getHeight(),panel.getY(), panel.getX()-5);
        	data2.add(window);
        	JLayeredPane layeredPane = topFrame.getLayeredPane();
            layeredPane.add(window, Integer.valueOf(3)); 
            topFrame.repaint();
            }
            
       }
    }
});
}

if("East".equals(selectedDirection)) {
	
	JComponent glassPane = (JComponent) topFrame.getGlassPane();
	glassPane.setVisible(true);
	
	glassPane.addMouseListener(mouseAdapter = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            clickPoint = e.getPoint();

            if(clickPoint != null) {
            glassPane.removeMouseListener(mouseAdapter);
            glassPane.setVisible(false);

            
            System.out.println(clickPoint.getX());
            
            for (MyPanels el : MyButton.data) {
            	Point newPoint = SwingUtilities.convertPoint(glassPane, clickPoint, el);
                if (el.contains(newPoint)) {
                	
                    sub = el;
                    break;
                }
            }
            if(sub==null) {
            WindowPanel window = new WindowPanel("East", panel.getBackground(), panel.getY(), panel.getY()+panel.getHeight(),panel.getY(), panel.getX() + panel.getWidth()-5);
        	data2.add(window);
        	JLayeredPane layeredPane = topFrame.getLayeredPane();
            layeredPane.add(window, Integer.valueOf(3)); 
            topFrame.repaint();
            }
            
       }
    }
});
}


			
	
			
			
		}
	}
}
