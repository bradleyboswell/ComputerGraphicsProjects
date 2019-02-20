package project5;

/**
 * 
 * @author Bradley Boswell
 * @class Computer Graphics
 * @description
 * Implement a cellular automaton with the following rules based on the four-neighbor of a cell: 
 * 1. A white cell becomes black if the number of its black neighbors is not 1; 
 * 2. A black cell stays black if the number of its black neighbors is either 1 or 3; 
 * 3. Otherwise, the cell becomes white.
 *
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Project5 extends JApplet {
	public static JFrame frame = null;
	public Automaton graphic = null;
	
	public static void main(String[] args) {
		frame = new JFrame();
		frame.setTitle("Boswell Project5 ExtraCredit");
		frame.setSize(new Dimension(1005,1005));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JApplet applet = new Project5();
		applet.init();
		frame.getContentPane().add(applet);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void init() {	
		graphic = new Automaton();
		graphic.setPreferredSize(new Dimension(1005,1005));
		add(graphic);
	}
}

class Automaton extends JPanel implements MouseListener, ActionListener{
	int n = 201;
	boolean[][] master;
	boolean[][] temp;
	Stroke s1 = new BasicStroke(2);
	Timer timer;
	public Automaton() {
	    setBackground(Color.white);
	    master = new boolean[n][n];
	    temp = new boolean[n][n];	    
	    for(int i = 0; i < n; i++) {
	    	for(int j = 0; j < n; j++) {
	    		master[i][j] = false;
	    		temp[i][j] = false;
	    	} 
	    }
	    master[(int) Math.ceil(n/2)][(int) Math.ceil(n/2)] = true;  //set center cell to black
	    addMouseListener(this);    		//Add mouse listener to panel to control timer
		timer = new Timer(100, this);  //Add timer event to this object
	    timer.start();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.lightGray); 
		
		int start = 1;
		int cellWidth = 5;
		int length = cellWidth*n;
		
		g2.setStroke(s1);
		for (int i = 0; i <= n; i++) {    //draw the grid
			g2.drawLine(0, start, length, start);
			g2.drawLine(start, 0, start, length);
			start = start + cellWidth;	
		}
		for(int i = 0; i < n; i++) {   //fill in black cells 
			for(int j = 0; j < n; j++) {
				if (master[i][j]) {
					g2.setColor(Color.BLACK);
					int x = i * cellWidth;
					int y = j * cellWidth;
					g2.fillRect(x, y, cellWidth, cellWidth);
				}
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		boolean tCells[][] = master;   //clone master array
		for (int i = 0; i < n; i++ ) {
			for(int j = 0; j < n; j++) {
				int neighbors = checkNeighbors(tCells, i, j);
				temp[i][j] = tCells[i][j];  //use temp array for control 
				
				if(tCells[i][j] == false && neighbors != 1 ) temp[i][j] = true;			//Check the neighbor rules 
				else if(tCells[i][j] == true && (neighbors == 1 || neighbors ==3)) temp[i][j] = true;
				else temp[i][j] = false;				
			}
		}
		master = temp;
	    temp = tCells;   
	    repaint();
	}
	
	//check for neighbors
	private int checkNeighbors(boolean[][] cell, int hostX, int hostY) {
		int xStart, xStop, yStart, yStop;
		int neighbors = 0;
		
		//define the start and stop indices for each cell surrounding our host cell
		if(hostX>0) xStart = hostX-1;  
		else xStart = hostX;

		if(hostX<n-1) xStop = hostX+1;
		else xStop = hostX;
		
		if(hostY>0) yStart = hostY-1;
		else yStart = hostY;
		
		if(hostY<n-1) yStop = hostY+1;
		else yStop = hostY;
  
		if (cell[hostX][hostY]) neighbors = neighbors - 1;  //Don't count the cell itself as a neighbor
		for (int i = xStart; i <= xStop; i++) {  //scan the 3x3 square surrounding out cell to check for neighbors
			if(i==hostX) { 						//if statement used to limit our neighbor count to only four-neighbors
				for (int j = yStart; j <= yStop; j++) {
					if(cell[i][j]) neighbors = neighbors+1;
		    	}
			}else {
				if(cell[i][hostY]) neighbors = neighbors+1;
		    }
		}
		return neighbors;
	}

	
	//Mouse listeners
	@Override
	public void mouseClicked(MouseEvent e) {
		if(timer.isRunning()) timer.stop();	
		else timer.start();
	}

	@Override
	public void mouseEntered(MouseEvent e) { }

	@Override
	public void mouseExited(MouseEvent e) { }

	@Override
	public void mousePressed(MouseEvent e) { }

	@Override
	public void mouseReleased(MouseEvent e) { }
}
