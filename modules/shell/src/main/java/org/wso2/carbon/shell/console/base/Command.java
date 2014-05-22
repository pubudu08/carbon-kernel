package org.wso2.carbon.shell.console.base;


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
     * will help the generate -help
     * @return
     */
    String description();

}
