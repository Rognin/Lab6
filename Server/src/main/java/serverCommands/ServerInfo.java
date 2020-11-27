package serverCommands;

import server.Server;

import java.io.IOException;

/**print information about the main collection*/
public class ServerInfo extends ServerCommand {
    Server server;

    public ServerInfo(Server server) {
        super(server);
        this.server = server;
    }

    @Override
    public void onCall(Object additionalInput) throws IOException {

        server.answer=("class: " + server.getSet().getClass() + "\n" +
                "initialization date: "+ server.date.toString()+"\n"+
                "number of elements:"+ server.getSet().size());
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
