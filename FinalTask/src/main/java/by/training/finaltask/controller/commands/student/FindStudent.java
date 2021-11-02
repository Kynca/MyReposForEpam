package by.training.finaltask.controller.commands.student;

import by.training.finaltask.bean.Result;
import by.training.finaltask.bean.entities.Role;
import by.training.finaltask.bean.entities.Student;
import by.training.finaltask.bean.entities.User;
import by.training.finaltask.bean.page.Page;
import by.training.finaltask.controller.AdminCommand;
import by.training.finaltask.controller.DeanCommand;
import by.training.finaltask.controller.StudentCommand;
import by.training.finaltask.service.ServiceFactory;
import by.training.finaltask.service.StudentService;
import by.training.finaltask.service.excpetion.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

public class FindStudent implements AdminCommand, StudentCommand, DeanCommand {

    private static final Logger controllerLog = LogManager.getLogger("ControllerLog");

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Result result = null;
        Integer id;
        try {
            StudentService studentService = ServiceFactory.getInstance().getStudentService();
            User user = (User) request.getSession(false).getAttribute("authorizedUser");
            controllerLog.info(user);
            switch (user.getRole()) {
                case ADMINISTRATOR:
                    id = (Integer) request.getSession(false).getAttribute("id");
                    result = new Result(Page.STUDENT_EDIT_JSP, false);
                    Student student = studentService.viewInfo(id, true);
                    controllerLog.info("student founded and =" + student);
                    request.setAttribute("student", student);
                    break;
                case STUDENT:
                    result = new Result(Page.VIEW_DEAN_INFO, true);
                    id = user.getId();
                    student = studentService.viewInfo(id, false);
                    controllerLog.info("student founded and = " + student);
                    if (student == null) {
                        if (user.getRole() == Role.STUDENT) {
                            return new Result(Page.LOGIN_FORM, true);
                        }
                    }
                    request.getSession(false).setAttribute("student", student);
                    break;
                case DEAN:
                    result = new Result(Page.STUDENT_EDIT_JSP, false);
                    Integer deanId = (Integer) request.getSession(false).getAttribute("deanId");
                    if (deanId != null) {
                        controllerLog.info("deanId != null");
                        id = (Integer) request.getSession(false).getAttribute("id");
                        student = studentService.viewInfo(id, false);
                        request.setAttribute("student", student);
                    } else {
                        result = new Result(Page.VIEW_DEAN_INFO, true);
                        controllerLog.info("deanId == null");
                        id = user.getId();
                        student = studentService.viewInfo(id, false);
                        controllerLog.info("student founded and = " + student);
                        request.getSession(false).setAttribute("student", student);
                    }
                    break;
                default:
                    return new Result(Page.LOGIN_FORM, true);
            }
        } catch (ServiceException e) {
            controllerLog.error(e + e.getMessage());
            request.getSession(false).setAttribute("error", e.getMessage());
            result = new Result(Page.ERROR, false);
        }catch (IllegalArgumentException e){
            controllerLog.error(e + e.getMessage());
            request.getSession(false).setAttribute("incorrectData", e.getMessage());
        }
        return result;
    }

    @Override
    public Set<Role> getRoleSet() {
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(Role.ADMINISTRATOR);
        roleSet.add(Role.STUDENT);
        roleSet.add(Role.DEAN);
        return roleSet;
    }
}
