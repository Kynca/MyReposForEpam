package by.training.task08xml.controller.web;

import by.training.task08xml.bean.Tariff;
import by.training.task08xml.bean.TariffWithMinutes;
import by.training.task08xml.bean.TariffWithoutMinutes;
import by.training.task08xml.service.ServiceFactory;
import by.training.task08xml.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@WebServlet(urlPatterns = "/webXml")
public class WebController extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        ServiceFactory factory = ServiceFactory.getInstance();
        final String FILE_PATH = "F:\\Tasks\\task08xml\\src\\main\\resources\\data\\";
        String parser = request.getParameter("parserChoice");
        String filename = request.getParameter("fileInput");
        List<Tariff> result = new ArrayList();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
            switch (parser) {
                case "DOM":
                    result = factory.getDomParser().parse(FILE_PATH + filename);
                    break;
                case "SAX":
                    result = factory.getSaxParser().parse(FILE_PATH + filename);
                    break;
                case "STAX":
                    result = factory.getStaxParser().parse(FILE_PATH + filename);
                    break;
            }
        } catch (ServiceException e) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Tariff table</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> Error occurs try again </h1>");
            out.println("<h1>" + FILE_PATH + filename + "</h1>");
            out.println("<h1>" + e.getMessage() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            return;
        }

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Tariff table</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<table width=\"100%\" border = \"1\"  align=\"center\"  >");
        out.println("<tr>");
        out.println("<th> Tariff name </th>");
        out.println("<th> Operator name </th>");
        out.println("<th> payroll name </th>");
        out.println("<th> Appearance date </th>");
        out.println("<th> Free internet </th>");
        out.println("<th> Wifi hosting </th>");
        out.println("<th> Favourite number </th>");
        out.println("<th> Subscription </th>");
        out.println("<th> Accumulation </th>");
        out.println("<th> Free minutes within </th>");
        out.println("<th> Free minutes out </th>");
        out.println("<th> Minute price within </th>");
        out.println("<th> Minute price out </th>");
        out.println("</tr>");
        for (Tariff tariff : result) {
            out.println("<tr>");
            out.println("<td>" + tariff.getName() + "</td>");
            out.println("<td>" + tariff.getOperatorName() + "</td>");
            out.println("<td>" + tariff.getPayroll() + "</td>");
            out.println("<td>" + tariff.getDate() + "</td>");
            out.println("<td>" + tariff.getParameters().getFreeInternet() + "</td>");
            out.println("<td>" + tariff.getParameters().getWifiHosting() + "</td>");
            if(tariff.getParameters().isFavorite()){
                out.println("<td>" + "+" + "</td>");
            }else{
                out.println("<td>" + "-" + "</td>");
            }
            if(tariff.getParameters().getSubscription() == null|| tariff.getParameters().getSubscription().equals("false")){
                out.println("<td>" + "-" + "</td>");
            }else{
                out.println("<td>" + tariff.getParameters().getSubscription() + "</td>");
            }
            if(tariff.getParameters().isAccumulation()){
                out.println("<td>" + "+" + "</td>");
            }else{
                out.println("<td>" + "-" + "</td>");
            }
            if(tariff instanceof TariffWithMinutes){
                out.println("<td>" + ((TariffWithMinutes) tariff).getFreeWithin() + "</td>");
                out.println("<td>" + ((TariffWithMinutes) tariff).getFreeOut() + "</td>");
                out.println("<td>" + "-" + "</td>");
                out.println("<td>" + "-" + "</td>");
            }else if(tariff instanceof TariffWithoutMinutes){
                out.println("<td>" + "-" + "</td>");
                out.println("<td>" + "-" + "</td>");
                out.println("<td>" + ((TariffWithoutMinutes) tariff).getWithinNetwork() + "</td>");
                out.println("<td>" + ((TariffWithoutMinutes) tariff).getOutOfNetwork() + "</td>");
            }
            out.println("</tr>");
        }
        out.println("</table>");
        out.println("</body><br>");
        out.println("<input center type=\"button\" onclick=\"history.back();\" value=\"Back to start page\"/>");
        out.println("</html>");
    }
}
