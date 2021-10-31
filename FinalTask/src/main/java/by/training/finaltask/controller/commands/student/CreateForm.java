package by.training.finaltask.controller.commands.student;

import by.training.finaltask.bean.Result;
import by.training.finaltask.bean.page.Page;
import by.training.finaltask.controller.DeanCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateForm implements DeanCommand {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        return new Result(Page.STUDENT_CREATE, false);
    }
}
