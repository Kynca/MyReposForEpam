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
import java.util.Calendar;
import java.util.Enumeration;

public class AddDocument implements StudentCommand {

    private static final Logger controllerLog = LogManager.getLogger("ControllerLog");

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) {
        Result result;
        request.getSession(false).removeAttribute("incorrectData");
        try {
            Integer id = (Integer) request.getAttribute("identity");
            if (id != 0) {
                controllerLog.debug("have user in add doc");
                Boolean deliveryType = Boolean.valueOf(request.getParameter("deliveryType"));
                String docTypeId = request.getParameter("docType");
                if(docTypeId == null){
                    return new Result(Page.DOCUMENT_ORDER_HTML, true);
                }
                String receiverName = request.getParameter("receiverName");
                String receiverMail = request.getParameter("receiverMail");
                String comment = request.getParameter("comment");
                Document document = new Document(Integer.valueOf(docTypeId), deliveryType, receiverName, receiverMail, comment, id);
                DocumentService documentService = ServiceFactory.getInstance().getDocumentService();
                if (documentService.createDocument(document)) {
                    controllerLog.info("doc added");
                    result = new Result(Page.DOCUMENT_LIST_HTML, true);
                } else {
                    controllerLog.info("doc not added");
                    result = new Result(Page.DOCUMENT_ORDER_HTML, true);
                    request.getSession(false).setAttribute("incorrectData", "incorrectData");
                }
            } else {
                result = new Result(Page.LOGIN_FORM, true);
            }
        } catch (ServiceException e) {
            controllerLog.error(e + e.getMessage());
            request.getSession(false).setAttribute("error", e.getMessage());
            result = new Result(Page.ERROR, false);
        }
        return result;
    }
}
