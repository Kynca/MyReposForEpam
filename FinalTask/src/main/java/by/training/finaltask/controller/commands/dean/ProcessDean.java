package by.training.finaltask.controller.commands.dean;

import by.training.finaltask.bean.Result;
import by.training.finaltask.bean.entities.Dean;
import by.training.finaltask.bean.entities.Role;
import by.training.finaltask.bean.entities.User;
import by.training.finaltask.bean.page.Page;
import by.training.finaltask.controller.AdminCommand;
import by.training.finaltask.service.DeanService;
import by.training.finaltask.service.ServiceFactory;
import by.training.finaltask.service.UserService;
import by.training.finaltask.service.excpetion.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProcessDean implements AdminCommand {
    private static final Logger controllerLog = LogManager.getLogger("ControllerLog");

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Result result;
        request.getSession(false).removeAttribute("incorrectData");
        try {
            Integer id = Integer.valueOf(request.getParameter("id"));
            Boolean choice = Boolean.valueOf(request.getParameter("action"));
            if (choice) {
                controllerLog.info(" choice was delete");
                DeanService deanService = ServiceFactory.getInstance().getDeanService();
                boolean isDeleted = deanService.deleteDean(id);
                controllerLog.info("is deleted? =" + isDeleted);
                if (!isDeleted) {
                    request.getSession(false).setAttribute("incorrectData", "incorrectData");
                }
                result = new Result(Page.DEAN_LIST_HTML, true);
            } else {
                controllerLog.info(" choice was edit student");
                request.getSession(false).setAttribute("id", id);
                result = new Result(Page.DEAN_FIND, true);
            }
        } catch (NumberFormatException e) {
            request.getSession(false).setAttribute("incorrectData", "incorrectData");
            result = new Result(Page.DEAN_LIST_HTML, true);
        } catch (ServiceException e) {
            controllerLog.error(e + e.getMessage());
            request.getSession(false).setAttribute("error", e.getMessage());
            result = new Result(Page.ERROR, false);
        }
        return result;
    }
}
