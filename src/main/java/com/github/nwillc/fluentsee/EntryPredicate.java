
/*
 * foo
 */

package com.github.nwillc.fluentsee;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class EntryPredicate implements Predicate<Entry> {
    public static final String CONTAINER = "container";
    public static final String TIMESTAMP = "timestamp";
    public static final String JSON = "json.";
    public static final char EQUALS = '=';
    private final Function<Entry,String> accessor;
    private Pattern regex;

    public EntryPredicate(String regex, Function<Entry, String> accessor) {
        this.accessor = accessor;
        this.regex = Pattern.compile(regex);
    }

    @Override
    public boolean test(Entry entry) {
        return regex.matcher(accessor.apply(entry)).matches();
    }

    public static EntryPredicate fromDescription(String str) {
        final int index = str.indexOf(EQUALS);
        final String field = str.substring(0,index);
        final String regex = str.substring(index + 1);

        Function<Entry, String> accessor;
        if (field.equals(CONTAINER)) {
            accessor = e -> e.container;
        } else if (field.equals(TIMESTAMP)) {
            accessor = e -> e.container;
        } else if (field.startsWith(JSON)) {
            final String jsonStr = field.substring(5);
            accessor = e -> e.json.get(jsonStr).toString();
        } else {
            throw new IllegalArgumentException("Unknown match field: " + field);
        }

        return new EntryPredicate(regex, accessor);
    }
}
