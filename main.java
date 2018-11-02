import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.event.*;
import pkg.*;
		
public class main extends JFrame{
	JPanel control;
	JButton run;
	JButton snode;
	JButton enode;
	JButton reset;
	main(int w,int h){
		this.setSize(w,h);

		display dis = new display(w,h-40);
		dis.setBounds(0,40,w,h-40);
		dis.setLayout(new FlowLayout());
		dis.setVisible(true);

		// buttons			
		snode = new JButton("SNode");
		snode.setBounds(w-50,20,40,50);
		snode.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dis.colorvalue = 2;
			}
		});

		enode = new JButton("ENODE");
		enode.setBounds(w-50,20,40,50);
		enode.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dis.colorvalue = 3;
			}
		});			
	
		run = new JButton("Run");
		run.setBounds(w-50,20,40,50);
		run.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dis.start();
			}
		});	

		reset = new JButton("Reset");
		reset.setBounds(w-50,20,40,50);
		reset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dis.reset();
			}
		});	
		//button end heres
		//zoom slider

		JSlider zoom = new JSlider(JSlider.HORIZONTAL,10,50,40);
		zoom.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){
				int val = ((JSlider)e.getSource()).getValue();
				System.out.println(val);
				dis.setboxno(val);

			}
		});

		//end



		control = new JPanel();
		control.setBounds(0,0,w,40);
		control.setLayout(new FlowLayout());
		control.setBackground(Color.GRAY);
		control.setVisible(true);
		control.add(snode);
		control.add(enode);
		control.add(run);
		control.add(reset);
		control.add(zoom);


		this.add(dis);
		this.add(control);
	}
	public static void main(String[] args) {
		main a = new main(600,640);
		a.setLayout(null);
		a.setVisible(true);
	}
}