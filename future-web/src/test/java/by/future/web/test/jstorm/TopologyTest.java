package by.future.web.test.jstorm;


import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import by.future.servicebiz.jstorm.word.bolt.WordCounter;
import by.future.servicebiz.jstorm.word.bolt.WordNormalizer;
import by.future.servicebiz.jstorm.word.spout.WordReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author：by@Deng
 * @Date：2018/10/19 9:38
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TopologyTest {

    @Test
    public void testJstorm() throws Exception{

        //创建topology生成器
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("word-reader",new WordReader());
        builder.setBolt("word-normalizer",new WordNormalizer()).shuffleGrouping("word-reader");
        builder.setBolt("word-counter",new WordCounter(),2).fieldsGrouping("word-normalizer",new Fields("word"));

        //topology自定义配置
        Config config = new Config();
        config.put("wordsFile","d:\\text.txt");
        config.setDebug(false);

        //配置每个bolt/spout的并发度都为1
        config.put(Config.TOPOLOGY_MAX_SPOUT_PENDING,1);

        //创建一个本地模式cluster
        LocalCluster cluster = new LocalCluster();

        //提交topology
        cluster.submitTopology("Getting-Started-Toplogie",config,builder.createTopology());

        //等待1min,1min会停止topolpgy和集群，视调式情况可增大该值
        Thread.sleep(1000 * 60);

        //kill topology
        cluster.killTopology("Getting-Started-Toplogie");

        cluster.shutdown();
    }


}
