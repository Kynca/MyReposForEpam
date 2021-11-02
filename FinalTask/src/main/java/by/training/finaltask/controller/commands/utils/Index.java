package by.training.finaltask.controller.commands.utils;

import by.training.finaltask.bean.Result;
import by.training.finaltask.bean.page.Page;
import by.training.finaltask.controller.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class Index implements Command {

    private static final Logger controllerLog = LogManager.getLogger("ControllerLog");

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        controllerLog.info("in index page");
        Cookie[] cookies = request.getCookies();

        String language = request.getParameter("language");
        controllerLog.info("dont have locale in cookie = " + language);

        if (language != null) {
            controllerLog.info("setting language");
            Cookie cookie = new Cookie("locale", language);
            response.addCookie(cookie);
            setLocale(request, language);
            return new Result(Page.INDEX_HTML, true);
        }

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("locale")) {
                    language = cookie.getValue();
                    controllerLog.info("have locale in cookie = " + language);
                    setLocale(request, language);
                    return new Result(Page.INDEX, false);
                }
            }
        }

        controllerLog.info("in the end of index");
        return new Result(Page.INDEX, false);
    }

    public void setLocale(HttpServletRequest request, String language) {
        String[] params = language.split("_");
        Locale locale = new Locale(params[0], params[1]);
        request.getSession(false).setAttribute("locale", locale);
    }
}
