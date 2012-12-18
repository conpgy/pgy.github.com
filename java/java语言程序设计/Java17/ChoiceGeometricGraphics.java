/*
 * 需求：一个显示各种几何图形的程序
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ChoiceGeometricGraphics extends JFrame
{
	//创建一个复选按钮和三个单选按钮
	private JCheckBox jchkFilled = new JCheckBox("Filled");
	private  JRadioButton jrbLine, jrbRectangle, jrbOval;
	private boolean isFilled = false;

	//创建一个FigurePanel变量
	FigurePanel figurePanel = new FigurePanel();

	public ChoiceGeometricGraphics()
	{
		//Create a new panel to hold check boxes
		JPanel jpButtons = new JPanel();
		jpButtons.setLayout(new GridLayout(1, 4));
		jpButtons.add(jrbLine = new JRadioButton("Line"));
		jpButtons.add(jrbRectangle = new JRadioButton("Rectangle"));
		jpButtons.add(jrbOval = new JRadioButton("Oval"));
		jpButtons.add(jchkFilled);
		add(jpButtons, BorderLayout.SOUTH);

		//将添加到框架中
		add(figurePanel, BorderLayout.CENTER);

		//Create a radio-button group to group three buttons
		ButtonGroup group = new ButtonGroup();
		group.add(jrbLine);
		group.add(jrbRectangle);
		group.add(jrbOval);

		//Register listeners for radio buttons and check box
		jrbLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				figurePanel.setType(FigurePanel.LINE);	
			}
		});
		jrbRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				figurePanel.setType(FigurePanel.RECTANGLE);	
			}
		});
		jrbOval.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				figurePanel.setType(FigurePanel.OVAL);	
			}
		});
		jchkFilled.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(isFilled)
				{
					figurePanel.setFilled(false);	
					isFilled = false;
				}
				else
				{
					figurePanel.setFilled(true);
					isFilled = true;
				}
			}
		});

		//Set initial graphics
		jrbOval.setSelected(true);
		figurePanel.setType(FigurePanel.OVAL);
	}

	public static void main(String[] args)
	{
		ChoiceGeometricGraphics frame = new ChoiceGeometricGraphics();
		frame.setTitle("Choice geometric graphics");
		frame.setSize(500, 250);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}	
}
