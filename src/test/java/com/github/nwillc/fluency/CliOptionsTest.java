

package com.github.nwillc.fluency;

import com.github.nwillc.contracts.UtilityClassContract;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class CliOptionsTest extends UtilityClassContract {
    @Override
    public Class<?> getClassToTest() {
        return CliOptions.class;
    }

    @Test
    public void testAllOptionsSupported() throws Exception {
        assertThat(CliOptions.getOptions()).isNotNull();
        assertThat(CliOptions.getOptions().recognizedOptions()).hasSize(CliOptions.CLI.values().length + 1);
    }
}