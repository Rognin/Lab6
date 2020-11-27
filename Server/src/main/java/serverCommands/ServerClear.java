package serverCommands;

import server.Server;

/**clear the collection*/
public class ServerClear extends ServerCommand {
    Server server;


    public ServerClear(Server server) {
        super(server);
        this.server = server;
    }

    @Override
    public void onCall(Object additionalInput) {
        server.getSet().clear();
        server.answer="success";
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
