package org.wso2.carbon.shell.console.base;

/**
 * Used to denote a class represents a command which is executable within a shell/scope or as a
 * command line process.
 */
public @interface Command {

    /**
     * This will return scope of the command
     * @return
     */
    String scope();

    /**
     * This will return name of the command
     * @return
     */
    String name();

    /**
     * This will return description of the command and
     * to generate descriptive -help
     * @return
     */
    String description();

}
