package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import adapter.BuildAuto;
import java.lang.StringBuffer;


public class DriverSixSelect extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BuildAuto build = new BuildAuto();
        String carName;

        carName = request.getParameter("carName");

        float price = build.getAutoMotive(carName).getBasePrice();

        PrintWriter out = response.getWriter();


        StringBuffer str = new StringBuffer();
        str.append( "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" +" +
                "http://www.w3.org/TR/html4/loose.dtd\">\n" +
                "<html> \n" +
                "<head> \n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; " +
                "charset=ISO-8859-1\"> \n" +
                "<title> " + carName + "</title> \n" +
                "</head> \n" +
                "<body> <div align='center'> \n" +
                "<form action=\"getPrice\" method=\"get\" enctype=\"multipart/form-data\" id=\"getPrice\" name=\"getPrice\">");

        str.append("Select all Option Sets <br> <br>");

        for (int i=0; i<build.getAutoMotive(carName).getOptionSetsSize();i++){
            str.append("Select a " + build.getAutoMotive(carName).getOptionSetName(i) + "<br>");
            for (int k=0; k<build.getAutoMotive(carName).getOptionSetSize(i); k++){
                str.append("<input type=\"radio\" name=\"option" + i + "\" value=\"" + build.getAutoMotive(carName).getOptionName(i,k) + "\"> " +  build.getAutoMotive(carName).getOptionName(i,k) + "<br>");
            }
        }
        str.append("<input type=\"hidden\" id=\"carName\" name=\"carName\" value=\"" + carName + "\">\n");
        str.append("<input type=\"submit\" />\n" + "</form>\n" +
                "</body> \n" + "</html>");

        out.println(str);
        build.getAutoMotive(carName).printAll();
    }
}

