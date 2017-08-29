
/*
 * foo
 */

package com.github.nwillc.fluentsee;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class EntryPredicate implements Predicate<Entry> {
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
        final int index = str.indexOf('=');
        final String field = str.substring(0,index);
        final String regex = str.substring(index + 1);

        Function<Entry, String> accessor = null;
        if (field.equals("container")) {
            accessor = e -> e.container;
        } else if (field.equals("timestamp")) {
            accessor = e -> e.container;
        } else if (field.startsWith("json.")) {
            final String jsonStr = field.substring(5);
            accessor = e -> e.json.get(jsonStr).toString();
        } else {
            throw new IllegalArgumentException("Unknown match field: " + field);
        }

        return new EntryPredicate(regex, accessor);
    }
}
