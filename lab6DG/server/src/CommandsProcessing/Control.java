package CommandsProcessing;
import AnswerSender.ServerAnswerNew;
import PersonData.AllException;
import AnswerSender.CommandToObjectServer;
import PersonData.CollectionOfPersons;
import PersonData.AllException;
import java.io.IOException;
import java.net.SocketException;
import java.sql.SQLException;
import static CommandsProcessing.Commands.*;
import static CommandsProcessing.Receiver.*;
import AnswerSender.ServerAnswerNew;
import PersonData.AllException;
import AnswerSender.CommandToObjectServer;
import java.io.IOException;
import java.sql.SQLException;
import static CommandsProcessing.Commands.*;
import static CommandsProcessing.Receiver.*;
public class Control extends Thread{
    Commands com = new Commands();
    Thread thread;
    /*private String history;
    int k =0;
    int yuy =0;*/

    CollectionOfPersons col = new CollectionOfPersons();

    public Control() throws IOException, AllException {
    }

    public void run () {
        try {
            sarray = s.trim().split(" ", 2);
        switch (sarray[0]) {
            case "help":
                CommandToObjectServer help = new CommandToObjectServer("help", com.help());
                ServerAnswerNew.commandToObjectServers.addLast(help);
                new ServerAnswerNew();
                break;
            case "info":
                CommandToObjectServer info = new CommandToObjectServer("info", com.info() );
                ServerAnswerNew.commandToObjectServers.addLast(info);
                new ServerAnswerNew();
                break;
            case "show":
                CommandToObjectServer show = new CommandToObjectServer("show", com.show());
                ServerAnswerNew.commandToObjectServers.addLast(show);
                new ServerAnswerNew();
                break;

            case "updateId":
                CommandToObjectServer updateId = new CommandToObjectServer("updateId", String.valueOf(com.updateId(g, person)));//эти переменные считываются программой как нулевые
                ServerAnswerNew.commandToObjectServers.addLast(updateId);
                new ServerAnswerNew();
                break;
            case "insertElement":
                CommandToObjectServer insertElement = new CommandToObjectServer("insertElement", com.insertElement(person));
                ServerAnswerNew.commandToObjectServers.addLast(insertElement);
                new ServerAnswerNew();
                break;
            case "removeByKey":
                CommandToObjectServer removeByKey = new CommandToObjectServer("removeByKey", com.removeByKey(g));
                ServerAnswerNew.commandToObjectServers.addLast(removeByKey);
                new ServerAnswerNew();
                break;
            case "removeGreater":
                CommandToObjectServer removeGr = new CommandToObjectServer("removeGreater", com.removeGreater(g));
                ServerAnswerNew.commandToObjectServers.addLast(removeGr);
                new ServerAnswerNew();
                break;
            case "removeLower":
                CommandToObjectServer removeLow = new CommandToObjectServer("removeLower", com.removeLower(g));
                ServerAnswerNew.commandToObjectServers.addLast(removeLow);
                new ServerAnswerNew();
                break;

            case "clear":
                CommandToObjectServer clear = new CommandToObjectServer("clear", com.clear());
                ServerAnswerNew.commandToObjectServers.addLast(clear);
                new ServerAnswerNew();
                break;
            case "filterName":
                CommandToObjectServer sbn = new CommandToObjectServer("filterName", com.filterName(name));
                ServerAnswerNew.commandToObjectServers.addLast(sbn);
                new ServerAnswerNew();
                break;
            case "sumOfHeight":
                CommandToObjectServer cbr = new CommandToObjectServer("sumOfHeight", com.sumOfHeight());
                ServerAnswerNew.commandToObjectServers.addLast(cbr);
                new ServerAnswerNew();
                break;
            case "history":
                CommandToObjectServer hist = new CommandToObjectServer("history", historyR);
                ServerAnswerNew.commandToObjectServers.addLast(hist);
                new ServerAnswerNew();
                break;

        } } catch (IOException | SQLException e){
            e.printStackTrace();
        }


    }}
