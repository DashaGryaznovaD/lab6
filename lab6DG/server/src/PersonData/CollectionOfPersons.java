package PersonData;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.SocketException;
import java.time.LocalDateTime;
import java.util.*;
public class CollectionOfPersons implements Serializable {
  public   static ArrayDeque<Person> MyCollection = new ArrayDeque<Person>();
    public static LocalDateTime time = LocalDateTime.now();
    public CollectionOfPersons() throws FileNotFoundException {
    }


    public void CollectionWork() throws FileNotFoundException, AllException, JAXBException {

        try{
            String path = getFilePathForSave1();
            if (path == null)
                System.out.println("----\nПуть не указан, дальнейшая работа не возможна.\n----");
            else {

                Scanner scan = new Scanner(new File(path)); // считывание данных из файла (по заданию: чтение данных из файла необходимо реализовать с помощью класса java.util.Scanner)
                String stringData = new String();
                while (scan.hasNext()) {
                    stringData = stringData + scan.nextLine();
                }
                scan.close();
                try {
                    StringReader sr = new StringReader(stringData); // преобразование xml строки в объект person
                    JAXBContext jaxbContext1 = JAXBContext.newInstance(PersonLists.class);
                    Unmarshaller unmarshaller = jaxbContext1.createUnmarshaller();
                    PersonLists lists1 = (PersonLists) unmarshaller.unmarshal(sr);
                    MyCollection.addAll(lists1.PersonList);
                } catch (JAXBException e) {
                    e.printStackTrace();
                }

            }
        }
        catch (NoSuchElementException e){
            e.getMessage();
        } catch (FileNotFoundException e) {
            System.out.println("Имя файла введено неправильно или права на чтения файла заданы неправильно");
            Scanner scanner = new Scanner(System.in);
            int u =0;
            while ((u!=1)&&(u!=2)) {
                System.out.println("Выберите: "+ "\n" + "1.Завершить программу чтобы поменять права доступа к файлу;" + "\n" + "2.Поменять имя файла");
                u = scanner.nextInt();
            }
            if (u==1) {
                System.out.println("Возвращайтесь с правильными правами на чтение!");
                System.exit(0);
            } else CollectionWork2();
        }




    }
    public void infoCollection(){

        System.out.println("Размерность коллекции равна "+ MyCollection.size() +"\n"+ "Тип коллекции -  "+ MyCollection.getClass() +"\n");


    }
    private static String getFilePathForSave1(){
        String path = System.getenv("FirstFile");
        if (path == null){
            return ("----\nПуть через переменную окружения duo не указан\n----");
        } else {
            return path;
        }
    }


    public static void showColllection(){
        System.out.println(MyCollection);

    }
    public void CollectionWork2() throws FileNotFoundException, AllException, JAXBException {

        try{

            System.out.println("Введите имя файла в ручную");
            Scanner scant = new Scanner(System.in);
            String nameFile = scant.nextLine();
            Scanner scan = new Scanner(new File(nameFile));
            String stringData = new String();
            while (scan.hasNext()) {
                stringData = stringData + scan.nextLine();
            }
            scan.close();
            try {
                StringReader sr = new StringReader(stringData); // преобразование xml строки в объект person
                JAXBContext jaxbContext1 = JAXBContext.newInstance(PersonLists.class);
                Unmarshaller unmarshaller = jaxbContext1.createUnmarshaller();
                PersonLists lists1 = (PersonLists) unmarshaller.unmarshal(sr);
                MyCollection.addAll(lists1.PersonList);
            } catch (JAXBException e) {
                e.printStackTrace();
            }


        }
        catch (NoSuchElementException e){
            e.getMessage();
        } catch (FileNotFoundException e) {
            System.out.println("Имя файла снова введено неверно");
            CollectionWork2();

        }



    }


}