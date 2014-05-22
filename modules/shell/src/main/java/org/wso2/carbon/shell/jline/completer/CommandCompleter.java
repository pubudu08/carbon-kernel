package org.wso2.carbon.shell.jline.completer;

import jline.console.completer.Completer;
import jline.console.completer.StringsCompleter;
import org.apache.felix.gogo.runtime.CommandSessionImpl;
import org.apache.felix.service.command.CommandSession;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 *   CommandCompleter represents
 */
public class CommandCompleter implements Completer{

    private CommandSession commandSession;
    private Set<String > commandList = new CopyOnWriteArraySet<String>();

    public CommandCompleter(CommandSession commandSession) {
        this.commandSession = commandSession;
    }

    @Override
    public int complete(String buffer, int cursor, List<CharSequence> candidates) {
        if(commandSession!= null){
            //TODO Create a global commandSession
            //get the global command session, in this case we have to maintain a global commandSession

        }
        extractCommands();
        int result = new StringsCompleter(commandList).complete(buffer,cursor,candidates);


        return result;
    }

    private void extractCommands(){
        if(commandList.isEmpty()){
            Set<String> namesOfCommands = new HashSet<String>(
                    (Set<String>) commandSession.get(CommandSessionImpl.COMMANDS)
            );
            for(String name :namesOfCommands){
                commandList.add(name);
                if(name.indexOf(':')>0){
                    commandList.add(name.substring(0,name.indexOf(':')));

                }

            }

        }

    }
}
