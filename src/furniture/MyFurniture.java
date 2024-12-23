package furniture;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import buttons.MyButton;
import frame.MyFrame;
import panels.MyPanels;
import rCLick.AddFurniture;
import rCLick.ClickFrame;
import rClick2.ClickFrame2;

public class MyFurniture extends JPanel{
	protected Point prevPoint;
	protected Point currentPoint;
    
    private int originalX;
    private int originalY;
	
	int x;
	int y;
	private MyPanels sub;
	
	private Image image;
	public String imagePath;
	public String room;
	public int a;
	public int b;
	public String roomType;
	
	public int d = 0;
	
	public int wrtX;
	public int wrtY;
	
	public static ArrayList<String> bedList = new ArrayList<>();
	public static ArrayList<String> chairList = new ArrayList<>();
	public static ArrayList<String> cupboardList = new ArrayList<>();
	public static ArrayList<String> deskList = new ArrayList<>();
	public static ArrayList<String> kitchentopList = new ArrayList<>();
	public static ArrayList<String> showerList = new ArrayList<>();
	public static ArrayList<String> sinkList = new ArrayList<>();
	public static ArrayList<String> sofaList = new ArrayList<>();
	public static ArrayList<String> tableList = new ArrayList<>();
	public static ArrayList<String> toiletList = new ArrayList<>();
	public static ArrayList<String> tvList = new ArrayList<>();

    static {
        bedList.add("/furniture/oops project images/Bed/Bed.png");
        bedList.add("/furniture/oops project images/Bed/Bed1.png");
        bedList.add("/furniture/oops project images/Bed/Bed2.png");
        bedList.add("/furniture/oops project images/Bed/Bed3.png");
        
        chairList.add("/furniture/oops project images/Chair/Chair.png");
        chairList.add("/furniture/oops project images/Chair/Chair1.png");
        chairList.add("/furniture/oops project images/Chair/Chair2.png");
        chairList.add("/furniture/oops project images/Chair/Chair3.png");
        
        cupboardList.add("/furniture/oops project images/Cupboard/Cupboard.png");
        cupboardList.add("/furniture/oops project images/Cupboard/Cupboard1.png");
        cupboardList.add("/furniture/oops project images/Cupboard/Cupboard2.png");
        cupboardList.add("/furniture/oops project images/Cupboard/Cupboard3.png");
        
        deskList.add("/furniture/oops project images/Desk/Desk.png");
        deskList.add("/furniture/oops project images/Desk/Desk1.png");
        deskList.add("/furniture/oops project images/Desk/Desk2.png");
        deskList.add("/furniture/oops project images/Desk/Desk3.png");
        
        kitchentopList.add("/furniture/oops project images/Kitchentop/Kitchentop.png");
        kitchentopList.add("/furniture/oops project images/Kitchentop/Kitchentop1.png");
        kitchentopList.add("/furniture/oops project images/Kitchentop/Kitchentop2.png");
        kitchentopList.add("/furniture/oops project images/Kitchentop/Kitchentop3.png");
        
        showerList.add("/furniture/oops project images/Shower/Shower.png");
        showerList.add("/furniture/oops project images/Shower/Shower1.png");
        showerList.add("/furniture/oops project images/Shower/Shower2.png");
        showerList.add("/furniture/oops project images/Shower/Shower3.png");
        
        sinkList.add("/furniture/oops project images/Sink/Sink.png");
        sinkList.add("/furniture/oops project images/Sink/Sink1.png");
        sinkList.add("/furniture/oops project images/Sink/Sink2.png");
        sinkList.add("/furniture/oops project images/Sink/Sink3.png");
        
        sofaList.add("/furniture/oops project images/Sofa/Sofa.png");
        sofaList.add("/furniture/oops project images/Sofa/Sofa1.png");
        sofaList.add("/furniture/oops project images/Sofa/Sofa2.png");
        sofaList.add("/furniture/oops project images/Sofa/Sofa3.png");
        
        tableList.add("/furniture/oops project images/Table/Table.png");
        tableList.add("/furniture/oops project images/Table/Table1.png");
        tableList.add("/furniture/oops project images/Table/Table2.png");
        tableList.add("/furniture/oops project images/Table/Table3.png");
        
        toiletList.add("/furniture/oops project images/Toilet/Toilet.png");
        toiletList.add("/furniture/oops project images/Toilet/Toilet1.png");
        toiletList.add("/furniture/oops project images/Toilet/Toilet2.png");
        toiletList.add("/furniture/oops project images/Toilet/Toilet3.png");
        
        tvList.add("/furniture/oops project images/TV/TV.png");
        tvList.add("/furniture/oops project images/TV/TV1.png");
        tvList.add("/furniture/oops project images/TV/TV2.png");
        tvList.add("/furniture/oops project images/TV/TV3.png");
    }
	
