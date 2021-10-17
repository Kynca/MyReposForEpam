package by.training.finaltask.controller.commands.dean;

import by.training.finaltask.bean.Result;
import by.training.finaltask.bean.entities.University;
import by.training.finaltask.bean.page.Page;
import by.training.finaltask.controller.AdminCommand;
import by.training.finaltask.service.DeanService;
import by.training.finaltask.service.ServiceFactory;
import by.training.finaltask.service.excpetion.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FindUniversities implements AdminCommand {

    private static final Logger debugLog = LogManager.getLogger("DebugLog");

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        debugLog.debug("in findUni");
        Result result;
        try {
            DeanService deanService = ServiceFactory.getInstance().getDeanService();
            List<University> universities = deanService.findUniversities();
            debugLog.debug("find universities");
            request.setAttribute("universities", universities);
            debugLog.debug("seted attribute" + request.getAttribute("universities"));
            result = new Result(Page.DEAN_CREATE_JSP, false);
        } catch (ServiceException e) {
            debugLog.debug("catch exception");
            result = new Result(Page.ERROR, false);
        }
        return result;
    }
}
