import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/eshoporder")   // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class EshopOrderServlet extends HttpServlet {

   // The doGet() runs once per HTTP GET request to this servlet.
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
      // Set the MIME type for the response message
      response.setContentType("text/html");
      // Get a output writer to write the response message into the network socket
      PrintWriter out = response.getWriter();

      // Print an HTML page as the output of the query
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head><title>Order Confirmation</title><link rel='stylesheet' href='styles.css' /></head>");
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



         out.println("<div id = 'mainTitle'> <div id ='titleText'> <h1 class='centerText'>Yet Another Game Shop</h1> </div> </div>");
         out.println("<div id='mainContainer'>");
          // Step 3 & 4: Execute a SQL SELECT query and Process the query result
         // Retrieve the books' id. Can order more than one books.
         String[] ids = request.getParameterValues("id");
         if (ids != null) {
            String sqlStr;
            int count;
 
            // Process each of the books
            for (int i = 0; i < ids.length; ++i) {
               // Update the qty of the table books
               sqlStr = "UPDATE games SET qty = qty - 1 WHERE id = " + ids[i];
               //out.println("<p>" + sqlStr + "</p>");  // for debugging
               count = stmt.executeUpdate(sqlStr);
               //out.println("<p>" + count + " record updated.</p>");
 
               // Create a transaction record
               sqlStr = "INSERT INTO orders (gameid, qtyordered) VALUES ("
                     + ids[i] + ", 1)";
               //out.println("<p>" + sqlStr + "</p>");  // for debugging
               count = stmt.executeUpdate(sqlStr);
               //out.println("<p>" + count + " record inserted.</p>");
               sqlStr = "SELECT title FROM games WHERE id = " + ids[i];
               ResultSet rset = stmt.executeQuery(sqlStr);
               rset.next();
               out.println("<h3>Your order for " + rset.getString("title")
                     + " has been confirmed.</h3>");
            }
      sqlStr = "select * from customers where username = '" + request.getParameter("username") + "'";
        ResultSet rset = stmt.executeQuery(sqlStr);
          while(rset.next()) {
                      // Print a paragraph <p>...</p> for each record
                      out.println("<br />Name: <span class='details'>" + rset.getString("name") + "</span>" );
                      out.println("<br />Email: <span class='details'>" + rset.getString("email") + "</span>" );
                      out.println("<br />Address: <span class='details'>" + rset.getString("address") + "</span>" );
                   }

            out.println("<br /><br /><h3>Thank you.<h3>");
         } else { // No book selected
            out.println("<h3>No game selected. >:(</h3>");
         }



         out.println("</div>");

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