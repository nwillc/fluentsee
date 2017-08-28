
package com.github.nwillc.fluentsee.predicates;

import com.github.nwillc.fluentsee.Entry;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public class JsonPredicate implements Predicate<Entry> {
    private final String field;
    private final Pattern regex;

    public JsonPredicate(String description) {
        final int index = description.indexOf('=');
        final int start = description.startsWith("json.") ? 5 : 0;
        field =  description.substring(start,index);
        regex = Pattern.compile(description.substring(index + 1));
    }

    @Override
    public boolean test(Entry entry) {
        return regex.matcher(entry.json.get(field).toString()).matches();
    }

}
