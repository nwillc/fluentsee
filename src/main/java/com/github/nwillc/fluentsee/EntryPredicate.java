
/*
 * Copyright 2017 nwillc@gmail.com
 *
 * Permission to use, copy, modify, and/or distribute this software for any purpose with or without fee is
 * hereby granted, provided that the above copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SOFTWARE
 * INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE
 * FOR ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM
 * LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION,
 * ARISING OUT OF OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */

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
    private final Pattern regex;

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
