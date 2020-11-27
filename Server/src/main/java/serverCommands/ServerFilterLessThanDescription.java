package serverCommands;

import server.Server;

import java.io.IOException;
import java.util.stream.Collectors;

/**print all the element with a description value less than the given one*/
public class ServerFilterLessThanDescription extends ServerCommandWithArgs {
    Server server;

    public ServerFilterLessThanDescription(Server server) {
        super(server);
        this.server = server;
    }

    /**the given description*/
    String description;

    @Override
    public void onCall(Object additionalInput) throws IOException {
        description = (String) additionalInput;
        server.getSet().stream().filter((l) -> l.getDescription().compareTo(description) < 0).collect(Collectors.toList()).forEach((p) -> server.answer += "\n" + p.toString());
    }

    @Override
    public void getArgs(String args) {
        super.getArgs(args);
        description = super.args;
    }

    @Override
    public String getHelp() {
        return super.getHelp();
    }
}
