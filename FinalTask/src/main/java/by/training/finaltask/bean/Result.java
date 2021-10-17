package by.training.finaltask.bean;

import by.training.finaltask.bean.page.Page;

public class Result {
    private boolean isRedirect;
    private Page page;

    public Result(Page page, boolean isRedirect){
        this.isRedirect = isRedirect;
        this.page = page;
    }

    public Page getPage(){
        return page;
    }

    public boolean isRedirect() {
        return isRedirect;
    }


}
