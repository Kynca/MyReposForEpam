package by.training.finaltask.controller.commands.documents;

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

public class ViewDocList implements StudentCommand {
    private static final Logger debugLog = LogManager.getLogger("DebugLog");

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response){
        debugLog.debug("in doc");
        Integer id = (Integer) request.getAttribute("identity");
        Result result = new Result(Page.DOC_LIST, false);
        if (id != null) {
            try {
                DocumentService service = ServiceFactory.getInstance().getDocumentService();
                debugLog.debug("get service");
                List<Document> documents = service.viewDocuments(id, false);
                debugLog.debug("view doc");
                request.setAttribute("documents", documents);
                debugLog.debug("in the end of doc");
            } catch (ServiceException e) {
                debugLog.debug(e.getMessage() + e.getMessage());
                result = new Result(Page.ERROR, true);
            }
        }
        return result;
    }
}
