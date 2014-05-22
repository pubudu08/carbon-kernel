package org.wso2.carbon.shell.jline.base;

import jline.Terminal;
import jline.console.ConsoleReader;
import org.apache.felix.gogo.runtime.Parser;
import org.apache.felix.service.command.CommandProcessor;
import org.apache.felix.service.command.CommandSession;
import org.apache.felix.service.command.Converter;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.concurrent.BlockingQueue;

/**
 * Main console implementation
 */
public class Console implements Runnable{

    private CommandProcessor commandProcessor;
    private Terminal terminal;
    protected CommandSession session;
    private ConsoleReader reader;
    private Thread thread;
    private InputStream consoleInput;
    private InputStream in;
    private PrintStream out;
    private PrintStream err;
    private Thread pipe;
    private boolean running;
    private boolean interrupt;
    private BlockingQueue<Integer> queue;


    public CommandSession getSession() {
        return session;
    }

    private void interrupt() {
        interrupt = true;
        thread.interrupt();
    }
    @Override
    public void run() {
        thread = Thread.currentThread();
        running = true;
        pipe.start();
        while (running) {
            try {
                String command = readAndParseCommand();
                if (command == null) {
                    break;
                }
                Object result = session.execute(command);
                if (result != null) {
                    session.getConsole().println(session.format(result, Converter.INSPECT));
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }

    protected boolean getBoolean(String name) {
        Object s = session.get(name);
        if (s == null) {
            s = System.getProperty(name);
        }
        if (s == null) {
            return false;
        }
        if (s instanceof Boolean) {
            return (Boolean) s;
        }
        return Boolean.parseBoolean(s.toString());
    }

    private String readAndParseCommand() throws IOException {
        String command = null;
        boolean loop = true;
        boolean first = true;
        while (loop) {
            //checkInterrupt();
            String line = reader.readLine();

            if (line == null)
            {
                break;
            }
            if (command == null) {
                command = line;
            } else {
                command += " " + line;
            }
            if (reader.getHistory().size()==0) {
                reader.getHistory().add(command);
            } else {
                if (command.length() > 0 && !" ".equals(command)) {
                    reader.getHistory().replace(command);
                }
            }
            try {
                new Parser(command).program();
                loop = false;
            } catch (Exception e) {
                loop = true;
                first = false;
            }
        }
        return command;
    }

}
