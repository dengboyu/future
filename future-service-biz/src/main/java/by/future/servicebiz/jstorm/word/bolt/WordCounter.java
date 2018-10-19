package by.future.servicebiz.jstorm.word.bolt;


import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Tuple;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author：by@Deng
 * @Date：2018/10/18 14:35
 */
public class WordCounter implements IRichBolt {

    Integer id;
    String name;
    Map<String,Integer> counters;
    private OutputCollector outputCollector;


    @Override
    public void execute(Tuple tuple) {

        String str = tuple.getString(0);
        if (!counters.containsKey(str)) {
            counters.put(str,1);
        }else{
            Integer c = counters.get(str) +1;
            counters.put(str,c);
        }

        //确认成功处理一个tuple
        outputCollector.ack(tuple);
    }



    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {

        counters = new HashMap<>();
        this.outputCollector = outputCollector;
        name = topologyContext.getThisComponentId();
        id = topologyContext.getThisTaskId();

    }


    /**
     * Topology执行完毕的清理工作，比如关闭连接、释放资源等操作都会写在这里
     * 因为这只是个Demo，我们用它来打印我们的计数器
     **/
    @Override
    public void cleanup() {

        System.out.println("-- word counter [" + name + "-" + id + "] --");

        for (Map.Entry<String, Integer> entry : counters.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        counters.clear();

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
