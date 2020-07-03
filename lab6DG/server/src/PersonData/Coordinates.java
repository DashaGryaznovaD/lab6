package PersonData;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Coordinates implements Serializable {
    public Double x;
    public Double y;
    public Coordinates(Double x, Double y) {
        this.x = x;
        this.y = y;
    }



    public Double getX(){
        return x;
    }
    public Double getY(){
        return y;
    }
    public void setX(Double aLong){
        this.x=x;
    }
    public void setY(Double aLong){
        this.y=y;
    }

    Coordinates (){
        this.x=x;
        this.y = y;
    }
    @Override
    public String toString() {
        return (
                "x =  " + x + ", y = "+ y);
    }
}
