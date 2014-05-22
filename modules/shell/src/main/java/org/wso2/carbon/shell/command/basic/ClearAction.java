package org.wso2.carbon.shell.command.basic;

import org.wso2.carbon.shell.console.base.AbstractAction;
import org.wso2.carbon.shell.console.base.Command;

/**
 * ClearAction class represents the command to clear console buffer
 */
@Command(scope = "console",name = "clear",description = "clears console buffer")
public class ClearAction extends AbstractAction {
    @Override
    public Object doExecute() throws Exception {
        System.out.print("\33[2J");
        System.out.flush();
        System.out.print("\33[1;1H");
        System.out.flush();
        return null;
    }
}
