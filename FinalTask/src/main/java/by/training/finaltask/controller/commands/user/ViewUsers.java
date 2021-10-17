package by.training.finaltask.controller.commands.user;

import by.training.finaltask.bean.Result;
import by.training.finaltask.bean.entities.User;
import by.training.finaltask.bean.page.Page;
import by.training.finaltask.controller.AdminCommand;
import by.training.finaltask.service.ServiceFactory;
import by.training.finaltask.service.UserService;
import by.training.finaltask.service.excpetion.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ViewUsers implements AdminCommand {

    private static final Logger debugLog = LogManager.getLogger("DebugLog");

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        debugLog.debug("in viewUsers");
        Result result;
        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            List<User> users = userService.viewUsers();
            request.setAttribute("users", users);
            result = new Result(Page.USER_LIST_JSP,false);
        } catch (ServiceException e) {
            debugLog.debug(e + e.getMessage());
            result = new Result(Page.ERROR, true);
        }
        return result;
    }
}
