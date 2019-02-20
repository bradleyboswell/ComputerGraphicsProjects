/**@author Bradley Boswell 
 * @class Computer Graphics
 * @description
 * A simple GUI application using java swing to move a graphical object around the screen and
 * change its color. Control the shapes location via mouse click, or arrow keys. Change its 
 * color via the radio buttons on the right hand side of the window. 
 */

package project1;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.ButtonGroup;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Project1Swing extends JApplet implements ActionListener{
	
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
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("Boswell Project 1 (SWING)");
		frame.setSize(650,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		JApplet applet = new Project1Swing();
		applet.init();
		frame.getContentPane().add(applet);
		frame.pack();
		frame.setVisible(true);
	}
	
	CircleMover panel = null;
	
	 public void init() {
		//Add area for circle 
		panel = new CircleMover();
		getContentPane().add(panel,BorderLayout.WEST);
		
		//Build Sidebar
		JPanel sidebar = new SideBar();
		
		GridLayout grid = new GridLayout(7,0);
		
		sidebar.setLayout(grid);
		
		//RADIO BUTTONS
		//Create Red Button
		JRadioButton redBtn = new JRadioButton(red);
		redBtn.setOpaque(false);
		//redBtn.setSize(100, 25);
	    redBtn.setActionCommand(red);
		redBtn.setSelected(true);
		
		//Green Green Button
		JRadioButton greenBtn = new JRadioButton(green);
		//greenBtn.setSize(100, 25);
		greenBtn.setOpaque(false);
	    greenBtn.setActionCommand(green);
				
	    //Create Blue Button
		JRadioButton blueBtn = new JRadioButton(blue);
		blueBtn.setSize(100, 25);
		blueBtn.setOpaque(false);
	    blueBtn.setActionCommand(blue);
		
	    //Group the buttons
	    ButtonGroup btnGroup = new ButtonGroup();
	    
	    btnGroup.add(redBtn);
	    btnGroup.add(greenBtn);
	    btnGroup.add(blueBtn);
			    
	    //add listeners
	    redBtn.addActionListener(this);
	    greenBtn.addActionListener(this);
	    blueBtn.addActionListener(this);
	    
	    //add radiobuttons to sidebar
	    sidebar.add(redBtn);
	    sidebar.add(greenBtn);
	    sidebar.add(blueBtn);
	    
	    //DIRECTIONAL BUTTONS
	    JButton upBtn = new JButton(up);
	    upBtn.setActionCommand(up);    
	    upBtn.setBackground(Color.LIGHT_GRAY);
	    upBtn.setSize(100, 25);
	    
	    JButton leftBtn = new JButton(left);
	    leftBtn.setActionCommand(left);
	    leftBtn.setBackground(Color.LIGHT_GRAY);
	    
	    JButton downBtn = new JButton(down);
	    downBtn.setActionCommand(down);
	    downBtn.setBackground(Color.LIGHT_GRAY);
	    
	    JButton rightBtn = new JButton(right);
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

		getContentPane().add(sidebar, BorderLayout.EAST);
	 }
	 
	 
	 @Override
	 public void actionPerformed(ActionEvent e) {
	 	String command = e.getActionCommand();
	 	
	 	if("red".equals(command)) {
	 		panel.currentColor = redColor;
	 	}else if("green".equals(command)) {
	 		panel.currentColor = greenColor;
	 	}else if("blue".equals(command)){
	 		panel.currentColor = blueColor;
	 	}else if("^".equals(command)){
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

class CircleMover extends JPanel implements MouseListener{
	
	Color currentColor = redColor;
	
	
	public CircleMover (){
		super();
		setPreferredSize(new Dimension(500, 400));
	    setBackground(Color.white);
	    addMouseListener(this);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
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
	public void mousePressed(MouseEvent ev) {
		xCoord = ev.getX()-(0.5*width);
		yCoord = ev.getY()-(0.5*width);
		repaint();
	}
	
	@Override
	public void mouseClicked(MouseEvent ev) {}

	@Override
	public void mouseEntered(MouseEvent ev) {}

	@Override
	public void mouseExited(MouseEvent ev) {}
	
	@Override
	public void mouseReleased(MouseEvent ev) {}
	
}

public class SideBar extends JPanel{

	public SideBar(){
		super();
		setPreferredSize(new Dimension(100, 400));
		setBackground(Color.LIGHT_GRAY);
	}
}
}