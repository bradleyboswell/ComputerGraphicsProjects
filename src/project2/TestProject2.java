package project2;
/**
 *
 * @author Hong Zhang
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TestProject2 extends JPanel {
    Shape shape = Project2.createYingYang();

    public TestProject2() {
        setPreferredSize(new Dimension(400, 400));
        setBackground(Color.white);
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ev) {
                shape = Project2.createSpirograph();
                repaint();
            }
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(200, 200);
        g2.fill(shape);
    }

    public static void main(String s[]) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new TestProject2();
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
