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

    private static final Logger debugLog = LogManager.getLogger("DebugLog");

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        debugLog.debug("in studentList");
        Result result;
        User user = (User) request.getSession(false).getAttribute("authorizedUser");
        debugLog.debug("user =" + user);
        try {
            StudentService studentService = ServiceFactory.getInstance().getStudentService();
            debugLog.debug("get stud service");
            if (user.getRole() == Role.ADMINISTRATOR) {
                debugLog.debug("in admin");
                Map<Student, Dean> studentsInfo = studentService.viewStudentsInfo();
                if (studentsInfo != null) {
                    request.setAttribute("studentsInfo", studentsInfo);
                } else {
                    request.setAttribute("failure", true);
                }
                result = new Result(Page.ADMIN_STUDENT_LIST, false);
            }else {
                Integer deanId = (Integer) request.getSession(false).getAttribute("deanId");
                studentService.viewDeanStudents(deanId);
                List<Student> studentList = studentService.viewDeanStudents(deanId);
                request.setAttribute("students", studentList);
//                result = new Result(Page.DEAN_STUDENT_LIST_HTML); TODO separated dean and admin edition
                return new Result(Page.ERROR, true);
            }
        } catch (ServiceException e) {
            debugLog.debug("catch exception" + e.getMessage() + e);
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
