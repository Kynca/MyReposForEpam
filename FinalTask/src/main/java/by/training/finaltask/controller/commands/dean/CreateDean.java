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
            Dean dean = new Dean(address, faculty, phoneNum, universityId);
            debugLog.debug("created dean");
            if(deanService.create(dean)){
                result = new Result(Page.DEAN_LIST_HTML, true);
            }else{
                result = new Result(Page.DEAN_FIND_UNIVERSITY,true);
            }
        } catch (ServiceException e) {
            debugLog.debug(e + e.getMessage());
           result = new Result(Page.ERROR,true);
        }catch (NumberFormatException e){
            result = new Result(Page.DEAN_FIND_UNIVERSITY, true);
        }
        return result;
    }
}
