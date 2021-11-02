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

public class CreateDean implements AdminCommand {

    private static final Logger controllerLog = LogManager.getLogger("ControllerLog");

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Result result;
        try {
            request.getSession(false).removeAttribute("incorrectData");
            DeanService deanService = ServiceFactory.getInstance().getDeanService();
            String faculty = request.getParameter("faculty");
            String address = request.getParameter("address");
            Long phoneNum = Long.valueOf(request.getParameter("phoneNumber"));
            Integer universityId = Integer.valueOf(request.getParameter("unicId"));
            Dean dean = new Dean(address, faculty, phoneNum, universityId);
            controllerLog.info(dean);
            if (deanService.create(dean)) {
                controllerLog.info("dean created");
                result = new Result(Page.DEAN_LIST_HTML, true);
            } else {
                controllerLog.info("dean was not created");
                result = new Result(Page.DEAN_FIND_UNIVERSITY, true);
            }
        } catch (ServiceException e) {
            controllerLog.error(e + e.getMessage());
            request.getSession(false).setAttribute("error", e.getMessage());
            result = new Result(Page.ERROR, false);
        } catch (NumberFormatException e) {
            result = new Result(Page.DEAN_FIND_UNIVERSITY, true);
            request.getSession(false).setAttribute("incorrectData", "you should put numbers on field phone num");
        } catch (IllegalArgumentException e) {
            controllerLog.error(e + e.getMessage());
            result = new Result(Page.DEAN_FIND_UNIVERSITY, true);
            request.getSession(false).setAttribute("incorrectData", e.getMessage());
        }
        return result;
    }
}
