

package com.github.nwillc.fluentsee;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.Test;

import java.util.function.Predicate;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class EntryPredicateTest {
    private static final Entry entry = EntryTest.getSample();
    private Predicate<Entry> predicate;

    @Test
    public void testContainerMatch() throws Exception {
        predicate = EntryPredicate.fromDescription("container=DEADBEEF");

        predicate.test(entry);
    }

    @Test
    public void testContainerStringPattern() throws Exception {
        predicate = EntryPredicate.fromDescription("container=DEAD.+");
        assertThat(predicate).isNotNull();
        AssertionsForClassTypes.assertThat(predicate.test(entry)).isTrue();
    }

    @Test
    public void testContainerNonMatch() throws Exception {
        predicate = EntryPredicate.fromDescription("container=foo");
        assertThat(predicate).isNotNull();
        AssertionsForClassTypes.assertThat(predicate.test(entry)).isFalse();
    }

    @Test
    public void testJsonMatch() throws Exception {
        predicate = EntryPredicate.fromDescription("json.foo=bar");
        AssertionsForClassTypes.assertThat(predicate.test(entry)).isTrue();
    }

    @Test
    public void testJsonMatchRegex() throws Exception {
        predicate = EntryPredicate.fromDescription("json.foo=[a-z]+");
        AssertionsForClassTypes.assertThat(predicate.test(entry)).isTrue();
    }

    @Test
    public void testJsonNonMatch() throws Exception {
        predicate = EntryPredicate.fromDescription("json.foo=wombat");
        AssertionsForClassTypes.assertThat(predicate.test(entry)).isFalse();
    }
}