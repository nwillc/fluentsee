package com.github.nwillc.fluentsee;


import com.github.nwillc.contracts.UtilityClassContract;
import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class JsonUtilTest extends UtilityClassContract {
    private static final String JSON = "{ \"foo\": \"bar\", \"count\": 42 }";

    @Override
    public Class<?> getClassToTest() {
        return JsonUtil.class;
    }

    @Test
    public void testToMap() throws Exception {
        final Map map = JsonUtil.toMap(JSON);
        assertThat(map).isNotNull();
        assertThat(map.get("foo")).isEqualTo("bar");
        assertThat(map.get("count")).isEqualTo(42);
    }
}