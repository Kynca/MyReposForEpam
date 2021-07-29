package by.training.task07.bean.composite;

import java.util.List;

/**
 * final part of a tree
 */
public class Leaf extends Component{

    char symbol;

    public Leaf(char symbol){
        this.symbol = symbol;
    }

    @Override
    public String collectText(){
        return String.valueOf(symbol);
    }

    @Override
    public void add(Component component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(Component component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Component> getCopy() {
        throw new UnsupportedOperationException();
    }
}
