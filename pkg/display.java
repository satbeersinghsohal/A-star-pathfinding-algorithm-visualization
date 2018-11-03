package pkg;
// import java.util.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.*;
import java.awt.event.*;
import pkg.*;


public class display extends JPanel implements MouseMotionListener , MouseListener, KeyListener{
	int w;
	int h;
	public static int boxno=20;
	static int arr[][];
	int reset = 0;
	static int b;
	node start,end;
	public static int colorvalue=0;

	public display(int w,int h){
		this.w = w;
		this.h = h;
		this.b = w/boxno;
		arr = new int[50][50];
		addMouseMotionListener(this);
		addMouseListener(this);
		addKeyListener(this);
	}
	
	public void setboxno(int val){
		boxno = val;
		b = w/boxno;
		repaint();
	}
	public void setspeed(int val){
		algo.speed = val;
		// repaint();
	}
	public void paintComponent(Graphics g){
		for(int i=0;i<boxno;i++){
			for(int j=0;j<boxno;j++){
				Color color = Color.WHITE;
				if(reset == 0){
					arr[i][j]=0;
				}else{
					switch(arr[i][j]){
						case 0: color = Color.WHITE;break;
						case 1: color = Color.BLACK;break;
						case 2: color = Color.YELLOW;  break;
						case 3: color = Color.ORANGE;break;
						case 4: color = Color.BLUE;break;
						case 5: color = Color.GREEN;break;
						case 6: color = Color.RED;break;
						case 7: color = Color.MAGENTA;break;
					}
				}
				g.setColor(color);
				g.fillRect(i*b,j*b,b,b);
				g.setColor(Color.BLACK);
				g.drawRect(i*b,j*b,b,b);
				if(start != null && start.x == i&& start.y == j ){
					g.drawString("S",i*b+b/2,j*b+b/2);
				}
				if(end   != null && end.x == i&& end.y == j){
					g.drawString("E",i*b+b/2,j*b+b/2);	
				}
			}
		}
		reset = 1;
		repaint();
	}

	public void start(){
		Graphics g = getGraphics();
		 algo a = new algo(start,end,g,b);
	}
	public void reset(){
		// a = null;
		reset = 0;
		start = null;
		end = null;
		repaint();
	}

	public void drawboxs(int i,int j){
		if(!(i<0||j<0||i>=boxno||j>=boxno)){
			
			if(colorvalue == 2 && start ==null){
				start = new node(i,j);
			}else if(colorvalue == 3 && end == null ){
				end = new node(i,j);
			}else if(colorvalue < 2 && start != null && start.isat(i,j)){
				start = null;	
			}else if(colorvalue < 2 && end != null && end.isat(i,j)){
				end = null;
				
			}else if(colorvalue >= 2){
				colorvalue = 1;
			}
				Graphics g = getGraphics();
				Color color = Color.WHITE;
				switch(colorvalue){
					case 0: color = Color.WHITE;break;
					case 1: color = Color.BLACK;break;
					case 2: color = Color.RED;  break;
					case 3: color = Color.ORANGE;break;
				}
				g.setColor(color);
				g.fillRect(i*b,j*b,b,b);
				g.setColor(Color.BLACK);
				g.drawRect(i*b,j*b,b,b);
				arr[i][j]=colorvalue;
		}
	}
	
    public void mousePressed(MouseEvent e) {
    	int button = e.getButton();
    	int x      = e.getX()/b;
    	int y      = e.getY()/b;

    	if(button == 1 && colorvalue == 2 ){
    		
    	}else if(button == 1 && colorvalue == 3 ){
    		
    	}else if(button == 1){
    		colorvalue = 1;
    	}else if(button ==3){
    		colorvalue = 0;
    	}

    	drawboxs(x,y);
    }  
	public void mouseDragged(MouseEvent e){
    	// int button = e.getButton();
    	// System.out.println(button);
    	int x      = e.getX()/b;
    	int y      = e.getY()/b;
    	drawboxs(x,y);
    	
	}

	public void mouseMoved(MouseEvent e){

	}
	public void mouseClicked(MouseEvent e) {  
    }  
    public void mouseEntered(MouseEvent e) {
    	System.out.println("mouseenter");
    }  
    public void mouseExited(MouseEvent e) {
    	System.out.println("mouseexit");
    }  
    public void mouseReleased(MouseEvent e) {}  
    public void keyReleased(KeyEvent e){
    	System.out.println(e);
    }
    public void keyPressed(KeyEvent e){
    	System.out.println(e);

    }
    public void keyTyped(KeyEvent e){
    	if(e.getKeyChar() == 's'){
    		System.out.println("it in");
    	}
    	System.out.println(e);

    }


}