package by.training.finaltask.controller.commands.dean;

import by.training.finaltask.bean.Result;
import by.training.finaltask.bean.entities.Dean;
import by.training.finaltask.bean.page.Page;
import by.training.finaltask.controller.AdminCommand;
import by.training.finaltask.service.DeanService;
import by.training.finaltask.service.ServiceFactory;
import by.training.finaltask.service.excpetion.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ViewDeans implements AdminCommand {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            DeanService deanService = ServiceFactory.getInstance().getDeanService();
            List<Dean> deans = deanService.viewDeans();
            request.setAttribute("deans", deans);
            return new Result(Page.DEAN_LIST_JSP, false);
        } catch (ServiceException e) {
            return new Result(Page.ERROR, true);
        }

    }
}
