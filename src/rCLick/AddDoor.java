package rCLick;

import java.awt.Color;
import java.awt.Container;
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

public class AddDoor extends JMenuItem implements ActionListener{
	
public static ArrayList<DoorPanel> data1 = new ArrayList<DoorPanel>();
	
private MyPanels panel;
private MyPanels sub;
private DoorPanel door2;
private MyFrame topFrame;
private Point clickPoint;
private MouseAdapter mouseAdapter;
	
	public AddDoor(MyPanels panel, MyFrame topFrame) {
		this.panel = panel;
		this.topFrame = topFrame;
		this.setText("Add Door");
		this.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		String[] Doortype = {"Main Door", "Regular Door"};
		JComboBox<String> doortype = new JComboBox<>(Doortype);
		
		String[] Direction = {"North", "South", "East", "West"};
		JComboBox<String> direction = new JComboBox<>(Direction);
		
		JPanel myPanel = new JPanel();
		myPanel.add(doortype);
		myPanel.add(direction);
		
		int result = JOptionPane.showConfirmDialog(null, myPanel,"Please specify door", JOptionPane.OK_CANCEL_OPTION);
		if(result == JOptionPane.OK_OPTION) {
			String selectedType = (String)doortype.getSelectedItem();
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
                        
                        for (MyPanels el : MyButton.data) {
                        	Point newPoint = SwingUtilities.convertPoint(glassPane, clickPoint, el);
                            if (el.contains(newPoint)) {
                            	
                                sub = el;
                                break;
                            }
                        }
                        
                        
                        if("Regular Door".equals(selectedType)) {

                        if (sub == null) {
                            System.out.println("Door can't be added here.");
                            return;
                        }

                        int start = Math.max(panel.getX(), sub.getX());
                        int end = Math.min(panel.getX() + panel.getWidth(), sub.getX() + sub.getWidth());

                        if (end - start > 60 && sub.getY()<panel.getY()) {
                            DoorPanel door = new DoorPanel("North", panel.getBackground(), sub.getBackground(), start, end, start, panel.getY() - 5);
                            data1.add(door);
                            JLayeredPane layeredPane = topFrame.getLayeredPane();
                            layeredPane.add(door, Integer.valueOf(3)); 
                            topFrame.repaint();
                        } 
                        
                        else {
                            System.out.println("Not enough space to add the door.");
                        }
                      }
                        
                        if("Main Door".equals(selectedType)) {
                        	if(!panel.getBackground().equals(Color.yellow)&& !panel.getBackground().equals(Color.red)) {
                        		JOptionPane.showMessageDialog(null,"Can't Add Main Door Here", "WARNING", JOptionPane.WARNING_MESSAGE);
                                return;
                        	}
                        	
                        	if (sub != null) {
                        		JOptionPane.showMessageDialog(null,"Can't Add Main Door Here", "WARNING", JOptionPane.WARNING_MESSAGE);
                                return;
                            }
                        	
                        	JPanel test = new JPanel();
                        	test.setBounds(panel.getX(), panel.getY()-5,60,10);
                        	
                        	
                
                             
                           
               			        for (MyPanels el : MyButton.data) {
               			            if (test.getBounds().intersects(el.getBounds())&&el!=panel) {
               			            	sub = el;
               			            	test.setBounds(sub.getX()+sub.getWidth(), panel.getY()-5,60,10);
               			            	for (MyPanels nt : MyButton.data) {
               			            		if (!(test.getBounds().intersects(nt.getBounds()))&&el!=panel&& test.getX()+test.getWidth()<panel.getX()+panel.getWidth()) {
               			            			 door2 = new DoorPanel("North", panel.getBackground(), Color.DARK_GRAY, test.getX(), panel.getX()+panel.getWidth(),test.getX(), panel.getY()-5);
               			            			 data1.add(door2);
               			            		}
               			            	}
               			            	
               			            }
               			        }
               			    
                            
                            JLayeredPane layeredPane = topFrame.getLayeredPane();
                            layeredPane.add(door2, Integer.valueOf(3)); 
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
				            if (clickPoint != null) {
				            	 glassPane.removeMouseListener(mouseAdapter);
			                     glassPane.setVisible(false);

		                        for (MyPanels el : MyButton.data) {
		                        	Point newPoint = SwingUtilities.convertPoint(glassPane, clickPoint, el);
		                            if (el.contains(newPoint)) {
		                                sub = el;
		                                break;
		                            }
		                        }
				               

				                if ("Regular Door".equals(selectedType)) {
				                    if (sub == null) {
				                        System.out.println("Door can't be added here.");
				                        return;
				                    }

				                    // Calculate the start and end points for the door along the common border
				                    int start = Math.max(panel.getX(), sub.getX());
				                    int end = Math.min(panel.getX() + panel.getWidth(), sub.getX() + sub.getWidth());
				                    
				                    if(end-start>60 && sub.getY()>panel.getY()) {
				                    DoorPanel door = new DoorPanel("South", panel.getBackground(), sub.getBackground(), start, end, start, panel.getY() + panel.getHeight()-5);
				                    JLayeredPane layeredPane = topFrame.getLayeredPane();
				                    layeredPane.add(door, Integer.valueOf(3));
				                    data1.add(door);
				                    topFrame.repaint();
				                    }
				                 
				                }

				                if ("Main Door".equals(selectedType)) {
				                	
				                	if(!panel.getBackground().equals(Color.yellow)&&!panel.getBackground().equals(Color.red)) {
		                        		System.out.println("Main Door can't be added to this room");
		                                return;
		                        	}
				                	
				                    if (sub != null) {
				                        System.out.println("Main Door can't be added here.");
				                        return;
				                    }

				                    JPanel test = new JPanel();
		                        	test.setBounds(panel.getX(), panel.getHeight() +panel.getY()-5,60,10);
		                        	
		                        	
		                
		                             
		                           
		               			        for (MyPanels el : MyButton.data) {
		               			            if (test.getBounds().intersects(el.getBounds())&&el!=panel) {
		               			            	sub = el;
		               			            	test.setBounds(sub.getX()+sub.getWidth(), panel.getHeight()+panel.getY()-5,60,10);
		               			            	for (MyPanels nt : MyButton.data) {
		               			            		if (!(test.getBounds().intersects(nt.getBounds()))&&el!=panel&& test.getX()+test.getWidth()<panel.getX()+panel.getWidth()) {
		               			            			 door2 = new DoorPanel("South", panel.getBackground(), Color.DARK_GRAY, test.getX(), panel.getX()+panel.getWidth(),test.getX(), panel.getY()+panel.getHeight()-5);
		               			            			 data1.add(door2);
		               			            		}
		               			            	}
		               			            	
		               			            }
		               			        }
		               			    
		                            
		                            JLayeredPane layeredPane = topFrame.getLayeredPane();
		                            layeredPane.add(door2, Integer.valueOf(3)); 
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
                                                

                        for (MyPanels el : MyButton.data) {
                        	Point newPoint = SwingUtilities.convertPoint(glassPane, clickPoint, el);
                            if (el.contains(newPoint)) {
                                sub = el;
                                break;
                            }
                        }
                        
                        if ("Regular Door".equals(selectedType)) {
                        if (sub == null) {
                            System.out.println("Door can't be added here.");
                            return;
                        }

                        int start = Math.max(panel.getY(), sub.getY());
                        int end = Math.min(panel.getY() + panel.getHeight(), sub.getY() + sub.getHeight());

                        if (end - start > 60 && panel.getX()<sub.getX()) {
                            DoorPanel door = new DoorPanel("East", panel.getBackground(), sub.getBackground(), start,end,start, panel.getX()+panel.getWidth()-5);
                            JLayeredPane layeredPane = topFrame.getLayeredPane();
                            layeredPane.add(door, Integer.valueOf(3)); 
                            data1.add(door);
                            topFrame.repaint();
                            System.out.println("You have added a door");
                        } 
                        
                        else {
                            System.out.println("Not enough space to add the door.");
                        }
                  }
                        if ("Main Door".equals(selectedType)) {
                        	if(!panel.getBackground().equals(Color.yellow)&&!panel.getBackground().equals(Color.red)) {
                        		System.out.println("Main Door can't be added to this room");
                                return;
                        	}
                        	
		                    if (sub != null) {
		                        System.out.println("Main Door can't be added here.");
		                        return;
		                    }

		                    JPanel test = new JPanel();
                        	test.setBounds(panel.getY(),panel.getX()+panel.getWidth()-5,60,10);
                        	
                        	
                
                             
                           
               			        for (MyPanels el : MyButton.data) {
               			            if (test.getBounds().intersects(el.getBounds())&&el!=panel) {
               			            	sub = el;
               			            	test.setBounds(panel.getX()+panel.getWidth(), panel.getHeight()+panel.getY()-5,60,10);
               			            	for (MyPanels nt : MyButton.data) {
               			            		if (!(test.getBounds().intersects(nt.getBounds()))&&el!=panel&& test.getX()+test.getWidth()<panel.getX()+panel.getWidth()) {
               			            			 door2 = new DoorPanel("East", panel.getBackground(), Color.DARK_GRAY, panel.getY(), panel.getY() + panel.getHeight(), panel.getY(),panel.getX()+panel.getWidth()-5);
               			            			 data1.add(door2);
               			            		}
               			            	}
               			            	
               			            }
               			        }
               			    
                            
                            JLayeredPane layeredPane = topFrame.getLayeredPane();
                            layeredPane.add(door2, Integer.valueOf(3)); 
                            topFrame.repaint();
		                }
               }
            }
       });
				
				
			}
			
