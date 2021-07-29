package by.training.task07.bean.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * intermediate part of a tree or root
 */
public class Composite extends Component {

    private List<Component> componentList = new ArrayList<>();
    private String delimiter;

    public Composite(String delimiter) {
        this.delimiter = delimiter;
    }

    @Override
    public String collectText() {
        StringBuilder builder = new StringBuilder();
        for (Component component : componentList) {
            builder.append(component.collectText());
        }
        return String.valueOf(builder.append(delimiter));
    }

    @Override
    public void add(Component component) {
        componentList.add(component);
    }

    @Override
    public void remove(Component component) {
        componentList.remove(component);
    }

    @Override
    public List<Component> getCopy() {
        List<Component> components = new ArrayList<>();
        components.addAll(componentList);
        return components;
    }
}
