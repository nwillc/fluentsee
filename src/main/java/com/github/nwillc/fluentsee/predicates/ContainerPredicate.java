
/*
 * foo
 */

package com.github.nwillc.fluentsee.predicates;

import com.github.nwillc.fluentsee.Entry;

import java.util.function.Predicate;


public class ContainerPredicate implements Predicate<Entry> {
    private final String regex;

    public ContainerPredicate(String regex) {
        this.regex = regex;
    }

    @Override
    public boolean test(Entry entry) {
        return entry.container.matches(regex);
    }
}
