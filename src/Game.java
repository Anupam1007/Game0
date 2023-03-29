import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Game extends JPanel implements ActionListener{
	Map m;
	public boolean inGame=true;
	private boolean leftDirection = false;
    private boolean rightDirection = false;
    private int flag=1;
	Timer timer;
	private final int difficulty=0;
	private int delay=10;
	public Random r=new Random();
	private int head=0;
	private int head1=0;
	private int size_temp=5;
	private int DOT_SIZE=100;
	private int points=0;
	private boolean gotIt=false;
	//game variables below:
	private final int numBalls=3;
	private int offset=100;//hardcoded
	private int downArr0[]=new int[numBalls];
	private int downArr2[]=new int[numBalls];
	private Image imgG[]=new Image[6];
	private Image imgG1[]=new Image[6];
	private Image imgG2[]=new Image[6];
	private Image imgG3[]=new Image[6];
	
	private final int constX=(Map.B_X)/12;
	private int batPos=constX;
	Game(){
		
		m=new Map();
		addKeyListener(new TAdapter());
		setFocusable(true);
		this.setPreferredSize(new Dimension(Map.B_X, Map.B_Y));
		timer = new Timer(10, this);
        timer.start();
        for(int i=0;i<numBalls;i++)
        	{
        		downArr0[i]= -i*Map.B_Y/numBalls;//{0,-80,-160,-240,-320}; //-1 for caught
        		downArr2[i]=-i*Map.B_Y/numBalls-offset/2-10;
        	}
        Random r=new Random();
        for(int i=0;i<numBalls;i++)
        	{
        		imgG[i]=m.img[r.nextInt(2)];
        		imgG1[i]=m.img[r.nextInt(2)];
        	}
	}
	private void restart() {
		inGame=true;
		//timer.setDelay(delay);
		//timer.restart();
		setFocusable(true);
		for(int i=0;i<numBalls;i++)
    	{
    		downArr0[i]= -i*Map.B_Y/numBalls;
    		downArr2[i]=-i*Map.B_Y/numBalls-offset/2-10;
    	}
	    Random r=new Random();
	    for(int i=0;i<numBalls;i++)
    	{
    		imgG[i]=m.img[r.nextInt(2)];
    		imgG1[i]=m.img[r.nextInt(2)];
    	}
	    head=head1=0;
	    points=0;
	    batPos=constX;
	}
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawLine(m.x0, m.y0+18, m.x1, m.y1);
        drawBalls(g);
    }
	private void drawBalls(Graphics g) {    	
    	if (inGame) {
    		
    		
    		for(int i=0;i<numBalls;i++)
    			{
    				g.drawImage(imgG[i], (m.x0)/6, (downArr0[i]), this);
    				g.drawImage(imgG1[i], m.x0/2+(m.x0)/6, (downArr2[i]), this);
    			}
    		g.drawImage(m.bat, batPos, (Map.B_Y)-15, this);
    		leftDirection=false;
    		rightDirection=false; 		
    		
    		String msg = String.valueOf(points);
            Font small = new Font("Helvetica", Font.BOLD, 16);
            FontMetrics metr = getFontMetrics(small);

            g.setColor(Color.black);
            g.setFont(small);
            g.drawString(msg, (Map.B_X) / 2-8, 16);
            
            Toolkit.getDefaultToolkit().sync();

        } else {
            gameOver(g);
        }  
	}
	@Override
    public void actionPerformed(ActionEvent e) {
    	
        if (inGame) {

            check(); // like apple check
            //checkCollision();
            move();
        }
        else {
        	String str="game over your score: "+String.valueOf(points)+"\nDo you want to play again?";
        	if ( JOptionPane.showConfirmDialog(null, str) == JOptionPane.YES_OPTION )
    		{
        		restart();
        		
    		}
        	else
        		System.exit(0);
        }
        repaint();
    }
	
	private synchronized void move() {
		for(int i=0;i<numBalls;i++) {
			downArr0[i]+=2;
			downArr2[i]+=2;
		}
		if (leftDirection) {
			batPos -= DOT_SIZE;
        }

        if (rightDirection) {
        	batPos += DOT_SIZE;
        }
	}
	private void check() {
		if(batPos==constX && downArr0[head]>=380 && downArr0[head]<390 ) {
			if(imgG[head] == m.img[0] )
				points++;
			else
				inGame=false;		
		}
		if(batPos==constX+m.x0/2 && downArr2[head1]>=380 && downArr2[head]<390) {
			if(imgG1[head1] == m.img[0] )
				points++;
			else
				inGame=false;
		}
		if(Map.B_Y-downArr0[head]<=difficulty) {
			{
				downArr0[head]=0;
				imgG[head]=m.img[r.nextInt(2)];
				//imgG1[head]=m.img[r.nextInt(2)];//big problem
			}
			if(head<numBalls-1)
				head++;
			else head=0;
		}	
		if(Map.B_Y-downArr2[head1]<=difficulty) {
			{
				downArr2[head1]=0;
				imgG1[head1]=m.img[r.nextInt(2)];
				//imgG1[head]=m.img[r.nextInt(2)];//big problem
			}
			if(head1<numBalls-1)
				head1++;
			else head1=0;
		}
	}
	private void gameOver(Graphics g) {
        
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (Map.B_X - metr.stringWidth(msg)) / 2, Map.B_Y / 2);
    }
		
	public class TAdapter extends KeyAdapter{
		@Override
	    public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
		    
		    if ((key == KeyEvent.VK_LEFT) && (!rightDirection) && batPos-100>0) {
		        leftDirection = true;
		    }
		
		    if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
		        rightDirection = true;

		    }
		    flag=1;
	}
	@Override
	public void keyReleased(KeyEvent e) {
		flag=0;
	}
	}
}
