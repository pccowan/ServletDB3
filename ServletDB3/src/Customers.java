import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 
 */
@WebServlet("/Customers")
public class Customers extends HttpServlet {
	
	static Connection conn;
	static String name,Lname;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Customers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		PrintWriter out = response.getWriter();
		
		response.setContentType("text/html");
		out.println("<html><header>");
		out.println("<h1> Customers Table </h1>");
		
		try {
		String url = "jdbc:oracle:thin:testuserdb/password@localhost";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Properties props = new Properties();
		props.setProperty("user", "testuserdb");
		props.setProperty("password","password");
		
		try{
		conn = DriverManager.getConnection(url,props);	
		} catch(SQLException e) {
			String msg = e.getMessage();
			out.println("<p>"+msg);
		}
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select customer_id,cust_first_name,cust_last_name from Demo_customers");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\" integrity=\"sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ==\" crossorigin=\"anonymous\">"+"</header>");                   
		
		out.println("<table class=\"table table-striped\">");
		
		while (rs.next()){
			
			name = rs.getString("cust_first_name");
			Lname= rs.getString("cust_last_name");
			String custid= rs.getString("customer_id");
			
			
			name= "<tr><td>"+"<a href='DBcustomers?custid=" + custid + "'>" + name + " " + Lname + "</a></td></tr><br>";
			out.println(name);
		}
		
		out.println("</table>");
		
		conn.close();
		}
		catch(Exception e){
			String msg = e.getMessage();
			out.println("<p>"+msg);
		}
		//out.println(name);
		// TODO Auto-generated method stub
	}
			

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
		// TODO Auto-generated method stub
	}

}
