package by.training.finaltask.controller.commands;

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

    private static final Logger debugLog = LogManager.getLogger("DebugLog");

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) {
        Result result;
        Enumeration<String> enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()){
            debugLog.debug(enumeration.nextElement());
        }
        try {
            Integer id = (Integer) request.getAttribute("identity");
            if (id != 0) {
                debugLog.debug("have user in add doc");
                Boolean deliveryType = Boolean.valueOf(request.getParameter("deliveryType"));
                Integer docTypeId = Integer.valueOf(request.getParameter("docType"));
                String receiverName = request.getParameter("receiverName");
                String receiverMail = request.getParameter("receiverMail");
                String comment = request.getParameter("comment");
                String currentDate = (2000 + Calendar.YEAR)  + "-";
                        if(Calendar.MONTH < 10){
                            currentDate += "0" + (Calendar.MONTH + 1) + "-";
                        }
                        if(Calendar.DAY_OF_MONTH < 10){
                            currentDate += "0" + Calendar.DAY_OF_MONTH;
                        }
                Document document = new Document(currentDate, docTypeId, deliveryType, receiverName, receiverMail, comment, id);
                debugLog.debug("created document");
                DocumentService documentService = ServiceFactory.getInstance().getDocumentService();
                debugLog.debug("get service");
                if (documentService.createDocument(document)) {
                    debugLog.debug("doc added");
                    result = new Result(Page.DOC_LIST_HTML, true);
                } else {
                    result = new Result(Page.DOC_ORDER_HTML, true);
                    debugLog.debug("doc not added");
                }
            } else {
                result = new Result(Page.LOGIN_FORM, true);
            }
        } catch (ServiceException e) {
            debugLog.debug(e + e.getMessage());
            result = new Result(Page.ERROR, true);
        }
        return result;
    }
}
