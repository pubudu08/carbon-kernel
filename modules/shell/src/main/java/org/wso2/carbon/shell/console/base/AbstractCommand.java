package org.wso2.carbon.shell.console.base;

import org.apache.felix.service.command.CommandSession;
import org.apache.felix.service.command.Function;

import java.util.List;

/**
 * Created by pubudu on 5/22/14.
 */
public abstract class AbstractCommand implements Function{

    public abstract Action createNewAction();

    @Override
    public Object execute(CommandSession session, List<Object> arguments) throws Exception {
        Action action = createNewAction();
        return action.execute(session);
    }
}
