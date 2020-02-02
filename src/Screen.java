import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Random;


import static javax.swing.BorderFactory.createLineBorder;

public class Screen extends JPanel implements Runnable{

    //Height and width in pixels of the window
    public static final int WIDTH = 800, HEIGHT = 600;
    //Size of tiles
    public static final int TILESIZE = 10;

    public Thread thread;
    public boolean running = false;
    public int[][] cells = new int[HEIGHT/TILESIZE][WIDTH/TILESIZE];
    public int[][] end = new int[HEIGHT/TILESIZE][WIDTH/TILESIZE];
    Random random = new Random();

    int ticks = 0;


    public Screen()
    {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        Border border = BorderFactory.createLineBorder(Color.BLACK,3);
        setBorder(border);
        setBackground(Color.WHITE);
        //start();
    }

    public void tick()
    {
        ticks++;
        if(ticks > 1000000)
        {
            ticks = 0;
            for(int i = 0; i < HEIGHT/TILESIZE; i++)
            {
                for(int j = 0; j < WIDTH/TILESIZE; j++)
                {
                    if(i - 1 > 0 && i + 1 < HEIGHT/TILESIZE && j - 1 > 0 && j + 1 < WIDTH/TILESIZE) {
                        int surroundingCells = surround(cells, i, j);
                        if (surroundingCells < 2 || surroundingCells > 3) {
                            end[i][j] = 0;
                        } else if (surroundingCells == 3) {
                            end[i][j] = 1;
                        }
                        else if(surroundingCells == 2 && cells[i][j] == 1)
                        {
                            end[i][j] = 1;
                        }
                    }
                }
            }

            for (int i = 0; i < HEIGHT/TILESIZE; i++) {
                for (int j = 0; j < WIDTH/TILESIZE; j++) {
                    cells[i][j] = end[i][j];
                }
            }
        }
    }


    public int surround(int[][] initialCells, int i, int j)
    {
        int surroundCounter = 0;
                                    //below     above         left          right           topleft         bottomright    top right         bottomleft
        int[] surroundingCells = {initialCells[i-1][j],initialCells[i+1][j],initialCells[i][j-1],initialCells[i][j+1],initialCells[i-1][j-1],initialCells[i+1][j+1], initialCells[i-1][j+1], initialCells[i+1][j-1]};

        for(int a = 0; a < surroundingCells.length; a++)
        {
            if(surroundingCells[a] == 1)
            {
                surroundCounter++;
            }
        }
        return surroundCounter;
    }

    public void resetCells()
    {
        for(int i = 0; i < cells.length; i++)
        {
            for(int j = 0; j < cells[0].length; j++)
            {
                cells[i][j] = 0;
            }
        }

        running = false;
        repaint();
    }

    public void paint(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,WIDTH, HEIGHT);

        //Colour the status of the cells
        for(int i = 0; i < HEIGHT/TILESIZE; i++)
        {
            for(int j = 0; j < WIDTH/TILESIZE; j++)
            {
                if(cells[i][j] == 1)
                {
                    g.setColor(Color.WHITE);
                    g.fillRect(j*TILESIZE, i*TILESIZE, TILESIZE, TILESIZE);
                }
                else if(cells[i][j] == 0)
                {
                    g.setColor(Color.BLACK);
                    g.fillRect(j*TILESIZE, i*TILESIZE, TILESIZE, TILESIZE);
                }

            }
        }

        //Draw Grid
        for(int i = 0; i < WIDTH/TILESIZE; i++)
        {
            g.setColor(Color.DARK_GRAY);
            g.drawLine(i*TILESIZE, 0, i * TILESIZE, HEIGHT);
        }

        for(int i = 0; i < WIDTH/TILESIZE; i++)
        {
            g.setColor(Color.DARK_GRAY);
            g.drawLine(0, i*TILESIZE, WIDTH, i*TILESIZE);
        }

    }

    @Override
    public void run() {
        while(running)
        {
            tick();
            repaint();
        }
        if(running == false)
        {
            for(int i = 0; i < HEIGHT/TILESIZE; i++)
            {
                for(int j = 0; j < WIDTH/TILESIZE; j++)
                {
                    if(cells[i][j] == 1) {
                        System.out.println("cells[" + i + "][" + j + "] = 1;");
                    }
                }
            }
            System.out.println("==============");
        }

    }

    public void start()
    {
        running = true;

        //Repeating line Shape
        /*cells[6][19] = 1;
        cells[7][18] = 1;
        cells[7][19] = 1;
        cells[7][20] = 1;
        cells[8][17] = 1;
        cells[8][18] = 1;
        cells[8][19] = 1;
        cells[8][20] = 1;
        cells[8][21] = 1;
        cells[15][17] = 1;
        cells[15][18] = 1;
        cells[15][19] = 1;
        cells[15][20] = 1;
        cells[15][21] = 1;
        cells[16][18] = 1;
        cells[16][19] = 1;
        cells[17][19] = 1;
        cells[16][20] = 1;*/

        //Big thing
        /*cells[9][15] = 1;
        cells[9][16] = 1;
        cells[9][17] = 1;
        cells[9][21] = 1;
        cells[9][22] = 1;
        cells[9][23] = 1;
        cells[11][13] = 1;
        cells[11][18] = 1;
        cells[11][20] = 1;
        cells[11][25] = 1;
        cells[12][13] = 1;
        cells[12][18] = 1;
        cells[12][20] = 1;
        cells[12][25] = 1;
        cells[13][13] = 1;
        cells[13][18] = 1;
        cells[13][20] = 1;
        cells[13][25] = 1;
        cells[14][15] = 1;
        cells[14][16] = 1;
        cells[14][17] = 1;
        cells[14][21] = 1;
        cells[14][22] = 1;
        cells[14][23] = 1;
        cells[16][15] = 1;
        cells[16][16] = 1;
        cells[16][17] = 1;
        cells[16][21] = 1;
        cells[16][22] = 1;
        cells[16][23] = 1;
        cells[17][13] = 1;
        cells[17][18] = 1;
        cells[17][20] = 1;
        cells[17][25] = 1;
        cells[18][13] = 1;
        cells[18][18] = 1;
        cells[18][20] = 1;
        cells[18][25] = 1;
        cells[19][13] = 1;
        cells[19][18] = 1;
        cells[19][20] = 1;
        cells[19][25] = 1;
        cells[21][15] = 1;
        cells[21][16] = 1;
        cells[21][17] = 1;
        cells[21][21] = 1;
        cells[21][22] = 1;
        cells[21][23] = 1;*/


        thread = new Thread(this, "GameLoop");
        thread.start();
    }


}
