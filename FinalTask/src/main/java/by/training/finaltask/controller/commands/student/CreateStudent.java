package by.training.finaltask.controller.commands.student;

import by.training.finaltask.bean.Result;
import by.training.finaltask.bean.entities.Role;
import by.training.finaltask.bean.entities.Student;
import by.training.finaltask.bean.entities.User;
import by.training.finaltask.bean.page.Page;
import by.training.finaltask.controller.DeanCommand;
import by.training.finaltask.service.ServiceFactory;
import by.training.finaltask.service.StudentService;
import by.training.finaltask.service.excpetion.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateStudent implements DeanCommand {

    private static final Logger controllerLog = LogManager.getLogger("ControllerLog");

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        controllerLog.info("creating student");
        request.getSession(false).removeAttribute("incorrectData");
        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        String patronymic = request.getParameter("patronymic");
        String date = request.getParameter("date");
        String mail = request.getParameter("mail");
        Integer deanId = (Integer) request.getSession(false).getAttribute("deanId");
        String login = request.getParameter("login");

        try {
            controllerLog.info("in try catch");
            StudentService studentService = ServiceFactory.getInstance().getStudentService();
            Student student = new Student(name, lastname, patronymic, date, mail, deanId);
            User user = new User();
            user.setRole(Role.STUDENT);
            user.setLogin(login);
            controllerLog.info("init user and stud");
            if (studentService.createStudent(student, user)) {
                return new Result(Page.STUDENT_LIST_HTML, true);
            } else {
                request.getSession(false).setAttribute("incorrectData", "incorrectData");
                return new Result(Page.STUDENT_CREATE_FORM, true);
            }
        } catch (ServiceException e) {
            controllerLog.error(e + e.getMessage());
            request.getSession(false).setAttribute("error", e.getMessage());
            return new Result(Page.ERROR, false);
        } catch (IllegalArgumentException e){
            controllerLog.error(e + e.getMessage());
            request.getSession(false).setAttribute("incorrectData", e.getMessage());
            return new Result(Page.STUDENT_CREATE_FORM, true);
        }
    }
}
