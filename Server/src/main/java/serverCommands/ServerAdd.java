package serverCommands;

import server.Server;
import basic.*;
import java.time.LocalDate;

/**add an element to the collection*/
public class ServerAdd extends ServerCommand {
    Server server;

    public ServerAdd(Server server) {
        super(server);
        this.server = server;
    }

    @Override
    public void onCall(Object additionalInput) {

        LabWork lw = (LabWork) additionalInput;
        lw.setId((long) server.getNumerOfElements()+1);
        lw.setCreationDate(LocalDate.now());
        server.getSet().add(lw);
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
