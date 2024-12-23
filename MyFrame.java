package frame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.Border;

import buttons.*;
import furniture.MyFurniture;
import panels.*;
import rCLick.AddDoor;
import rCLick.AddFurniture;
import rCLick.AddWindow;

public class MyFrame extends JFrame {
	
	public static MyFrame frame;
	public static File currentfile;
	
    BedRoom bedroom = new BedRoom(0,200,this);
    Kitchen kitchen = new Kitchen(0,250,this);
    Bathroom bathroom = new Bathroom(0,300,this);
    LivingRoom livingroom = new LivingRoom(0,350,this);
    Clear clear = new Clear(0,0);
    OpenFile open = new OpenFile(0,50);
    SaveFile save = new SaveFile(0,100);
    DottedGridPanel gridPanel = new DottedGridPanel();
    JLayeredPane layeredPane = new JLayeredPane();

    public MyFrame(){
    	
        this.setTitle("My frame");
        this.setSize(1920,1080);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        
        this.add(bedroom);
        this.add(kitchen);
        this.add(bathroom);
        this.add(livingroom);
        this.add(clear);
        this.add(open);
        this.add(save);
        
        
        layeredPane.setBounds(0,0,1920,1080);
        gridPanel.setBounds(0, 0, 1920, 1080);
        gridPanel.setBackground(Color.DARK_GRAY);
        layeredPane.add(gridPanel, Integer.valueOf(0));

        this.add(layeredPane);
        this.add(gridPanel);


    }
    
