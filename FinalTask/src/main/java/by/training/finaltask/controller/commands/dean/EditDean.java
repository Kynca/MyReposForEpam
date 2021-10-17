package by.training.finaltask.controller.commands.dean;

import by.training.finaltask.bean.Result;
import by.training.finaltask.bean.entities.Dean;
import by.training.finaltask.bean.page.Page;
import by.training.finaltask.controller.AdminCommand;
import by.training.finaltask.service.DeanService;
import by.training.finaltask.service.ServiceFactory;
import by.training.finaltask.service.excpetion.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditDean implements AdminCommand {

    private static final Logger debugLog = LogManager.getLogger("DebugLog");

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Result result;
        try {
            DeanService deanService = ServiceFactory.getInstance().getDeanService();
            String faculty = request.getParameter("faculty");
            String address = request.getParameter("address");
            Long phoneNum = Long.valueOf(request.getParameter("phoneNumber"));
            Integer universityId = Integer.valueOf(request.getParameter("unicId"));
            debugLog.debug("init variables");
            Integer id = Integer.valueOf(request.getParameter("id"));
            debugLog.debug("init id = " + id);
            Dean dean = new Dean(id, faculty, address, phoneNum, universityId);

            if (deanService.updateDean(dean)) {
                result = new Result(Page.DEAN_LIST_HTML, true);
                request.getSession(false).removeAttribute("id");
            } else {
                result = new Result(Page.DEAN_FIND, true);
            }
        } catch (ServiceException e) {
            debugLog.debug(e + e.getMessage());
            result = new Result(Page.ERROR, true);
        } catch (NumberFormatException e) {
            debugLog.debug(e + e.getMessage());
            result = new Result(Page.DEAN_FIND, true);
        }
        return result;
    }
}
