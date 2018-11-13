package by.future.servicebiz.es;


import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;

/**
 * @Author：by@Deng
 * @Date：2018/10/25 14:02
 */
public class ElasticsearchTest {

    @Bean
    public TransportClient client(){

        try {
//            TransportAddress transportAddress = new TransportAddress(InetAddresses.toAddrString());

            TransportClient client = new PreBuiltTransportClient(Settings.EMPTY);
//            client.addTransportAddress()


            client.prepareDelete();


        } catch (Exception e) {
            e.printStackTrace();
        }



        return null;
    }



}
