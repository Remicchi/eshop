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
      out.println("<head><title>Search</title><link rel='stylesheet' href='styles.css' /></head>");
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
String[] genres = request.getParameterValues("genre");
String[] title = request.getParameterValues("title");

out.println("<div id = 'mainTitle'> <div id ='titleText'> <h1 class='centerText'>Yet Another Game Shop</h1> </div> </div>");


out.println("<div id='mainContainer'>");

if (developers != null || genres != null) {

         String sqlStr = "SELECT * FROM games WHERE ";
         if (developers != null) {
            sqlStr += "developer IN (";
           for (int i = 0; i < developers.length; ++i) {
              if (i < developers.length - 1) {
                 sqlStr += "'" + developers[i] + "', ";  // need a commas
              } else {
                 sqlStr += "'" + developers[i] + "'";    // no commas
              }
           }
           sqlStr += ")";
         }
         if (genres != null) {
          if (developers != null) {
            sqlStr += " AND ";
          }
          sqlStr += " genre in (";
           for (int i = 0; i < genres.length; ++i) {
              if (i < genres.length - 1) {
                 sqlStr += "'" + genres[i] + "', ";  // need a commas
              } else {
                 sqlStr += "'" + genres[i] + "'";    // no commas
              }
           }
           sqlStr+= ") ";
         }
         sqlStr+= " AND qty > 0 ORDER BY title ASC";





         out.println("<h3>Query:</h3>");
         out.println("<div id= 'sqlstatement'> <p>" + sqlStr + "</p> </div>"); // Echo for debugging
         ResultSet rset = stmt.executeQuery(sqlStr);  // Send the query to the server

         // Step 4: Process the query result set
         int count = 0;

         out.println("<form method='post' action='eshoporder'>");
         
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
         
         out.println("<th>Title</th>");
         out.println("<th>Developer</th>");
         out.println("<th>Genre</th>");
         out.println("<th>Price</th>");
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
                + rset.getString("title")
                + "</td>" 
                + "<td>"
                + rset.getString("developer")
                + "</td>"
                + "<td>"
                + rset.getString("genre")
                + "</td>"
                + "<td>"
                + " $" + rset.getString("price") + "</p>"
                + "</td>"
                + "</tr>"
                );
         }
         
        //out.println("</tbody>");
        out.println("</table>");

       out.println("<input type='hidden' name='username' value = '" + request.getParameter("username") + "' />");



         sqlStr = "select * from customers where username = '" + request.getParameter("username") + "'";
         rset = stmt.executeQuery(sqlStr);
          while(rset.next()) {
                      // Print a paragraph <p>...</p> for each record
                      out.println("<br />Name: <span class='details'>" + rset.getString("name") + "</span>" );
                      out.println("<br />Email: <span class='details'>" + rset.getString("email") + "</span>" );
                      out.println("<br />Address: <span class='details'>" + rset.getString("address") + "</span>" );
                      count++;
                   }




     out.println("<br />");
       out.println("<br />");
       out.println("<p><input type='submit' value='ORDER' />");
        out.println("</form>");
   out.println("<div id='loggedin'>Logged in as " +  request.getParameter("username") + "</div>");
      } else {

        out.println("<h2>No search options selected.</h2>");
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