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

    private static final Logger debugLog = LogManager.getLogger("DebugLog");

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        debugLog.debug("in view dean");
        Student student = (Student) request.getSession(false).getAttribute("student");
        request.getSession().removeAttribute("student");
        debugLog.debug("get student =" + student);
        Integer id = student.getDeanId();
        try {
            DeanService deanService = ServiceFactory.getInstance().getDeanService();
            Dean dean = deanService.findDean(id);
            if(dean != null){
                request.setAttribute("dean", dean);
                request.setAttribute("student", student);
                return new Result(Page.PROFILE_JSP, false);
            }
            return new Result(Page.LOGIN_FORM, true);
        } catch (ServiceException e) {
            return new Result(Page.ERROR, true);
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
