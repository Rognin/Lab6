package serverCommands;

import server.Server;

import java.io.IOException;

/**print the last 6 commands entered*/
public class ServerHistory extends ServerCommand {
    Server server;

    public ServerHistory(Server server) {
        super(server);
        this.server = server;
    }

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
