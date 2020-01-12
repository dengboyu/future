package by.future.common.test.io;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @Author：by@Deng
 * @Date：2020/1/11 17:50
 */
public class ClientConnection {


    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost",8080);

        //创建读取服务端返回流
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        //创建想服务器写入流PrintWriter
        PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);

        //向服务器发送字符串信息，此处即使写失败也不会抛出异常信息，并且一直会阻塞到写入操作系统或网络IO出现异常为止
        pw.println("hello");

        //阻塞读取服务端返回信息，
        br.readLine();

        socket.close();

    }




}
