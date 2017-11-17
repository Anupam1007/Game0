import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Map{
	static int B_X=400;
	static int B_Y=400;
	final int x0=B_X/2;
	final int y0=0;
	final int x1=B_Y/2;
	final int y1=B_Y;
	Image ball;
	Image bat;
	Image head;
	Image[] img=new Image[2];
	
	Map(){
		ImageIcon imageIcon = new ImageIcon( Game.class.getResource("ImagesFolder/apple.png"));
    	Image image = imageIcon.getImage(); // transform it 
    	Image newimg = image.getScaledInstance(20,20,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
    	imageIcon = new ImageIcon(newimg); 
    	img[0] = imageIcon.getImage();
    	
    	imageIcon = new ImageIcon(Game.class.getResource("ImagesFolder/dot.png")); // load the image to a imageIcon
    	image = imageIcon.getImage(); // transform it 
    	newimg = image.getScaledInstance(20,20,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
    	imageIcon = new ImageIcon(newimg); 
    	bat = imageIcon.getImage();
    	
    	
    	imageIcon = new ImageIcon(Game.class.getResource("ImagesFolder/head.png")); // load the image to a imageIcon
    	image = imageIcon.getImage(); // transform it 
    	newimg = image.getScaledInstance(20,20,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
    	imageIcon = new ImageIcon(newimg); 
    	img[1] = imageIcon.getImage();
	}
	
}
