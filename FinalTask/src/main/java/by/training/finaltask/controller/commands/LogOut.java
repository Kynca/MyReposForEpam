package by.training.finaltask.controller.commands;

import by.training.finaltask.bean.Result;
import by.training.finaltask.bean.page.Page;
import by.training.finaltask.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogOut implements Command {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        request.getSession().invalidate();
        return new Result(Page.LOGIN_FORM, true);
    }
}
