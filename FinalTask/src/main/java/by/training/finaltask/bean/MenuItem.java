package by.training.finaltask.bean;

import java.io.Serializable;

public class MenuItem implements Serializable {
    private String name;
    private String url;

    public MenuItem(String url, String name) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
