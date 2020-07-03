package PersonData;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;
@XmlRootElement(name="lists")
public class PersonLists implements Serializable {

    public  List<Person> PersonList;

    public List<Person> getPersonList() {
        return PersonList;
    }

    public void setPersonList(List<Person> PersonList) {
        this.PersonList = PersonList;
    }

}
