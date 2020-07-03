package Auth;
import PersonData.*;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;

public class InterScanner {

    public static Person personCreator() throws AllException {
        Color color = null;
        int t = 0;
        Color1 color1 = null;
        Country country = null;
        Scanner scan = new Scanner(System.in);
        try{
            t = CollectionOfPersons.MyCollection.size();
            long keyScanner = 0;
            while ((keyScanner<=0)||(keyScanner>=101)) {
                System.out.println("Введите ключ существа. Значение должно быть целочисленным и содержаться в отрезке от 1 до 100");
                keyScanner = scan.nextLong();
            }

            String nameScanner = "";
            while (nameScanner.isEmpty()) {
                System.out.println("Введите имя существа");
                Scanner scan22 = new Scanner(System.in);
                nameScanner = scan22.nextLine();
            }

            Double heightScanner = 0.0;
            while ((heightScanner<=0.0)||(heightScanner>1000)) {
                System.out.println("Введите рост персонажа в см (Double), если после ввода вам будет предложен еще один ввод роста, то ваше предыдущее значение некорректно ");

                heightScanner = scan.nextDouble();

            }
            Integer ColorScanner = 0;
            while ((ColorScanner!=1)&&(ColorScanner!=2)&&(ColorScanner!=3)&&(ColorScanner!=4) &&(ColorScanner!=5)){
                System.out.println("Выберите цвет волос существа (Введите номер цвета):" + "\n" + "1.Красный" + "\n" + "2.Черный" + "\n" + "3.Желтый" + "\n" + "4.Оранжевый" + "\n" + "5.Коричневый" + "\n" +"Если вы введете неккоректное значение, вам будет преддложено повторить ввод.");
                ColorScanner = scan.nextInt();
                if (ColorScanner == 1) color = Color.RED;
                if (ColorScanner == 2) color = Color.BLACK;
                if (ColorScanner == 3) color = Color.YELLOW;
                if (ColorScanner == 4) color = Color.ORANGE;
                if (ColorScanner == 5) color = Color.BROWN;
            }
            Integer ColorScanner1 = 0;
            while ((ColorScanner1!=1)&&(ColorScanner1!=2)&&(ColorScanner1!=3)&&(ColorScanner1!=4) &&(ColorScanner1!=5)){
                System.out.println("Выберите цвет глаз существа (Введите номер цвета):" + "\n" + "1.Красный" + "\n" + "2.Черный" + "\n" + "3.Голубой" + "\n" + "4.Оранжевый" + "\n" + "5.Белый"+ "\n" +"Если вы введете неккоректное значение, вам будет преддложено повторить ввод.");
                ColorScanner1 = scan.nextInt();

                if (ColorScanner1 == 1) color1 = Color1.RED;
                if (ColorScanner1 == 2) color1 = Color1.BLACK;
                if (ColorScanner1 == 3) color1 = Color1.BLUE;
                if (ColorScanner1 == 4) color1 = Color1.ORANGE;
                if (ColorScanner1 == 5) color1 = Color1.WHITE;
            }
            Double coordinateX = null;
            Double coordinateY = null;
            while (coordinateX == null) {
                System.out.println("Введите координату X, координата не может иметь значение null!");
                coordinateX = scan.nextDouble();}
            System.out.println("Введите координату Y");
            coordinateY = scan.nextDouble();
            Integer countryScanner = 0;
            while ((countryScanner!=1)&&(countryScanner!=2)&&(countryScanner!=3)){

                System.out.println("Выберите страну существа (Введите номер страны):" + "\n" + "1.Соединенное Королевство" + "\n" + "2.Ватикан" + "\n" + "3.Тайланд"+ "\n" +"Если вы введете неккоректное значение, вам будет преддложено повторить ввод.");
                countryScanner = scan.nextInt();
                if (countryScanner == 1) country = Country.UNITED_KINGDOM;
                if (countryScanner == 2) country = Country.VATICAN;
                if (countryScanner == 3) country = Country.THAILAND;
            }
           Integer locationY = null;
            System.out.println("Выберите месторасположение существа в пространстве.");
            System.out.println("Введите координату X (тип Long)");
            Integer locationX = scan.nextInt();
            while (locationY== null) {
                System.out.println("Введите координату Y (тип Double). Значение не может быть null!");
                locationY = scan.nextInt();}
            System.out.println("Введите координату Z (тип Integer)");
            Integer locationZ = scan.nextInt();
            Person pers = new Person(nameScanner, new Coordinates(coordinateX, coordinateY), heightScanner, color, color1, country, new Location(locationX, locationY, locationZ));
            pers.setKey(keyScanner);
            return pers;
        }catch (InputMismatchException e ){

            System.out.println("Ошибка ввода. Заполните данные для персонажа заново!");
            personCreator();
        } catch (
                NoSuchElementException e){

            System.out.println("ошибка NoSuchElement, начните заново");
            personCreator();}
        catch (NullPointerException e ){
            System.out.println("ошибка null, начните заново");
            personCreator();
        }
        return null;
    }

