

package com.github.nwillc.fluency;

import joptsimple.OptionParser;

final public class CliOptions {
    public enum CLI {
        log,
        match,
        help
    }

    private CliOptions() {
    }

    public static OptionParser getOptions() {
        OptionParser parser = new OptionParser(true);
        parser.accepts(CLI.help.name(), "Get command line help.").forHelp();
        parser.accepts(CLI.log.name(), "Log file to use.")
                .withRequiredArg().ofType(String.class)
                .describedAs("filename")
                .required();
        parser.accepts(CLI.match.name(),"Define a match for filtering output. May pass in multiple matches.")
                .withRequiredArg().ofType(String.class)
                .describedAs("field=regex");
        return parser;
    }
}
