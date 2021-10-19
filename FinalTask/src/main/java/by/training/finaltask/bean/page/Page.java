package by.training.finaltask.bean.page;

public enum Page {
    ERROR("/error.jsp"),
    LOGIN("/login.jsp"),
    LOGIN_FORM("/loginForm.html"),
    PROFILE_JSP("/profile.jsp"),
    PROFILE_HTML("/profile.html"),
    INDEX("/index.jsp"),
    INDEX_HTML("/.html"),

    ADMIN_STUDENT_LIST("/student/adminList.jsp"),
    DEAN_STUDENT_LIST("/student/deanList.jsp"),
    STUDENT_LIST_HTML("/student/list.html"),
    STUDENT_EDIT_JSP("/student/edit.jsp"),
    STUDENT_EDIT_HTML("/student/edit.html"),
    STUDENT_FIND("/student/find.html"),
    STUDENT_CREATE("/student/create.jsp"),

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
    DEAN_FIND_UNIVERSITY("/dean/find/uni.html"),

    DOCUMENT_DEAN_LIST("/document/deanList.jsp"),
    DOCUMENT_DEAN_LIST_HTML("/document/dean/list.html"),
    DOCUMENT_LIST("/document/studList.jsp"),
    DOCUMENT_LIST_HTML("/document/stud/list.html"),
    DOCUMENT_ORDER_JSP("/document/order.jsp"),
    DOCUMENT_ORDER_HTML("/document/order.html"),

    MARK_LIST("/mark/list.jsp")
    ;

    private String value;

    Page(String value){
        this.value = value;
    }

    public String getPage(){
        return value;
   }

}
