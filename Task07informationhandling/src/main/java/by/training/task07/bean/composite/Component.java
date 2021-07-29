package by.training.task07.bean.composite;


import java.util.List;

/**
 * based class for composite pattern
 */
public abstract class Component {
    public abstract String collectText();
    public abstract void add(Component component);
    public abstract void remove(Component component);
    public abstract List<Component> getCopy();

}
