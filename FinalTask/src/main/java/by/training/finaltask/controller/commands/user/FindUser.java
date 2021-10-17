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

public class FindUser implements AdminCommand {

    private static final Logger debugLog = LogManager.getLogger("DebugLog");

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Result result;
        debugLog.debug("in find user");
        try {
            Integer id = (Integer) request.getSession(false).getAttribute("id");
            debugLog.debug(id);
            UserService userService = ServiceFactory.getInstance().getUserService();
            User user = userService.findUser(id);
            debugLog.debug("student founded and =" + user);
            request.setAttribute("user", user);
            result = new Result(Page.USER_EDIT_JSP, false);
        } catch (ServiceException e) {
            result = new Result(Page.USER_LIST_HTML, true);
        }
        return result;
    }
}
