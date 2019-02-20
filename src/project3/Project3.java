package project3;

/**
 * @author - Bradley Boswell
 * @class - Computer Graphics
 * @assignment - Project 3
 * @description - An interactive GUI for using Java's AffineTransform in order to manipulate 
 * the transformations of a graphic object.
 */


import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Project3 extends JApplet implements ActionListener{
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("Boswell_Project3");
		frame.setSize(new Dimension(800,800));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JApplet applet = new Project3();
		applet.init();
		frame.getContentPane().add(applet);
		frame.pack();
		frame.setVisible(true);
	}
	
	public Arrow arrowShape = null;
	public JTextField row00, row01, row02, 
	row10, row11, row12;
	
	public void init() {
		BorderLayout borderlayout = new BorderLayout();
		borderlayout.preferredLayoutSize(this);
		setLayout(borderlayout);
		
		arrowShape = new Arrow();
		add(arrowShape, BorderLayout.CENTER);
		
		JPanel tPanel = new TransformationPanel();
		add(tPanel, BorderLayout.SOUTH);
		GridLayout grid = new GridLayout(2,4);
		tPanel.setLayout(grid);
		
		
		//Add Text Fields
		row00 = new JTextField("1");
		row01 = new JTextField("0");
		row02 = new JTextField("0");
		row10 = new JTextField("0");
		row11 = new JTextField("1");
		row12 = new JTextField("0");
		JButton apply = new JButton("Apply");
		JButton reset = new JButton("Reset");
		apply.setActionCommand("Apply");
		reset.setActionCommand("Reset");
		apply.addActionListener(this);
		reset.addActionListener(this);
		
		tPanel.add(row00);
		tPanel.add(row01);
		tPanel.add(row02);
		tPanel.add(apply);
		tPanel.add(row10);
		tPanel.add(row11);
		tPanel.add(row12);
		tPanel.add(reset);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		//Apply
		//for (int i = 0; i < 2; i++){
		//for(int j = 0; j <3; j++){		
		//m[i+2*j] = 0,2,4,1,3,5        CLEVER AF
		//}}
		
		if("Apply".equals(command)) {
			arrowShape.r00 = Double.parseDouble(row00.getText());
			arrowShape.r01 = Double.parseDouble(row01.getText());
			arrowShape.r02 = Double.parseDouble(row02.getText());
			arrowShape.r10 = Double.parseDouble(row10.getText());
			arrowShape.r11 = Double.parseDouble(row11.getText());
			arrowShape.r12 = Double.parseDouble(row12.getText());	
		}else if("Reset".equals(command)) {
			arrowShape.r00 = 1;
			arrowShape.r01 = 0;
			arrowShape.r02 = 0;
			arrowShape.r10 = 0;
			arrowShape.r11 = 1;
			arrowShape.r12 = 0;
			row00.setText("1");
			row01.setText("0");
			row02.setText("0");
			row10.setText("0");
			row11.setText("1");
			row12.setText("0");	
		}
		repaint();
	}
	
}

class Arrow extends JPanel{
	public static double r00=1;
	public static double r01=0;
	public static double r02=0;
	public static double r10=0;
	public static double r11=1;
	public static double r12=0;
	
	
	public Arrow() {
		super();
		setPreferredSize(new Dimension(600,600));
		setBackground(Color.WHITE);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		
		Path2D path = new Path2D.Double();
		path.moveTo(200,500);
		path.lineTo(200, 200);
		path.lineTo(125, 200);
		path.lineTo(250, 50);
		path.lineTo(375, 200);
		path.lineTo(300, 200);
		path.lineTo(300, 500);
		path.curveTo(300, 500, 250, 300, 200, 500);
		path.closePath();
		path.moveTo(250, 325);
		path.lineTo(250, 200);
		
		GradientPaint gp = new GradientPaint(250,50,Color.BLACK,275,75,Color.WHITE,true);
		g2.setPaint(gp);
		BasicStroke stroke = new BasicStroke(12,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER);
		g2.setStroke(stroke);
		AffineTransform at = new AffineTransform(r00,r10,r01,r11,r02,r12);
		g2.setTransform(at);
		g2.draw(path);
	}
}

class TransformationPanel extends JPanel {
	
	public TransformationPanel() {
	    super();
	}
}
