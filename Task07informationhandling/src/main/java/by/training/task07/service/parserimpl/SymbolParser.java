package by.training.task07.service.parserimpl;

import by.training.task07.bean.composite.Component;
import by.training.task07.bean.composite.Leaf;
import by.training.task07.service.Parser;

/**
 * class which parse words into symbols
 */
public class SymbolParser implements Parser {

    /**
     * parse every symbol in word into char create leaf and add it to tree
     * @param part word in string
     * @param root link to parent
     */
    public void parse(String part, Component root) {
        char[] symbols = part.toCharArray();
        for (char symbol : symbols) {
            Leaf child = new Leaf(symbol);
            root.add(child);
        }
    }

}
