package by.training.finaltask.controller.commands.document;

import by.training.finaltask.bean.Result;
import by.training.finaltask.bean.entities.Document;
import by.training.finaltask.bean.page.Page;
import by.training.finaltask.controller.StudentCommand;
import by.training.finaltask.service.DocumentService;
import by.training.finaltask.service.ServiceFactory;
import by.training.finaltask.service.excpetion.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ViewStudentDocuments implements StudentCommand {
    private static final Logger controllerLog = LogManager.getLogger("ControllerLog");

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response){
        Integer id = (Integer) request.getAttribute("identity");
        Result result = new Result(Page.DOCUMENT_LIST, false);
        if (id != null) {
            try {
                DocumentService service = ServiceFactory.getInstance().getDocumentService();
                List<Document> documents = service.viewDocuments(id, false);
                request.setAttribute("documents", documents);
            } catch (ServiceException e) {
                controllerLog.error(e.getMessage() + e.getMessage());
                request.getSession(false).setAttribute("error", e.getMessage());
                result = new Result(Page.ERROR, false);
            }
        }
        return result;
    }
}
