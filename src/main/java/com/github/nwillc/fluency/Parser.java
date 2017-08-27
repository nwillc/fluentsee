package com.github.nwillc.fluency;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static Entry parseEntry(String sample1) {
        final Pattern pattern = Pattern.compile("([^\\s]+)\\s+([a-z0-9]+)\\s+(\\{.*})");
        final Matcher matcher = pattern.matcher(sample1);

        if (!matcher.find()) {
            return null;
        }

        return new Entry(matcher.group(1), matcher.group(2), matcher.group(3));
    }
}
