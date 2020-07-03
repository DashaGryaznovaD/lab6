package Auth;
import PersonData.AllException;
import PersonData.CollectionOfPersons;
import PersonData.Person;

import javax.net.SocketFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import java.nio.channels.SocketChannel;
import java.util.*;
import java.nio.*;
import java.util.concurrent.*;
import PersonData.AllException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import java.util.*;
import java.nio.*;
import java.util.concurrent.locks.ReentrantLock;
public class CommandReader implements Runnable {
    Integer integer =0;
    private boolean isConnected = false;
    Thread thread;
    ReentrantLock locker;
    public CommandReader() throws IOException {
        locker = new ReentrantLock(); // создаем блокировку
        thread=new Thread(this, "Поток клиента с отправкой команд");
        thread.start(); //запускаем поток
    }
    private SocketChannel createChannel() throws IOException {
        ByteBuffer bf = ByteBuffer.allocate(16384);
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(true);
        SocketAddress socketAddress = new InetSocketAddress("localhost", 3030);
        try {

            socketChannel.connect(socketAddress);
        }catch (ConnectException e){
            createChannel();
        }
        return socketChannel;
    }
    /**
     * Метод Start считывает введённую пользователем команду и вызывает соответстующий метод из класса Commands; выбрасывает исключения при некорректном пользовательском вводе
     * @exception IllegalStateException в случае выбрасывания данного исключения программа завершается
     */
    @Override
    public void run()  {
        while (!isConnected) {
            isConnected = true;
            ArrayList<String> history = new ArrayList<>();
            Scanner scanner = new Scanner(System.in);
            String commandName = "";
            System.out.println("Введите команду для выполнения. Чтобы узнать все доступные команды введите 'help'");
            try {

                while (!commandName.equals("exit")) {
                    commandName = scanner.nextLine();
                    commandName = commandName.trim();
                    String[] commandsArray = commandName.trim().split(" ", 2);
                    switch (commandsArray[0]) {
                        case "info":
                            SocketChannel socketchannel = createChannel(); // 2 new lines 2 try
                            ObjectOutputStream outputStream = new ObjectOutputStream(socketchannel.socket().getOutputStream());
                            ReceiveDataFromServer receiveDataFromServer = new ReceiveDataFromServer();
                            history.add(commandName);
                            ComplicatedObject info = new ComplicatedObject("info");
                            outputStream.writeObject(info);
                            receiveDataFromServer.receive();
                            socketchannel.close();
                            outputStream.close();
                            break;
                        case "help":
                            SocketChannel socketchannel1 = createChannel(); // 2 new lines 2 try
                            System.out.println(integer);
                            ObjectOutputStream outputStream1 = new ObjectOutputStream(socketchannel1.socket().getOutputStream());
                            ReceiveDataFromServer receiveDataFromServer1 = new ReceiveDataFromServer();
                            history.add(commandName);
                            ComplicatedObject help = new ComplicatedObject("help");
                            outputStream1.writeObject(help);
                            receiveDataFromServer1.receive();
                            socketchannel1.close();
                            outputStream1.close();
                            break;
                        case "clear":
                            System.out.println("Если у Вас уже есть учётная запись введите логин." +
                                    "\n" + "Если у Вас ещё нет учётной записи, необходимо её создать: для этого придумайте логин. " +
                                    "\n" + "Ваш логин:");
                            Authorization authorization = new Authorization();
                            authorization.authorize();
                            SocketChannel socketchannel3 = createChannel(); // 2 new lines 2 try
                            integer=1;
                            ObjectOutputStream outputStream3 = new ObjectOutputStream(socketchannel3.socket().getOutputStream());
                            ReceiveDataFromServer receiveDataFromServer3 = new ReceiveDataFromServer();
                            history.add(commandName);
                            ComplicatedObject clear = new ComplicatedObject("clear");
                            outputStream3.writeObject(clear);
                            receiveDataFromServer3.receive();
                            socketchannel3.close();
                            outputStream3.close();
                            break;
                        case "show":
                            SocketChannel socketchannel4 = createChannel(); // 2 new lines 2 try
                            ObjectOutputStream outputStream4 = new ObjectOutputStream(socketchannel4.socket().getOutputStream());
                            ReceiveDataFromServer receiveDataFromServer4 = new ReceiveDataFromServer();
                            history.add(commandName);
                            ComplicatedObject show = new ComplicatedObject("show");
                            outputStream4.writeObject(show);
                            receiveDataFromServer4.receive();
                            socketchannel4.close();
                            outputStream4.close();
                            break;
                        case "insertElement":
                            System.out.println("Если у Вас уже есть учётная запись введите логин." +
                                    "\n" + "Если у Вас ещё нет учётной записи, необходимо её создать: для этого придумайте логин. " +
                                    "\n" + "Ваш логин:");
                            Authorization authorization1 = new Authorization();
                            authorization1.authorize();
                            history.add(commandName);
                            ComplicatedObject co = new ComplicatedObject("insertElement", InterScanner.personCreator());
                            SocketChannel socketchannel5 = createChannel(); // 2 new lines 2 try
                            ObjectOutputStream outputStream5 = new ObjectOutputStream(socketchannel5.socket().getOutputStream());
                            ReceiveDataFromServer receiveDataFromServer5 = new ReceiveDataFromServer();
                            outputStream5.writeObject(co);
                            receiveDataFromServer5.receive();
                            socketchannel5.close();
                            outputStream5.close();
                            break;
                        case "updateId":
                            System.out.println("Если у Вас уже есть учётная запись введите логин." +
                                    "\n" + "Если у Вас ещё нет учётной записи, необходимо её создать: для этого придумайте логин. " +
                                    "\n" + "Ваш логин:");
                            Authorization authorization2 = new Authorization();
                            authorization2.authorize();
                            history.add(commandName); //сначала получаю все данные, а потом отправляю
                            long ug =0;
                            while ((ug<1)||(ug>10000)) {
                                System.out.println("Введите id персонажа чтобы обновить элемент коллекции. Значение Id должно находится в отрезке [1,10000]");
                                Scanner scanner1 = new Scanner(System.in);
                                ug = scanner1.nextLong();}
                            ComplicatedObject obj = new ComplicatedObject("updateId", ug, InterScanner.changeElement(ug));
                            SocketChannel socketchannel6 = createChannel(); // 2 new lines 2 try
                            ObjectOutputStream outputStream6 = new ObjectOutputStream(socketchannel6.socket().getOutputStream());
                            ReceiveDataFromServer receiveDataFromServer6 = new ReceiveDataFromServer();
                            outputStream6.writeObject(obj);
                            receiveDataFromServer6.receive();
                            outputStream6.close();
                            socketchannel6.close();
                            break;
                        case "removeByKey":
                            System.out.println("Если у Вас уже есть учётная запись введите логин." +
                                    "\n" + "Если у Вас ещё нет учётной записи, необходимо её создать: для этого придумайте логин. " +
                                    "\n" + "Ваш логин:");
                            Authorization authorization3 = new Authorization();
                            authorization3.authorize();
                            history.add(commandName);
                            long ug1 =0;
                            while ((ug1<1)||(ug1>10000)) {
                                System.out.println("Введите key персонажа чтобы удалить элемент коллекции. Значение Id должно находится в отрезке [1,10000]");
                                Scanner scanner1 = new Scanner(System.in);
                                ug1 = scanner1.nextLong();}
                            ComplicatedObject remove = new ComplicatedObject("removeByKey", ug1);
                            SocketChannel socketchannel7 = createChannel(); // 2 new lines 2 try
                            ObjectOutputStream outputStream7 = new ObjectOutputStream(socketchannel7.socket().getOutputStream());
                            ReceiveDataFromServer receiveDataFromServer7 = new ReceiveDataFromServer();
                            outputStream7.writeObject(remove);
                            receiveDataFromServer7.receive();
                            outputStream7.close();
                            socketchannel7.close();
                            break;
                        case "sumOfHeight":


                            ComplicatedObject heightSum = new ComplicatedObject("sumOfHeight");
                            SocketChannel socketchannel8 = createChannel(); // 2 new lines 2 try
                            ObjectOutputStream outputStream8 = new ObjectOutputStream(socketchannel8.socket().getOutputStream());
                            ReceiveDataFromServer receiveDataFromServer8 = new ReceiveDataFromServer();
                            outputStream8.writeObject(heightSum);
                            receiveDataFromServer8.receive();
                            outputStream8.close();
                            socketchannel8.close();
                            break;
                        case "filterName":
                            history.add(commandName);
                            String nameScanner = "" ;
                            Scanner scan = new Scanner(System.in);
                            while (nameScanner.length()!=5) {
                                System.out.println("Введите пять символов!");
                                nameScanner = scan.nextLine();
                            }
                            ComplicatedObject maxName = new ComplicatedObject("filterName", nameScanner);
                            SocketChannel socketchannel9 = createChannel(); // 2 new lines 2 try
                            ObjectOutputStream outputStream9 = new ObjectOutputStream(socketchannel9.socket().getOutputStream());
                            ReceiveDataFromServer receiveDataFromServer9 = new ReceiveDataFromServer();
                            outputStream9.writeObject(maxName);
                            receiveDataFromServer9.receive();
                            outputStream9.close();
                            socketchannel9.close();
                            break;
                        case "removeGreater":
                            System.out.println("Если у Вас уже есть учётная запись введите логин." +
                                    "\n" + "Если у Вас ещё нет учётной записи, необходимо её создать: для этого придумайте логин. " +
                                    "\n" + "Ваш логин:");
                            Authorization authorization33 = new Authorization();
                            authorization33.authorize();
                            SocketChannel socketchannel10 = createChannel(); // 2 new lines 2 try
                            ObjectOutputStream outputStream10 = new ObjectOutputStream(socketchannel10.socket().getOutputStream());
                            ReceiveDataFromServer receiveDataFromServer10 = new ReceiveDataFromServer();
                            history.add(commandName);
                            long ugg1 =0;
                            while ((ugg1<1)||(ugg1>10000)) {
                                System.out.println("Введите id чтобы удалить элементы коллекции, id которых больше введенного. Значение id должно находится в отрезке [1,10000]");
                                Scanner scanner1 = new Scanner(System.in);
                                ugg1 = scanner1.nextLong();}
                            ComplicatedObject filter = new ComplicatedObject("removeGreater", ugg1);
                            outputStream10.writeObject(filter);
                            receiveDataFromServer10.receive();
                            outputStream10.close();
                            socketchannel10.close();
                            break;
                        case "removeLower":
                            System.out.println("Если у Вас уже есть учётная запись введите логин." +
                                    "\n" + "Если у Вас ещё нет учётной записи, необходимо её создать: для этого придумайте логин. " +
                                    "\n" + "Ваш логин:");
                            Authorization authorization44 = new Authorization();
                            authorization44.authorize();
                            SocketChannel socketchannel11 = createChannel(); // 2 new lines 2 try
                            ObjectOutputStream outputStream11 = new ObjectOutputStream(socketchannel11.socket().getOutputStream());
                            ReceiveDataFromServer receiveDataFromServer11 = new ReceiveDataFromServer();
                            history.add(commandName);
                            long ugg15 =0;
                            while ((ugg15<1)||(ugg15>10000)) {
                                System.out.println("Введите id  чтобы удалить элементы коллекции, id которых меньше введенного. Значение id должно находится в отрезке [1,10000]");
                                Scanner scanner1 = new Scanner(System.in);
                                ugg15 = scanner1.nextLong();}
                            ComplicatedObject removeGr = new ComplicatedObject("removeLower", ugg15);
                            outputStream11.writeObject(removeGr);
                            receiveDataFromServer11.receive();
                            outputStream11.close();
                            socketchannel11.close();
                            break;
                        case "history":
                            SocketChannel socketchannel12 = createChannel(); // 2 new lines 2 try
                            ObjectOutputStream outputStream12 = new ObjectOutputStream(socketchannel12.socket().getOutputStream());
                            ReceiveDataFromServer receiveDataFromServer12 = new ReceiveDataFromServer();
                            history.add(commandName);ComplicatedObject history1 = new ComplicatedObject("history", history.toString());
                            System.out.println(history);
                            outputStream12.writeObject(history1);
                            receiveDataFromServer12.receive();
                            outputStream12.close();
                            socketchannel12.close();
                            break;
                        case "exit":
                            SocketChannel socketchanne101 = createChannel(); // 2 new lines 2 try
                            ObjectOutputStream outputStream101 = new ObjectOutputStream(socketchanne101.socket().getOutputStream());
                            ComplicatedObject exit = new ComplicatedObject("exit");
                            outputStream101.writeObject(exit);
                            outputStream101.close();
                            socketchanne101.close();
                            break;
                        case "script":
                            history.add(commandName);
                            ComplicatedObject executeScript = new ComplicatedObject("script");
                            SocketChannel socketchanne100 = createChannel(); // 2 new lines 2 try
                            ObjectOutputStream outputStream100 = new ObjectOutputStream(socketchanne100.socket().getOutputStream());
                            ReceiveDataFromServer receiveDataFromServer100 = new ReceiveDataFromServer();
                            outputStream100.writeObject(executeScript);
                            receiveDataFromServer100.receive();
                            outputStream100.close();
                            socketchanne100.close();
                            break;

                        default:
                            System.out.println("Такой команды нет, повторите ввод. Чтобы узнать список доступных команд введите help.");
                    }
                }
            } catch (IllegalStateException e) {
                System.out.println("Программа завершена");
            } catch (NoSuchElementException e) {
                e.getMessage();
            } catch (SocketException e) {
                System.out.println("Проблемы с сервером");
                System.exit(0);
            } catch (IOException e) {
                System.out.println("Удаленный хост принудительно разорвал существующее подключение. Клиенту необходимо повторить подключение.");
                e.printStackTrace();
            } catch (AllException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
