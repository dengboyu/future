package by.future.web.websocket.tomcat8.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * @author by@Deng
 * @create 2019-09-22 00:56
 */
public class StudySocket {

    public static void main(String[] args) {

        try {

            Socket socket = new Socket("127.0.0.1",28888);

            PrintStream printStream = new PrintStream(socket.getOutputStream());

            //往服务器端发送信息
            printStream.println("我在练习client的socket");

            printStream.flush();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //读取服务端返回的信息
            String line = bufferedReader.readLine();

            System.out.println("来自服务端返回信息："+line);

            bufferedReader.close();
            printStream.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
