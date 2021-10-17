package by.training.finaltask.controller.commands;

import by.training.finaltask.bean.Result;
import by.training.finaltask.bean.page.Page;
import by.training.finaltask.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Index implements Command {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        return new Result(Page.INDEX, false);
    }
}
