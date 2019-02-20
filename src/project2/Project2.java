package project2;

/**
 * @author - Bradley Boswell
 * @class - Computer Grahpics
 * @description - 
 * An exploration of constructive geometry (via yin-yang graphic) and 
 * the capabilities of the Path2D object in java (via spirograph graphic)
 */

import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;

public class Project2 {

	public static Shape createYingYang() {
		
		//Layer the circle in halfs
		Ellipse2D.Double bigCircle = new Ellipse2D.Double();
		bigCircle.setFrameFromCenter(0, 0, 100, 100);
		Area bigCirc = new Area(bigCircle);	
		Arc2D.Double whiteHalf = new Arc2D.Double(bigCircle.getBounds2D().getX()+1, bigCircle.getBounds2D().getY()+1, bigCircle.getBounds2D().getWidth()-2, bigCircle.getBounds2D().getHeight()-2, -90, -180, Arc2D.OPEN);
		Area leftArea = new Area(whiteHalf);
		bigCirc.subtract(leftArea);
		
		//Establish top circles
		Ellipse2D.Double topCircle = new Ellipse2D.Double();
		topCircle.setFrameFromCenter(0, -50, -50, -1);
		Ellipse2D.Double smallBlackCircle = new Ellipse2D.Double();
		smallBlackCircle.setFrameFromCenter(0, -50, -5, -45);
		Area topCirc = new Area(topCircle);
		Area smallBlack = new Area(smallBlackCircle);
		bigCirc.subtract(topCirc);
		bigCirc.add(smallBlack);
		
		//Establish bottom circles
		Ellipse2D.Double bottomCircle = new Ellipse2D.Double();
		bottomCircle.setFrameFromCenter(0, 50, -50, -1);
		Ellipse2D.Double smallWhiteCircle = new Ellipse2D.Double();
		smallWhiteCircle.setFrameFromCenter(0, 50, -5, 45);
		Area bottomCirc = new Area(bottomCircle);
		Area smallWhite = new Area(smallWhiteCircle);
		bigCirc.add(bottomCirc);
		bigCirc.subtract(smallWhite);
		
		//Return ying yang area
		return bigCirc;
	}

	public static Shape createSpirograph() {
		double r1 = 30;
		double r2 = 40;
		double p = 60;
		int nPoints = 1000;
		double xCenter = 0;
		double yCenter = 0;
		double x, y;
		Path2D path = new Path2D.Double(Path2D.WIND_EVEN_ODD);
		path.moveTo(xCenter, yCenter);
		for (int i=1; i <= nPoints; i++) {
			double t = 8*Math.PI/nPoints * i;
		    x = (int)((r1+r2)*Math.cos(t)-p*Math.cos(((r1+r2)/r2)*t));
		    y = (int)((r1+r2)*Math.sin(t)-p*Math.sin(((r1+r2)/r2)*t));     
		    path.lineTo(x,y);
		}	
		return path;
	}

	
}
