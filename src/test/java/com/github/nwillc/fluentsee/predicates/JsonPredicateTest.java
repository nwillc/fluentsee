
package com.github.nwillc.fluentsee.predicates;


import com.github.nwillc.fluentsee.Entry;
import com.github.nwillc.fluentsee.EntryTest;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class JsonPredicateTest {
    private static final Entry entry = EntryTest.getSample();

    @Test
    public void testMatch() throws Exception {
        final JsonPredicate predicate = new JsonPredicate("json.foo=bar");
        assertThat(predicate.test(entry)).isTrue();
    }

    @Test
    public void testMatchRegex() throws Exception {
        final JsonPredicate predicate = new JsonPredicate("json.foo=[a-z]+");
        assertThat(predicate.test(entry)).isTrue();
    }

    @Test
    public void testNonMatch() throws Exception {
        final JsonPredicate predicate = new JsonPredicate("json.foo=wombat");
        assertThat(predicate.test(entry)).isFalse();
    }
}