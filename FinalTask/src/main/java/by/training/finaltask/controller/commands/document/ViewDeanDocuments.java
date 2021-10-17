package by.training.finaltask.controller.commands.document;

import by.training.finaltask.bean.Result;
import by.training.finaltask.bean.entities.Document;
import by.training.finaltask.bean.entities.User;
import by.training.finaltask.bean.page.Page;
import by.training.finaltask.controller.DeanCommand;
import by.training.finaltask.service.DocumentService;
import by.training.finaltask.service.ServiceFactory;
import by.training.finaltask.service.excpetion.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ViewDeanDocuments implements DeanCommand {

    private static final Logger debugLog = LogManager.getLogger("DebugLog");

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            Integer deanId = (Integer) request.getSession(false).getAttribute("deanId");
            DocumentService documentService = ServiceFactory.getInstance().getDocumentService();
            List<Document> documents = documentService.viewDocuments(deanId, true);
            debugLog.debug(documents);
            request.setAttribute("documents", documents);

            return new Result(Page.DOCUMENT_DEAN_LIST, false);
        } catch (ServiceException e) {
            debugLog.debug(e);
            return new Result(Page.ERROR, true);
        }
    }
}
