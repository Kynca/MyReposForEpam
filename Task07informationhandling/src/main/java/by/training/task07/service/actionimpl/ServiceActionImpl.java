package by.training.task07.service.actionimpl;

import by.training.task07.bean.composite.Component;
import by.training.task07.bean.composite.Composite;
import by.training.task07.bean.composite.Leaf;
import by.training.task07.dao.exception.DaoException;
import by.training.task07.dao.FileOperations;
import by.training.task07.service.ServiceAction;
import by.training.task07.service.exceptrion.ServiceException;
import by.training.task07.service.parserimpl.*;

import java.util.*;

/**
 * implements ServiceAction interface for working with text
 */
public class ServiceActionImpl implements ServiceAction {

    private Composite composite;

    /**
     * @param filename filename from which text for parse will be taken
     * @throws ServiceException occurs error in dao layer
     */
    public void parseText(String filename) throws ServiceException {
        FileOperations fileOperations = new FileOperations();
        try {
            String text = fileOperations.readFile(filename);
            composite = new Composite("");
            SymbolParser symbolParser = new SymbolParser();
            WordParser wordParser = new WordParser(symbolParser);
            LexemeParser lexemeParser = new LexemeParser(wordParser);
            SentenceParser sentenceParser = new SentenceParser(lexemeParser);
            ParagraphParser paragraphParser = new ParagraphParser(sentenceParser);
            paragraphParser.parse(text, composite);
            composite.collectText();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Restores original text with counted expressions
     * @return String collected from tree
     */
    public String collectText() {
        String result = composite.collectText();
        return result;
    }

    /**
     * sort paragraph list by amount of sentences on each
     * @return sorted list of paragraphs
     */
    public List<Component> sortParagraph() {
        List<Component> copyComposites = composite.getCopy();
        Collections.sort(copyComposites, Comparator.comparingInt(o -> o.getCopy().size()));
        return copyComposites;
    }

    /**
     * sort words by size on each sentences
     * @return sorted list sentences
     */
    public List<Component> sortWords() {
        List<Component> copyComposites = composite.getCopy();
        List<Component> sentences = new ArrayList<>();
        List<Component> words;
        Composite lexemeComp;

        for (Component paragraph : copyComposites) {
            for (Component sentence : paragraph.getCopy()) {
                words = new ArrayList<>();
                lexemeComp = new Composite("");
                for (Component lexeme : sentence.getCopy()) {
                    words.addAll(lexeme.getCopy());
                }
                Collections.sort(words, (o1, o2) -> {
                    if (o1 instanceof Leaf) {
                        return -1;
                    } else if (o2 instanceof Leaf) {
                        return 1;
                    } else {
                        return o1.getCopy().size() - o2.getCopy().size();
                    }
                });
                for (Component word : words) {
                    lexemeComp.add(word);
                }
                sentences.add(lexemeComp);
            }
        }
        return sentences;
    }

    /**
     * sort lexeme by the number of occurrences of a character, if they match sorts by the number of words
     * @param symbol symbol by which lexeme is sorted
     * @return sorted list of lexeme
     */
    public List<Component> sortLexeme(String symbol) {
        HashMap<Component, Integer> lexemeMap = new HashMap<>();
        List<Component> lexemeList = new ArrayList<>();

        for (Component paragraph : composite.getCopy()) {
            for (Component sentence : paragraph.getCopy()) {
                for (Component lexeme : sentence.getCopy()) {
                    lexemeList.add(lexeme);
                }
            }
        }

        for (Component lexeme : lexemeList) {
            int counter = 0;
            for (Component word : lexeme.getCopy()) {
                for (Component leaf : word.getCopy()) {
                    if (leaf.collectText().equals(symbol)) {
                        counter++;
                    }
                }
            }
            lexemeMap.put(lexeme, counter);
        }

        Collections.sort(lexemeList, (o1, o2) -> {
            int result = lexemeMap.get(o1) - lexemeMap.get(o2);
            if (result == 0) {
                return o1.getCopy().size() - o2.getCopy().size();
            }
            return result;
        });

        return lexemeList;
    }
}
