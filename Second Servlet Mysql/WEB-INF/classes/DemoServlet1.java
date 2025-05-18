import jakarta.servlet.http.*;  
import jakarta.servlet.*;  
import java.io.*; 
import java.sql.*;  

public class DemoServlet1 extends HttpServlet {  
    public void doGet(HttpServletRequest req, HttpServletResponse res)  
    throws ServletException, IOException {  

        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();

        pw.println("<html><head>");
        pw.println("<style>");
        pw.println("body { font-family: Arial, sans-serif; background: linear-gradient(135deg, #74ebd5, #acb6e5); padding: 20px; }");
        pw.println("h1 { text-align: center; color: #333; }");
        pw.println("table { width: 80%; margin: auto; border-collapse: collapse; box-shadow: 0 0 10px rgba(0,0,0,0.1); background-color: #fff; }");
        pw.println("th, td { padding: 12px 15px; text-align: center; border-bottom: 1px solid #ddd; }");
        pw.println("th { background-color: #4CAF50; color: white; }");
        pw.println("tr:hover { background-color: #f1f1f1; }");
        pw.println("</style>");
        pw.println("</head><body>");

        pw.println("<h1>Welcome to Maharashtra eBookShop</h1>");
        pw.println("<table>");
        pw.println("<tr><th>Book ID</th><th>Book Title</th><th>Book Author</th><th>Book Price</th><th>Quantity</th></tr>");

        try { 
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/maharashtra", "root", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from bookshop"); 

            while (rs.next()) {    
                pw.println("<tr><td>" + rs.getObject(1).toString() + "</td><td>" + rs.getString(2) + 
                           "</td><td>" + rs.getString(3) + "</td><td>" + rs.getDouble(4) + 
                           "</td><td>" + rs.getInt(5) + "</td></tr>");
            }

            con.close();
        } catch (Exception e) {
            pw.println("<tr><td colspan='5' style='color:red;'>Error: " + e.getMessage() + "</td></tr>");
        } 

        pw.println("</table>");
        pw.println("</body></html>");    
        pw.close();
    }  
}  
