import jakarta.servlet.http.*;  
import jakarta.servlet.*;  
import java.io.*; 
import java.sql.*;  
public class DeleteServlet extends HttpServlet{  
public void doPost(HttpServletRequest req,HttpServletResponse res)  
throws ServletException,IOException  
{  
res.setContentType("text/html");//setting the conte  nt type  
PrintWriter pw=res.getWriter();//get the stream to write the data   

try{ 

    int id = Integer.parseInt(req.getParameter("t1"));
    

Class.forName("com.mysql.jdbc.Driver");
 
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/maharashtra","root","");

PreparedStatement pst=con.prepareStatement("delete from bookshop where id=?");

pst.setInt(1, id);


int i = pst.executeUpdate();


if(i==1){
    pw.println("Record Deleted");
}
else{
    pw.println("Record not Deleted");

}
}catch(Exception e){ pw.print(e);} 
pw.println("</table>");
pw.println("</body></html>");    
pw.close();//closing the stream  
}}  