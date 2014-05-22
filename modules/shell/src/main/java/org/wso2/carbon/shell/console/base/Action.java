package org.wso2.carbon.shell.console.base;

import org.apache.felix.service.command.CommandSession;

/**
 * Action interface represents action to perform any given command
 */
public interface Action  {
    Object execute(CommandSession session)throws Exception;
}
