package serverCommands;

import server.Server;

import java.io.IOException;

/**
 * list all the elements of the main collection
 */
public class ServerShow extends ServerCommand {
    Server server;

    public ServerShow(Server server) {
        super(server);
        this.server = server;
    }

    @Override
    public void onCall(Object additionalInput) throws IOException {
//        HashSet<main.LabWork> set = ServerTCP.getSet();
//        Iterator<main.LabWork> i = set.iterator();
//        while (i.hasNext()) ServerTCP.answer+=("\n"+i.next().toString());
        server.getSet().forEach(l -> server.answer += "\n" + l.toString());
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
