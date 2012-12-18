/*
 * 需求：定义一个类扩展JPanel类。在面板中放置三个按钮，然后从这个用户
 	 定义的面板类中创建两个面板
 */
import java.awt.FlowLayout;
import javax.swing.*;

public class MyJPanel extends JPanel
{
	public MyJPanel( String buttonName1, String buttonName2, String buttonName3 )
	{
		setLayout( new FlowLayout(FlowLayout.LEFT, 5, 5) );

		add( new JButton(buttonName1) );
		add( new JButton(buttonName2) );
		add( new JButton(buttonName3) );
	}

	public static void main(String[] args)
	{
		MyJPanel p1 = new MyJPanel( "Button 1", "Button 2", "Button 3" );
		MyJPanel p2 = new MyJPanel( "Button 4", "Button 5", "Button 6" );

		JFrame frame = new JFrame();
		frame.setLayout( new FlowLayout(FlowLayout.LEFT, 10, 10) );
		frame.add(p1);
		frame.add(p2);

		frame.setTitle( "Exercise12_4" );
		frame.setSize( 650, 100 );
		frame.setLocationRelativeTo( null );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setVisible( true );
	}
}
