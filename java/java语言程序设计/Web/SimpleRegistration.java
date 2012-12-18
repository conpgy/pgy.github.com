import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class SimpleRegistration extends HttpServlet
{
	// Use a prepared statement to store a student into the database
	private PreparedStatement pstmt;

	/** Initialize variable */	
	public void init() throws ServletException
	{
		initializeJdbc();
	}

	/** Process the HTTP Post request */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// Obtain parameters from the client
		String name = request.getParameter("name");
		String phone = request.getParameter("telephone");
		String email = request.getParameter("email");
		String address = request.getParameter("street");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");

		try
		{
			if(name.length() == 0)
			{
				out.println("name are required");
				return;
			}

			storeStudent(name, phone, email, address, city, state, zip);

			out.println(name + " is now registered in the database");
		}
		catch(Exception ex)
		{
			out.println("Error: " + ex.getMessage());
		}
		finally
		{
			out.close();
		}
	}

	/** Initialize database connection */
	private void initializeJdbc()
	{
		try
		{
			// Declare driver and connection string
			String driver = "com.mysql.jdbc.Driver";
			String connectionString = "jdbc:mysql://localhost/test";

			// Load the driver
			Class.forName(driver);

			// Connect to the sample database
			Connection conn = DriverManager.getConnection(connectionString);

			// Create a Statement
			pstmt = conn.prepareStatement("insert into Address (name, telephone, " +
				"email, street, city, state, zip) values (?, ?, ?, ?, ?, ?, ?)");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	/** Store a student record to the database */
	private void storeStudent(String name, String phone, String email, String address,
		String city, String state, String zip) throws SQLException
	{
		pstmt.setString(1, name);
		pstmt.setString(2, phone);
		pstmt.setString(3, email);
		pstmt.setString(4, address);
		pstmt.setString(5, city);
		pstmt.setString(6, state);
		pstmt.setString(7, zip);
		pstmt.executeUpdate();
	}
}
