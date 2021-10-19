package by.training.finaltask.controller.commands.utils;

import by.training.finaltask.bean.Result;
import by.training.finaltask.bean.entities.Role;
import by.training.finaltask.bean.entities.User;
import by.training.finaltask.bean.page.Page;
import by.training.finaltask.controller.AdminCommand;
import by.training.finaltask.controller.DeanCommand;
import by.training.finaltask.controller.StudentCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

public class ProfileDefiner implements AdminCommand, StudentCommand, DeanCommand {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Result result;
        User user = (User) request.getSession().getAttribute("authorizedUser");
        switch (user.getRole()) {
            case STUDENT, DEAN:
                result = new Result(Page.STUDENT_FIND, true);
                break;
            case ADMINISTRATOR:
                result = new Result(Page.PROFILE_JSP, false);
                break;
            default:
                result = new Result(Page.LOGIN_FORM, true);
                break;
        }
        return result;
    }

    @Override
    public Set<Role> getRoleSet() {
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(Role.ADMINISTRATOR);
        roleSet.add(Role.STUDENT);
        roleSet.add(Role.DEAN);
        return roleSet;
    }
}
