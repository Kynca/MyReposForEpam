package by.training.finaltask.controller.filter;

import by.training.finaltask.bean.entities.Role;
import by.training.finaltask.bean.entities.User;
import by.training.finaltask.controller.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SecurityFilter implements Filter {
    private static final Logger debugLog = LogManager.getLogger("DebugLog");

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpRequest.getSession(false);
        debugLog.debug("in security filter");
        Command command = (Command) httpRequest.getAttribute("command");
        debugLog.debug("in security " + command.toString());
        User user;
        boolean executable = false;
        if (session != null) {
            user = (User) session.getAttribute("authorizedUser");
            debugLog.debug("have session");
            if (user != null) {
                debugLog.debug(user + "not null");
                Role role = user.getRole();
                debugLog.debug(role);
                servletRequest.setAttribute("identity", user.getId());
                if (command.getRoleSet() != null) {
                    debugLog.debug(user.getRole().getValue() + " " + command.getRoleSet());
                    if (command.getRoleSet().contains(user.getRole())) {
                        executable = true;
                    }
                } else {
                    executable = true;
                }
            }
        }
        if (command.getRoleSet().size() == 0) {
            executable = true;
        }
        if (executable) {
            debugLog.debug("executable");
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/loginForm.html");
        }
    }
}