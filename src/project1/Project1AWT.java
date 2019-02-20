/**@author Bradley Boswell
 * @class Computer Graphics
 * @description
 * A simple GUI application using ONLY java awt to move a graphical object around the screen and
 * change its color. Control the shapes location via mouse click, or arrow keys. Change its 
 * color via the radio buttons on the right hand side of the window. 
 */


package project1;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import java.applet.Applet;


public class Project1AWT extends Canvas implements ActionListener, ItemListener {
	
	//Button strings
	public String red = "red";
	public String green = "green";
	public String blue = "blue";
	public String left = "<";
	public String right = ">";
	public String up = "^";
	public String down = "v";	
	
	//Circle properties
	public double xCoord = 200;
	public double yCoord = 200;
	public double width = 100;
	public double height = 100;
	
	//Colors
	Color redColor = Color.red;
	Color greenColor = Color.green;
	Color blueColor = Color.blue;
	Color currentColor = redColor;
	
	static Panel sidebar;

	public static void main(String[] args) {
		Frame frame = new Frame();
		sidebar = new Panel();
		sidebar.setPreferredSize(new Dimension(100, 400));
		sidebar.setBackground(Color.LIGHT_GRAY);
		
		FlowLayout layout = new FlowLayout();
		layout.setHgap(0);
		
		frame.setLayout(layout);
		
		WindowListener listener = new WindowAdapter() {
		   public void windowClosing(WindowEvent ev) {
		     System.exit(0);
		   }
		};
		frame.addWindowListener(listener);
		
		frame.add(new Project1AWT());
		frame.add(sidebar);
		frame.setVisible(true);
		frame.setSize(650,400);
		frame.setResizable(false);
		frame.pack();
	}
	
	public Project1AWT() {
		setBackground(Color.WHITE);
		setSize(650,400);
		
		MouseListener mouseListener = new MouseAdapter() {
		     public void mousePressed(MouseEvent ev) {
		    	xCoord = ev.getX()-(0.5*width);
		 		yCoord = ev.getY()-(0.5*width);
		 		repaint();
		     }
		};
	    addMouseListener(mouseListener);
		
		GridLayout grid = new GridLayout(7,0);
		
		sidebar.setLayout(grid);
		
		//RADIO BUTTONS
		Checkbox redBtn, blueBtn, greenBtn;
		CheckboxGroup colorGroup = new CheckboxGroup();
		//Create Buttons
		redBtn = new Checkbox("red", colorGroup, true);
		greenBtn = new Checkbox("green", colorGroup, false);
		blueBtn = new Checkbox("blue", colorGroup, false);
		sidebar.add(redBtn);
		sidebar.add(greenBtn);
		sidebar.add(blueBtn);
		redBtn.addItemListener(this);
		greenBtn.addItemListener(this);
		blueBtn.addItemListener(this);
		

	    //DIRECTIONAL BUTTONS
	    Button upBtn = new Button(up);
	    upBtn.setActionCommand(up);    
	    upBtn.setBackground(Color.LIGHT_GRAY);
	    
	    Button leftBtn = new Button(left);
	    leftBtn.setActionCommand(left);
	    leftBtn.setBackground(Color.LIGHT_GRAY);
	    
	    Button downBtn = new Button(down);
	    downBtn.setActionCommand(down);
	    downBtn.setBackground(Color.LIGHT_GRAY);
	    
	    Button rightBtn = new Button(right);
	    rightBtn.setActionCommand(right);
	    rightBtn.setBackground(Color.LIGHT_GRAY);
	    
	    upBtn.addActionListener(this);
	    leftBtn.addActionListener(this);
	    downBtn.addActionListener(this);
	    rightBtn.addActionListener(this);
	    
	    //add directional buttons to sidebar
	    sidebar.add(upBtn);
	    sidebar.add(leftBtn);
	    sidebar.add(downBtn);
	    sidebar.add(rightBtn);
	}

	public void paint(Graphics g) {
		Graphics2D g1 = (Graphics2D) g;
		
		Shape circle = new Ellipse2D.Double(xCoord, yCoord, width, height);
		g1.setPaint(currentColor);
		g1.fill(circle);
		
		BasicStroke outline = new BasicStroke();
		g1.setColor(Color.black);
		g1.draw(circle);
		
		Shape yLine = new Line2D.Double(xCoord+(0.5*width),yCoord-(width*1/3),xCoord+(0.5*width),yCoord+width+(width*1/3));
		g1.draw(yLine);
		
		Shape xLine = new Line2D.Double(xCoord-(width*1/3),yCoord+(0.5*width),xCoord+width+(width*1/3),yCoord+(0.5*width));
		g1.draw(xLine);
	}
	
	
	@Override
	public void itemStateChanged(ItemEvent e) {
	 	System.out.println(e.getItem());
		 
		if(e.getItem().equals("red")) {
	 		currentColor = redColor;
	 	}else if(e.getItem().equals("green")) {
	 		currentColor = greenColor;
	 	}else if(e.getItem().equals("blue")) {
	 		currentColor = blueColor;
	 	}
	 	System.out.println(currentColor);
		repaint(); 	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
	 	System.out.println(command);
	 	if("^".equals(command)){
	 		yCoord -= 5.0;
	 	}else if("<".equals(command)) {
	 		xCoord -= 5.0;
	 	}else if(">".equals(command)) {
	 		xCoord += 5.0;
	 	}else if("v".equals(command)) {
	 		yCoord += 5.0;
	 	}
		repaint();
		
	}
}