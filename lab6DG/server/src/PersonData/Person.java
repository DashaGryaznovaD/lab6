package PersonData;

import java.io.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.xml.bind.annotation.*;
import javax.xml.parsers.ParserConfigurationException;
public class Person implements Serializable{
    private long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Double height; //Поле может быть null, Значение поля должно быть больше 0
    private Color color; //Поле не может быть null
    private Color1 color1; //Поле не может быть null
    private Country country; //Поле может быть null
    private Location location; //Поле не может быть null

    private java.util.Date creationDate1;
    private long key;
    private String login;
    public Person(String name, long id, Coordinates coordinates, Double height,long key,  Color color, Color1 color1, Country country, Location location, String login) throws AllException {
        this.id=id;
        this.key=key;



        this.name=name; if ((name.trim().length() == 0) || (name==null) ) throw new AllException("Ошибка, строка имя персонажа не может быть пустым");
        this.coordinates = coordinates; if (coordinates==null) throw new AllException("Поле не может быть null");
        this.height = height; if (height==null) throw new AllException("Поле не может быть null");
        if (height<0) throw new AllException("Рост не может быть отрицательным");
        this.color = color; if (color ==null) throw new AllException("Поле не может быть null");
        this.color1 = color1; if (color1 ==null) throw new AllException("Поле не может быть null");
        this.country = country; if (country ==null) throw new AllException("Поле не может быть null");
        this.location = location; if (location==null) throw new AllException("Поле не может быть null");
        // this.creationDate = LocalDateTime.now(); if (creationDate==null) throw new AllException("Поле не может быть null");
        this.creationDate1 = java.util.Date.from(Instant.now());
        this.login = login;

    }
    public Person(String name, Coordinates coordinates, Double height, Color color, Color1 color1, Country country, Location location) throws AllException {
        this.id=randomID();
        this.key=randomKey();



        this.name=name; if ((name.trim().length() == 0) || (name==null) ) throw new AllException("Ошибка, строка имя персонажа не может быть пустым");
        this.coordinates = coordinates; if (coordinates==null) throw new AllException("Поле не может быть null");
        this.height = height; if (height==null) throw new AllException("Поле не может быть null");
        if (height<0) throw new AllException("Рост не может быть отрицательным");
        this.color = color; if (color ==null) throw new AllException("Поле не может быть null");
        this.color1 = color1; if (color1 ==null) throw new AllException("Поле не может быть null");
        this.country = country; if (country ==null) throw new AllException("Поле не может быть null");
        this.location = location; if (location==null) throw new AllException("Поле не может быть null");
        // this.creationDate = LocalDateTime.now(); if (creationDate==null) throw new AllException("Поле не может быть null");
        this.creationDate1 = java.util.Date.from(Instant.now());


    }
    public java.util.Date getCreationDate1() {return creationDate1;}
    public void setCreationDate1(java.util.Date creationDate1){
        this.creationDate1 = creationDate1;
    }
    @XmlElement
    public String getName() {
        return name;
    }
    public void setName (String name){
        this.name = name;
    }

    @XmlElement
    public Coordinates getCoordinates(){
        return coordinates;
    }
    public void  setCoordinates(Coordinates coordinates){ this.coordinates = coordinates;}
    @XmlElement
    public long getId() {return id;}
    public void setId(long id) {this.id=id;}
    @XmlElement
    public long getKey() {return key;}
    public void setKey(long key) {this.key=key;}

    @XmlElement
    public Country getCountry(){
        return country;
    }
    public void setCountry(Country country) {
        this.country = country;
    }

