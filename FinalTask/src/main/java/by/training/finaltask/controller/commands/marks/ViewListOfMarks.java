package by.training.finaltask.controller.commands.marks;

import by.training.finaltask.bean.Result;
import by.training.finaltask.bean.entities.Mark;
import by.training.finaltask.bean.page.Page;
import by.training.finaltask.controller.StudentCommand;
import by.training.finaltask.service.MarkService;
import by.training.finaltask.service.ServiceFactory;
import by.training.finaltask.service.excpetion.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ViewListOfMarks implements StudentCommand {
    private static final Logger debugLog = LogManager.getLogger("DebugLog");
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Integer id = (Integer) request.getAttribute("identity");
        Result result = new Result(Page.MARK_LIST, false);
        debugLog.debug(id);
        if (id != null) {
            try {
                MarkService service = ServiceFactory.getInstance().getMarkService();
                List<Mark> marks = service.viewMarks(id);
                request.setAttribute("marks", marks);
            } catch (ServiceException e) {
                result = new Result(Page.ERROR, true);
            }
        }
        return result;
    }
}
