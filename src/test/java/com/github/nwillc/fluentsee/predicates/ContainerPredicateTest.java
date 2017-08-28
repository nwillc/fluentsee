
/*
 * foo
 */

package com.github.nwillc.fluentsee.predicates;


import com.github.nwillc.fluentsee.Entry;
import com.github.nwillc.fluentsee.predicates.ContainerPredicate;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ContainerPredicateTest {
    private static final String TIME_STAMP = "1234567";
    private static final String CONTAINER = "DEADBEEF";
    private static final String JSON = "{ \"foo\": \"bar\" }";
    private static final Entry entry = new Entry(TIME_STAMP, CONTAINER, JSON);

    @Test
    public void testContainerStringEquals() throws Exception {
        final ContainerPredicate predicate = new ContainerPredicate(CONTAINER);
        assertThat(predicate).isNotNull();
        assertThat(predicate.test(entry)).isTrue();
    }

    @Test
    public void testContainerStringPattern() throws Exception {
        final ContainerPredicate predicate = new ContainerPredicate("DEAD.+");
        assertThat(predicate).isNotNull();
        assertThat(predicate.test(entry)).isTrue();
    }

    @Test
    public void testNonMatch() throws Exception {
        final ContainerPredicate predicate = new ContainerPredicate("foo");
        assertThat(predicate).isNotNull();
        assertThat(predicate.test(entry)).isFalse();
    }
}