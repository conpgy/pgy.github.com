import java.awt.*;
import javax.swing.*;

public class DisplayClock extends JFrame
{
	public DisplayClock()
	{
		//Create an analog clock for the current time
		StillClock clock = new StillClock();

		//Display hout, minutes, and second in the message panel
		MessagePanel messagePanel = new MessagePanel( clock.getHour() +
				":" + clock.getMinute() + ":" + clock.getSecond());
		messagePanel.setCentered( true );
		messagePanel.setForeground( Color.blue );
		messagePanel.setFont( new Font("Courier", Font.BOLD, 16) );

		//Add the clock and message panel to the frame
		add( clock );
		add( messagePanel, BorderLayout.SOUTH );
	}

	public static void main(String[] args)
	{
		DisplayClock frame = new DisplayClock();
		frame.setTitle( "Display clock" );
		frame.setSize( 300, 350 );
		frame.setLocationRelativeTo( null );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setVisible( true );
	}
}
