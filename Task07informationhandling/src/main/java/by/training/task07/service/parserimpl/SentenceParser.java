package by.training.task07.service.parserimpl;

import by.training.task07.bean.composite.Component;
import by.training.task07.bean.composite.Composite;
import by.training.task07.service.Parser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Pattern;

/**
 * class for parsing text into sentences
 */
public class SentenceParser implements Parser {

//    private static final Logger debugLog = LogManager.getLogger("DebugLog");

    private Parser parser;


    public SentenceParser(Parser parser) {
        this.parser = parser;
    }
    /**
     * parse received text into sentences by using regular expressions, add child to tree and give each sentences in next parser
     * @param paragraph paragraph string
     * @param root parent link
     */
    public void parse(String paragraph, Component root) {
        Pattern pattern = Pattern.compile("\\?!|\\.{3}|\\.|\\?|!");
        String[] strings = pattern.split(paragraph);
        Composite child;
        String[] delimiters;

        paragraph = paragraph.replaceAll("[^?!.]", "\s");
        paragraph = paragraph.replaceAll("\\s{2,}", "\s");
        paragraph = paragraph.substring(1);
        delimiters = paragraph.split(" ");

        for (int i = 0; i < strings.length; i++) {
            if (i != strings.length - 1) {
                child = new Composite(delimiters[i] + "\s");
            } else {
                child = new Composite(delimiters[i]);
            }
            root.add(child);
            parser.parse(strings[i], child);
        }
    }
}
