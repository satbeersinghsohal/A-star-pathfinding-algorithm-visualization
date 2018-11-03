import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.event.*;
import pkg.*;
		
public class main extends JFrame{
	JPanel control;
	JPanel buttons;
	JPanel sliders;
	JLabel zoomlabel;
	JLabel speedlabel;
	JButton run;
	JButton snode;
	JButton enode;
	JButton reset;
	main(int w,int h){
		this.setSize(w,h);

		display dis = new display(w,h-60);
		dis.setBounds(0,60,w,h-60);
		dis.setLayout(new FlowLayout());
		dis.setVisible(true);

		// buttons			
		snode = new JButton("SNode");
		snode.setBounds(0,10,80,40);
		snode.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dis.colorvalue = 2;
			}
		});

		enode = new JButton("ENode");
		enode.setBounds(80,10,80,40);
		enode.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dis.colorvalue = 3;
			}
		});			
	
		run = new JButton("Run");
		run.setBounds(160,10,80,40);
		run.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dis.start();
			}
		});	

		reset = new JButton("Reset");
		reset.setBounds(240,10,80,40);
		reset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dis.reset();
			}
		});	
		//button end heres
		//zoom slider

		JSlider zoom = new JSlider(JSlider.HORIZONTAL,10,50,20);
		zoom.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){
				int val = ((JSlider)e.getSource()).getValue();
				System.out.println(val);
				dis.setboxno(val);

			}
		});
		JSlider speed = new JSlider(JSlider.HORIZONTAL,0,20,20);
		speed.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){
				int val = ((JSlider)e.getSource()).getValue();
				System.out.println(20-val);
				dis.setspeed(20-val);
			}
		});
		//end



		zoom.setBounds(50,0,160,30);
		speed.setBounds(50,30,160,30);

		zoomlabel = new JLabel("Zoom");
		speedlabel = new JLabel("Speed");

		zoomlabel.setBounds(0,0,50,30);
		speedlabel.setBounds(0,30,50,30);

		control = new JPanel();
		buttons = new JPanel();
		sliders = new JPanel();
		control.setBounds(0,0,w,60);
		control.setLayout(null);
		// control.setBackground(Color.GRAY);
		control.setVisible(true);
		
		buttons.setBounds(0,0,(int)(w*0.6),60);
		buttons.setLayout(null);
		// buttons.setBackground(Color.GRAY);
		buttons.setVisible(true);

		sliders.setBounds((int)(w*0.6),0,w,60);
		sliders.setLayout(null);
		// sliders.setBackground(Color.GRAY);
		sliders.setVisible(true);

		buttons.add(enode);
		buttons.add(snode);
		buttons.add(run);
		buttons.add(reset);

		sliders.add(zoomlabel);
		sliders.add(speedlabel);
		sliders.add(zoom);
		sliders.add(speed);


		control.add(buttons);
		control.add(sliders);


		this.add(dis);
		this.add(control);
	}
	public static void main(String[] args) {
		main a = new main(600,660);
		a.setLayout(null);
		a.setVisible(true);
	}
}