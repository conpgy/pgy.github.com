import java.sql.*;
import javax.sql.RowSet;
import com.sun.rowset.*;

public class ScrollUpdateRowSet
{
	public static void main(String[] args) throws SQLException, ClassNotFoundException
	{
		// Load the JDBC driver
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver loaded");

		// Create a row set
		RowSet rowSet = new JdbcRowSetImpl();

		// Set RowSetproperties
		rowSet.setUrl("jdbc:mysql://localhost/test");
		rowSet.setCommand("select * from Sdt");
		rowSet.execute();

		// Iterate through the result and print the student names
		System.out.println("Before update ");
		ResultSetMetaData rsMetaData = rowSet.getMetaData();
		while(rowSet.next())
		{
			for(int i = 1; i <= rsMetaData.getColumnCount(); i++)
				System.out.printf("%-12s\t",rowSet.getObject(i));
			System.out.println();
		}

		rowSet.absolute(2);	// Move cursor to the 2nd row
		rowSet.deleteRow();	// Delete the second row

		rowSet.absolute(5);	// Move cursor to the 5th row
		rowSet.updateString("phone", "912921111");	// updates the column
		rowSet.updateRow();

		rowSet.moveToInsertRow();	// Move cursor to the insert row
		rowSet.updateString("ssn", "111111111");	// Update the ssn
		rowSet.updateString("lastName", "Yao");		// Update the lastName
		rowSet.updateString("firstName", "An");		// Update the firstName
		rowSet.insertRow();	// Insert the row
		rowSet.moveToCurrentRow();	// Move the cursor to the current row

		System.out.println("After update ");
		rowSet.first();
		while(rowSet.next())
		{
			for(int i = 1; i <= rsMetaData.getColumnCount(); i++)
				System.out.printf("%-12s\t",rowSet.getObject(i));
			System.out.println();
		}

		// Close the connection
		rowSet.close();
	}
}
