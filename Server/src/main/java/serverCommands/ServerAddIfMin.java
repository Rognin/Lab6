package serverCommands;

import server.Server;
import basic.*;
import java.io.IOException;
import java.time.LocalDate;

/**
 * add an element to the collection if it's the smallest one
 */
public class ServerAddIfMin extends ServerCommand {
    Server server;

    public ServerAddIfMin(Server server) {
        super(server);
        this.server = server;
    }

    @Override
    public void onCall(Object additionalInput) throws IOException {

        if (additionalInput != null) {

            LabWork lw = (LabWork) additionalInput;
            lw.setId((long) (server.getSet().size() + 1));
            lw.setCreationDate(LocalDate.now());
            boolean flag = true;
            for (LabWork labwork : server.getSet()) {
                if (labwork.compareTo(lw) < 0) flag = false;
            }

            if (flag) {
                server.getSet().add((LabWork) additionalInput);
                server.answer = "success";
            } else {
                server.answer = "the element wasn't minimal";
            }
        }
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
