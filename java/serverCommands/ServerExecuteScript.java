package serverCommands;

import server.Server;

import java.io.IOException;

/**execute a script from a file*/
public class ServerExecuteScript extends ServerCommandWithArgs {
    Server server;

    public ServerExecuteScript(Server server) {
        super(server);
        this.server = server;
    }

    /**the parameter*/
    String args = "";

    /**the name of the file with a script*/
    String fileName = "";

    @Override
    public void onCall(Object additionalInput) throws IOException {
        }

    @Override
    public void getArgs(String args) {
        super.getArgs(args);
    }

    @Override
    public String getHelp() {
        return super.getHelp();
    }
}
