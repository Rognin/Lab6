package serverCommands;

import basic.LabworkByIdComparator;
import server.Server;

import java.io.IOException;

/**
 * print the information about the element in the main collection with the greatest id value
 */
public class ServerMaxById extends ServerCommand {
    Server server;

    public ServerMaxById(Server server) {
        super(server);
        this.server = server;
    }

    @Override
    public void onCall(Object additionalInput) throws IOException {
        server.answer = server.getSet().stream().max(new LabworkByIdComparator()).get().toString();
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
