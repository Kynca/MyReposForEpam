package by.training.finaltask.controller.commands.dean;

import by.training.finaltask.bean.Result;
import by.training.finaltask.bean.entities.Dean;
import by.training.finaltask.bean.entities.Role;
import by.training.finaltask.bean.entities.User;
import by.training.finaltask.bean.page.Page;
import by.training.finaltask.controller.AdminCommand;
import by.training.finaltask.service.DeanService;
import by.training.finaltask.service.ServiceFactory;
import by.training.finaltask.service.UserService;
import by.training.finaltask.service.excpetion.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProcessDean implements AdminCommand {
    private static final Logger debugLog = LogManager.getLogger("DebugLog");

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Result result;
        debugLog.debug("in process");
        try {
            Integer id = Integer.valueOf(request.getParameter("id"));
            Boolean choice = Boolean.valueOf(request.getParameter("action"));
            debugLog.debug("init" + id + " " + choice);
            if (choice) {
                debugLog.debug("delete");
                DeanService deanService = ServiceFactory.getInstance().getDeanService();
                boolean isDeleted = deanService.deleteDean(id);
                debugLog.debug("is deleted? =" + isDeleted);
                if (!isDeleted) {
                    debugLog.debug("fail");
                    request.setAttribute("msg", "incorrectData");
                }
                result = new Result(Page.DEAN_LIST_HTML, true);
            } else {
                debugLog.debug("edit");
                request.getSession(false).setAttribute("id", id);
                result = new Result(Page.DEAN_FIND, true);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("msg", "incorrectData");
            result = new Result(Page.DEAN_LIST_HTML, true);
        } catch (ServiceException e) {
            debugLog.debug("get exception" + e + e.getMessage());
            result = new Result(Page.ERROR, true);
        }
        return result;
    }
}
