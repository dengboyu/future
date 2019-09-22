package by.future.web.websocket.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 服务器端socket
 *
 * @author by@Deng
 * @create 2019-09-22 13:14
 */
public class ChatServer {

    //服务器端socket
    private ServerSocket serverSocket;

    //输入流列表
    List<BufferedReader> brList = new ArrayList<>();

    //输出流列表
    List<PrintWriter> pwList = new ArrayList<>();

    //聊天信息链表
    private LinkedList<String> msgList = new LinkedList<>();

    public ChatServer() {

        try {
            serverSocket = new ServerSocket(28888);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //开启接收client的socket线程
        new AcceptSocketThread(serverSocket,brList,pwList,msgList).start();

        //开启给client发送消息线程
        new SendMsgToClientThread(pwList,msgList).start();

        System.out.println("服务端已启动...");
    }

    public static void main(String[] args) {
        new ChatServer();
    }

}
