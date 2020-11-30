package serverCommands;

import server.Server;

import java.io.IOException;

public class ServerCommand {
    Server server;

    public ServerCommand(Server server) {
        this.server = server;
    }

    private static final long serialVersionUID = 1;

    /**
     * the main method that determines how a command functions
     * @param additionalInput*/
    public void onCall(Object additionalInput) throws IOException {
    }

    /**used to process the argument*/
    public void getArgs(String args) {
    }

    /**
     * used to get command-specific help, currently not implemented
     * @return the helping information
     * */
    public String getHelp() {
        return "";
    }
}
