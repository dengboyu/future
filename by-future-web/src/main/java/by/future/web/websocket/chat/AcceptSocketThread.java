package by.future.web.websocket.chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 * 接收客户端请求socket线程
 *
 * @author by@Deng
 * @create 2019-09-22 13:22
 */
public class AcceptSocketThread extends Thread{

    private ServerSocket serverSocket;
    private List<BufferedReader> brList;
    private List<PrintWriter> pwList;
    private LinkedList<String> msgList;

    public AcceptSocketThread(ServerSocket serverSocket, List<BufferedReader> brList, List<PrintWriter> pwList, LinkedList<String> msgList) {
        this.serverSocket = serverSocket;
        this.brList = brList;
        this.pwList = pwList;
        this.msgList = msgList;
    }

    @Override
    public void run() {

        try {

            while (this.isAlive()){
                //接收客户端的socket
                Socket socket = serverSocket.accept();

                //建立与客户端的通信管道
                if(socket !=null){

                    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    //添加到输入流列表
                    brList.add(br);

                    //开启一个线程接收该客户端的聊天信息
                    new GetMsgFromClientThread(br,msgList).start();

                    //添加到输出流列表
                    pwList.add(new PrintWriter(socket.getOutputStream()));

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
}
