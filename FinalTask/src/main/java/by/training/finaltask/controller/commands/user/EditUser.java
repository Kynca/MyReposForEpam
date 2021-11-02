package by.training.finaltask.controller.commands.user;

import by.training.finaltask.bean.Result;
import by.training.finaltask.bean.entities.Role;
import by.training.finaltask.bean.entities.Student;
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

public class EditUser implements AdminCommand {

    private static final Logger controllerLog = LogManager.getLogger("ControllerLog");

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Result result;
        request.getSession(false).removeAttribute("incorrectData");
        Integer id = Integer.valueOf(request.getParameter("id"));
        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            String login = request.getParameter("login");
            Role role = Role.getByCode(Integer.valueOf(request.getParameter("role")));
            User user = new User(id, login, role);
            if (userService.updateUser(user)) {
                result = new Result(Page.USER_LIST_HTML, true);
            } else {
                request.getSession().setAttribute("id", id);
                request.getSession(false).setAttribute("incorrectData", "incorrectData");
                result = new Result(Page.USER_FIND, true);
            }
        } catch (ServiceException e) {
            controllerLog.error(e + e.getMessage());
            request.getSession(false).setAttribute("error", e.getMessage());
            result = new Result(Page.ERROR, false);
        } catch (IllegalArgumentException e) {
            controllerLog.error(e + e.getMessage());
            request.getSession(false).setAttribute("incorrectData", e.getMessage());
            result = new Result(Page.USER_FIND, true);
        }
        return result;

    }
}
