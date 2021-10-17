package by.training.finaltask.controller.filter;

import by.training.finaltask.controller.Command;
import by.training.finaltask.controller.commands.*;
import by.training.finaltask.controller.commands.document.*;
import by.training.finaltask.controller.commands.marks.ViewListOfMarks;
import by.training.finaltask.controller.commands.dean.*;
import by.training.finaltask.controller.commands.student.*;
import by.training.finaltask.controller.commands.user.EditUser;
import by.training.finaltask.controller.commands.user.FindUser;
import by.training.finaltask.controller.commands.user.ProcessUser;
import by.training.finaltask.controller.commands.user.ViewUsers;
import by.training.finaltask.controller.commands.utils.Index;
import by.training.finaltask.controller.commands.Login;
import by.training.finaltask.controller.commands.utils.LoginForm;
import by.training.finaltask.controller.commands.utils.ProfileDefiner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommandFromUriFilter implements Filter {
    private static final Logger debugLog = LogManager.getLogger("DebugLog");

    private static final Map<String, Command> repository = new ConcurrentHashMap<>();

    static {
        repository.put("/", new Index());
        repository.put("/index", new Index());
        repository.put("/loginForm", new LoginForm());
        repository.put("/login", new Login());
        repository.put("/logout", new LogOut());
        repository.put("/profile", new ProfileDefiner());
        repository.put("/viewDeanInfo",new ViewDeanInfo());

        repository.put("/mark/list", new ViewListOfMarks());

        repository.put("/student/list", new ViewStudentList());
        repository.put("/student/process", new ProcessStudent());
        repository.put("/student/find", new FindStudent());
        repository.put("/student/edit", new EditStudent());
        repository.put("/student/create", new CreateStudent());

        repository.put("/user/list", new ViewUsers());
        repository.put("/user/process", new ProcessUser());
        repository.put("/user/find", new FindUser());
        repository.put("/user/edit", new EditUser());

        repository.put("/dean/list", new ViewDeans());
        repository.put("/dean/process", new ProcessDean());
        repository.put("/dean/find", new FindDean());
        repository.put("/dean/edit", new EditDean());
        repository.put("/dean/find/uni", new FindUniversities());
        repository.put("/dean/create", new CreateDean());

        repository.put("/document/dean/list", new ViewDeanDocuments());
        repository.put("/document/stud/list", new ViewStudentDocuments());
        repository.put("/document/edit" , new EditDocument());
        repository.put("/document/order",new ViewDocTypes());
        repository.put("/document/order/add", new AddDocument());

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            String contextPath = request.getContextPath();
            String uri = request.getRequestURI();

            int beginCommand = contextPath.length();
            int endCommand = uri.lastIndexOf('.');
            String commandName;

            if (endCommand >= 0) {
                commandName = uri.substring(beginCommand, endCommand);
            } else {
                commandName = uri.substring(beginCommand);
            }
            debugLog.debug(commandName);
            try {
                Command command = repository.get(commandName);
                debugLog.debug("got it" + command.toString());
                request.setAttribute("command", command);
                debugLog.debug("set it");
                filterChain.doFilter(servletRequest, servletResponse);
            }catch (NullPointerException e){
                debugLog.debug("error in uri filter");
                request.setAttribute("error", "неверный адресс" + commandName);//TODO
                request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(servletRequest,servletResponse);
            }
        }else{
            debugLog.debug("error in filter");
            servletRequest.setAttribute("error", "неверный адресс1");//TODO
            servletRequest.getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(servletRequest,servletResponse);
        }
    }
}
