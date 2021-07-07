package by.training.task05.bean;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Enum class with 3 languages which used in this programme
 */
public enum MessageManager {
    En(ResourceBundle.getBundle("properties.languages", new Locale("en", "EN"))),
    Ru(ResourceBundle.getBundle("properties.languages", new Locale("ru", "RU"))),
    By(ResourceBundle.getBundle("properties.languages", new Locale("be", "BY")));

    private ResourceBundle bundle;

    MessageManager(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public String getString(String key){
        return bundle.getString(key);
    }
}

