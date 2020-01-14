package servlets;
import util.FileIO;
import adapter.BuildAuto;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.http.Part;


@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class DriverSixUpload extends HttpServlet {
    BuildAuto build = new BuildAuto();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*response.setStatus(HttpServletResponse.SC_OK);

        response.getWriter().println("GET");
        response.getWriter().flush();
        response.getWriter().close();*/
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        String uploadPath = getServletContext().getRealPath("") + File.separator + "Files";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();

        Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
        String fileName = "";
        if(filePart != null){
            fileName = this.getFileName(filePart);
            InputStream fileContent = filePart.getInputStream();
            FileIO FIO = new FileIO();
            build.addAutoMotive(FIO.UnSerializeStream(fileContent));
        }
        StringBuffer str = new StringBuffer();

        response.setStatus(HttpServletResponse.SC_OK);
        str.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" +" +
                "http://www.w3.org/TR/html4/loose.dtd\">\n" +
                "<html> \n" +
                "<head> \n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; " +
                "charset=ISO-8859-1\"> \n" +
                "<title>upload</title> \n" +
                "</head> \n" +
                "<body> <div align='center'> \n");
        str.append(fileName + " was successfully uploaded <br> <br>");
        str.append("Available Models - <br>");

        build.addNameToUploadedNames(fileName);

        for (int i=0; i<build.getUploadedNames().size();i++){
            str.append(build.getUploadedNames().get(i) + "<br>");
        }

        str.append("<br> \n <button onclick=\"location.href = '/web_war_exploded/';\" value = \"Return\"> Return </button>");

        str.append("</body> \n </html>");

        response.getWriter().println(str);
        response.getWriter().flush();
        response.getWriter().close();
    }


    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename"))
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
        }
        return "";
    }
}

