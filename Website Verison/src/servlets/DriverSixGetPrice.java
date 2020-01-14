package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import adapter.BuildAuto;
import java.lang.StringBuffer;

public class DriverSixGetPrice extends HttpServlet {
    private BuildAuto build = new BuildAuto();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String carName = request.getParameter("carName");
        PrintWriter out = response.getWriter();
        StringBuffer str = new StringBuffer();
        str.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" +" +
                "http://www.w3.org/TR/html4/loose.dtd\">\n" +
                "<html> \n" +
                "<head> \n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; " +
                "charset=ISO-8859-1\"> \n" +
                "<title> " + carName + "</title> \n" +
                "<style>\n" +
                "table {\n" +
                "  font-family: arial, sans-serif;\n" +
                "  border-collapse: collapse;\n" +
                "  width: 100%;\n" +
                "}\n" +
                "\n" +
                "td, th {\n" +
                "  border: 1px solid #dddddd;\n" +
                "  text-align: left;\n" +
                "  padding: 8px;\n" +
                "}\n" +
                "\n" +
                "tr:nth-child(even) {\n" +
                "  background-color: #dddddd;\n" +
                "}\n" +
                "</style>" +
                "</head> \n" +
                "<body> <div align='center'> \n" +
                "<table> \n" +
                "<tr> \n" +
                "<th> Option Name </th>\n" +
                "<th> Option choice </th>\n" +
                "<th> Option Value </th>\n" +
                "</tr>\n");
        for (int i = 0; i<build.getAutoMotive(carName).getOptionSetsSize();i++){
           build.getAutoMotive(carName).setOptionChoice(build.getAutoMotive(carName).getOptionSetName(i), request.getParameter("option" + i));
           str.append("<tr> \n" +
                   "<th>" + build.getAutoMotive(carName).getOptionSetName(i) + "</th>\n" +
                   "<th>" + request.getParameter("option" + i) + "</th>\n" +
                   "<th>" + build.getAutoMotive(carName).getOptionValue(i, build.getAutoMotive(carName).findOptionInOptionSet(i, request.getParameter("option" + i))) + "</th>\n" +
                   "</tr>\n"
                );
        }

        str.append("</table> ");
        str.append("Base Price - " + build.getAutoMotive(carName).getBasePrice() + "<br>");
        str.append("Total Price - " + build.getAutoMotive(carName).getTotalPrice());
        str.append("\n </body> \n </html>");
        out.println(str);
    }
}

