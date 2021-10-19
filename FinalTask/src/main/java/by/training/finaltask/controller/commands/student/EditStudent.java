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

    private static final Logger controllerLog = LogManager.getLogger("ControllerLog");

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        request.getSession(false).removeAttribute("incorrectData");
        Result result;
        try {
            StudentService studentService = ServiceFactory.getInstance().getStudentService();
            String name = request.getParameter("name");
            String lastname = request.getParameter("lastname");
            String patronymic = request.getParameter("patronymic");
            String date = request.getParameter("date");
            String mail = request.getParameter("mail");
            Integer dean_id = Integer.valueOf(request.getParameter("deanId"));
            Integer id = Integer.valueOf(request.getParameter("id"));
            Student student = new Student(id, name, lastname, patronymic, date, mail, dean_id);

            if (studentService.updateInfo(student)) {
                controllerLog.info("updated info");
                result = new Result(Page.STUDENT_LIST_HTML, true);
                request.getSession(false).removeAttribute("id");
            } else {
                request.setAttribute("id", id);
                request.getSession(false).setAttribute("incorrectData", "incorrectData");
                result = new Result(Page.STUDENT_FIND, true);
            }
        } catch (ServiceException e) {
            controllerLog.error(e + e.getMessage());
            request.getSession(false).setAttribute("error", e.getMessage());
            result = new Result(Page.ERROR, false);
        }
        return result;
    }
}
