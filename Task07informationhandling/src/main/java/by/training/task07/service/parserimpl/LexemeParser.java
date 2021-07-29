package by.training.task07.service.parserimpl;

import by.training.task07.bean.composite.Component;
import by.training.task07.bean.composite.Composite;
import by.training.task07.service.Parser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Pattern;

/**
 * class for parsing text into lexemes
 */
public class LexemeParser implements Parser {

    private static final Logger debugLog = LogManager.getLogger("DebugLog");
    private Parser parser;

    public LexemeParser(Parser parser) {
        this.parser = parser;
    }

    /**
     * parse received text into lexeme by using regular expressions, add child to tree and give each lexeme in next parser
     * @param sentences sentence
     * @param root parent link
     */
    public void parse(String sentences, Component root) {
        debugLog.debug(sentences);
        Pattern pattern = Pattern.compile("-\\s|,\\s|;\\s|:\\s");
        if (sentences.charAt(0) == ' ') {
            sentences = sentences.substring(1);
        }
        String[] strings = pattern.split(sentences);
        Composite child;
        sentences = sentences.replaceAll("([a-z]-[a-z])","");
        sentences = sentences.replaceAll("[^,;:-]|", "");
        String[] delimiters = sentences.split("");

        for (int i = 0; i < strings.length; i++) {
            if (i != strings.length - 1) {
                child = new Composite(delimiters[i] + "\s");
            } else {
                child = new Composite("");
            }
            root.add(child);
            parser.parse(strings[i], child);
        }
    }

}
