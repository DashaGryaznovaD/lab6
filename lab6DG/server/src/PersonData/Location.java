package PersonData;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Location implements Serializable {
    public Integer x;
    public Integer y; //Поле не может быть null
    public Integer z; //Поле не может быть null
    public Location(Integer x, Integer y, Integer z) throws AllException{
        this.x=x;
        this.y = y;
        this.z = z;
    }


    public Location(){
        this.x=x;
        this.y = y;
        this.z = z;
    }
    public Integer getX() {
        return x;
    }
    public void setX (Integer x){
        this.x = x;
    }
    public Integer getY() {
        return y;
    }
    public void setY (Integer y){
        this.y = y;
    }
    public Integer getZ() {
        return z;
    }
    public void setZ (Integer z){
        this.z = z;
    }
    @Override
    public String toString() {
        return ("Месторасположение в пространстве : x = " + x + ", y = "+ y+ ", z = " + z + "\n");
    }
}
