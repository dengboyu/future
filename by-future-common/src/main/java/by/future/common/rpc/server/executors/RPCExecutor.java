package by.future.common.rpc.server.executors;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 监听client连接，将其封装成task由线程执行
 *
 * @author by@Deng
 * @create 2020-03-21 11:26
 */
public class RPCExecutor {

    static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static void exporter(String hostName, int port) throws IOException {

        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(hostName,port));

        try {

            Socket socket = serverSocket.accept();

            executorService.execute(new ServerExecutorTask(socket));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            serverSocket.close();
        }
    }


}
