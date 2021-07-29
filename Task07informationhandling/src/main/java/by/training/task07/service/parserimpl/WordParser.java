package by.training.task07.service.parserimpl;

import by.training.task07.bean.composite.Component;
import by.training.task07.bean.composite.Composite;
import by.training.task07.bean.composite.Leaf;
import by.training.task07.service.Parser;
import by.training.task07.service.intepreterimpl.RpnTransformer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * class for parsing text into words
 */
public class WordParser implements Parser {

    private static final Logger debugLog = LogManager.getLogger("DebugLog");

    private Parser parser;

    public WordParser(Parser parser) {
        this.parser = parser;
    }

    /**
     * parse received text into words by using regular expressions, add child to tree and give each word in next parser
     * if parser defined that word is expression create RpnTransformer which calculate the value of expression
     * @param lexeme lexeme
     * @param root parent link
     */

    public void parse(String lexeme, Component root) {
        Pattern pattern = Pattern.compile("\\s");
//        debugLog.debug(builder);
        String[] strings = pattern.split(lexeme);
        Composite child;
        pattern = Pattern.compile("\\d");


        for (int i = 0; i < strings.length; i++) {
            Matcher matcher = pattern.matcher(strings[i]);

            if (matcher.find()) {
                RpnTransformer calculator = new RpnTransformer();
                int res = calculator.calculateExp(strings[i]);
                String del="";
                if (i != strings.length - 1) {
                    del =" ";
                }
                String finalDel = del;
                Leaf leaf = new Leaf(' ') {
                    @Override
                    public String collectText() {
                        return res + finalDel;
                    }

                    @Override
                    public List<Component> getCopy() {
                        List <Component> list = new ArrayList<>(1);
                        return list;
                    }
                };
                root.add(leaf);
                continue;
            }

            if (i != strings.length - 1) {
                child = new Composite("\s");
            } else {
                child = new Composite("");
            }
            root.add(child);
            parser.parse(strings[i], child);
        }
    }
}
