package project4;

/**
 * @author Bradley Boswell
 * @class Computer Graphics
 * @description
 * Write a Java program with a text field, a button, and a text area as shown below. 
 * A user may enter a string in the text field. When the button “Print” is clicked, 
 * the text area will display the pattern of the string formed with the character ‘*’. 
 * Print the same pattern to the console. 
 */

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.PrintStream;


public class Project4 extends JApplet{

	public static JFrame frame = null;
	
	public static void main(String[] args) {
		frame = new JFrame();
		frame.setTitle("Boswell_Project4");
		frame.setSize(new Dimension(1000,600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JApplet applet = new Project4();
		applet.init();
		frame.getContentPane().add(applet);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	public JTextField field = null;
	public JButton button = null;
	public PrintPreview printpreview = null;
	
	public void init() {
		BorderLayout borderlayout = new BorderLayout();
		borderlayout.preferredLayoutSize(this);
		setLayout(borderlayout);
		
		JPanel topbar = new JPanel();
		topbar.setPreferredSize(new Dimension(1000,25));
		add(topbar, BorderLayout.NORTH);
		FlowLayout flow = new FlowLayout();
		flow.setVgap(0);
		topbar.setLayout(flow);
			
		field = new JTextField();
		field.setPreferredSize(new Dimension(900,25));
		button = new JButton("Print");
		button.setPreferredSize(new Dimension(100,25));	
		button.addActionListener(ev -> buttonClick());
		topbar.add(field);
		topbar.add(button);
		
		printpreview = new PrintPreview();
		printpreview.setPreferredSize(new Dimension(900,600));
		getContentPane().add(printpreview, BorderLayout.CENTER);	
	}

	//Event Handler
	private void buttonClick() {
		printpreview.input = field.getText();	
		printpreview.metrics = printpreview.getGraphics().getFontMetrics();	
		printpreview.ta.setText(null);
		repaint();
	}

}

class PrintPreview extends JPanel{
	public final Font gfont = new Font("TimesRoman", Font.BOLD, 18);
	public final Font tfont = new Font("Consolas", Font.PLAIN, 18);
	public Dimension dimensions = new Dimension(1000,600);
	
	//Defaults
	public String input = " ";
	public BufferedImage image = null;
	public int width = 1;
    public int height = 1;
	public JTextArea ta = null;
	public PrintStream output = null;
	public FontMetrics metrics = null;
	
	public PrintPreview(){
		super();
		setPreferredSize(dimensions);
		setBackground(Color.WHITE);
		ta = new JTextArea();
		ta.setFont(tfont);
		ta.setEditable(false);
		ta.setPreferredSize(dimensions);
		add(ta);
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
        g2.setFont(gfont);
        metrics = g2.getFontMetrics();
        width = metrics.stringWidth(input);
        height = metrics.getAscent();       
        image = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        g2 = image.createGraphics();
        g2.setFont(gfont);
        g2.drawString(input, 0, height);
        System.out.println(metrics.getAscent());
        if(input == " ") return;
        else {
	        for (int h = 0; h < height; h++) {
	            for (int w = 0; w < width; w++) {
	            	if(image.getRGB(w, h) == 0) {
	            		System.out.print(" ");
	            		ta.append(" ");
	            	}else {
	            		System.out.print("*");
	            		ta.append("*");
	            	}
	            }
	            ta.append("\n");
	            System.out.println();
	        }
        }   
	}
}

