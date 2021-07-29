package by.training.task07.service.parserimpl;

import by.training.task07.bean.composite.Component;
import by.training.task07.bean.composite.Composite;
import by.training.task07.service.Parser;

import java.util.regex.Pattern;

/**
 * class for parsing text into paragraphs
 */
public class ParagraphParser implements Parser {
    private Parser parser;

    public ParagraphParser(Parser parser) {
        this.parser = parser;
    }

    /**
     * parse received text into paragraph by using regular expressions, add child to tree and give each paragraph in next parser
     * @param part sentence
     * @param root parent link
     */

    public void parse(String part, Component root) {
        Pattern pattern = Pattern.compile("\\s{4}|\\t");
        part = part.replaceAll("\r", "");
        part = part.replaceAll("\n", "");
        String[] strings = pattern.split(part);
        Composite child;

        for (int i = 1; i < strings.length; i++) {
            if (i == 1) {
                child = new Composite(System.lineSeparator() + "\t") {
                    @Override
                    public String collectText() {
                        return "\t" + super.collectText();
                    }
                };
            } else if (i == strings.length - 1) {
                child = new Composite("");
            } else {
                child = new Composite(System.lineSeparator() + "\t");
            }

            root.add(child);
            parser.parse(strings[i], child);
        }
    }
}
