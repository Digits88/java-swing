package chapter6;

import javax.swing.*;

/**
 * A model that dynamically generates n-letter words.
 */
public class WordListModel extends AbstractListModel<String> {

    private int length;
    public final static char FIRST = 'a';
    public final static char LAST = 'z';
    
    public WordListModel(int n) {
        length = n;
    }
    
    public int getSize() {
        return (int) Math.pow(LAST - FIRST + 1, length);
    }
    
    public String getElementAt(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = (char) (FIRST + n % (LAST - FIRST + 1));
            sb.insert(0, c);
            n = n / (LAST - FIRST + 1);
        }
        return sb.toString();
    }
}