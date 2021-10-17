package by.training.finaltask.controller.commands.student;

import by.training.finaltask.bean.Result;
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

public class EditStudent implements AdminCommand {

    private static final Logger debugLog = LogManager.getLogger("DebugLog");

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Result result;
        try {
            StudentService studentService = ServiceFactory.getInstance().getStudentService();
            String name = request.getParameter("name");
            String lastname = request.getParameter("lastname");
            String patronymic = request.getParameter("patronymic");
            String date = request.getParameter("date");
            String mail = request.getParameter("mail");
            Integer dean_id = Integer.valueOf(request.getParameter("deanId"));
            debugLog.debug("init variables");
            Integer id = Integer.valueOf(request.getParameter("id"));
            debugLog.debug("init id = " + id);
            Student student = new Student(id, name, lastname, patronymic, date, mail, dean_id);

            if (studentService.updateInfo(student)) {
                result = new Result(Page.STUDENT_LIST_HTML, true);
                request.getSession(false).removeAttribute("id");
            } else {
                request.setAttribute("id", id);
                result = new Result(Page.STUDENT_EDIT_JSP, true);
            }
        } catch (ServiceException e) {
            result = new Result(Page.ERROR, true);
        }
        return result;
    }
}
