import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MultipleWindowsDemo extends JFrame
{
	private JTextArea jta;
	private JButton jbtShowHistogram = new JButton("Show Histogram");
	private Histogram histogram = new Histogram();

	//Create a new frame to hold the histogram panel
	private JFrame histogramFrame = new JFrame();

	public MultipleWindowsDemo()
	{
		//Store text area in a scroll pane
		JScrollPane scrollPane = new JScrollPane(jta = new JTextArea());
		scrollPane.setPreferredSize(new Dimension(300, 200));
		jta.setWrapStyleWord(true);
		jta.setLineWrap(true);

		//Place scroll pane and button in the frame
		add(scrollPane, BorderLayout.CENTER);
		add(jbtShowHistogram, BorderLayout.SOUTH);

		//Register listener
		jbtShowHistogram.addActionListener(new ActionListener() {
				/** Handle the button action */
			public void actionPerformed(ActionEvent e)
			{
				//Count the letters in the text area
				int[] count = countLetters();
				
				//Set the letter count to histogram for display
				histogram.showHistogram(count);
				
				//Show the frame
				histogramFrame.setVisible(true);
			}
		});

		//Create a new frame to hold the histogram panel
		histogramFrame.add(histogram);
		histogramFrame.pack();
		histogramFrame.setTitle("Histogram");
	}

	/** Count the letters in the text area */
	private int[] countLetters()
	{
		//Count for 26 letters
		int[] count = new int[26];

		//Get contents from the text area
		String text = jta.getText();

		//Count occurrences of each letter (case insensitive)
		for( int i = 0; i < text.length(); i++ )
		{
			char character = text.charAt(i);

			if((character >= 'A') && (character <= 'Z'))
			{
				count[character - 'A']++;
			}
			else if((character >= 'a') && (character <= 'z'))
			{
				count[character - 'a']++;
			}
		}

		return count;
	}

	public static void main(String[] args)
	{
		MultipleWindowsDemo frame = new MultipleWindowsDemo();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("MutipleWindowsDemo");
		frame.pack();
		frame.setVisible(true);
	}
}
