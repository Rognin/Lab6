package serverCommands;

import basic.LabWork;
import server.Server;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * remove all elements with a value less than that of the given one
 */
public class ServerRemoveLower extends ServerCommand {
    Server server;

    public ServerRemoveLower(Server server) {
        super(server);
        this.server = server;
    }

    @Override
    public void onCall(Object additionalInput) throws IOException {
        LabWork lw = (LabWork) additionalInput;

        Set<LabWork> set = server.getSet().stream().filter((l) -> l.compareTo(lw) < 0).collect(Collectors.toSet());
        set.forEach(l -> server.getSet().remove(l));
        server.answer = "success";
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
