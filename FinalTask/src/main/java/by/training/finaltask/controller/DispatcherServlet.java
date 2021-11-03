package by.training.finaltask.controller;

import by.training.finaltask.bean.Result;
import by.training.finaltask.bean.page.Page;
import by.training.finaltask.dao.DaoFactory;
import by.training.finaltask.dao.connectionpool.ConnectionPool;
import by.training.finaltask.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet(urlPatterns ="*.html", name = "DispatcherServlet")
public class DispatcherServlet extends HttpServlet {

    private static final Logger debugLog = LogManager.getLogger("DebugLog");

    public void init() {
        try {
            ConnectionPool.getInstance().init("database.properties");
        } catch (Exception e) {
            destroy();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        debugLog.debug("procces request");
        Command command = (Command) request.getAttribute("command");
        Result result;
        try {
            result = command.execute(request, response);
            debugLog.debug(result.isRedirect());
        } catch (ServletException e) {
            //logger
            request.setAttribute("error_msg", e.getMessage());
            result = new Result(Page.ERROR, false);
        }
        debugLog.debug("test" + result.getPage().getPage());
        Page page = result.getPage();
        if (result.isRedirect()) {
            sendRedirect(response, request.getContextPath() + page.getPage());
        } else {
            dispatch(request, response,"/WEB-INF/jsp" + page.getPage());
        }
    }

    private void dispatch(HttpServletRequest request, HttpServletResponse response, String page)
            throws IOException, ServletException {
        debugLog.debug("page to forward " + page);
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(page);
        requestDispatcher.forward(request, response);
    }

    private void sendRedirect(HttpServletResponse response, String path) {
        try {
            debugLog.debug("page to redirect " + path);
            response.sendRedirect(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
