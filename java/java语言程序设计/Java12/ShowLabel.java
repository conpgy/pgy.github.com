/*
 * 需求：编写一个程序，实现在四个标签上显示四行文本，在每个标签上添加一条边界线 
 */

import java.awt.GridLayout;
import javax.swing.*;

public class ShowLabel extends JFrame
{
	public ShowLabel()
	{
		setLayout( new GridLayout( 4, 1, 5, 5 ) );

		add( new JLabel("Department of computer science") );
		add( new JLabel("School of Computing") );
		add( new JLabel( "Armstrong Atlantic State University" ) );
		add( new JLabel( "Tel:(921)921-6440" ) );
	}

	public static void main(String[] args)
	{
		ShowLabel frame = 
	}
}
