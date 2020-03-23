package by.future.common.rpc.client;

import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;

/**
 * @author by@Deng
 * @create 2020-03-21 11:41
 */
public class RPCImporter<T> {


    public T importer(final Class<?> serviceClass, final InetSocketAddress addr){

        return (T) Proxy.newProxyInstance(serviceClass.getClassLoader(),new Class<?>[]{serviceClass.getInterfaces()[0]},new HandleImporter(serviceClass,addr));
    }


}