    class DottedGridPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.WHITE); // Set dot color

            int dotSpacing = 20; // Space between dots

            // Draw the dots in a grid pattern
            for (int x = 0; x < getWidth(); x += dotSpacing) {
                for (int y = 0; y < getHeight(); y += dotSpacing) {
                    g2d.fillOval(x, y, 2, 2); // Draw a small dot at (x, y)
                }
            }
        }
    }
    
  
    public String storedata() {
    	StringBuilder string = new StringBuilder();
    	for(MyPanels el: MyButton.data) {
    		string.append(el.getX() + "," + el.getY() +"," + el.getWidth() + "," + el.getHeight() +","
    				+ el.color.getRed()+","+ el.color.getGreen() + "," + el.color.getBlue()+",");
    	}
    	
    	string.append("\n");
    	
    	for(DoorPanel el : AddDoor.data1) {
    		string.append(el.direction + "," + el.color1.getRed() + "," + el.color1.getGreen()+ "," + el.color1.getBlue()
    		+ "," + el.color2.getRed() + "," + el.color2.getGreen()+ "," + el.color2.getBlue() + "," + el.startO + "," +el.end
    		+ "," + el.startD + ","+ el.extra+",");
    	}
    	
    	string.append("\n");
    	
    	for(WindowPanel el: AddWindow.data2) {
    		string.append(el.direction + "," + el.color.getRed() + "," + el.color.getGreen()+ "," + el.color.getBlue()
    		+ ","  + el.startOW + "," +el.end+ ","+  el.startDW + ","  + el.extra+",");
    	}
    	
    	string.append("\n");
    	
    	for(MyFurniture el : AddFurniture.data3) {
    		string.append(el.getX() + "," + el.getY() +"," + el.getWidth() + "," + el.getHeight() +"," + el.imagePath+","+el.roomType+",");
    	}
    	
    	String str = string.toString();
    	return str;
    }

    public void writeToFile(File file) {
    	try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
    		writer.write(MyFrame.this.storedata());
    		JOptionPane.showMessageDialog(this, "File saved successfully!");
    	} catch (IOException e) {
    		e.printStackTrace();
    		JOptionPane.showMessageDialog(this, "Error saving file.");
    	}
    }
	public void saveFile() {
    // If currentFile is null, provide a predefined directory to save files
		if (currentfile == null) {
			JFileChooser fileChooser = new JFileChooser();

        // Set the default directory (path) where files will be saved
			File defaultDirectory = new File("../year 2 sem 1");  // Change this to your desired folder
			if (!defaultDirectory.exists()) {
            defaultDirectory.mkdirs();  // Ensure the directory exists
			}
        
			fileChooser.setCurrentDirectory(defaultDirectory);  // Set the default directory in the file chooser

			int result = fileChooser.showSaveDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) {
				currentfile = fileChooser.getSelectedFile();
				writeToFile(currentfile);  // Save the color to the selected file
			}
		} else {
        writeToFile(currentfile);  // If currentFile is not null, just save to the existing file
		}
	}
	
	public void readFromFile(File file) {
	    try {
	        BufferedReader reader = new BufferedReader(new FileReader(file));
	        String line;

	        // Read room data
	        line = reader.readLine();
	        if (line != null && !line.trim().isEmpty()) {
	            String[] roomData = line.split(",");
	            for (int i = 0; i < roomData.length; i += 7) {
	                // Parsing room data (x, y, width, height, color)
	                int x = Integer.parseInt(roomData[i].trim());
	                int y = Integer.parseInt(roomData[i + 1].trim());
	                int width = Integer.parseInt(roomData[i + 2].trim());
	                int height = Integer.parseInt(roomData[i + 3].trim());
	                Color color = new Color(
	                    Integer.parseInt(roomData[i + 4].trim()),
	                    Integer.parseInt(roomData[i + 5].trim()),
	                    Integer.parseInt(roomData[i + 6].trim())
	                );
	                // Assuming a method to add rooms: `addRoom(x, y, width, height, color)`
	                MyPanels panel = new MyPanels(x, y, width, height, color);
	                Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 5);
	                panel.setBorder(lineBorder);
	                MyFrame.this.layeredPane.add(panel, Integer.valueOf(2));
	        		MyButton.data.add(panel);
	        		MyFrame.this.repaint();
	            }
	        }

	        // Read door data
	        line = reader.readLine();
	        if (line != null && !line.trim().isEmpty()) {
	            String[] doorData = line.split(",");
	            for (int i = 0; i < doorData.length; i += 11) {
	                // Parsing door data (direction, color1, color2, start, end, extra)
	                String direction = doorData[i].trim();
	                Color color1 = new Color(
	                    Integer.parseInt(doorData[i + 1].trim()),
	                    Integer.parseInt(doorData[i + 2].trim()),
	                    Integer.parseInt(doorData[i + 3].trim())
	                );
	                Color color2 = new Color(
	                    Integer.parseInt(doorData[i + 4].trim()),
	                    Integer.parseInt(doorData[i + 5].trim()),
	                    Integer.parseInt(doorData[i + 6].trim())
	                );
	                int startO = Integer.parseInt(doorData[i + 7].trim());
	                int end = Integer.parseInt(doorData[i + 8].trim());
	                int startD = Integer.parseInt(doorData[i + 9].trim());
	                int extra = Integer.parseInt(doorData[i + 10].trim());
	                // Assuming a method to add doors: `addDoor(direction, color1, color2, start, end, extra)`
	                DoorPanel panel = new DoorPanel(direction, color1, color2, startO, end, startD, extra);
	                MyFrame.this.layeredPane.add(panel, Integer.valueOf(3));
	        		AddDoor.data1.add(panel);
	        		MyFrame.this.repaint();
	            }
	        }

	        // Read window data
	        line = reader.readLine();
	        if (line != null && !line.trim().isEmpty()) {
	            String[] windowData = line.split(",");
	            for (int i = 0; i < windowData.length; i += 8) {
	                // Parsing window data (direction, color, start, end, extra)
	                String direction = windowData[i].trim();
	                Color color = new Color(
	                    Integer.parseInt(windowData[i + 1].trim()),
	                    Integer.parseInt(windowData[i + 2].trim()),
	                    Integer.parseInt(windowData[i + 3].trim())
	                );
	                int startOW = Integer.parseInt(windowData[i + 4].trim());
	                int end = Integer.parseInt(windowData[i + 5].trim());
	                int startDW = Integer.parseInt(windowData[i + 6].trim());
	                int extra = Integer.parseInt(windowData[i + 7].trim());
	                // Assuming a method to add windows: `addWindow(direction, color, start, end, extra)`
	                WindowPanel panel = new WindowPanel(direction, color, startOW, end, startDW, extra);
	                MyFrame.this.layeredPane.add(panel, Integer.valueOf(3));
	        		AddWindow.data2.add(panel);
	        		MyFrame.this.repaint();
	            }
	        }

	        // Read furniture data
	        line = reader.readLine();
	        if (line != null && !line.trim().isEmpty()) {
	            String[] furnitureData = line.split(",");
	            for (int i = 0; i < furnitureData.length; i += 6) {
	                // Parsing furniture data (x, y, width, height, imagePath)
	                int x = Integer.parseInt(furnitureData[i].trim());
	                int y = Integer.parseInt(furnitureData[i + 1].trim());
	                int width = Integer.parseInt(furnitureData[i + 2].trim());
	                int height = Integer.parseInt(furnitureData[i + 3].trim());
	                String imagePath = furnitureData[i + 4].trim();
	                String roomType = furnitureData[i + 5].trim();
	                // Assuming a method to add furniture: `addFurniture(x, y, width, height, imagePath)`
	                MyFurniture panel = new MyFurniture(x, y, width, height, imagePath, roomType);
	                MyFrame.this.layeredPane.add(panel, Integer.valueOf(4));
	        		AddFurniture.data3.add(panel);
	        		MyFrame.this.repaint();
	            }
	        }

	        reader.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(this, "Error opening file.");
	    }
	}

	public void openFile() {
	    Clear.clearFrame();  // Assuming this method clears the frame before loading a new file
	    
	    JFileChooser fileChooser = new JFileChooser();

	    // Set the default directory (path) where the file chooser will open
	    File defaultDirectory = new File("../year 2 sem 1");  // Change this to your desired folder
	    if (!defaultDirectory.exists()) {
	        defaultDirectory.mkdirs();  // Ensure the directory exists
	    }

	    fileChooser.setCurrentDirectory(defaultDirectory);  // Set the default directory in the file chooser

	    int result = fileChooser.showOpenDialog(this);
	    if (result == JFileChooser.APPROVE_OPTION) {
	        File currentfile = fileChooser.getSelectedFile();  // You need to define currentfile
	        readFromFile(currentfile);  // Open and read from the selected file
	    }
	}



    public static void main(String[] args) {
         frame = new MyFrame();
    }
}

