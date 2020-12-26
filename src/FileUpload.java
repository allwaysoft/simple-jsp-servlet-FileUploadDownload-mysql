
 
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
 
@WebServlet("/FileUpload")
@MultipartConfig
public class FileUpload extends HttpServlet {
 
    /**
     *
     */
    private static final long serialVersionUID = 1L;
 
    protected void doPost(HttpServletRequest request,  HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
 
        final Part filePart = request.getPart("file");
        String id = request.getParameter("id");
 
        InputStream FileBytes = null;
        final PrintWriter writer = response.getWriter();
 
        try {
 
          //if (!filePart.getContentType().equals("image/jpeg"))
          //  {
          //             writer.println("<br/> Invalid File");
          //             return;
          //  }
 
          // else if (filePart.getSize()>1048576 ) { //2mb
          //     {
          //    writer.println("<br/> File size too big");
          //    return;
          //     }
          // }
 
        	String filename = filePart.getSubmittedFileName();
        	FileBytes = filePart.getInputStream();  // to get the body of the request as binary data
 
            final byte[] bytes = new byte[FileBytes.available()];
             FileBytes.read(bytes);  //Storing the binary data in bytes array.
 
            Connection  con=null;
             Statement stmt=null;
 
               try {
                     Class.forName("com.mysql.cj.jdbc.Driver");
                     con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC","root","root");
                  } catch (Exception e) {
                        System.out.println(e);
                        System.exit(0);
                              }
                int success=0;
                PreparedStatement pstmt = con.prepareStatement("INSERT INTO upload VALUES(?,?,?)");
                pstmt.setString(1, id);
                pstmt.setBytes(2,bytes);    //Storing binary data in blob field.
                pstmt.setString(3,filename);    //Storing binary data in blob field.
                success = pstmt.executeUpdate();
                if(success>=1)  System.out.println("Data Stored");
                 con.close(); 
 
                 writer.println("<br/> Book Successfully Stored");
 
        } catch (FileNotFoundException fnf) {
            writer.println("You  did not specify a file to upload");
            writer.println("<br/> ERROR: " + fnf.getMessage());
 
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
 
            if (FileBytes != null) {
                FileBytes.close();
            }
            if (writer != null) {
                writer.close();
            }
        }
 
    }
 
}