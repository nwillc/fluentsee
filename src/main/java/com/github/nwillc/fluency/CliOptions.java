

package com.github.nwillc.fluency;

import joptsimple.OptionParser;

final public class CliOptions {
    public enum CLI {
        log,
        help
    }

    private CliOptions() {
    }

    public static OptionParser getOptions() {
        OptionParser parser = new OptionParser(true);
        parser.accepts(CLI.help.name(), "Get command line help.").forHelp();
        parser.accepts(CLI.log.name(), "Log file to use.")
                .withRequiredArg().ofType(String.class).describedAs("filename");
        return parser;
    }
}
