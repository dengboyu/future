package by.future.common.test.io;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author：by@Deng
 * @Date：2020/1/11 17:59
 */
public class ServerConnection {

    public static void main(String[] args)  throws IOException {

        ServerSocket serverSocket = new ServerSocket(8080);

        Socket socket = serverSocket.accept();



    }






}