			if ("West".equals(selectedDirection)) {
				JComponent glassPane = (JComponent) topFrame.getGlassPane();
				glassPane.setVisible(true);
				glassPane.addMouseListener(mouseAdapter = new MouseAdapter() {
			        @Override
			        public void mouseClicked(MouseEvent e) {
			            clickPoint = e.getPoint();

			            if (clickPoint != null) {
			                glassPane.removeMouseListener(mouseAdapter);
			                glassPane.setVisible(false);
			                

			                for (MyPanels el : MyButton.data) {
			                	Point newPoint = SwingUtilities.convertPoint(glassPane, clickPoint, el);
	                            if (el.contains(newPoint)) {
			                        sub = el;
			                        break;
			                    }
			                }

			                if ("Regular Door".equals(selectedType)) {
			                    if (sub == null) {
			                        System.out.println("Door can't be added here.");
			                        return;
			                    }

			                    int start = Math.max(panel.getY(), sub.getY());
			                    int end = Math.min(panel.getY() + panel.getHeight(), sub.getY() + sub.getHeight());

			                    if (end - start > 60 && panel.getX() > sub.getX()) {
			                        DoorPanel door = new DoorPanel("West", panel.getBackground(), sub.getBackground(), start, end,start, panel.getX() - 5);
			                        data1.add(door);
			                        JLayeredPane layeredPane = topFrame.getLayeredPane();
			                        layeredPane.add(door, Integer.valueOf(3));
			                        topFrame.repaint();
			                    } else {
			                        System.out.println("Not enough space to add the door.");
			                    }
			                }

			                if ("Main Door".equals(selectedType)) {
			                	
			                	if(!panel.getBackground().equals(Color.yellow)&&!panel.getBackground().equals(Color.red)) {
	                        		System.out.println("Main Door can't be added to this room");
	                                return;
	                        	}
			                	
			                    if (sub != null) {
			                        System.out.println("Main Door can't be added here.");
			                        return;
			                    }

			                    DoorPanel door = new DoorPanel("West", panel.getBackground(),Color.DARK_GRAY, panel.getY(), panel.getY() + panel.getHeight(),panel.getY(), panel.getX()-5);
			                    data1.add(door);
			                    
			                    
			                    if (door.color2== Color.DARK_GRAY) {
	               			        for (MyPanels el : MyButton.data) {
	               			            if (door.getBounds().intersects(el.getBounds())&&el.getBackground().equals(door.color1)) {
	               			            	JOptionPane.showMessageDialog(null,"Can't Add Main Door Here", "WARNING", JOptionPane.WARNING_MESSAGE);
	               			            	
	               			            	data1.remove(door);
	               			            	
	               			                return; // Exit after handling the overlap
	               			            }
	               			        }
	               			    }
			                    
			                    JLayeredPane layeredPane = topFrame.getLayeredPane();
			                    layeredPane.add(door, Integer.valueOf(3));
			                    topFrame.repaint();
			                }
			            }
			        }
			    });
			}
		}
	}
}
