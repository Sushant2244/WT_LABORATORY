import jakarta.servlet.http.*;  
import jakarta.servlet.*;  
import java.io.*; 
import java.sql.*;  
public class DemoServlet extends HttpServlet{  
public void doPost(HttpServletRequest req,HttpServletResponse res)  
throws ServletException,IOException  
{  
res.setContentType("text/html");//setting the conte  nt type  
PrintWriter pw=res.getWriter();//get the stream to write the data   

try{ 

    int id = Integer.parseInt(req.getParameter("t1"));
    String name = req.getParameter("t2");
    String author = req.getParameter("t3");
    double price = Double.parseDouble(req.getParameter("t4"));
    int quantity = Integer.parseInt(req.getParameter("t5"));
    

Class.forName("com.mysql.jdbc.Driver");
 
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/maharashtra","root","");

PreparedStatement pst=con.prepareStatement("insert into bookshop values(?,?,?,?,?)");

pst.setInt(1, id);
pst.setString(2, name);
pst.setString(3, author);
pst.setDouble(4, price);
pst.setInt(5, quantity);

int i = pst.executeUpdate();


if(i==1){
    pw.println("Record inserted");
}
else{
    pw.println("Record not inserted");

}
}catch(Exception e){ pw.print(e);} 
pw.println("</table>");
pw.println("</body></html>");    
pw.close();//closing the stream  
}}  