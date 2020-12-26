package com.anirudha;
 
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
/**
 * Servlet implementation class GetDetails
 */
@WebServlet("/FileReadPdf")
public class DBFileDownloadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
        
      protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         
        String id = request.getParameter("id")!=null?request.getParameter("id"):"NA";
         
        ServletOutputStream sos;
        Connection  con=null;
        PreparedStatement pstmt=null;
         
        response.setContentType("image/jpeg");
 
        response.setHeader("Content-disposition","inline; filename="+id+".jpg" );
 
 
         sos = response.getOutputStream();
         
 
           try {
               Class.forName("com.mysql.cj.jdbc.Driver");
               con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC","root","root");
          } catch (Exception e) {
                     System.out.println(e);
                     System.exit(0); 
                          }
            
          ResultSet rset=null;
            try {
                pstmt = con.prepareStatement("Select pdf from upload where id=?");
                System.out.println("Select pdf from upload where id="+id.trim());
                pstmt.setString(1, id.trim());
                rset = pstmt.executeQuery();
                if (rset.next()) {
                    sos.write(rset.getBytes("pdf"));
                	System.out.println(rset.getBytes("pdf"));
                }
                else
                    return;
                 
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
     
        sos.flush();
        sos.close();
         
    }
 
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }
 
}