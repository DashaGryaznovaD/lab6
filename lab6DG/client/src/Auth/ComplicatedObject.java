package Auth;
import PersonData.Person;

import java.io.Serializable;
public class ComplicatedObject implements Serializable {
    private String command;
    private Person person;
    private long id;
    private Long param;
    private long p;
    private String history;
    private String name;
    ComplicatedObject(String command){
        this.command = command;
    }
    ComplicatedObject(String command, Person person){
        this.command = command;
        this.person = person;
    }

    ComplicatedObject(String command, long id, Person person){
        this.command = command;
        this.id = id;
        this.person = person;
    }
    ComplicatedObject(String command,String name ){
        this.command = command;
        this.name = name;
    }
    ComplicatedObject(String command, long id){
        this.command = command;
        this.id = id;
    }
    public String getCommand(){
        return command;
    }
    public Person getPerson(){
        return person;
    }
    public Long getParam(){return param;}
    public long getId(){
        return id;
    }
    public long getP(){return p;}
    public String getHistory(){return history;}
    public String getName () {return name;}
    @Override
    public String toString() {
        return ("command: " + command + "\n" );
    }
}