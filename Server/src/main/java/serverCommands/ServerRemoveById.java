package serverCommands;

import basic.LabWork;
import server.Server;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * remove an element with the given id from the main collection
 */
public class ServerRemoveById extends ServerCommand {
    Server server;

    public ServerRemoveById(Server server) {
        super(server);
        this.server = server;
    }

    /**
     * the given id
     */
    long id;

    @Override
    public void onCall(Object additionalInput) throws IOException {

        id = (long) additionalInput;

        Set<LabWork> set = server.getSet().stream().filter((l) -> l.getId() == id).collect(Collectors.toSet());
        set.forEach(l -> server.getSet().remove(l));
        server.answer = ("success");
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
