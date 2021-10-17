package by.training.finaltask.bean.page;

public enum Page {
    ERROR("/WEB-INF/jsp/error.jsp"),
    LOGIN("/login.jsp"),
    LOGIN_FORM("/loginForm.html"),
    PROFILE_JSP("/profile.jsp"),
    PROFILE_HTML("/profile.html"),
    MARK_LIST("/stud/markList.jsp"),
    DOC_LIST("/stud/documentList.jsp"),
    DOC_LIST_HTML("/stud/documentList.html"),
    DOC_ORDER_JSP("/stud/documentOrder.jsp"),
    DOC_ORDER_HTML("/stud/documentOrder.html"),
    INDEX("/index.jsp"),

    STUDENT_LIST("/student/list.jsp"),
    STUDENT_LIST_HTML("/student/list.html"),
    STUDENT_EDIT_JSP("/student/edit.jsp"),
    STUDENT_EDIT_HTML("/student/edit.html"),
    STUDENT_FIND("/student/find.html"),

    USER_FIND("/user/find.html"),
    USER_LIST_HTML("/user/list.html"),
    USER_LIST_JSP("/user/list.jsp"),
    USER_EDIT_JSP("/user/edit.jsp"),
    USER_EDIT_HTML("/user/edit.html"),

    VIEW_DEAN_INFO("/viewDeanInfo.html"),
    DEAN_LIST_HTML("/dean/list.html"),
    DEAN_LIST_JSP("/dean/list.jsp"),
    DEAN_EDIT_JSP("/dean/edit.jsp"),
    DEAN_EDIT_HTML("/dean/edit.html"),
    DEAN_FIND("/dean/find.html"),
    DEAN_CREATE_HTML("/dean/create.html"),
    DEAN_CREATE_JSP("/dean/create.jsp"),
    DEAN_FIND_UNIVERSITY("/dean/find/uni.html")

    ;

    private String value;

    Page(String value){
        this.value = value;
    }

    public String getPage(){
        return value;
   }

}
