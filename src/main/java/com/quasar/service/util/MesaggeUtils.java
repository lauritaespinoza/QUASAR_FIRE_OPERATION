package com.quasar.service.util;

import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class MesaggeUtils {

    public static String[][] transformToArray2D(List<List<String>> encodeList, int maxSize) {
        String[][] array = new String[encodeList.size()][];
        String[] blankArray = new String[0];
        for (int i = 0; i < encodeList.size(); i++) {
            for (int j = encodeList.get(i).size(); j < maxSize; j++)
                encodeList.get(i).add("");
            array[i] = encodeList.get(i).toArray(blankArray);
        }
        return array;
    }

    public static String[][] reverseArray(String[]... messages) {
        for (int i = 0; i < messages.length; i++) {
            int size = messages[i].length;
            int k = 0;
            String[] reverse = new String[size];
            for (int j = size - 1; j >= 0; j--) {
                reverse[k] = messages[i][j];
                k++;
            }
            messages[i] = reverse;
        }
        return messages;
    }

    public static String selectWord(List<String> words) {
        Assert.notNull(words, "The parameters must bet not null");
        words = words.stream().distinct().filter(w -> !w.isEmpty()).collect(Collectors.toList());
        if (words == null || words.isEmpty())
            return null;
        else {
            if (words.size() == 1)
                return words.get(0);
            else {
                return String.join(" ", words);
            }
        }
    }
}
