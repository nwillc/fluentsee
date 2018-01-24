module com.github.nwillc.fluentsee {

    exports com.github.nwillc.fluentsee;
    opens com.github.nwillc.fluentsee to jdk.contract.tests;
    exports com.github.nwillc.fluentsee.util;
    opens com.github.nwillc.fluentsee.util to jdk.contract.tests;

    requires tinylog;
    requires jopt.simple;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jdk8;
    
}