	public MyFurniture(int x, int y,int a, int b, String imagePath, String roomType) {
		this.x = x;
		this.y = y;
		this.imagePath = imagePath;
		this.roomType = roomType;
		this.a = a;
		this.b = b;
        image = new ImageIcon(getClass().getResource(imagePath)).getImage();
        setBounds(x,y, a, b);
        
		Listener3 listener = new Listener3();        
        this.addMouseListener(listener);
        this.addMouseMotionListener(listener);
       
       
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(),getHeight(), this);
        }
    }
	
	public class Listener3 extends MouseAdapter{
		
		public void mousePressed(MouseEvent e){
        	MyFrame topFrame = (MyFrame) MyFurniture.this.getTopLevelAncestor();
        	JLayeredPane layeredPane = ((MyFrame) topFrame).getLayeredPane();
        	layeredPane.setLayer(MyFurniture.this, Integer.valueOf(6));
        	
        	
            prevPoint = e.getPoint();
            originalX = MyFurniture.this.getX();
            originalY = MyFurniture.this.getY();
            
            for(MyPanels el : MyButton.data) {
        		if(el.getBounds().contains(MyFurniture.this.getBounds())){
        			sub = el;
        			break;
        		}
        	}
        }
		
		public void mouseDragged(MouseEvent e){
			currentPoint = e.getPoint();

            int transX = (int)(currentPoint.getX() - prevPoint.getX());
            int transY = (int)(currentPoint.getY() - prevPoint.getY());

            int newX = MyFurniture.this.getX() + transX;
            int newY = MyFurniture.this.getY() + transY;
            
            MyFurniture.this.setLocation(newX, newY);
		}
		
		public void mouseReleased(MouseEvent e){
			
			for(MyFurniture el: AddFurniture.data3) {
		    	if(el!=MyFurniture.this && MyFurniture.this.getBounds().intersects(el.getBounds())) {
		    		JOptionPane.showMessageDialog(null,"Overlap Alert", "WARNING", JOptionPane.WARNING_MESSAGE);
		    		MyFurniture.this.setLocation(originalX, originalY);
		    	    return;
		    	}
		    }
			
			MyFrame topFrame = (MyFrame) MyFurniture.this.getTopLevelAncestor();
        	JLayeredPane layeredPane = ((MyFrame) topFrame).getLayeredPane();
        	layeredPane.setLayer(MyFurniture.this, Integer.valueOf(4));
        	
        	
        	if(!sub.getBounds().contains(MyFurniture.this.getBounds())) {
        		MyFurniture.this.setLocation(originalX, originalY);
        	}
		}
		
		public void mouseClicked(MouseEvent e) {
        	if(e.getButton() == MouseEvent.BUTTON3) {
        		ClickFrame2 cframe = new ClickFrame2(MyFurniture.this);
        		cframe.show(MyFurniture.this, e.getX(), e.getY());
        	
        	}
        }

		
		
	}
}
