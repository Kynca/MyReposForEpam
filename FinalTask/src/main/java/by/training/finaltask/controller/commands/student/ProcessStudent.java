package by.training.finaltask.controller.commands.student;

import by.training.finaltask.bean.Result;
import by.training.finaltask.bean.entities.Role;
import by.training.finaltask.bean.entities.Student;
import by.training.finaltask.bean.entities.User;
import by.training.finaltask.bean.page.Page;
import by.training.finaltask.controller.AdminCommand;
import by.training.finaltask.controller.DeanCommand;
import by.training.finaltask.service.ServiceFactory;
import by.training.finaltask.service.StudentService;
import by.training.finaltask.service.excpetion.ServiceException;
import by.training.finaltask.service.impl.StudentServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

public class ProcessStudent implements AdminCommand, DeanCommand {

    private static final Logger controllerLog = LogManager.getLogger("ControllerLog");

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Result result;
        request.getSession(false).removeAttribute("incorrectData");
        User user = (User) request.getSession().getAttribute("authorizedUser");
        Integer deanId = null;
        try {
            Integer id = Integer.valueOf(request.getParameter("id"));
            Boolean choice = Boolean.valueOf(request.getParameter("action"));
            controllerLog.info("init" + id + " " + choice);
            if (choice) {
                controllerLog.info("delete");
                StudentService studentService = ServiceFactory.getInstance().getStudentService();
                if(user.getRole() == Role.DEAN){
                    deanId = (Integer) request.getSession(false).getAttribute("deanId");
                }
                boolean isDeleted = studentService.deleteStudent(id, deanId);
                controllerLog.info("is deleted? =" + isDeleted);
                if (!isDeleted) {
                    controllerLog.info("fail");
                    request.getSession(false).setAttribute("incorrectData", "incorrectData");
                }

                result = new Result(Page.STUDENT_LIST_HTML, true);
            } else {
                controllerLog.info("edit");
                request.getSession(false).setAttribute("id", id);
                result = new Result(Page.STUDENT_FIND, true);
            }
        } catch (NumberFormatException e) {
            controllerLog.error("incorrect data");
            request.getSession(false).setAttribute("incorrectData", "incorrectData");
            result = new Result(Page.STUDENT_LIST_HTML, true);
        } catch (ServiceException e) {
            controllerLog.error(e + e.getMessage());
            request.getSession(false).setAttribute("error", e.getMessage());
            result = new Result(Page.ERROR, false);
        }
        return result;
    }

    @Override
    public Set<Role> getRoleSet() {
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(Role.DEAN);
        roleSet.add(Role.ADMINISTRATOR);
        return roleSet;
    }
}
