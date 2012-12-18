/*
 * 需求：练习使用BorderLayout管理器
 */

import java.awt.BorderLayout;
import javax.swing.*;

public class UseBorderLayout extends JFrame
{
	public UseBorderLayout()
	{
		//Create  panel to group buttons
		JPanel p1 = new JPanel( new BorderLayout(2, 2) );
		JPanel p2 = new JPanel( new BorderLayout(2, 2) );
		JButton jbt1 = new JButton("Button 1");
		JButton jbt2 = new JButton("Button 2");
		JButton jbt3 = new JButton("Button 3");
		JButton jbt4 = new JButton("Button 4");
		JButton jbt5 = new JButton("Button 5");
		JButton jbt6 = new JButton("Button 6");
        
		//Add buttons to p1 or p2
		p1.add(jbt1, BorderLayout.WEST);
		p1.add(jbt2, BorderLayout.CENTER);
		p1.add(jbt3, BorderLayout.EAST);
		p2.add(jbt4, BorderLayout.WEST);
		p2.add(jbt5, BorderLayout.CENTER);
		p2.add(jbt6, BorderLayout.EAST);
        
		//Add two panels to the frame
		setLayout( new BorderLayout(20, 10) );
		add(p1, BorderLayout.CENTER);
		add(p2, BorderLayout.EAST);
	}

	public static void main(String[] args)
	{
		//Create a frame and set its propertities
		JFrame frame = new UseBorderLayout();
		frame.setTitle( "Exercise12_1" );
		frame.setSize( 650, 100 );
		frame.setLocationRelativeTo( null );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setVisible( true );
	}
}
