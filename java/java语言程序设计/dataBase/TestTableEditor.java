import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class TestTableEditor extends JApplet
{
	private JComboBox jcboURL = new JComboBox(new String[] {"jdbc:mysql://localhost/test", 
			"jdbc:odbc:exampleMDBDataSource",
			"jdbc:oracle:thin:@liang.armstrong.edu:1521:orcl"});
	private JComboBox jcboDriver = new JComboBox(new String[] {"com.mysql.jdbc.Driver",
			"sun.jdbc.odbc.JdbcOdbcDriver",
			"oracle.jdbc.driver.OracleDriver"});
	
	private JButton jbtConnect = new JButton("Connect to DB & get Table");
	private JTextField jtfUserName = new JTextField();
	private JPasswordField jpfPassword = new JPasswordField();
	private JTextField jtfTableName = new JTextField();
	private TableEditor tableEditor1 = new TableEditor();
	private JLabel jlblStatus = new JLabel();

	/** Creates new form TestTableEditor */
	public TestTableEditor()
	{
		JPanel jPane1 = new JPanel();
		jPane1.setLayout(new GridLayout(5, 0));
		jPane1.add(jcboDriver);
		jPane1.add(jcboURL);
		jPane1.add(jtfUserName);
		jPane1.add(jpfPassword);
		jPane1.add(jtfTableName);

		JPanel jPane2 = new JPanel();
		jPane2.setLayout(new GridLayout(5, 0));
		jPane2.add(new JLabel("JDBC Driver"));
		jPane2.add(new JLabel("Database URL"));
		jPane2.add(new JLabel("Username"));
		jPane2.add(new JLabel("Password"));
		jPane2.add(new JLabel("Table Name"));

		JPanel jPane3 = new JPanel();
		jPane3.setLayout(new BorderLayout());
		jPane3.add(jbtConnect, BorderLayout.SOUTH);
		jPane3.add(jPane2, BorderLayout.WEST);
		jPane3.add(jPane1, BorderLayout.CENTER);
		tableEditor1.setPreferredSize(new Dimension(400, 200));

		add(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jPane3, tableEditor1), BorderLayout.CENTER);
		add(jlblStatus, BorderLayout.SOUTH);

		jbtConnect.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				try
				{
					// Connect to the database
					Connection connection = getConnection();
					tableEditor1.setConnectionAndTable(connection, jtfTableName.getText().trim());
				}
				catch(Exception ex)
				{
					jlblStatus.setText(ex.toString());
				}
			}
		});
	}

	/** Connect to a database */
	private Connection getConnection() throws Exception
	{
		// Load the JDBC driver
		System.out.println((String)jcboDriver.getSelectedItem());
		Class.forName(((String)jcboDriver.getSelectedItem()).trim());
		System.out.println("Driver loaded");

		// Establish a connection
		Connection connection = DriverManager.getConnection(((String)jcboURL.getSelectedItem()).trim(), 
				jtfUserName.getText().trim(), new String(jpfPassword.getPassword()));
		jlblStatus.setText("Database connected");

		return connection;
	}

	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		TestTableEditor applet = new TestTableEditor();
		frame.add(applet, BorderLayout.CENTER);

		frame.setTitle("Test Table Editor");
		frame.setSize(1100, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
