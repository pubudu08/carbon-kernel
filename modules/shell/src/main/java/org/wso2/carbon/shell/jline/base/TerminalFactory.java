package org.wso2.carbon.shell.jline.base;

import jline.NoInterruptUnixTerminal;
import jline.Terminal;

/**
 * TerminalFactory represents initial jline terminal
 */
public class TerminalFactory {

    private Terminal terminal;

    public synchronized Terminal getTerminal() throws Exception{
        if(terminal==null){
            init();
        }
        return terminal;
    }

    public void init(){
        jline.TerminalFactory.registerFlavor(jline.TerminalFactory.Flavor.UNIX, NoInterruptUnixTerminal.class);
        terminal = jline.TerminalFactory.create();

    }

    public synchronized void destroy()throws Exception{
        if(terminal!=null){
            terminal.restore();
            terminal=null;

        }

    }
}
