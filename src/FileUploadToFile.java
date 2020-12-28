//中文
 
import java.io.FileNotFoundException;
import java.io.IOException;

 
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
 
@WebServlet("/FileUploadToFile")
@MultipartConfig
public class FileUploadToFile extends HttpServlet {
 
    /**
     *
     */
    private static final long serialVersionUID = 1L;
 
    protected void doPost(HttpServletRequest request,  HttpServletResponse response)throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        final Part filePart = request.getPart("file");

 
        try {
 
 
        	String filename = filePart.getSubmittedFileName();
        	filePart.write("F:\\uploadtest\\" + filename );  
        }catch (FileNotFoundException fne) {
            request.setAttribute("message", "There was an error: " + fne.getMessage());
        }
        getServletContext().getRequestDispatcher("/result.jsp").forward(request, response);
               
 
    }
 
}