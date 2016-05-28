import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DemoServlet")
public class DemoServlet extends HttpServlet {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    resp.setContentType("text/plain");
    resp.getWriter().println("{ \"name\": \"World\" }");
  }
  
  public String getConn() {
		Connection con;
		Statement stmt; 
		String url = "jdbc:google:mysql://eternal-empire-131718:myinstance/guestbook?user=root&amp;password=jonsnow";
		
		String tableCreate = "CREATE TABLE WATCHERS " +
			    "(NAME VARCHAR(32), ID INTEGER, AGE INTEGER, " +
			    "SALARY INTEGER)";
		
		try {
	        // Load the class that provides the new "jdbc:google:mysql://" prefix.
			Class.forName("com.mysql.jdbc.GoogleDriver");
	      } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	      }
	
		try {
			con = DriverManager.getConnection(url);
			stmt = con.createStatement();
			stmt.executeUpdate("DROP TABLE WATCHERS");
			stmt.executeUpdate(tableCreate);
			System.out.println("Created");
			stmt.executeUpdate( "INSERT INTO WATCHERS " + "VALUES ('Jon', 101, 24, 100)");
			System.out.println("Inserted");
			String query = "SELECT NAME, ID FROM WATCHERS";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
			    String s = rs.getString("NAME");
			    int n = rs.getInt("ID");
			    return (s + "   " + n);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "Nothing";
	}
}
