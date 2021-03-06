package server;

// A Java program for a Serverside

import serverCommands.*;
import basic.*;

import java.io.*;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Server {

    public Server() {
        date = LocalDate.now();
        socket = null;
        server = null;
        set = Collections.synchronizedSet(new HashSet<LabWork>());
        answer = "";
        commands = new HashMap<String, ServerCommand>();
    }

    public LocalDate date;
    //initialize socket and input stream
    Socket socket;
    ServerSocket server;
//    public static BufferedReader checkForCommand = null;

    /**
     * the main collection with Labworks
     */
    private Set<LabWork> set;

    public Set<LabWork> getSet() {
        return set;
    }

    public int getNumerOfElements() {
        return set.size();
    }

    public volatile String answer;

    public File inputFile;

    public HashMap<String, ServerCommand> commands;

    /**
     * a method for getting the extension of a file from it's name
     *
     * @param fileName
     * @return extension
     */
    public String getFileExtensionFromName(String fileName) {
        // если в имени файла есть точка и она не является первым символом в названии файла
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            // то вырезаем все знаки после последней точки в названии файла, то есть ХХХХХ.txt -> txt
            return fileName.substring(fileName.lastIndexOf(".") + 1);
            // в противном случае возвращаем заглушку, то есть расширение не найдено
        else return "";
    }

    public boolean checkAbilityToLoad(String fileName) {

        if (fileName == null) {
            System.out.println("an environment variable SET_PATH was expected");
            fileName = "theSet.csv";
            System.out.println("the file name has been set to the default (theSet.csv)");
        }

        boolean load = true;
        boolean isCsv = true;

        if (!getFileExtensionFromName(fileName).equals("csv")) {
            System.out.println("the file specified in the SET_PATH environment variable is not .csv (nothing was loaded)");
            load = false;
            isCsv = false;
        }

        if (!isCsv) {
            fileName += ".csv";
            System.out.println("we will try to create a save-load file named " + fileName);
            inputFile = new File(fileName);
            try {
                inputFile.createNewFile();
                System.out.println("success");
            } catch (IOException e) {
                System.out.println("looks like we can't create the file");
                load = false;
            }
            System.out.println("please change the SET_PATH environment variable to " + fileName);
        } else {
            inputFile = new File(fileName);
            if (!inputFile.exists()) {
                try {
                    inputFile.createNewFile();
                    System.out.println("there was no file by the name specified in the SET_PATH environment variable so we created a new one");
                } catch (IOException e) {
                    System.out.println("the file specified in the SET_PATH environment variable doesn't exist and we can't create a new one");
                    load = false;
                }
            }
        }
        return load;
    }

    public void load(File inputFile) throws FileNotFoundException {

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inputFile));

        BufferedReader br = new BufferedReader(new InputStreamReader(bis));

        while (true) {
            String input = null;
            try {
                input = br.readLine();
            } catch (IOException e) {
                System.out.println("IO exception while trying to load the collection, probably can't read the file");
            }

            if (input == null || input.isEmpty()) break;
            String[] currentInput = input.split(",");
            String[] date = currentInput[4].split("-");
            LocalDate theDate = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
            set.add(new LabWork(Long.parseLong(currentInput[0]), currentInput[1], new Coordinates(Long.parseLong(currentInput[2]), Integer.parseInt(currentInput[3])), theDate, Double.parseDouble(currentInput[5]), currentInput[6], Difficulty.valueOf(currentInput[7]), new Person(currentInput[8], Integer.parseInt(currentInput[9]), Integer.parseInt(currentInput[10]), currentInput[11], Color.valueOf(currentInput[12]))));
        }
    }


    public void doAllTheWork() {

        CommandPack commandPack;

        commands.put("help", new ServerHelp(this));
        commands.put("info", new ServerInfo(this));
        commands.put("show", new ServerShow(this));
        commands.put("add", new ServerAdd(this));
        commands.put("update", new ServerUpdate(this));
        commands.put("remove_by_id", new ServerRemoveById(this));
        commands.put("clear", new ServerClear(this));
        commands.put("execute_script", new ServerExecuteScript(this));
        commands.put("add_if_min", new ServerAddIfMin(this));
        commands.put("remove_lower", new ServerRemoveLower(this));
        commands.put("history", new ServerHistory(this));
        commands.put("max_by_id", new ServerMaxById(this));
        commands.put("filter_less_than_description", new ServerFilterLessThanDescription(this));
        commands.put("filter_greater_than_description", new ServerFilterGreaterThanDescription(this));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int port = -1;

        while (port < 0) {
            System.out.println("enter the port you would like to use, enter 'd' for the default (5000)");
            try {
                String desiredPort = br.readLine();
                if (desiredPort.equals("d")) {
                    port = 5000;
                    System.out.println("port set to default (5000)");
                } else {
                    int tmpPort = Integer.parseInt(desiredPort);
                    if (tmpPort < 0 || tmpPort > 65535) {
                        System.out.println("the port should be a number between 0 and 65535");
                    } else {
                        port = tmpPort;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("please enter either a number or 'd' for default");
            }
        }

        Runnable r = () -> {
            try {
                while (true) {
                    if (br.ready()) {
                        String input = br.readLine();
                        if (input.equals("exit")) {
                            ServerCommand save = new ServerSave(this);
                            save.onCall("");
                            System.exit(0);
                        }
                        if (input.equals("")) continue;
                        if (input.equals("save")) {
                            ServerCommand save = new ServerSave(this);
                            save.onCall("");
                        } else {
                            System.out.println("There is no such command");
                        }
                    }
                }
            } catch (IOException e) {
//                System.out.println("IOex while reading input from console");
            }
        };
        Thread t = new Thread(r);
        t.start();

        String fileName = System.getenv("SET_PATH");
        boolean load = checkAbilityToLoad(fileName);
        try {
            if (load) load(inputFile);
        } catch (FileNotFoundException e) {
            System.out.println("collection file not found");
        }


        try {
            server = new ServerSocket(port);
            System.out.println("Server started");
            while (true) {
                System.out.println("Waiting for a client ...");
                socket = server.accept();
                ClientHandler ch = new ClientHandler(this);
                Thread clientHandlerThread = new Thread(ch);
                clientHandlerThread.start();
            }

        } catch (IOException e) {
//            e.printStackTrace();
        }


        ServerCommand save = new ServerSave(this);
        try {
            save.onCall("");
        } catch (
                IOException e) {
            System.out.println("couldn't save");
        }

    }

    public static void main(String[] args) {
        Server server = new Server();
        server.doAllTheWork();
    }
}