package Auth;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Random;
import java.util.Scanner;
public class Authorization implements Serializable {
    public String login;
    public String password;
    Scanner scanner;
    private boolean isConnected = false;
    public Authorization(){}
    public Authorization(String login,String password){
        this.login = login;
        this.password = password;
    }
    public void checkLogin(){
        if ((login == null) ||(login.trim().length()==0)){
            System.out.println("Логин не может быть пустым. Повторите ввод: ");
            scanLogin();
        }
    }
    public void scanLogin(){
        scanner = new Scanner(System.in);
        login = scanner.nextLine();
        checkLogin();
    }
    public void authorize() throws IOException, InterruptedException {
        scanLogin();
        System.out.println("Если Вы уже авторизованы, введите пароль, а если нет - можете придумать его."+
                "Чтобы пароль сгенерировался автоматически нажмите enter: ");
        password = scanner.nextLine();
        if ((password == null) ||(password.trim().length()==0)){
            password = Password();
            System.out.println("Ваш пароль: " + password);
        }
        Authorization user = new Authorization(login,password);
        while (!isConnected) {
            isConnected = true;
            SocketChannel socketChannel = createChannel();
            ObjectOutputStream outputStream = new ObjectOutputStream(socketChannel.socket().getOutputStream());
            outputStream.writeObject(user);
            socketChannel.close();
            outputStream.close();
        }
        isConnected = false;
    }
    private String Password() {
        Random random = new Random();
        String alphabet = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890";
        char[] resultArr = new char[8];
        for (int i = 0; i < resultArr.length; i++)
            resultArr[i] = alphabet.charAt(random.nextInt(alphabet.length()));
        return new String(resultArr);
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
    public  String getLogin(){
        return login;
    }
    public String getPassword(){
        return password;
    }

}