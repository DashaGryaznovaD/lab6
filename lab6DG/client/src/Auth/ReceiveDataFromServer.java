package Auth;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.locks.ReentrantLock;

public class ReceiveDataFromServer{
    public static String data;
    public static ReentrantLock locker = new ReentrantLock();
    private DatagramSocket ds;
    public ReceiveDataFromServer(){}
    public void receive() {
        try {
            locker.lock();
            ds  = new DatagramSocket(3030);
            byte[] buf = new byte[65535];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            ds.receive(packet);
            ds.close();
            data = new String(buf, StandardCharsets.UTF_8);
            if (!data.contains("00010010")) {
                System.out.println(data);
            }
            else if (data.contains("00010010")) {
                System.out.println("Пользователь с таким логином уже существует или пароль был введён неправильно. Повторите авторизацию."
                        + "Введите логин: ");
                Authorization a = new Authorization();
                a.authorize();
            }
        }catch (IOException | InterruptedException e){
            //  e.printStackTrace();
        }finally {
            locker.unlock();
        }
    }

}