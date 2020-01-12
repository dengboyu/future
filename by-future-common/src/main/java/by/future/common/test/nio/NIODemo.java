package by.future.common.test.nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO demo
 *
 * @Author：by@Deng
 * @Date：2020/1/4 13:47
 */
public class NIODemo {


    /**
     * 以下code将Sever端的监听连接请求的事件和处理请求的事件放在一个线程中，但是在真实应用中，通常会把他们放到两个线程中，一个线程以阻塞方式负责监听
     * 客户端的连接请求，另一个线程负责业务处理请求，负责业务处理的线程才会真正用NIO的方式，像Tomcat、Jetty都是使用这种方式
     *
     * Tomcat:connector组件负责接收请求，container组件负责处理请求，connector将request/response对象传递给container处理
     *
     * 
     * @Author：by@Deng
     * @Date：2020/1/4 14:45
     */
    public void selector() throws IOException {

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        Selector selector = Selector.open();

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);   //非阻塞模式
        ssc.socket().bind(new InetSocketAddress(8080));
        ssc.register(selector, SelectionKey.OP_ACCEPT); //注册监听事件

        Set<SelectionKey> selectedKeys = selector.selectedKeys();

        Iterator it = selectedKeys.iterator();
        while (it.hasNext()){
            SelectionKey selectionKey = (SelectionKey) it.next();
            if((selectionKey.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT){
                ServerSocketChannel ssChannel = (ServerSocketChannel) selectionKey.channel();

                SocketChannel socketChannel = ssChannel.accept();   //接受到服务端的请求
                socketChannel.configureBlocking(false);
                socketChannel.register(selector,SelectionKey.OP_READ);

            }else if((selectionKey.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ){
                SocketChannel socketChannel  = (SocketChannel) selectionKey.channel();
                while(true){
                    buffer.clear();

                    int n = socketChannel.read(buffer); //读取数据
                    if(n<=0){
                        break;
                    }
                    buffer.flip();
                }
            }

            it.remove();
        }

    }



}
