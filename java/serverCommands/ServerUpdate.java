package serverCommands;

import basic.LabWork;
import server.Server;
import basic.*;
import java.io.IOException;
import java.time.LocalDate;

/**
 * update the value of the element with the given id
 */
public class ServerUpdate extends ServerCommand {
    Server server;

    public ServerUpdate(Server server) {
        super(server);
        this.server = server;
    }

    /**
     * the given id
     */
    long id;
    UpdateObjectsPack uop;

    @Override
    public void onCall(Object additionalInput) throws IOException {

        uop = (UpdateObjectsPack) additionalInput;
        LabWork lw = uop.getLw();
        id = uop.getId();
        lw.setId(id);
        lw.setCreationDate(LocalDate.now());

        boolean flag = server.getSet().stream().anyMatch(l -> l.getId() == id);

        if (flag) {
            LabWork labWork = server.getSet().stream().filter(l -> l.getId() == id).findAny().get();
            server.getSet().remove(labWork);
            server.getSet().add(lw);
            server.answer = "success";
        } else {
            server.answer = ("There is no element with this id");
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
