package by.future.web.websocket.tomcat8.demo;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author by@Deng
 * @create 2019-09-22 00:57
 */
public class StudyServerSocket extends Thread {

    private ServerSocket serverSocket;
    private int num=0;

    public StudyServerSocket() {

        try {

            serverSocket = new ServerSocket(28888);

        } catch (IOException e) {
            e.printStackTrace();
        }

        this.start();
        System.out.println("服务器启动...");

    }


    @Override
    public void run() {

        while (this.isAlive()){
            try {
                //接收client端的socket
                Socket socket = serverSocket.accept();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String line = bufferedReader.readLine();
                System.out.println("客户端发送消息:"+line);

                PrintStream printStream = new PrintStream(socket.getOutputStream());
                printStream.println("您是第"+(++num)+"个访问服务器的用户");
                printStream.flush();

                bufferedReader.close();
                printStream.close();
                socket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new StudyServerSocket();
    }
}