    @XmlElement
    public Double getHeight(){
        return  height;
    }
    public void setHeight(Double height){
        this.height = height;
    }
    @XmlElement
    public Color getColor(){
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    @XmlElement
    public Color1 getColor1(){
        return color1;
    }
    public void setColor1(Color1 color1){
        this.color1 = color1;
    }
    @XmlElement
    public Location getLocation(){
        return location;
    }
    public void setLocation(Location location) {this.location = location;}
    public Person() throws IOException, ParserConfigurationException, AllException {

    }
    public long randomID(){
        this.id = (long) (Math.random()*(1000+1));
        return this.id;
    }
    public long randomKey(){
        this.key = (long) (Math.random()*(100+1));
        return this.key;
    }
    public String DataForm() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        String formattedDateTime = currentDateTime.format(formatter);
        return formattedDateTime;
    }
    public void removeByKey() throws IOException, AllException {
        long idScanner = -20000;
        int k = 0;
        System.out.println("Введите ключ элемента (тип Long), чтобы удалить элемент с этим ключом");
        try {
            Scanner scan = new Scanner(System.in);
            while ((idScanner<=0)||(idScanner>100)) {
                System.out.println("что ключ должен быть целочисленным и содержаться в отрезке от 1 до 100");
                try{
                    idScanner = scan.nextLong();} catch (NoSuchElementException e){
                    System.out.println("ошибка NoSuchElement, повторите операцию ввода");
                    removeByKey();}
            }
            Iterator<Person> itr = CollectionOfPersons.MyCollection.iterator();
            while(itr.hasNext()){
                Person pers33 = itr.next();
                if(pers33.getKey() == idScanner){
                    CollectionOfPersons.MyCollection.remove(pers33);
                    System.out.println("Элемент коллекции был успешно удален.");
                    k = k+1;
                } }
            if (k == 0){
                System.out.println("Элемента с введенным вами ключом в коллекции нет.");
            }} catch (InputMismatchException e) {
            System.out.println("Ошибка ввода, повторите");
            removeByKey();
        }
    }
    public void compare()  {
        long idScanner = -10;
        int k = 0;
        int t = CollectionOfPersons.MyCollection.size();
        int t1 = CollectionOfPersons.MyCollection.size();
        System.out.println("Введите ключ элемента (тип Long), чтобы удалить из коллекции элементы, ключи которых превышают заданный");
        try {

            Scanner scan = new Scanner(System.in);
            while ((idScanner<=0)||(idScanner>100)) {
                System.out.println("ключ должен быть целочисленным и содержаться в отрезке от 1 до 100");
                try{
                    idScanner = scan.nextLong();} catch (NoSuchElementException e){
                    System.out.println("ошибка NoSuchElement, повторите операцию ввода");
                    compare();}
            }
            Iterator<Person> itr = CollectionOfPersons.MyCollection.iterator();


            Iterator<Person> itr1 = CollectionOfPersons.MyCollection.iterator();
            while(itr1.hasNext()){
                Person pers222 = itr1.next();
                if(pers222.getKey() > idScanner){
                    itr1.remove();
                    t= t-1;
                }
            }
            if (t1!=t) System.out.println("Коллекция была обновлена."); else System.out.println("В коллекции нет элемента, превышающего введенный вами.");
        } catch (InputMismatchException e){
            System.out.println("Ошибка ввода, повторите");
            compare();}

    }
    public void compare2()  {
        long idScanner = -10;
        int k = 0;
        int t = CollectionOfPersons.MyCollection.size();
        int t1 = CollectionOfPersons.MyCollection.size();
        System.out.println("Введите ключ элемента (тип Long), чтобы удалить из коллекции элементы, ключи которых меньше заданного");
        try {

            Scanner scan = new Scanner(System.in);
            while ((idScanner<=0)||(idScanner>100)) {
                System.out.println("ключ должен быть целочисленным и содержаться в отрезке от 1 до 100");
                try{
                    idScanner = scan.nextLong();} catch (NoSuchElementException e){
                    System.out.println("ошибка NoSuchElement, повторите операцию ввода");
                    compare2();}}
            Iterator<Person> itr = CollectionOfPersons.MyCollection.iterator();


            Iterator<Person> itr1 = CollectionOfPersons.MyCollection.iterator();
            while(itr1.hasNext()){
                Person pers222 = itr1.next();
                if(pers222.getKey() < idScanner){
                    itr1.remove();
                    t= t-1;
                }
            }
            if (t1!=t) System.out.println("Коллекция была обновлена."); else System.out.println("В коллекции нет элемента, меньшего чем введенный вами.");}

        catch (InputMismatchException e){
            System.out.println("Ошибка ввода, повторите");
            compare2();}

    }
    public void sumOfHeight(){
        double sum = 0.0;
        Iterator<Person> itr = CollectionOfPersons.MyCollection.iterator();
        while(itr.hasNext()){
            sum = sum +itr.next().getHeight();

        }
        System.out.println("Если сложить значения роста каждого существа, то получится " + sum + " см");
    }
    public void maxEye() throws ParserConfigurationException, IOException, AllException {
        Person persBrown = new Person();
        persBrown = null;
        Iterator<Person> itr = CollectionOfPersons.MyCollection.iterator();
        while(itr.hasNext()){
            Person perst = itr.next();
            if (perst.getColor1() == Color1.WHITE) {
                persBrown = perst;
            }


        }
        if (persBrown == null){
            System.out.println("В коллекции нет элемента с максимальным значением цвета глаз");
        } else
            System.out.println(persBrown);
    }
    public void stringFishing(){
        String nameScanner = "hhh222" ;
        int k = 0;
        System.out.println("Введите несколько символов; На экран будут выведены существа, названия которых содержат эти символы");
        try {

            Scanner scan = new Scanner(System.in);
            while ((nameScanner.length()<1)||(nameScanner.equals("hhh222"))) {
                System.out.println("Строка должна быть длиной хотя бы в один символ!");
                try{
                    nameScanner = scan.nextLine();} catch (NoSuchElementException e){
                    System.out.println("ошибка NoSuchElement, повторите ввод");
                    stringFishing();}
            }
            Iterator<Person> itr2 = CollectionOfPersons.MyCollection.iterator();
            while (itr2.hasNext()) {
                Person persona = itr2.next();
                String s = persona.name;
                boolean isContain1 = s.contains(nameScanner);
                if (isContain1) {System.out.println(persona);
                    k=k+1;}
            }
            if (k == 0) System.out.println("В коллекции нет элемента, имя которого оодержит введенные вами символы");}
        catch (InputMismatchException e ){
            System.out.println("Ошибка ввода, повторите");
            stringFishing();
        }
    }
    @Override
    public String toString() {
        return (  " Имя: " + name + "\n" +
                "Рост: " + height + "\n" + "Цвет волос: " + color + "\n" + "Цвет глаз: " + color1 + "\n" + "Гражданство " + country +  "\n"+
                "id: " + id + "\n" + "key: " + getKey() + "\n"+
                "Координаты: " + coordinates + "\n" + location + "\n");
    }

}