package by.training.finaltask.controller.commands;

import by.training.finaltask.bean.Result;
import by.training.finaltask.bean.page.Page;
import by.training.finaltask.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class LogOut implements Command {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Locale locale = (Locale) request.getSession(false).getAttribute("locale");
        request.getSession().invalidate();
        request.getSession().setAttribute("locale", locale);
        return new Result(Page.INDEX_HTML, true);
    }
}
