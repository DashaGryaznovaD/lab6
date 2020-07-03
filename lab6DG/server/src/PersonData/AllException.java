package PersonData;

import java.io.Serializable;

public class AllException extends Exception implements Serializable {
    private Double number;
    public Double getNumber(){return number;}
    public AllException(String message){

        super(message);

    }

}
