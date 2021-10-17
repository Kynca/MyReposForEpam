package by.training.finaltask.controller.commands.student;

import by.training.finaltask.bean.Result;
import by.training.finaltask.bean.entities.Dean;
import by.training.finaltask.bean.entities.Student;
import by.training.finaltask.bean.page.Page;
import by.training.finaltask.controller.AdminCommand;
import by.training.finaltask.service.ServiceFactory;
import by.training.finaltask.service.StudentService;
import by.training.finaltask.service.excpetion.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class ViewStudentList implements AdminCommand {

    private static final Logger debugLog = LogManager.getLogger("DebugLog");

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        debugLog.debug("in studentList");
        Result result;
        try {
            StudentService studentService = ServiceFactory.getInstance().getStudentService();
            Map<Student, Dean> studentsInfo = studentService.viewStudentsInfo();
            if(studentsInfo != null) {
                request.setAttribute("studentsInfo", studentsInfo);
            }else{
                request.setAttribute("failure", true);
            }
            result = new Result(Page.STUDENT_LIST, false);
        } catch (ServiceException e) {
            debugLog.debug("catch exception" + e.getMessage() + e);
            result = new Result(Page.ERROR, false);
        }
        return result;
    }
}
