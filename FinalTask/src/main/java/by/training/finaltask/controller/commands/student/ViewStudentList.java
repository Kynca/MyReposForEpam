package by.training.finaltask.controller.commands.student;

import by.training.finaltask.bean.Result;
import by.training.finaltask.bean.entities.Dean;
import by.training.finaltask.bean.entities.Role;
import by.training.finaltask.bean.entities.Student;
import by.training.finaltask.bean.entities.User;
import by.training.finaltask.bean.page.Page;
import by.training.finaltask.controller.AdminCommand;
import by.training.finaltask.controller.DeanCommand;
import by.training.finaltask.service.ServiceFactory;
import by.training.finaltask.service.StudentService;
import by.training.finaltask.service.excpetion.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ViewStudentList implements AdminCommand, DeanCommand {

    private static final Logger controllerLog = LogManager.getLogger("ControllerLog");

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        request.getSession(false).removeAttribute("incorrectData");
        Result result;
        User user = (User) request.getSession(false).getAttribute("authorizedUser");
        controllerLog.debug("user =" + user);
        try {
            StudentService studentService = ServiceFactory.getInstance().getStudentService();
            if (user.getRole() == Role.ADMINISTRATOR) {
                controllerLog.debug("in admin");
                Map<Student, Dean> studentsInfo = studentService.viewStudentsInfo();
                if (studentsInfo != null) {
                    request.setAttribute("studentsInfo", studentsInfo);
                } else {
                    request.setAttribute("failure", true);
                }
                result = new Result(Page.ADMIN_STUDENT_LIST, false);
            }else {
                Integer deanId = (Integer) request.getSession(false).getAttribute("deanId");
                List<Student> studentList = studentService.viewDeanStudents(deanId);
                request.setAttribute("students", studentList);
                result = new Result(Page.DEAN_STUDENT_LIST, false);
                controllerLog.info("students found and set");
            }
        } catch (ServiceException e) {
            controllerLog.error(e.getMessage() + e);
            request.getSession(false).setAttribute("error", e.getMessage());
            result = new Result(Page.ERROR, false);
        }
        return result;
    }

    @Override
    public Set<Role> getRoleSet() {
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(Role.ADMINISTRATOR);
        roleSet.add(Role.DEAN);
        return roleSet;
    }
}
