package org.wso2.carbon.shell.console.base;

import org.apache.felix.service.command.CommandSession;

import java.util.Objects;

/**
 * Created by pubudu on 5/22/14.
 */
public abstract class AbstractAction implements Action {

    protected CommandSession commandSession;

   public Object execute(CommandSession commandSession) throws Exception{
       this.commandSession = commandSession;
       return doExecute();

   }
    public abstract Object doExecute() throws Exception;

}
