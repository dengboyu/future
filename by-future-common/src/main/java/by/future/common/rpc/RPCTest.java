package by.future.common.rpc;

import by.future.common.rpc.client.RPCImporter;
import by.future.common.rpc.server.executors.RPCExecutor;
import by.future.common.rpc.server.impl.EchoServiceImpl;
import by.future.common.rpc.server.interfaces.IEchoService;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @author by@Deng
 * @create 2020-03-21 12:14
 */
public class RPCTest {

    public static void main(String[] args) throws IOException {


        new Thread(()->{

            try {
                RPCExecutor.exporter("localhost",8080);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();


        RPCImporter<IEchoService> rpcImporter = new RPCImporter<>();
        IEchoService echoService =rpcImporter.importer(EchoServiceImpl.class,new InetSocketAddress("localhost",8080));

        System.out.println(echoService.echo("嗯嗯"));


    }


}
