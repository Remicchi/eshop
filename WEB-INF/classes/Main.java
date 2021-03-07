// To save as "ebookshop\WEB-INF\classes\QueryServlet.java".
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/main")   // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class Main extends HttpServlet {

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
      out.println("<head><title>Game Shop</title><link rel='stylesheet' href='styles.css' /></head>");
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
         String sqlStr = "SELECT DISTINCT developer FROM games";



         out.println("<div id = 'mainTitle'> <div id ='titleText'> <h1 class='centerText'>Yet Another Game Shop</h1> </div> </div>");


         ResultSet rset = stmt.executeQuery(sqlStr);  // Send the query to the server






out.println("<div id='mainContainer'>");
		out.println("<form method='post' action='query'>");

      out.println("<div id='searchGrid'>");

		out.println("<div id='developer' class='centerText'><h2>Select developer:</h2></div>");

      out.println("<div id='genre' class='centerText'><h2>Select genre:</h2></div>");



out.println("<div id='developers'>");
         int count = 0;
         while(rset.next()) {
            // Print a paragraph <p>...</p> for each record
            out.println("<br /><input class = 'searchOption' type='checkbox' name='developer' value='" + rset.getString("developer") + "' />" + rset.getString("developer"));
            count++;
         }
   out.println("</div>");


out.println("<input type='submit' value='Search' id ='searchnoi' />");
out.println("</form>");



sqlStr = "SELECT DISTINCT genre FROM games";
rset = stmt.executeQuery(sqlStr);

out.println("<div id='genres'>");
         count = 0;
         while(rset.next()) {
            // Print a paragraph <p>...</p> for each recordgenre
            out.println("<br /><input class = 'searchOption' type='checkbox' name='genre' value='" + rset.getString("genre") + "' />" + rset.getString("genre"));
            count++;
         }
   out.println("</div>");


out.println("</div>");//grid

    


		out.println("</form>");
		








out.println("<h2>Search:</h2>");
      out.println("<form method='post' action='queryText'>");
      out.println("<input type='text' name='text' />");
      out.println("<input type='submit' value='Search' />");

    	out.println("</form>");




      out.println("</div>"); //maincontainer

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