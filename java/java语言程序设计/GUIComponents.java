import javax.swing.*;

public class GUIComponents
{
	public static void main(String[] args)
	{
		//Create a button with text OK
		JButton jbtOK = new JButton( "OK" );

		//Create a button with text Cancel
		JButton jbtCancel = new JButton( "Cancel" );

		//Create a label with text "Enter you name: "
		JLabel jlblName = new JLabel( "Enter your name: " );

		//Create a text field with text "Type Name Here"
		JTextField jtfName = new JTextField( "Type Name Here" );

		//Create a check box with text bold
		JCheckBox jchkBold = new JCheckBox( "Bold" );

		//Create a check box with text italic
		JCheckBox jchkItalic = new JCheckBox( "Italic" );

		//Create a radio button with text red
		JRadioButton jrbRed = new JRadioButton( "Red" );

		//Create a radio button with text yellow
		JRadioButton jrbYellow = new JRadioButton( "Yellow" );

		//Create a combo box with several choices
		JComboBox jcboColor = new JComboBox( new String[]{ "Freshman", "Sophomore", "Junior", "Senior" } );

		//Create a panel to group components
		JPanel panel = new JPanel();
		panel.add(jbtOK);
		panel.add(jbtCancel);
		panel.add(jlblName);
		panel.add(jtfName);
		panel.add(jchkBold);
		panel.add(jchkItalic);
		panel.add(jrbRed);
		panel.add(jrbYellow);
		panel.add(jcboColor);

		JFrame frame = new JFrame();
		frame.add(panel);
		frame.setTitle("Show GUI Components");
		frame.setSize( 450,100 );
		frame.setLocation( 200,100 );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setVisible( true );
	}
}
