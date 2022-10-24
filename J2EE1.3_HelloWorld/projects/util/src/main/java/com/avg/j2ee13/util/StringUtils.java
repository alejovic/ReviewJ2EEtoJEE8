package com.avg.j2ee13.util;

import com.evermind.util.ByteString;

import java.util.Collection;
import java.util.Iterator;
import java.util.StringTokenizer;

public class StringUtils {

    private static final String DEFAULT_SEPARATOR = ";";

    private StringUtils(){}

    // public String[] split(String regex) was introduced in Java 1.4
    public static String[] split(String string) {
        return split(string, DEFAULT_SEPARATOR);
    }

    public static String[] split(String string, String separator) {
        final StringTokenizer st = new StringTokenizer(string, separator);
        final String[] fields = new String[st.countTokens()];
        int index = 0;
        while (st.hasMoreTokens()) {
            fields[index] = st.nextToken();
            index++;
        }
        return fields;
    }

    public static String join(String[] string, String delimeter) {
        StringBuffer buffer = new StringBuffer();

        for(int i = 0; i < string.length; ++i) {
            buffer.append(string[i]);
            buffer.append(delimeter);
        }

        return buffer.toString();
    }

    public static String replace(String source, char original, String newDelimeter) {
        int swapIndex;
        if ((swapIndex = source.indexOf(original)) < 0) {
            return source;
        } else {
            StringBuffer result = new StringBuffer();

            int lastSwapIndex;
            for(lastSwapIndex = 0; swapIndex >= 0; swapIndex = source.indexOf(original, lastSwapIndex)) {
                result.append(source.substring(lastSwapIndex, swapIndex));
                result.append(newDelimeter);
                lastSwapIndex = swapIndex + 1;
            }

            if (lastSwapIndex < source.length()) {
                result.append(source.substring(lastSwapIndex, source.length()));
            }

            return result.toString();
        }
    }

    public static String replace(String source, String original, String newDelimeter) {
        int swapIndex;
        if ((swapIndex = source.indexOf(original)) < 0) {
            return source;
        } else {
            StringBuffer result = new StringBuffer();

            int lastSwapIndex;
            for(lastSwapIndex = 0; swapIndex >= 0; swapIndex = source.indexOf(original, lastSwapIndex)) {
                result.append(source.substring(lastSwapIndex, swapIndex));
                result.append(newDelimeter);
                lastSwapIndex = swapIndex + original.length();
            }

            if (lastSwapIndex < source.length()) {
                result.append(source.substring(lastSwapIndex, source.length()));
            }

            return result.toString();
        }
    }

    public static ByteString[] toByteString(String[] strings) {
        ByteString[] byteStrings = new ByteString[strings.length];

        for(int i = 0; i < byteStrings.length; ++i) {
            if (strings[i] != null) {
                byteStrings[i] = new ByteString(strings[i]);
            }
        }

        return byteStrings;
    }

    public static ByteString[] toByteString(Collection collection) {
        ByteString[] byteStrings = new ByteString[collection.size()];
        Iterator iterator = collection.iterator();

        for(int i = 0; iterator.hasNext(); ++i) {
            Object object = iterator.next();
            if (object != null) {
                byteStrings[i] = new ByteString(object.toString());
            }
        }

        return byteStrings;
    }

    public static int getMatchedEndLength(String a, String b) {
        int size = a.length() > b.length() ? b.length() : a.length();

        for(int i = 0; i < size; ++i) {
            if (a.charAt(a.length() - i - 1) != b.charAt(b.length() - i - 1)) {
                return i;
            }
        }

        return size;
    }

    public static int getMatchesIn(String content, String match) {
        int matchPos = 0;

        for(int i = 0; i < content.length(); ++i) {
            if (matchPos == match.length()) {
                return matchPos;
            }

            if (content.charAt(i) == match.charAt(matchPos)) {
                ++matchPos;
            }
        }

        return matchPos;
    }

    public static int getMatchesInBackwards(String content, String match) {
        int matchPos = match.length() - 1;

        for(int i = content.length() - 1; i >= 0; --i) {
            if (matchPos == -1) {
                return match.length();
            }

            if (content.charAt(i) == match.charAt(matchPos)) {
                --matchPos;
            }
        }

        return match.length() - 1 - matchPos;
    }
}