    public static long findById(){
        long idScanner2 = -200;
        int k2 = 0;
        System.out.println("Введите id элемента (тип Long), чтобы обновить его");
        try {


            Scanner scanerr2 = new Scanner(System.in);
            while ((idScanner2<=0)||(idScanner2>1000)) {
                System.out.println("Значение id должжно быть положительным и не может превышать 1000");

                idScanner2 = scanerr2.nextLong();}
            Iterator<Person> itr = CollectionOfPersons.MyCollection.iterator();
            while(itr.hasNext()){
                Person pers34 = itr.next();
                if(pers34.getId() == idScanner2){

                    k2 = k2+1;
                }
            }
            if (k2 == 0){
                System.out.println("");
            }} catch(InputMismatchException e) {
            System.out.println("Ошибка ввода, повторите");
            findById();
        }
        if (k2==0) return k2; else return idScanner2;

    }
    public static Person changeElement(long x) throws ParserConfigurationException, IOException, AllException {
        long r =x;

        if (r==0) System.out.println("Элемента с таким id нет"); else {
            Person pers35 = new Person();
            Color color = null;
            Color1 color1 = null;
            Country country = null;
            Scanner scan = new Scanner(System.in);
            try{
                long keyScanner = 0;
                while ((keyScanner<=0)||(keyScanner>=101)) {
                    System.out.println("Введите ключ существа. Значение должно быть целочисленным и содержаться в отрезке от 1 до 100");

                    keyScanner = scan.nextLong();
                }
                System.out.println("Введите имя существа");
                Scanner scan22 = new Scanner(System.in);
                String nameScanner = scan22.nextLine();

                Double heightScanner = 0.0;
                while ((heightScanner<=0.0)||(heightScanner>1000)) {
                    System.out.println("Введите рост персонажа в см (Double), если после ввода вам будет предложен еще один ввод роста, то ваше предыдущее значение некорректно ");

                    heightScanner = scan.nextDouble();

                }
                Integer ColorScanner = 0;
                while ((ColorScanner!=1)&&(ColorScanner!=2)&&(ColorScanner!=3)&&(ColorScanner!=4) &&(ColorScanner!=5)){
                    System.out.println("Выберите цвет волос существа (Введите номер цвета):" + "\n" + "1.Красный" + "\n" + "2.Черный" + "\n" + "3.Желтый" + "\n" + "4.Оранжевый" + "\n" + "5.Коричневый" + "\n" +"Если вы введете неккоректное значение, вам будет преддложено повторить ввод.");
                    ColorScanner = scan.nextInt();
                    if (ColorScanner == 1) color = Color.RED;
                    if (ColorScanner == 2) color = Color.BLACK;
                    if (ColorScanner == 3) color = Color.YELLOW;
                    if (ColorScanner == 4) color = Color.ORANGE;
                    if (ColorScanner == 5) color = Color.BROWN;
                }
                Integer ColorScanner1 = 0;
                while ((ColorScanner1!=1)&&(ColorScanner1!=2)&&(ColorScanner1!=3)&&(ColorScanner1!=4) &&(ColorScanner1!=5)){
                    System.out.println("Выберите цвет глаз существа (Введите номер цвета):" + "\n" + "1.Красный" + "\n" + "2.Черный" + "\n" + "3.Голубой" + "\n" + "4.Оранжевый" + "\n" + "5.Белый"+ "\n" +"Если вы введете неккоректное значение, вам будет преддложено повторить ввод.");
                    ColorScanner1 = scan.nextInt();

                    if (ColorScanner1 == 1) color1 = Color1.RED;
                    if (ColorScanner1 == 2) color1 = Color1.BLACK;
                    if (ColorScanner1 == 3) color1 = Color1.BLUE;
                    if (ColorScanner1 == 4) color1 = Color1.ORANGE;
                    if (ColorScanner1 == 5) color1 = Color1.WHITE;
                }
                Double coordinateX = null;
                Double coordinateY = null;
                while (coordinateX == null) {
                    System.out.println("Введите координату X, координата не может иметь значение null!");
                    coordinateX = scan.nextDouble();}
                System.out.println("Введите координату Y");
                coordinateY = scan.nextDouble();
                Integer countryScanner = 0;
                while ((countryScanner!=1)&&(countryScanner!=2)&&(countryScanner!=3)){

                    System.out.println("Выберите страну существа (Введите номер страны):" + "\n" + "1.Соединенное Королевство" + "\n" + "2.Ватикан" + "\n" + "3.Тайланд"+ "\n" +"Если вы введете неккоректное значение, вам будет преддложено повторить ввод.");
                    countryScanner = scan.nextInt();
                    if (countryScanner == 1) country = Country.UNITED_KINGDOM;
                    if (countryScanner == 2) country = Country.VATICAN;
                    if (countryScanner == 3) country = Country.THAILAND;
                }
                Integer locationY = null;
                System.out.println("Выберите месторасположение существа в пространстве.");
                System.out.println("Введите координату X (тип Long)");
                Integer locationX = scan.nextInt();
                while (locationY== null) {
                    System.out.println("Введите координату Y (тип Double). Значение не может быть null!");
                    locationY = scan.nextInt();}
                System.out.println("Введите координату Z (тип Integer)");
                Integer locationZ = scan.nextInt();
                int k = 0;
Person pers = new Person(nameScanner, new Coordinates(coordinateX, coordinateY), heightScanner, color, color1, country, new Location(locationX, locationY, locationZ) );
pers.setKey(keyScanner);
return pers;
            }catch (InputMismatchException e ){

                System.out.println("Ошибка ввода. Заполните данные для персонажа заново!");
                changeElement(x);
            } catch (NoSuchElementException e){
                System.out.println("ошибка NoSuchElement, начните заново");
                changeElement(x);}
            catch (NullPointerException e ) {
                System.out.println("Ошибка null");
                changeElement(x);
            }
            System.out.println("Значения элемента изменены");
        }
        return null;
    }



}