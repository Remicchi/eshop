// To save as "ebookshop\WEB-INF\classes\QueryServlet.java".
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/query")   // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class QueryServlet extends HttpServlet {

   // The doGet() runs once per HTTP GET request to this servlet.
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
      // Set the MIME type for the response message
      response.setContentType("text/html");
      // Get a output writer to write the response message into the network socket
      PrintWriter out = response.getWriter();
      // Print an HTML page as the output of the query
      out.println("<html>");
      out.println("<head><title>Query Response</title><link rel='stylesheet' href='styles.css' /></head>");
      out.println("<body>");

      try (
         // Step 1: Allocate a database 'Connection' object
         Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/eshop?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
               "myuser", "xxxx");   // For MySQL
               // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"

         // Step 2: Allocate a 'Statement' object in the Connection
         Statement stmt = conn.createStatement();
      ) {
         // Step 3: Execute a SQL SELECT query
        

         String[] developers = request.getParameterValues("developer");  // Returns an array of Strings
         String sqlStr = "SELECT * FROM games WHERE developer IN (";
         for (int i = 0; i < developers.length; ++i) {
            if (i < developers.length - 1) {
               sqlStr += "'" + developers[i] + "', ";  // need a commas
            } else {
               sqlStr += "'" + developers[i] + "'";    // no commas
            }
         }
         sqlStr += ") AND qty > 0 ORDER BY developer ASC, title ASC";




         out.println("<h3>Thank you for your query.</h3>");
         out.println("<p>Your SQL statement is: " + sqlStr + "</p>"); // Echo for debugging
         ResultSet rset = stmt.executeQuery(sqlStr);  // Send the query to the server

         // Step 4: Process the query result set
         int count = 0;

         out.println("<form method='get' action='eshoporder'>");
         
         // For each row in ResultSet, print one checkbox inside the <form>
        out.println("");
      out.println("<style>");
      out.println("table {");
      out.println("  font-family: arial, sans-serif;");
      out.println("  border-collapse: collapse;");
      out.println("  width: 100%;");
      out.println("}");

      out.println("td, th {");
      out.println("  border: 1px solid #dddddd;");
      out.println("  text-align: left;");
      out.println("  padding: 8px;");
      out.println("}");

      out.println("tr:nth-child(even) {");
      out.println("  background-color: #dddddd;");
      out.println("}");
      out.println("</style>");
      out.println("");

         out.println("<table>");
         //out.println("<tbody>");
         out.println("<tr>");
         out.println("<th></th>");
         
         out.println("<th>developer</th>");
         out.println("<th>TITLE</th>");
         out.println("<th>PRICE</th>");
         out.println("</tr>");
         while(rset.next()) {
            out.println("<tr>"
               + "<td>"
               + "<p><input type='checkbox' name='id' value="
               //+ "</td>"
                //+ "<td>"
                + "'" + rset.getString("id") + "' /></td>"
                //+ "" 
                + "<td>"
                + rset.getString("developer")
                + "</td>" 
                + "<td>"
                + rset.getString("title")
                + "</td>"
                + "<td>"
                + " $" + rset.getString("price") + "</p>"
                + "</td>"
                + "</tr>"
                );
         }
         
        //out.println("</tbody>");
        out.println("</table>");

        out.println("<p>Enter your Name: <input type='text' name='cust_name' /></p>");
        out.println("<p>Enter your Email: <input type='text' name='cust_email' /></p>");
        out.println("<p>Enter your Phone Number: <input type='text' id='cust_phone' /></p>");
       
       out.println("<p><input type='submit' value='ORDER' />");
        out.println("</form>");



      } catch(Exception ex) {
         out.println("<p>Error: " + ex.getMessage() + "</p>");
         out.println("<p>Check Tomcat console for details.</p>");
         ex.printStackTrace();
      }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)
 
      out.println("</body></html>");
      out.close();
   }

   public void doPost (HttpServletRequest request, HttpServletResponse response)
                   throws ServletException, IOException {
   doGet(request, response);  // Re-direct POST request to doGet()
   }
}