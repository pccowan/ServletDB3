

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
 *implementation class DBConnection
 */
@WebServlet("/DBcustomers")
public class DBcustomers extends HttpServlet {
	
	static Connection conn;
	static String name,Lname;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBcustomers() {
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
		//out.println("<h1> Hello World </h1>");
		
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
		String custid = request.getParameter("custid");
		ResultSet rs = stmt.executeQuery("select * from Demo_customers where customer_id="+custid);
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\" integrity=\"sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ==\" crossorigin=\"anonymous\">"+"</header>");                   
		
		out.println("<table class=\"table table-hover\"");
		
		while (rs.next()){
			name = rs.getString("cust_first_name");
			Lname= rs.getString("cust_last_name");
			String CID= rs.getString("customer_id");
			String address= rs.getString("cust_street_address1");
			String city= rs.getString("cust_city");
			String state= rs.getString("cust_state");
			String zip= rs.getString("cust_postal_code");
			
			name= "<tr><th><h1>"+"Welcome, "+"</h1>\n"
			+name+" "+Lname+"</th>"+"<br>"+"<td>"+"CustomerID Number: "+
					CID+"</td>"+"<br>"+"<td>"+"Street Address: "+address+"</td>"+"<br>"+
			"<td>"+"City: "+city+"</td>"+"<br>"+"<td>"+"State: "+state+"</td>"+"<br>"+"<td>"+"ZipCode: "+zip+"</td></tr>";
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
