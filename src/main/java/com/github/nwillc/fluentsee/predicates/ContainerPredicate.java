
/*
 * foo
 */

package com.github.nwillc.fluentsee.predicates;

import com.github.nwillc.fluentsee.Entry;

import java.util.function.Predicate;
import java.util.regex.Pattern;


public class ContainerPredicate implements Predicate<Entry> {
    private final Pattern regex;

    public ContainerPredicate(String regex) {
        this.regex = Pattern.compile(regex);
    }

    @Override
    public boolean test(Entry entry) {
        return regex.matcher(entry.container).matches();
    }
}
