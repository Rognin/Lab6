/**command child created to give all commands with a parameter a common processing method*/
package serverCommands;

import server.Server;

public class ServerCommandWithArgs extends ServerCommand {
    Server server;

    public ServerCommandWithArgs(Server server) {
        super(server);
        this.server = server;
    }

    private static final long serialVersionUID = 1;

    /**the value is the argument*/
    String args = "";

    /**
     * used to process the argument and account for the case when the argument is missing
     * @param args the argument
     * */
    @Override
    public void getArgs(String args) {

    }
}
