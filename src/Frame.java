import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class Frame extends JFrame {

    public JButton reset;
    public JButton start;
    public JButton stop;

    public Frame()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Game Of Life");
        setLayout(new BorderLayout());
        setResizable(false);

        init();
    }

    public void init()
    {
        Screen s = new Screen();
        start = new JButton();
        start.setPreferredSize(new Dimension(100,30));
        start.setBackground(Color.WHITE);
        start.setLabel("Start");
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s.start();
            }
        });

        stop = new JButton();
        stop.setPreferredSize(new Dimension(100,30));
        stop.setBackground(Color.WHITE);
        stop.setLabel("Stop");
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s.running = false;
            }
        });

        reset = new JButton();
        reset.setPreferredSize(new Dimension(100,30));
        reset.setBackground(Color.WHITE);
        reset.setLabel("Reset");
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s.resetCells();
            }
        });

        s.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int xCoor = e.getX()/Screen.TILESIZE;
                int yCoor = e.getY()/Screen.TILESIZE;


                if(s.cells[yCoor][xCoor] == 1)
                {
                    s.cells[yCoor][xCoor] = 0;
                }
                else
                {
                    s.cells[yCoor][xCoor] = 1;
                }
                s.repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        s.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int counter = 0;
                counter++;
                int xCoor = e.getX()/Screen.TILESIZE;
                int yCoor = e.getY()/Screen.TILESIZE;

                if(s.cells[yCoor][xCoor] == 1)
                {
                    //s.cells[yCoor][xCoor] = 0;
                }
                else
                {
                    s.cells[yCoor][xCoor] = 1;
                }
                s.repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });

        JPanel bottom = new JPanel();
        bottom.setPreferredSize(new Dimension(400, 40));
        bottom.add(start);
        bottom.add(stop);
        bottom.add(reset);
        bottom.setBackground(Color.BLACK);
        bottom.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3, isAlwaysOnTop()));

        add(bottom, BorderLayout.SOUTH);
        add(s);


        pack();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Frame();
    }

}
