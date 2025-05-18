import jakarta.servlet.http.*;  
import jakarta.servlet.*;  
import java.io.*; 
import java.sql.*;  
public class UpdateServlet extends HttpServlet{  
public void doPost(HttpServletRequest req,HttpServletResponse res)  
throws ServletException,IOException  
{  
res.setContentType("text/html");//setting the conte  nt type  
PrintWriter pw=res.getWriter();//get the stream to write the data   

try{ 

    int id = Integer.parseInt(req.getParameter("t1"));
    double price = Double.parseDouble(req.getParameter("t2"));
    int quantity = Integer.parseInt(req.getParameter("t3"));

Class.forName("com.mysql.jdbc.Driver");
 
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/maharashtra","root","");

PreparedStatement pst = con.prepareStatement("UPDATE bookshop SET price = ?, quantity = ? WHERE id = ?");

pst.setDouble(1, price);
pst.setInt(2, quantity);
pst.setInt(3, id);
int i = pst.executeUpdate();


if(i==1){
    pw.println("Record Updated");
}
else{
    pw.println("Record not Updated");

}
}catch(Exception e){ pw.print(e);} 
pw.println("</table>");
pw.println("</body></html>");    
pw.close();//closing the stream  
}}  