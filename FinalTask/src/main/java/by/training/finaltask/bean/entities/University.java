package by.training.finaltask.bean.entities;

import by.training.finaltask.bean.Entity;

import java.io.Serializable;
import java.util.Objects;

public class University extends Entity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        University that = (University) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "University{ "+ super.getId() +
                "name='" + name + '\'' +
                '}';
    }
}
