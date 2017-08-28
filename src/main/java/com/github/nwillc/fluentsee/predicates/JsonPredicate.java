
package com.github.nwillc.fluentsee.predicates;


import com.github.nwillc.fluentsee.Entry;

import java.util.function.Predicate;

public class JsonPredicate implements Predicate<Entry> {
    private final String field;
    private final String regex;

    public JsonPredicate(String field, String regex) {
        this.field = field;
        this.regex = regex;
    }

    public JsonPredicate(String description) {
        final int index = description.indexOf('=');
        final int start = description.startsWith("json.") ? 5 : 0;
        field =  description.substring(start,index);
        regex = description.substring(index + 1);
    }

    @Override
    public boolean test(Entry entry) {
        return entry.json.get(field).toString().matches(regex);
    }

    @Override
    public String toString() {
        return "JsonPredicate{" +
                "field='" + field + '\'' +
                ", regex='" + regex + '\'' +
                '}';
    }
}
