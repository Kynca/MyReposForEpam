package by.training.finaltask.controller.commands.student;

import by.training.finaltask.bean.Result;
import by.training.finaltask.bean.entities.Student;
import by.training.finaltask.bean.page.Page;
import by.training.finaltask.controller.DeanCommand;
import by.training.finaltask.service.ServiceFactory;
import by.training.finaltask.service.StudentService;
import by.training.finaltask.service.excpetion.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateStudent implements DeanCommand {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        String patronymic = request.getParameter("patronymic");
        String date = request.getParameter("date");
        String mail = request.getParameter("mail");
        Integer deanId = (Integer) request.getSession(false).getAttribute("deanId");

        try {
            StudentService studentService = ServiceFactory.getInstance().getStudentService();
            Student student = new Student(name, lastname, patronymic, date, mail, deanId);
            if (studentService.createStudent(student)) {
                return new Result(Page.STUDENT_LIST_HTML, true);
                //TODO notification
            } else {
                return new Result(Page.STUDENT_CREATE, true);
            }
        } catch (ServiceException e) {
            return new Result(Page.ERROR, true);
        }
    }
}
