package by.training.finaltask.controller.commands.dean;

import by.training.finaltask.bean.Result;
import by.training.finaltask.bean.entities.Dean;
import by.training.finaltask.bean.entities.University;
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
import java.util.List;
import java.util.Map;

public class FindDean implements AdminCommand {

    private static final Logger debugLog = LogManager.getLogger("DebugLog");

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Result result;
        debugLog.debug("in find dean");
        try {
            Integer id = (Integer) request.getSession(false).getAttribute("id");
            debugLog.debug(id);
            DeanService deanService = ServiceFactory.getInstance().getDeanService();
            Map<Dean, List<University> > map = deanService.findDeanUniversities(id);
            if(map == null){
                    return new Result(Page.DEAN_LIST_HTML,true);
            }
            for (Dean dean : map.keySet()) {
                request.setAttribute("dean", dean);
                request.setAttribute("universities", map.get(dean));
            }
            result = new Result(Page.DEAN_EDIT_JSP, false);
        } catch (ServiceException e) {
            result = new Result(Page.DEAN_LIST_HTML, true);
        }
        return result;
    }
}
