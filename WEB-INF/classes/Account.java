// To save as "<TOMCAT_HOME>\webapps\hello\WEB-INF\classes\QueryServlet.java".
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/account")   // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class Account extends HttpServlet {

   // The doGet() runs once per HTTP GET request to this servlet.
   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
      // Set the MIME type for the response message
      response.setContentType("text/html");
      // Get a output writer to write the response message into the network socket
      PrintWriter out = response.getWriter();

      // Print an HTML page as the output of the query
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head><title>Create Account</title><link rel='stylesheet' href='styles.css' /></head>");
      out.println("<body onload='document.main.submit()' >");

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
         //String sqlStr = "select password from customers where username = "
         //      + "'" + request.getParameter("username") + "'";   // Single-quote SQL string

     //    ResultSet rset = stmt.executeQuery(sqlStr);  // Send the query to the server
         
    //     if(rset.next()) {
    //       if (rset.getString("password").equals(request.getParameter("password"))){
    //           out.println("<form method='post' action='main' name='main'>" );
     //           out.println("<input type='hidden' name='username' value = '" + request.getParameter("username") + "' />");
     ///            out.println("</form>");
     //    }} else {

out.println("<div id = 'mainTitle'> <div id ='titleText'> <h1 class='centerText'>Yet Another Game Shop</h1> </div> </div> <div id='mainContainer'> ");
out.println("<div><h2 class='centerText'>Create Account</h2></div>");
//out.println("<div id='error'><h2 class='centerText'>Wrong username or password!</h2></div>");

  out.println("<div id='loginForm'><form method='post' action='account'> <span>Enter Username:</span> <input type='text' name='username' value = '");
   out.println(request.getParameter("username"));
   out.println("' /> <br /><br /> <span>Enter Password:<span> <input type='password' name='password' /> ");
   out.println("<br /><br /> <span>Enter Name:<span> <input type='text' name='name' /> ");
   out.println("<br /><br /> <span>Enter Name:<span> <input type='text' name='email' /> ");
      out.println("<br /><br /> <span>Enter Name:<span> <input type='text' name='address' /> ");
     out.println(" <br /><br /> <input type='submit' value='Create Account'> </form> </div>");

//}

      } catch(Exception ex) {
         out.println("<p>Error: " + ex.getMessage() + "</p>");
         out.println("<p>Check Tomcat console for details.</p>");
         ex.printStackTrace();
      }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)
 
      out.println("</body></html>");
      out.close();
   }
}