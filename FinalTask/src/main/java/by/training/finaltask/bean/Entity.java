package by.training.finaltask.bean;

import java.io.Serializable;

public abstract class Entity implements Serializable {
    private Integer id;

    public Entity() {
        id = null;
    }

    public Entity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
