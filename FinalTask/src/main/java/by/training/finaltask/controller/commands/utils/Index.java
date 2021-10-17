package by.training.finaltask.controller.commands.utils;

import by.training.finaltask.bean.Result;
import by.training.finaltask.bean.page.Page;
import by.training.finaltask.controller.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class Index implements Command {
    private static final Logger debugLog = LogManager.getLogger("DebugLog");
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String language = request.getParameter("language");
        debugLog.debug("in index command" + language);
        if(language != null){
            String[] params = language.split("_");
            Locale locale = new Locale(params[0],params[1]);
            request.getSession(false).setAttribute("locale", locale);
            return new Result(Page.LOGIN_FORM, true);
        }
        return new Result(Page.INDEX, false);
    }
}
