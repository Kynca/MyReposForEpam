package by.training.finaltask.controller.commands.dean;

import by.training.finaltask.bean.Result;
import by.training.finaltask.bean.entities.Dean;
import by.training.finaltask.bean.entities.Role;
import by.training.finaltask.bean.entities.Student;
import by.training.finaltask.bean.entities.University;
import by.training.finaltask.bean.page.Page;
import by.training.finaltask.controller.DeanCommand;
import by.training.finaltask.controller.StudentCommand;
import by.training.finaltask.service.DeanService;
import by.training.finaltask.service.ServiceFactory;
import by.training.finaltask.service.excpetion.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

public class ViewDeanInfo implements StudentCommand, DeanCommand {

    private static final Logger controllerLog = LogManager.getLogger("ControllerLog");

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        controllerLog.debug("in viewDean");
        Student student = (Student) request.getSession(false).getAttribute("student");
        request.getSession().removeAttribute("student");
        controllerLog.info("get student = " + student);
        Integer id = student.getDeanId();
        request.getSession(false).setAttribute("deanId", id);
        try {
            DeanService deanService = ServiceFactory.getInstance().getDeanService();
            Dean dean = deanService.findDean(id);
            if(dean != null){
                request.setAttribute("dean", dean);
                request.setAttribute("student", student);
                return new Result(Page.PROFILE_JSP, false);
            }
            controllerLog.info("cannot find user dean");
            return new Result(Page.LOGIN_FORM, true);
        } catch (ServiceException e) {
            controllerLog.error(e + e.getMessage());
            request.getSession().setAttribute("error",  e.getMessage());
            return new Result(Page.ERROR, false);
        }
    }

    @Override
    public Set<Role> getRoleSet() {
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(Role.STUDENT);
        roleSet.add(Role.DEAN);
        return roleSet;
    }
}
