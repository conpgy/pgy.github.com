/*
 * 需求：练习使用FlowLayout管理器
 */

import java.awt.FlowLayout;
import javax.swing.*;

public class UseFlowLayout extends JFrame
{
	public UseFlowLayout()
	{
		//Create  panel to group buttons
		JPanel p1 = new JPanel( new FlowLayout(FlowLayout.LEFT, 2, 2) );
		JPanel p2 = new JPanel( new FlowLayout(FlowLayout.LEFT, 2, 2) );
		JButton jbt1 = new JButton("Button 1");
		JButton jbt2 = new JButton("Button 2");
		JButton jbt3 = new JButton("Button 3");
		JButton jbt4 = new JButton("Button 4");
		JButton jbt5 = new JButton("Button 5");
		JButton jbt6 = new JButton("Button 6");
        
		//Add buttons to p1 or p2
		p1.add(jbt1);
		p1.add(jbt2);
		p1.add(jbt3);
		p2.add(jbt4);
		p2.add(jbt5);
		p2.add(jbt6);
        
		//Add two panels to the frame
		setLayout( new FlowLayout(FlowLayout.LEFT, 10, 20) );
		add(p1);
		add(p2);
	}

	public static void main(String[] args)
	{
		//Create a frame and set its propertities
		UseFlowLayout frame = new UseFlowLayout();
		frame.setTitle( "Exercise12_1" );
		frame.setSize( 650, 100 );
		frame.setLocationRelativeTo( null );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setVisible( true );
	}
}
