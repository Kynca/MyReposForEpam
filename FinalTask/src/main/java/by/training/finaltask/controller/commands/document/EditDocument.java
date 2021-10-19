package by.training.finaltask.controller.commands.document;

import by.training.finaltask.bean.Result;
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

public class EditDocument implements DeanCommand {

    private static final Logger controllerLog = LogManager.getLogger("ControllerLog");

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String path = request.getParameter("path");
        Integer id = Integer.valueOf(request.getParameter("id"));
        request.getSession(false).removeAttribute("incorrectData");
        try {
            DocumentService documentService = ServiceFactory.getInstance().getDocumentService();
            documentService.addFile(path, id);
            return new Result(Page.DOCUMENT_DEAN_LIST_HTML, true);
        } catch (ServiceException e) {
            controllerLog.error(e + e.getMessage());
            request.getSession(false).setAttribute("error", e.getMessage());
            return new Result(Page.ERROR, false);
        }
    }
}
