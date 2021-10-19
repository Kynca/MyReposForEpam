package by.training.finaltask.controller.commands.user;

import by.training.finaltask.bean.Result;
import by.training.finaltask.bean.entities.Role;
import by.training.finaltask.bean.entities.User;
import by.training.finaltask.bean.page.Page;
import by.training.finaltask.controller.AdminCommand;
import by.training.finaltask.service.ServiceFactory;
import by.training.finaltask.service.StudentService;
import by.training.finaltask.service.UserService;
import by.training.finaltask.service.excpetion.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProcessUser implements AdminCommand {

    private static final Logger controllerLog = LogManager.getLogger("ControllerLog");

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Result result;
        request.getSession(false).removeAttribute("incorrectData");
        try {
            Integer id = Integer.valueOf(request.getParameter("id"));
            Boolean choice = Boolean.valueOf(request.getParameter("action"));
            controllerLog.info("init" + id + " " + choice);
            if (choice) {
                controllerLog.info("delete");
                UserService userService = ServiceFactory.getInstance().getUserService();
                boolean isDeleted = userService.deleteUser(id);
                controllerLog.info("is deleted? =" + isDeleted);
                if (!isDeleted) {
                    controllerLog.debug("fail");
                    request.getSession(false).setAttribute("incorrectData", "incorrectData");
                }
                result = new Result(Page.USER_LIST_HTML, true);
            } else {
                controllerLog.info("edit");
                request.getSession(false).setAttribute("id", id);
                result = new Result(Page.USER_FIND, true);
            }
        } catch (NumberFormatException e) {
            request.getSession(false).setAttribute("incorrectData", "incorrectData");
            result = new Result(Page.USER_LIST_HTML, true);
        } catch (ServiceException e) {
            controllerLog.error("get exception" + e + e.getMessage());
            request.getSession(false).setAttribute("error", e.getMessage());
            result = new Result(Page.ERROR, false);
        }
        return result;
    }
}
