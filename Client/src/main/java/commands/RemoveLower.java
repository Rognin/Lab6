package commands;

import client.Client;
import basic.*;
import java.io.IOException;

/**remove all elements with a value less than that of the given one*/
public class RemoveLower extends Command {
    Client client;

    public RemoveLower(Client client) {
        super(client);
        this.client = client;
    }

    @Override
    public void onCall(String args) throws IOException {
        LabWorkUserInputReader ir = new LabWorkUserInputReader(client.input);
        LabWork lw = ir.getUserInput();

        client.additionalOutputToServer = lw;
    }

    @Override
    public void getArgs(String args) throws IOException {
        super.getArgs(args);
    }

    @Override
    public String getHelp() {
        return super.getHelp();
    }
}
