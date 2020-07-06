package CommandsProcessing;
import PersonData.*;

import PersonData.*;
import org.omg.PortableInterceptor.LOCATION_FORWARD;
import org.postgresql.util.PSQLException;
import java.io.*;
import java.sql.*;
import java.time.Instant;
import java.util.*;
import java.util.Date;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.time.Instant;
import java.util.*;
import static Auth.ReceiveDataFromServer.locker;
/**
 * Класс Commands содержит методы, используемы в классе CommandControl для выполнения команд пользователя
 */
public class Commands {
    static Person f;

    static {
        try {
            f = new Person();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AllException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static Scanner slmmsk;
    private static ArrayList al = new ArrayList();
    private static int q=0;

    public Commands() throws IOException, AllException {
    }

    /**
     * Метод help выводит справку по доступным командам
     */
    public static String help(){
        String help = "1. help - справка по командам" +"\n" + "2. info - информация о колекции" + "\n" + "3. show - вывести все элементы коллекции" +
                "\n" + "4. insertElement - добавить новый элемент с заданным ключом"+"\n"+"5. updateId - обновить значение элемента по id"+"\n"+"6. removeByKey - удалить элемент по ключу" +
                "\n"+"7. clear - очистить коллекцию " + "\n"+"8. script - скрипт"+"\n"+
                "9. exit - завершить программу без сохранения в файл" + "\n" + "10. removeGreater - удалить все эелементы, превышающие заданный"+"\n"+
                "11. removeLower - удалить все элементы, которые меньше заданного"+"\n"+ "12. history - вывести последние 15 команд" + "\n"+
                "13. sumOfHeight - вывести сумму значений поля high всех элементов коллекции"+"\n"+
                "14. maxByEyeColor - вывести любой объект из коллекции, значение поля eyeColor которого является максимальным";
        return help;
    }
    /**
     * Метод info выводит в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
     */
    public static String info(){
        String info;
        if (CollectionOfPersons.MyCollection.size() != 0){
            info = "Размер коллекции: " + CollectionOfPersons.MyCollection.size()+ "\n"
                    + "Тип коллекции: " + CollectionOfPersons.MyCollection.getClass() + "\n"
                    + "Дата инициализации: " + Date.from(Instant.now()) ;
        }
        else  info = "Коллекция пуста";
        return info;
    }
    public static void loadDB(){
        CollectionOfPersons.MyCollection.clear();
        String sql = "SELECT * FROM PersonLists1";
        try (Connection connection = DriverManager.getConnection(Receiver.URL,Receiver.USERNAME,Receiver.PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                long id = resultSet.getInt("id");
                java.util.Date creationDate = resultSet.getDate("creationDate");
                Double height = resultSet.getDouble("height");
                Long key = resultSet.getLong("key");
                String colorData = resultSet.getString("color");
                String color1Data = resultSet.getString("color1");
                String countryData = resultSet.getString("country");
                double x = resultSet.getDouble("x");
                double y = resultSet.getDouble("y");
                Integer locationX = resultSet.getInt("locationX");
                Integer locationY = resultSet.getInt("locationY");
                Integer locationZ = resultSet.getInt("locationZ");
                String login = resultSet.getString("login");
                Person personperson = new Person(name, id,  new Coordinates(x,y), height, key, setColor(colorData), setColor1(color1Data), setCountry(countryData),
                        new Location(locationX, locationY, locationZ), login);
                CollectionOfPersons.MyCollection.add(personperson);
            }
        } catch (SQLException | AllException e) {
            System.out.println("возникла ошибка");
            e.printStackTrace();
        }
    }
    public static Color setColor(String colorr){
        Color color = Color.RED;
        switch (colorr){
            case("RED"):
                color= Color.RED;
                break;
            case("BLACK"):
                color = Color.BLACK;
                break;
            case("YELLOW"):
                color = Color.YELLOW;
                break;
            case("ORANGE"):
                color = Color.ORANGE;
                break;
            case("BROWN"):
                color = Color.BROWN;
                break;

        }
        return color;
    }
    public static Color1 setColor1(String colorr1){
        Color1 color1 = Color1.RED;
        switch (colorr1){
            case("RED"):
                color1= Color1.RED;
                break;
            case("BLACK"):
                color1 = Color1.BLACK;
                break;
            case("BLUE"):
                color1 = Color1.BLUE;
                break;
            case("ORANGE"):
                color1 = Color1.ORANGE;
                break;
            case("WHITE"):
                color1 = Color1.WHITE;
                break;

        }
        return color1;
    }
    public static Country setCountry(String countryy){
        Country country = Country.THAILAND;
        switch (countryy){
            case("THAILAND"):
                country= Country.THAILAND;
                break;
            case("UNITED_KINGDOM"):
                country= Country.UNITED_KINGDOM;
                break;
            case("VATICAN"):
                country= Country.VATICAN;
                break;
        }
        return country;
    }



   /* public static String show(){
        String show;
        if (CollectionOfFlats.collection.size() != 0) {


            return "Коллекция" + CollectionOfFlats.collection;
        }
        else show = "Коллекция пуста";
        return show;
    }*/
   /* public static String insertElement(Person person){
        CollectionOfPersons.MyCollection.add(person);
        String s = "Элемент успешно добавлен в коллецию.";
        return s;
    }*/
    
     public static String show(){
      String show;
      if (collection.size() != 0) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("[");
          collection.stream().forEach((p) -> stringBuilder.append(p.toString()+","));
          stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
          return String.valueOf(stringBuilder.append("]"));
      }
      else show = "Коллекция пуста";
      return show;
  }
    public static String insertElement(Person personon) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String answer = "";
        String sqlStatement = "INSERT INTO PersonLists1 ( creationDate,name, height,key,color,color1,country,x,y,locationX,locationY,locationZ,login) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";
        try  {
            connection = DriverManager.getConnection(Receiver.URL, Receiver.USERNAME, Receiver.PASSWORD);
            preparedStatement = connection.prepareStatement(sqlStatement);
            java.util.Date crDate = personon.getCreationDate1();
            java.sql.Date sqlDate = new java.sql.Date(crDate.getTime());
            preparedStatement.setDate(1, sqlDate);
            preparedStatement.setString(2, personon.getName());
            preparedStatement.setDouble(3, personon.getHeight());
            preparedStatement.setDouble(4, personon.getKey());
            preparedStatement.setString(5, String.valueOf(personon.getColor()));
            preparedStatement.setString(6, String.valueOf(personon.getColor1()));
            preparedStatement.setString(7, String.valueOf(personon.getCountry()));
            preparedStatement.setDouble(8, personon.getCoordinates().getX());
            preparedStatement.setDouble(9, personon.getCoordinates().getY());
            preparedStatement.setInt(10, personon.getLocation().getX());
            preparedStatement.setInt(11, personon.getLocation().getY());
            preparedStatement.setInt(12, personon.getLocation().getZ());
            preparedStatement.setString(13,Receiver.login);
            preparedStatement.executeUpdate();
            loadDB();
            answer = "Элемент успешно добавлен в коллекцию.";
        } catch (SQLException e) {
            e.printStackTrace();
            answer += "Возникла ошибка.";
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return answer;
    }

    /**
     * Метод updateId обновляет значение элемента коллекции, id которого равен заданному, работает по аналогии с методом add
     * @exception InputMismatchException при выбрасывании этого исключение выводится сообщение о том, что поле заполнено некорректно
     */
    /*public static String updateId(long x, Person person1){
        int k2=0;
        Iterator<Person> itr = CollectionOfPersons.MyCollection.iterator();
        while(itr.hasNext()){
            Person person = itr.next();
            try {
                if(person.getId() == x){
                    k2= k2+1;


                    person.setName(person1.getName());
                    person.setCoordinates(person1.getCoordinates());
                    person.setHeight(person1.getHeight());
                    person.setColor(person1.getColor());
                    person.setColor1(person1.getColor1());
                    person.setKey(person1.getKey());
                    person.setLocation(person1.getLocation());

                }}catch (ConcurrentModificationException e){}
        }
        if (k2 == 0){
            return"Элемента с введенным вами id в коллекции нет." ;
        } else {
            return "Коллекция обновлена";
        }
    }*/
    public static String updateId(long x, Person person1) throws SQLException {
        locker.lock();
        java.util.Date crDate = person1.getCreationDate1();
        java.sql.Date sqlDate = new java.sql.Date(crDate.getTime());
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String answer = "";
        String condition = "SELECT * FROM PersonLists1 WHERE login = " + "'" + Receiver.login + "'";
        int ix = (int) (Math.random() * (1000 + 1));
        try {
            connection = DriverManager.getConnection(Receiver.URL, Receiver.USERNAME, Receiver.PASSWORD);
            preparedStatement = connection.prepareStatement(condition, ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = preparedStatement.executeQuery();
            int counter = 0;
            while (resultSet.next()) {
                if ((resultSet.getInt("id")) == x) {
                    resultSet.updateString("name", person1.getName());
                    resultSet.updateDouble("height", person1.getHeight());
                    resultSet.updateDouble("key", person1.getKey());
                    resultSet.updateString("color", String.valueOf(person1.getColor()));
                    resultSet.updateString("color1", String.valueOf(person1.getColor1()));
                    resultSet.updateString("country", String.valueOf(person1.getCountry()));
                    resultSet.updateDate("creationdate", sqlDate);
                    resultSet.updateInt("id", ix);
                    resultSet.updateInt("locationX", person1.getLocation().getX());
                    resultSet.updateInt("locationY", person1.getLocation().getY());
                    resultSet.updateInt("locationZ", person1.getLocation().getZ());
                    resultSet.updateDouble("x", person1.getCoordinates().getX());
                    resultSet.updateDouble("y", person1.getCoordinates().getY());
                    resultSet.updateRow();
                    counter++;
                }
            }
            if (counter == 0) {
                answer = "Элемент с таким id не найден или у пользователя (" + Receiver.login + ") нет доступа к модификации объекта c id " + x;
            } else {
                answer = "Значения элемента успешно обновлены.";
                CollectionOfPersons.MyCollection.clear();
                loadDB();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            answer += "Возникла ошибка.";
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
            locker.unlock();
        }
        return answer;
    }
    /**
     * Метод remove_by_id удаляет элемент из коллекции по его id
     * @exception InputMismatchException выбрасывает исключение при некорректном пользовательском вводе
     */
    /*public static String removeByKey(long x){
        int uuu =0;
        Iterator<Person> itr22 = CollectionOfPersons.MyCollection.iterator();
        while(itr22.hasNext()){
            Person person = itr22.next();
            if(person.getKey() == x){
                uuu=uuu+1;

            } }
        Iterator<Person> itr = CollectionOfPersons.MyCollection.iterator();
        if (uuu==0 ) return "В коллекции нет элемента с таким key "; else {
            while(itr.hasNext()){
                Person person = itr.next();
                if(person.getKey() == x){
                    CollectionOfPersons.MyCollection.remove(person);
                    return "Элемент коллекции был успешно удален.";

                } }}
        return "";
    }*/
    public static String removeByKey(long x) throws SQLException {
        locker.lock();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String answer ="";
        String select = "DELETE FROM PersonLists1 WHERE key = " + x + " AND login = " + "'" + Receiver.logins.getFirst() + "'" + ";";
        try  {
            connection = DriverManager.getConnection(Receiver.URL, Receiver.USERNAME, Receiver.PASSWORD);
            preparedStatement = connection.prepareStatement(select);
            int j = preparedStatement.executeUpdate();
            if (j==1){
                answer = "Элемент успешно удалён";
                CollectionOfPersons.MyCollection.clear();
                loadDB();
            } else if (j == 0){
                answer = "Элемент с таким ключом не найден / пользователь с логином "+ Receiver.login + " не имеет доступа к элементу с ключом " + x;
            }else{
                answer = "Элемент успешно удалён";
                CollectionOfPersons.MyCollection.clear();
                loadDB();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            locker.unlock();
        }
        return answer;
    }
    public static String show(){
        String show;
        if (CollectionOfPersons.MyCollection.size() != 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[");
            CollectionOfPersons.MyCollection.stream().forEach((p) -> stringBuilder.append(p.toString()+","));
            stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
            return String.valueOf(stringBuilder.append("]"));
        }
        else show = "Коллекция пуста";
        return show;
    }
   /* public static String show(){
        String show;
        if (CollectionOfPersons.MyCollection.size() != 0) {
         show =  CollectionOfPersons.MyCollection.toString();
        }
        else show = "Коллекция пуста";
        return show;
    }*/
    public static String filterName(String x){
        int k=0;
        Iterator<Person> itr23 = CollectionOfPersons.MyCollection.iterator();
        while (itr23.hasNext()) {
            Person person = itr23.next();
            String s = person.getName();

            if (s.contains(x)) {
                k=k+1;
            }
        }

        if (k==0) return "В коллекции нет такого персонажа" ; else {
            Iterator<Person> itr232 = CollectionOfPersons.MyCollection.iterator();
            while (itr232.hasNext()) {
                Person person = itr232.next();
                String s = person.getName();

                if (s.contains(x)) {
                    return "Персонаж " + person;
                }
            }
        }
        return "";
    }




    /**
     * Метод maxByName выводит максимальное по длине name в коллекции
     */
    public static String sumOfHeight() {
        double sum = 0.0;
        Iterator<Person> itr = CollectionOfPersons.MyCollection.iterator();
        while(itr.hasNext()){
            sum = sum +itr.next().getHeight();

        }
return String.valueOf(sum);
       }


    /*public static String removeGreater(long x){
        int t = CollectionOfPersons.MyCollection.size();
        int t1 = CollectionOfPersons.MyCollection.size();



        Iterator<Person> itr1 = CollectionOfPersons.MyCollection.iterator();
        while(itr1.hasNext()){
            Person p = itr1.next();
            if(p.getKey() > x){
                itr1.remove();
                t= t-1;
            }
        }
        if (t1!=t) return "Коллекция была обновлена."; else return "В коллекции нет элемента, превышающего введенный вами.";
    }*/
    public static String removeGreater (long uu){
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String answer ="";
        String select = "DELETE FROM PersonLists1 WHERE id > " + uu + " AND login = " + "'" + Receiver.login + "'" + ";";
        try  {
            connection = DriverManager.getConnection(Receiver.URL, Receiver.USERNAME, Receiver.PASSWORD);
            preparedStatement = connection.prepareStatement(select);
            int j = preparedStatement.executeUpdate();
            if (j==1){
                answer = "Элементы, id которых превышает " + uu+ " созданы пользователем с логином "+ Receiver.login +", успешно удалены.";
            } else if (j == 0){
                answer = "Элементы, id которых превышает " + uu + " не найдены.";
            }else{
                answer = "Элементы, id которых превышает " + uu + " созданы пользователем с логином "+ Receiver.login +", успешно удалены.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CollectionOfPersons.MyCollection.clear();
        loadDB();
        return answer;
    }

    /*public static String removeLower(long x) {
        int t = CollectionOfPersons.MyCollection.size();
        int t1 = CollectionOfPersons.MyCollection.size();



        Iterator<Person> itr1 = CollectionOfPersons.MyCollection.iterator();
        while (itr1.hasNext()) {
            Person person = itr1.next();
            if (person.getKey() < x) {
                itr1.remove();
                t = t - 1;
            }
        }
        if (t1 != t) return "Коллекция была обновлена.";
        else return "В коллекции нет элемента, меньшего чем введенный вами.";

    }*/
    public static String removeLower (long uu){
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String answer ="";
        String select = "DELETE FROM PersonLists1 WHERE id < " + uu + " AND login = " + "'" + Receiver.login + "'" + ";";
        try  {
            connection = DriverManager.getConnection(Receiver.URL, Receiver.USERNAME, Receiver.PASSWORD);
            preparedStatement = connection.prepareStatement(select);
            int j = preparedStatement.executeUpdate();
            if (j==1){
                answer = "Элементы, id которых меньше " + uu+ " созданы пользователем с логином "+ Receiver.login +", успешно удалены.";
            } else if (j == 0){
                answer = "Элементы, id которых меньше " + uu + " не найдены.";
            }else{
                answer = "Элементы, id которых меньше " + uu + " созданы пользователем с логином "+ Receiver.login +", успешно удалены.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CollectionOfPersons.MyCollection.clear();
        loadDB();
        return answer;
    }


    private static String getFilePathForScript(){
        String path = System.getenv("duo");

        if (path == null){
            return ("----\nПуть через переменную окружения dos не указан\n----");
        } else {
            return path;
        }
    }


    /*public static String clear(){
        CollectionOfPersons.MyCollection.clear();
        String clear = "Коллекция успешно отчищена";
        return clear;
    }*/
    public static String clear() throws SQLException {
        String clear = "";
        try {
            Connection conn = DriverManager.getConnection(Receiver.URL, Receiver.USERNAME, Receiver.PASSWORD);
            String deleteTableSQL = "DELETE FROM PersonLists1 WHERE login = " + "'" + Receiver.login + "'" + ";";
            Statement statement = conn.createStatement();
            statement.execute(deleteTableSQL);
            CollectionOfPersons.MyCollection.clear();
            loadDB();
            clear = "Коллекция успешно отформатирована. (Удалены все объекты, принадлежащие пользователю с логином " + Receiver.login + ")";
        }catch (PSQLException e){
            clear = "Данный пользователь "+ "(" + Receiver.login + ")" +" не может удалить элементы коллекции, т.к. они созданы другим пользователем. ";
        }
        return clear;
    }
    public static void save(){

        try{
            String pathToFile = getFilePathForSave();
            if (pathToFile == null)
                System.out.println("----\nПуть не указан, дальнейшая работа не возможна.\n----");
            else {
                JAXBContext jaxbContext;
                PersonLists lists = new PersonLists();
                List lst = new ArrayList();
                lst.addAll(CollectionOfPersons.MyCollection);
                lists.setPersonList(lst);
                try {
                    File file = new File(pathToFile);
                    PrintWriter pw = new PrintWriter(file);
                    jaxbContext = JAXBContext.newInstance(PersonLists.class);
                    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
                    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                    jaxbMarshaller.marshal(lists, pw);
                } catch (JAXBException | FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (NoSuchElementException e){
            e.getMessage();
        }
    }
    private static String getFilePathForSave(){
        String path = System.getenv("SecondFile");
        if (path == null){
            return ("----\nПуть через переменную окружения duo не указан\n----");
        } else {
            return path;
        }

    }

    public  static ArrayList<String> hy() {
        int yuy =0;
        Scanner scanner = null;
        ArrayList<String> scriptoData = new ArrayList<>();
        try {



            Scanner scanInteractive = new Scanner(System.in);
            String userCommand = "";
            String[] finalUserCommand;

            FileReader fr = new FileReader("scriptFile.TXT");
            Scanner scanCloser = new Scanner(fr);


            while ((scanCloser.hasNextLine()) &&(yuy<5)) {

                userCommand = scanCloser.nextLine();
                //userCommand = userCommand.trim();
                finalUserCommand = userCommand.trim().split(" ", 2);


                switch (finalUserCommand[0]) {
                    case"help":
                        help();
                        scriptoData.add(help());
                        break;
                    case"info":
                        info();
                        scriptoData.add(info());

                        break;
                    case"clear":
                        CollectionOfPersons.MyCollection.clear();
                        System.out.println("Все элементы коллекции удалены");

                        break;
                    case"show":
                        show();
                        scriptoData.add(show());

                        break;
                    case"insertElement":
                        int t = CollectionOfPersons.MyCollection.size();
                        Person fl =new Person();
                        fl.setName(scanCloser.nextLine());
                        Double num = Double.parseDouble(scanCloser.nextLine());
                        Double num2 = Double.parseDouble(scanCloser.nextLine());
                        fl.setCoordinates(new Coordinates(num, num2));
                        Double num3 = Double.parseDouble(scanCloser.nextLine());
                        fl.setHeight(num3);
                        Long num4 = Long.parseLong(scanCloser.nextLine());
                        fl.setKey(num4);
                        String color = scanCloser.nextLine();
                        if (color.equals("1"))
                            fl.setColor(Color.RED);
                        if (color.equals("2"))
                            fl.setColor(Color.BLACK);
                        if (color.equals("3"))
                            fl.setColor(Color.YELLOW);
                        if (color.equals("4"))
                            fl.setColor(Color.ORANGE);
                        if (color.equals("5"))
                            fl.setColor(Color.BROWN);
                        String color1 = scanCloser.nextLine();
                        if (color1.equals("1"))
                            fl.setColor1(Color1.RED);
                        if (color1.equals("2"))
                            fl.setColor1(Color1.BLACK);
                        if (color1.equals("3"))
                            fl.setColor1(Color1.BLUE);
                        if (color1.equals("4"))
                            fl.setColor1(Color1.WHITE);
                        if (color1.equals("5"))
                            fl.setColor1(Color1.ORANGE);
                        String country = scanCloser.nextLine();
                        if (country.equals("1"))
                            fl.setCountry(Country.UNITED_KINGDOM);
                        if (country.equals("2"))
                            fl.setCountry(Country.VATICAN);
                        if (country.equals("3"))
                            fl.setCountry(Country.THAILAND);
                        Location location = new Location();
                        location.x = Integer.parseInt(scanCloser.nextLine());
                        location.y = Integer.parseInt(scanCloser.nextLine());
                        location.z = Integer.parseInt(scanCloser.nextLine());


                        fl.setLocation(location);
                        CollectionOfPersons.MyCollection.add(fl);
                        if (CollectionOfPersons.MyCollection.size()!=t)
                            System.out.println("Персонаж успешно добавлен в коллекцию.");
                        break;
                    case "removeByKey":
                        removeByIdScript(scanCloser.nextLine());

                        break;

                    case"updateById":
                        updateIdScript(scanCloser.nextLine());

                        break;
                    case "sumOfHeight":
                      sumOfHeight();

                        break;

                    case "filterName":
                        stringFishingScript(scanCloser.nextLine());

                        break;
                    case "removeGreater":
                        compareScript(scanCloser.nextLine());

                        break;
                    case "removeLower":
                        compare2Script(scanCloser.nextLine());
                        break;
                    case "script":
                        yuy =yuy+1;
                        hy();
                        break;
                    case"exit":
                        System.exit(0);
                }


            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AllException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return scriptoData;

    }

    public static void removeByIdScript(String str) {
        long idScanner = -20000;
        int k = 0;
        Long numID = Long.parseLong(str);
        idScanner = numID;

        Iterator<Person> itr = CollectionOfPersons.MyCollection.iterator();
        while(itr.hasNext()){
            Person person = itr.next();
            if(person.getKey() == idScanner){
                CollectionOfPersons.MyCollection.remove(person);
                System.out.println("Элемент коллекции был успешно удален.");
                k = k+1;
            } }
        if (k == 0){
            System.out.println("Элемента с введенным вами key в коллекции нет.");
        }
    }
    public static void updateIdScript(String str)  {
        long idScanner = -200;
        int k = 0;
        Long numID = Long.parseLong(str);


        idScanner = numID;
        Iterator<Person> itr =CollectionOfPersons.MyCollection.iterator();
        while(itr.hasNext()){
            Person pppp = itr.next();
            if(pppp.getId() == idScanner){
                pppp.setId(pppp.randomID());
                System.out.println("id элемента был успешно обновлен.");
                k = k+1;
            }
        }
        if (k == 0){
            System.out.println("Элемента с введенным вами id в коллекции нет.");
        }
    }

    public static void stringFishingScript(String str){
        String nameScanner = "hh" ;
        int k = 0;





        nameScanner = str;

        Iterator<Person> itr2 = CollectionOfPersons.MyCollection.iterator();
        while (itr2.hasNext()) {
            Person person = itr2.next();
            String s = person.getName();
            String n = s.substring(0, 5);
            if (n.equals(nameScanner)) {
                System.out.println(person);
                k=k+1;
            }
        }
        if (k == 0) System.out.println("В коллекции нет элемента, имя которого начинается с введенной вами строки или введенная вами строка содержит меньше 5 символов");
    }
    public static void compareScript(String str)  {
        long idScanner = -10;
        Long numID = Long.parseLong(str);
        int k = 0;
        int t = CollectionOfPersons.MyCollection.size();
        int t1 = CollectionOfPersons.MyCollection.size();




        idScanner = numID;
        Iterator<Person> itr = CollectionOfPersons.MyCollection.iterator();


            Iterator<Person> itr1 = CollectionOfPersons.MyCollection.iterator();
            while(itr1.hasNext()){
                Person popop = itr1.next();
                if(popop.getKey() > idScanner){
                    itr1.remove();
                    t= t-1;
                }
            }
            if (t1!=t) System.out.println("Коллекция была обновлена."); else System.out.println("В коллекции нет элемента, превышающего введенный вами.");



    }
    public static void compare2Script(String str)  {
        long idScanner = -10;
        int k = 0;
        Long numID = Long.parseLong(str);
        int t = CollectionOfPersons.MyCollection.size();
        int t1 = CollectionOfPersons.MyCollection.size();



        idScanner = numID;
        Iterator<Person> itr = CollectionOfPersons.MyCollection.iterator();


            Iterator<Person> itr1 = CollectionOfPersons.MyCollection.iterator();
            while (itr1.hasNext()) {
                Person personio = itr1.next();
                if (personio.getKey() < idScanner) {
                    itr1.remove();
                    t = t - 1;
                }
            }
            if (t1 != t) System.out.println("Коллекция была обновлена.");
            else System.out.println("В коллекции нет элемента, который меньше введенного вами.");

    }




}
